package com.mrbysco.stonks;

import com.mrbysco.stonks.client.ClientHandler;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.IExtensionPoint;
import net.neoforged.fml.IExtensionPoint.DisplayTest;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(Stonks.MOD_ID)
public class Stonks {
	public static final String MOD_ID = "stonks";

	public Stonks(IEventBus eventBus) {
		if (FMLEnvironment.dist.isClient()) {
			eventBus.addListener(ClientHandler::registerLayerDefinitions);
		}

		//Make sure the mod being absent on the other network side does not cause the client to display the server as incompatible
		ModLoadingContext.get().registerExtensionPoint(DisplayTest.class, () ->
				new IExtensionPoint.DisplayTest(() -> "Trans Rights Are Human Rights",
						(remoteVersionString, networkBool) -> networkBool));
	}
}
