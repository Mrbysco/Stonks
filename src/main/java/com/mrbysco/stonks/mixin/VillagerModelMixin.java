package com.mrbysco.stonks.mixin;

import com.mrbysco.stonks.client.HeadHelper;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.npc.VillagerModel;
import net.minecraft.client.renderer.entity.state.VillagerRenderState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VillagerModel.class)
public abstract class VillagerModelMixin extends EntityModel<VillagerRenderState> {

	@Shadow
	@Final
	private ModelPart head;

	@Unique
	private ModelPart hat;

	@Unique
	private ModelPart hatRim;

	protected VillagerModelMixin(ModelPart root) {
		super(root);
	}

	@Inject(method = "<init>(Lnet/minecraft/client/model/geom/ModelPart;)V",
			at = @At("TAIL"))
	private void stonks$init(ModelPart root, CallbackInfo ci) {
		this.hat = this.head.getChild("hat");
		this.hatRim = hat.getChild("hat_rim");
	}

	@Inject(method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/VillagerRenderState;)V",
			at = @At("TAIL"))
	public void stonks$setupAnim(VillagerRenderState renderState, CallbackInfo ci) {
		HeadHelper.handleHead(renderState, head, hat, hatRim);
	}
}
