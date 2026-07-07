package com.vivids.mod.client.model;

import com.vivids.mod.VividsMod;
import com.vivids.mod.entity.boss.VividBossEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

/**
 * Modelo de los 4 jefes. Mismo patron que VividModel (piernas ancladas
 * al suelo, sin flotar) pero mas grande, para que se sientan imponentes.
 * ESTADO: FUNCIONAL.
 */
public class VividBossModel extends HierarchicalModel<VividBossEntity> {

    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(new ResourceLocation(VividsMod.MOD_ID, "vivid_boss"), "main");

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart legLeft;
    private final ModelPart legRight;

    public VividBossModel(ModelPart root) {
        this.root = root;
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.legLeft = root.getChild("leg_left");
        this.legRight = root.getChild("leg_right");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition parts = mesh.getRoot();

        parts.addOrReplaceChild("leg_left",
                CubeListBuilder.create().texOffs(0, 32).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 14.0F, 6.0F),
                PartPose.offset(-3.5F, 28.0F, 0.0F));
        parts.addOrReplaceChild("leg_right",
                CubeListBuilder.create().texOffs(24, 32).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 14.0F, 6.0F),
                PartPose.offset(3.5F, 28.0F, 0.0F));

        parts.addOrReplaceChild("body",
                CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -16.0F, -4.0F, 12.0F, 16.0F, 8.0F),
                PartPose.offset(0.0F, 28.0F, 0.0F));

        parts.addOrReplaceChild("head",
                CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F),
                PartPose.offset(0.0F, 8.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public ModelPart root() { return root; }

    @Override
    public void setupAnim(VividBossEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
        this.head.xRot = headPitch * ((float) Math.PI / 180F);
        this.legLeft.xRot = net.minecraft.util.Mth.cos(limbSwing * 0.6662F) * 1.2F * limbSwingAmount;
        this.legRight.xRot = net.minecraft.util.Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.2F * limbSwingAmount;
    }
}
