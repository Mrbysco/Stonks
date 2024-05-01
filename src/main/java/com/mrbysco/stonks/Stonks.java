package com.mrbysco.stonks;

import com.mrbysco.stonks.client.ClientHandler;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Stonks.MOD_ID)
public class Stonks {
	public static final String MOD_ID = "stonks";

	public Stonks(IEventBus eventBus, Dist dist) {
		if (dist.isClient()) {
			eventBus.addListener(ClientHandler::registerLayerDefinitions);
		}
	}
}
