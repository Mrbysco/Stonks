package com.mrbysco.stonks.mixin;

import com.mrbysco.stonks.client.ClientHandler;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.VillagerRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(VillagerRenderer.class)
public class VillagerRendererMixin {

	@ModifyArg(method = "<init>(Lnet/minecraft/client/renderer/entity/EntityRendererProvider$Context;)V",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/renderer/entity/EntityRendererProvider$Context;bakeLayer(Lnet/minecraft/client/model/geom/ModelLayerLocation;)Lnet/minecraft/client/model/geom/ModelPart;"),
			index = 0)
	private static ModelLayerLocation stonks_init(ModelLayerLocation location) {
		return ClientHandler.STONKS_VILLAGER;
	}
}
