package com.vivids.mod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;

/**
 * "Vivito" - variante bebe de los Vivids.
 * ESTADO: FUNCIONAL - registrado en ModEntities, nace del breeding de
 * dos VividEntity adultos (ver VividEntity#getBreedOffspring), tamano
 * reducido real (0.5x) via getScale().
 */
public class VividitoEntity extends VividEntity {

    public VividitoEntity(EntityType<? extends Animal> type, Level level) {
        super(type, level);
        this.setAge(-24000); // nace como bebe
    }

    @Override
    public float getScale() {
        return 0.5F;
    }
}
