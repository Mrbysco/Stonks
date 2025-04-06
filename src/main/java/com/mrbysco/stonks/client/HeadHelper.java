package com.mrbysco.stonks.client;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.VillagerRenderState;

public class HeadHelper {
	public static void handleHead(VillagerRenderState renderState, ModelPart head, ModelPart hat, ModelPart hatRim) {
		head.setRotation(0, 0, 0);
		head.y = renderState.getRenderDataOrDefault(ClientHandler.HEAD_HEIGHT, 9.4F);

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
