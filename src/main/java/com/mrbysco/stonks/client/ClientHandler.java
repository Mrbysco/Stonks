package com.mrbysco.stonks.client;

import com.mrbysco.stonks.Stonks;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class ClientHandler {
	public static final ModelLayerLocation STONKS_VILLAGER = new ModelLayerLocation(new ResourceLocation(Stonks.MOD_ID, "villager"), "main");

	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(STONKS_VILLAGER, () -> LayerDefinition.create(createStonksMesh(), 64, 64));
	}

	private static MeshDefinition createStonksMesh() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		float f = 0.5F;
		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F,
						new CubeDeformation(0.0F)), PartPose.ZERO);
		PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create()
				.texOffs(32, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F,
						new CubeDeformation(f)), PartPose.offset(0.0F, 0.1F, 0.0F));
		hat.addOrReplaceChild("hat_rim", CubeListBuilder.create()
				.texOffs(30, 47).addBox(-8.0F, -9.0F, -7.0F, 16.0F, 16.0F, 1.0F,
						new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, -6.0F, -4.0F, 3.1416F, 0.0F, 0.0F));

		head.addOrReplaceChild("nose", CubeListBuilder.create()
				.texOffs(24, 0).addBox(-1.0F, 1.0F, -12.0F, 2.0F, 4.0F, 2.0F,
						new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
				.texOffs(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F), PartPose.ZERO);
		body.addOrReplaceChild("jacket", CubeListBuilder.create()
				.texOffs(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 18.0F, 6.0F,
						new CubeDeformation(0.5F)), PartPose.ZERO);
		partdefinition.addOrReplaceChild("arms", CubeListBuilder.create()
						.texOffs(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F)
						.texOffs(44, 22).addBox(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, true)
						.texOffs(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F),
				PartPose.offsetAndRotation(0.0F, 3.0F, -1.0F, -0.75F, 0.0F, 0.0F));
		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create()
						.texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F),
				PartPose.offset(-2.0F, 12.0F, 0.0F));
		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create()
						.texOffs(0, 22).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F),
				PartPose.offset(2.0F, 12.0F, 0.0F));

		return meshdefinition;
	}
}
