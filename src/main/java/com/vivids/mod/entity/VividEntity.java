package com.vivids.mod.entity;

import com.vivids.mod.registry.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;

import javax.annotation.Nullable;

/**
 * Entidad "Vivid Normal".
 * ESTADO: FUNCIONAL.
 *
 * - Camina y navega el mundo con pathfinding real (como un aldeano/lobo).
 * - No flota: usa el pathfinder estandar de Animal, que mantiene a la
 *   entidad apoyada en el suelo solido en todo momento.
 * - Se domestica con Comida Vivid.
 * - Una vez domesticado: sigue al jugador, se puede sentar, y evita a
 *   los Creepers (huida real via AvoidEntityGoal).
 * - Sirve de base para VividBathEntity y para el futuro VividitoEntity
 *   (variante bebe, ver TODO en getBreedOffspring).
 */
public class VividEntity extends Animal {

    private static final EntityDataAccessor<Boolean> SITTING =
            SynchedEntityData.defineId(VividEntity.class, EntityDataSerializers.BOOLEAN);

    public VividEntity(EntityType<? extends Animal> type, Level level) {
        super(type, level);
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, -1.0F);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 14.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.28D)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.SAFE_FALL_DISTANCE, 5.0D);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SITTING, false);
    }

    @Override
    protected void registerGoals() {
        // Prioridad 0: huir de amenazas reales (Creepers)
        this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, Creeper.class, 8.0F, 1.6D, 1.6D));
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new PanicGoal(this, 1.6D));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.15D, itemStack -> isTameFood(itemStack), false));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    /** Comida usada para domesticar/tentar a esta variante. Sobreescribible por subclases. */
    protected boolean isTameFood(ItemStack stack) {
        return stack.is(ModItems.VIVID_FOOD.get());
    }

    public boolean isSitting() {
        return this.entityData.get(SITTING);
    }

    public void setSitting(boolean sitting) {
        this.entityData.set(SITTING, sitting);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!this.level().isClientSide) {
            if (isTameFood(stack) && this.isTame_() == false) {
                if (!player.getAbilities().instabuild) stack.shrink(1);
                this.tameByPlayer(player);
                return InteractionResult.SUCCESS;
            }
            if (this.isOwnedBy(player) && stack.isEmpty()) {
                this.setSitting(!this.isSitting());
                return InteractionResult.SUCCESS;
            }
        }
        return super.mobInteract(player, hand);
    }

    // --- Sistema simple de "tameo" propio (independiente de TamableAnimal para
    // permitir logica distinta entre Vivid Normal y Vivid del Bano) ---
    private boolean tamed = false;
    @Nullable private java.util.UUID ownerUUID;

    public boolean isTame_() {
        return tamed;
    }

    public void tameByPlayer(Player player) {
        this.tamed = true;
        this.ownerUUID = player.getUUID();
        this.level().broadcastEntityEvent(this, (byte) 7); // particulas de corazon vanilla
    }

    public boolean isOwnedBy(Player player) {
        return tamed && ownerUUID != null && ownerUUID.equals(player.getUUID());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("Tamed", tamed);
        if (ownerUUID != null) tag.putUUID("Owner", ownerUUID);
        tag.putBoolean("Sitting", isSitting());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.tamed = tag.getBoolean("Tamed");
        if (tag.hasUUID("Owner")) this.ownerUUID = tag.getUUID("Owner");
        this.setSitting(tag.getBoolean("Sitting"));
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(net.minecraft.server.level.ServerLevel level, AgeableMob otherParent) {
        return com.vivids.mod.registry.ModEntities.VIVITO.get().create(level);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return com.vivids.mod.registry.ModSounds.VIVID_IDLE.get();
    }

    @Override
    protected SoundEvent getHurtSound(net.minecraft.world.damagesource.DamageSource source) {
        return com.vivids.mod.registry.ModSounds.VIVID_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return com.vivids.mod.registry.ModSounds.VIVID_DEATH.get();
    }
}
