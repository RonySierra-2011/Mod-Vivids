package com.vivids.mod.client.model;

import com.vivids.mod.VividsMod;
import com.vivids.mod.entity.VividEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

/**
 * Modelo del Vivid.
 * ESTADO: FUNCIONAL - modelo simple tipo "criatura de aldea" con
 * cabeza, cuerpo y 2 piernas cortas. El origen de las piernas esta
 * calculado para que los pies toquen y=24 (el suelo del modelo),
 * exactamente igual que el patron que usa Villager en vanilla, para
 * evitar el efecto de "flotar" sobre el terreno.
 */
public class VividModel extends HierarchicalModel<VividEntity> {

    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(new ResourceLocation(VividsMod.MOD_ID, "vivid"), "main");

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart legLeft;
    private final ModelPart legRight;

    public VividModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.legLeft = root.getChild("leg_left");
        this.legRight = root.getChild("leg_right");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition parts = mesh.getRoot();

        // Piernas: desde el suelo (y=24) hacia arriba, altura 8 -> pies tocan y=24 exacto.
        parts.addOrReplaceChild("leg_left",
                CubeListBuilder.create().texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F),
                PartPose.offset(-2.0F, 16.0F, 0.0F));
        parts.addOrReplaceChild("leg_right",
                CubeListBuilder.create().texOffs(16, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F),
                PartPose.offset(2.0F, 16.0F, 0.0F));

        // Cuerpo apoyado justo encima de las piernas (y=16 hacia arriba).
        parts.addOrReplaceChild("body",
                CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -3.0F, 8.0F, 10.0F, 6.0F),
                PartPose.offset(0.0F, 16.0F, 0.0F));

        // Cabeza encima del cuerpo.
        parts.addOrReplaceChild("head",
                CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F),
                PartPose.offset(0.0F, 6.0F, 0.0F));

        return LayerDefinition.create(mesh, 32, 32);
    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setupAnim(VividEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.head.xRot = headPitch * ((float) Math.PI / 180F);

        if (entity.isSitting()) {
            this.legLeft.xRot = -1.4F;
            this.legRight.xRot = -1.4F;
            this.body.y = 18.0F;
        } else {
            // Animacion de caminar real: piernas se mueven segun limbSwing,
            // igual que el patron vanilla de Villager/Wolf.
            this.legLeft.xRot = net.minecraft.util.Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
            this.legRight.xRot = net.minecraft.util.Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
            this.body.y = 16.0F;
        }
    }
}
