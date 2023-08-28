package com.mrbysco.stonks.mixin;

import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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

	@Inject(method = "setupAnim(Lnet/minecraft/world/entity/Entity;FFFFF)V",
			at = @At("TAIL"))
	public void stonks_setupAnim(T villager, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
		if (villager instanceof AbstractVillager abstractVillager) {
			head.setRotation(0, 0, 0);

			Player player = abstractVillager.level().getNearestPlayer(TargetingConditions.forNonCombat(), abstractVillager);
			if (player != null) {
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
