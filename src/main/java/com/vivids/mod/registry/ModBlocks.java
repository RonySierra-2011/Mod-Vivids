package com.vivids.mod.registry;

import com.vivids.mod.VividsMod;
import com.vivids.mod.block.BathroomPortalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Registro de bloques.
 * ESTADO: FUNCIONAL - solo el marco/bloque de portal por ahora. Los
 * bloques decorativos de la Dimension del Bano (azulejos, tuberias,
 * espejos) NO estan aqui: esa dimension usa bloques VANILLA reutilizados
 * (quartz_block, prismarine, white_terracotta, water) para poder generar
 * terreno real sin necesitar texturas/modelos de bloque nuevos.
 */
public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, VividsMod.MOD_ID);

    public static final RegistryObject<Block> BATHROOM_PORTAL_BLOCK = BLOCKS.register("bathroom_portal_block",
            () -> new BathroomPortalBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_BLUE)
                    .sound(SoundType.GLASS)
                    .noCollission()
                    .strength(-1.0F, 3600000.0F)
                    .noLootTable()));
}
