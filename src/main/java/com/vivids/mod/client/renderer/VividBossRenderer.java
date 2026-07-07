package com.vivids.mod.client.renderer;

import com.vivids.mod.client.model.VividBossModel;
import com.vivids.mod.entity.boss.VividBossEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

/**
 * Renderer generico para los 4 jefes.
 * ESTADO: FUNCIONAL - usa VividBossModel (mismo patron de piernas
 * ancladas al suelo, escalado mas grande) con una textura propia por jefe.
 */
public class VividBossRenderer extends MobRenderer<VividBossEntity, VividBossModel> {

    private final ResourceLocation texture;

    public VividBossRenderer(EntityRendererProvider.Context context, String textureName) {
        super(context, new VividBossModel(context.bakeLayer(VividBossModel.LAYER_LOCATION)), 0.9F);
        this.texture = new ResourceLocation("vivids", "textures/entity/" + textureName + ".png");
    }

    @Override
    public ResourceLocation getTextureLocation(VividBossEntity entity) {
        return texture;
    }
}
