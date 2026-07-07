package com.vivids.mod;

import com.vivids.mod.registry.ModCreativeTab;
import com.vivids.mod.registry.ModEntities;
import com.vivids.mod.registry.ModItems;
import com.vivids.mod.registry.ModMenus;
import com.vivids.mod.registry.ModSounds;
import com.vivids.mod.registry.ModBlocks;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegisterEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Clase principal del mod Vivids.
 *
 * ESTADO: FUNCIONAL. Registra items, entidades, sonidos y la pestaña creativa.
 * Los sistemas de dimension y jefes se registran desde sus propias clases
 * (ver paquete world y dimension) y estan marcados como ESQUELETO en su
 * documentacion interna.
 */
@Mod("vivids")
public class VividsMod {

    public static final String MOD_ID = "vivids";
    public static final Logger LOGGER = LogManager.getLogger();

    public VividsMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModSounds.SOUND_EVENTS.register(modEventBus);
        ModCreativeTab.CREATIVE_MODE_TABS.register(modEventBus);
        ModMenus.MENU_TYPES.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::onClientSetup);
        modEventBus.addListener(this::registerAttributes);

        net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(this);

        LOGGER.info("Vivids mod inicializado.");
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Vivids: common setup completo.");
    }

    private void onClientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            net.minecraft.client.gui.screens.MenuScreens.register(
                    ModMenus.VIVID_BACKPACK_MENU.get(),
                    com.vivids.mod.client.screen.VividBackpackScreen::new);
        });
        LOGGER.info("Vivids: client setup completo.");
    }

    private void registerAttributes(final EntityAttributeCreationEvent event) {
        event.put(ModEntities.VIVID.get(), com.vivids.mod.entity.VividEntity.createAttributes().build());
        event.put(ModEntities.VIVID_BATH.get(), com.vivids.mod.entity.VividBathEntity.createAttributes().build());
        event.put(ModEntities.VIVITO.get(), com.vivids.mod.entity.VividEntity.createAttributes().build());
        event.put(ModEntities.REY_VIVID.get(), com.vivids.mod.entity.boss.VividBossEntity.createBossAttributes().build());
        event.put(ModEntities.GUARDIAN_VIVID.get(), com.vivids.mod.entity.boss.VividBossEntity.createBossAttributes().build());
        event.put(ModEntities.DUQUE_JABONOSO.get(), com.vivids.mod.entity.boss.VividBossEntity.createBossAttributes().build());
        event.put(ModEntities.LIMPIADOR_SUPREMO.get(), com.vivids.mod.entity.boss.VividBossEntity.createBossAttributes().build());
    }
}
