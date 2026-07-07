package com.vivids.mod.entity;

import com.vivids.mod.registry.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * Entidad "Vivid del Bano".
 * ESTADO: FUNCIONAL A NIVEL BASE (hereda toda la IA de caminar/domesticar
 * de VividEntity), con la diferencia de que se domestica con Jabon
 * Espumoso en vez de Comida Vivid.
 *
 * PENDIENTE (ESQUELETO): logica de spawn exclusivo dentro de la Dimension
 * del Bano y comportamientos unicos (limpiar moho, interactuar con
 * lavamanos/banos). Ver paquete world/dimension para el estado de esa
 * dimension.
 */
public class VividBathEntity extends VividEntity {

    public VividBathEntity(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }

    @Override
    protected net.minecraft.world.item.Item getTameFoodItem() {
        return ModItems.SOAPY_FOAM.get();
    }
}
