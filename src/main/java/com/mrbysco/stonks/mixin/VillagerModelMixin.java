package com.mrbysco.stonks.mixin;

import com.mrbysco.stonks.client.HeadHelper;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.VillagerRenderState;
import net.minecraft.world.entity.Entity;
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

	@Inject(method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/VillagerRenderState;)V",
			at = @At("TAIL"))
	public void stonks$setupAnim(VillagerRenderState renderState, CallbackInfo ci) {
		HeadHelper.handleHead(renderState, head, hat, hatRim);
	}
}
