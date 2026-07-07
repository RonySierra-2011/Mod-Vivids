package com.vivids.mod.client.renderer;

import com.vivids.mod.VividsMod;
import com.vivids.mod.client.model.VividModel;
import com.vivids.mod.entity.VividEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

/**
 * Renderer del Vivid.
 * ESTADO: FUNCIONAL. Usa VividModel (piernas ancladas al suelo) y la
 * textura vivid.png (16x16 estilo vanilla, incluida como placeholder,
 * ver README para reemplazarla por tu propio arte).
 */
public class VividRenderer extends MobRenderer<VividEntity, VividModel> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(VividsMod.MOD_ID, "textures/entity/vivid.png");

    public VividRenderer(EntityRendererProvider.Context context) {
        super(context, new VividModel(context.bakeLayer(VividModel.LAYER_LOCATION)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(VividEntity entity) {
        return TEXTURE;
    }
}
