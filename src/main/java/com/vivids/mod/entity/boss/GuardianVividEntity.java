package com.vivids.mod.entity.boss;

import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;

/** GuardianVividEntity - uno de los 4 jefes de Vivids. ESTADO: FUNCIONAL (hereda toda la logica de VividBossEntity). */
public class GuardianVividEntity extends VividBossEntity {
    public GuardianVividEntity(EntityType<? extends Animal> type, Level level) {
        super(type, level, BossEvent.BossBarColor.GREEN);
    }
}
