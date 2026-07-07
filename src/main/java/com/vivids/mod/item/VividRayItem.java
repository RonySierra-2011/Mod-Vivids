package com.vivids.mod.item;

import com.vivids.mod.registry.ModSounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Arma "Rayo Vivid" / "Rayo del Bano".
 * ESTADO: FUNCIONAL (mecanica de sobrecalentamiento real usando NBT del
 * ItemStack). No usa flechas: dispara un proyectil de energia propio.
 *
 * PENDIENTE (ESQUELETO): el proyectil actual reutiliza ThrownTrident como
 * base visual temporal. Para un proyectil 100% original (con su propio
 * renderer/textura de rayo) hay que crear una clase EnergyBoltEntity
 * separada, ver TODO abajo.
 */
public class VividRayItem extends Item {

    public enum RayType { OVERWORLD, BATHROOM }

    private static final int MAX_SHOTS_BEFORE_OVERHEAT = 6;
    private static final int OVERHEAT_TICKS = 20 * 20; // 20 segundos

    private final RayType type;

    public VividRayItem(Properties properties, RayType type) {
        super(properties);
        this.type = type;
    }

    public RayType getType() {
        return type;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, net.minecraft.world.entity.player.Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        CompoundTag tag = stack.getOrCreateTag();

        long overheatUntil = tag.getLong("OverheatUntil");
        if (level.getGameTime() < overheatUntil) {
            // Sobrecalentado: no dispara.
            return InteractionResultHolder.fail(stack);
        }

        int shots = tag.getInt("ShotsFired") + 1;
        tag.putInt("ShotsFired", shots);

        if (!level.isClientSide) {
            // TODO: reemplazar por un proyectil propio EnergyBoltEntity con
            // renderer de rayo electrico (overworld) o burbuja explosiva (bathroom).
            ThrownTrident bolt = new ThrownTrident(level, player, stack);
            bolt.setOwner(player);
            bolt.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.2F, 1.0F);
            level.addFreshEntity(bolt);
            level.playSound(null, player.blockPosition(), ModSounds.RAY_SHOOT.get(), SoundSource.PLAYERS, 1.0F, 1.2F);
        }

        if (shots >= MAX_SHOTS_BEFORE_OVERHEAT) {
            tag.putLong("OverheatUntil", level.getGameTime() + OVERHEAT_TICKS);
            tag.putInt("ShotsFired", 0);
            if (!level.isClientSide) {
                level.playSound(null, player.blockPosition(), ModSounds.RAY_OVERHEAT.get(), SoundSource.PLAYERS, 1.0F, 0.8F);
            }
        }

        player.getCooldowns().addCooldown(this, 6); // pequeño cooldown entre disparos individuales
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }

    /** Usado por la UI/HUD para mostrar la barra de sobrecalentamiento (ver ClientModEvents). */
    public static boolean isOverheated(ItemStack stack, long gameTime) {
        return stack.hasTag() && gameTime < stack.getTag().getLong("OverheatUntil");
    }
}
