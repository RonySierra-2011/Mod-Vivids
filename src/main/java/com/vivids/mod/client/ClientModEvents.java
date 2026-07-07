package com.vivids.mod.client;

import com.vivids.mod.client.model.VividBossModel;
import com.vivids.mod.client.model.VividModel;
import com.vivids.mod.client.renderer.VividBossRenderer;
import com.vivids.mod.client.renderer.VividRenderer;
import com.vivids.mod.registry.ModEntities;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;

/**
 * Registro de renderers de cliente.
 * ESTADO: FUNCIONAL - conecta VividModel/VividBossModel + sus renderers
 * para que ninguna entidad del mod flote sobre el suelo.
 */
@Mod.EventBusSubscriber(modid = "vivids", bus = Mod.EventBusSubscriber.Bus.MOD, value = net.minecraftforge.api.distmarker.Dist.CLIENT)
public class ClientModEvents {

    @net.minecraftforge.eventbus.api.SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.VIVID.get(), VividRenderer::new);
        event.registerEntityRenderer(ModEntities.VIVID_BATH.get(), VividRenderer::new);
        event.registerEntityRenderer(ModEntities.VIVITO.get(), VividRenderer::new);
        event.registerEntityRenderer(ModEntities.REY_VIVID.get(), ctx -> new VividBossRenderer(ctx, "rey_vivid"));
        event.registerEntityRenderer(ModEntities.GUARDIAN_VIVID.get(), ctx -> new VividBossRenderer(ctx, "guardian_vivid"));
        event.registerEntityRenderer(ModEntities.DUQUE_JABONOSO.get(), ctx -> new VividBossRenderer(ctx, "duque_jabonoso"));
        event.registerEntityRenderer(ModEntities.LIMPIADOR_SUPREMO.get(), ctx -> new VividBossRenderer(ctx, "limpiador_supremo"));
    }

    @net.minecraftforge.eventbus.api.SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(VividModel.LAYER_LOCATION, VividModel::createBodyLayer);
        event.registerLayerDefinition(VividBossModel.LAYER_LOCATION, VividBossModel::createBodyLayer);
    }

    // El registro de pantallas (MenuScreens.register) se hace en el
    // evento FMLClientSetupEvent dentro de VividsMod.java, ya que
    // MenuScreens no es seguro de llamar en el hilo de carga de mods.
}
