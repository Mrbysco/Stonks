package com.mrbysco.stonks.mixin;

import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.AbstractVillager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VillagerModel.class)
public class VillagerModelMixin<T extends Entity> {

	@Shadow
	@Final
	private ModelPart head;

	@Shadow
	@Final
	private ModelPart hatRim;

	@Shadow
	@Final
	private ModelPart hat;

	@Inject(method = "createBodyModel()Lnet/minecraft/client/model/geom/builders/MeshDefinition;",
			at = @At("HEAD"), cancellable = true)
	private static void createBodyModel(CallbackInfoReturnable<MeshDefinition> cir) {
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

		cir.setReturnValue(meshdefinition);
	}

	@Inject(method = "setupAnim(Lnet/minecraft/world/entity/Entity;FFFFF)V",
			at = @At("TAIL"))
	public void setupAnim(T villager, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
		if (villager instanceof AbstractVillager abstractVillager) {
			head.setRotation(0, 0, 0);

			boolean flag = abstractVillager.level().hasNearbyAlivePlayer(abstractVillager.getX(), abstractVillager.getY(), abstractVillager.getZ(), 5D);
			if (flag) {
				if (abstractVillager.getMainHandItem().isEmpty()) {
					if (head.y <= 9.4F) {
						head.y += 0.05F;
					}
				} else {
					if (head.y >= 0) {
						head.y -= 0.05F;
					}
				}
			}

			if (head.y < 8) {
				head.visible = true;
				hat.visible = true;
				hatRim.visible = true;
			} else {
				head.visible = false;
				hat.visible = false;
				hatRim.visible = false;
			}
		}
	}
}
