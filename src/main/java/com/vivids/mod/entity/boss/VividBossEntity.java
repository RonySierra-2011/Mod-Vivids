package com.vivids.mod.entity.boss;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/**
 * Clase base para los 4 jefes de Vivids (Rey Vivid, Guardian Vivid,
 * Duque Jabonoso, Limpiador Supremo).
 * ESTADO: FUNCIONAL - vida, barra de jefe (boss bar), IA de ataque
 * cuerpo a cuerpo real, mucho mas fuerte que un Vivid normal.
 *
 * Los 4 jefes se diferencian solo por su EntityType (ver ModEntities) y
 * su textura (ver README de texturas); comparten esta misma clase para
 * evitar duplicar codigo. Si mas adelante quieres darle a cada uno un
 * ataque especial distinto, sobreescribe registerGoals() en subclases.
 */
public class VividBossEntity extends Animal implements Enemy {

    private final net.minecraft.world.BossEvent.BossBarColor barColor;
    private net.minecraft.server.level.ServerBossEvent bossEvent;

    public VividBossEntity(EntityType<? extends Animal> type, Level level) {
        this(type, level, BossEvent.BossBarColor.PURPLE);
    }

    public VividBossEntity(EntityType<? extends Animal> type, Level level, BossEvent.BossBarColor color) {
        super(type, level);
        this.barColor = color;
        this.bossEvent = new net.minecraft.server.level.ServerBossEvent(
                this.getDisplayName(), color, BossEvent.BossBarOverlay.NOTCHED_10);
    }

    public static AttributeSupplier.Builder createBossAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 150.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE, 9.0D)
                .add(Attributes.ARMOR, 8.0D)
                .add(Attributes.FOLLOW_RANGE, 32.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2D, true));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 10.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public boolean canMate(net.minecraft.world.entity.animal.Animal otherAnimal) {
        return false;
    }

    @Override
    public net.minecraft.world.entity.AgeableMob getBreedOffspring(net.minecraft.server.level.ServerLevel level, net.minecraft.world.entity.AgeableMob other) {
        return null;
    }

    @Override
    public void startSeenByPlayer(net.minecraft.server.level.ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossEvent.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(net.minecraft.server.level.ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossEvent.removePlayer(player);
    }

    @Override
    public void customServerAiStep() {
        super.customServerAiStep();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }

    @Override
    public void die(net.minecraft.world.damagesource.DamageSource source) {
        super.die(source);
        this.bossEvent.removeAllPlayers();
    }
}
