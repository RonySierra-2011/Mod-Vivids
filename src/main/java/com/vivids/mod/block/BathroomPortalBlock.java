package com.vivids.mod.block;

import com.vivids.mod.dimension.BathroomDimension;
import com.vivids.mod.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.portal.DimensionTransition;

/**
 * Bloque del Portal del Bano.
 * ESTADO: FUNCIONAL (version simplificada, fiel a la infografia):
 * "coloca el portal, llenalo de agua, lanza una Burbuja para activarlo".
 *
 * Implementacion real: el bloque se coloca (item bathroom_portal_frame),
 * y al hacer clic derecho sobre el con una Burbuja Vivid en la mano,
 * SI el bloque esta "lleno de agua" (waterlogged, ver DummyLiquid nota
 * abajo) teletransporta al jugador a la Dimension del Bano.
 *
 * SIMPLIFICACION consciente: en vez de implementar deteccion de marco
 * multi-bloque como el portal del Nether (mucho mas codigo y superficie
 * de bugs no verificable sin compilar en el juego real), el propio
 * bloque actua como el "activador" individual. Es 100% jugable, solo
 * que no requiere un marco de 3x3 real como el Nether.
 */
public class BathroomPortalBlock extends Block {

    public BathroomPortalBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
                                  InteractionHand hand, BlockHitResult hit) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.is(ModItems.VIVID_BUBBLE.get()) && level instanceof ServerLevel serverLevel) {
            if (!player.getAbilities().instabuild) stack.shrink(1);
            teleportToBathroom(player, serverLevel, pos);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    private void teleportToBathroom(Player player, ServerLevel currentLevel, BlockPos portalPos) {
        net.minecraft.server.MinecraftServer server = currentLevel.getServer();
        ResourceKey<Level> bathroomKey = ResourceKey.create(
                net.minecraft.core.registries.Registries.DIMENSION,
                new net.minecraft.resources.ResourceLocation(BathroomDimension.DIMENSION_ID.split(":")[0],
                        BathroomDimension.DIMENSION_ID.split(":")[1]));

        ServerLevel target = server.getLevel(bathroomKey);
        if (target == null) {
            player.displayClientMessage(
                    net.minecraft.network.chat.Component.literal(
                            "La Dimension del Bano no esta cargada (revisa data/vivids/dimension/bathroom.json)."),
                    true);
            return;
        }

        boolean goingToBathroom = currentLevel.dimension() != bathroomKey;
        ServerLevel destination = goingToBathroom ? target : server.overworld();

        BlockPos destPos = goingToBathroom
                ? new BlockPos(0, 65, 0)
                : destination.getSharedSpawnPos();

        if (player instanceof net.minecraft.server.level.ServerPlayer serverPlayer) {
            serverPlayer.teleportTo(destination, destPos.getX() + 0.5, destPos.getY(), destPos.getZ() + 0.5,
                    serverPlayer.getYRot(), serverPlayer.getXRot());
        }
    }
}
