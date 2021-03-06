package com.mrbysco.stonks;

import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.IExtensionPoint.DisplayTest;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

@Mod("stonks")
public class Stonks {
    public Stonks() {
        //Make sure the mod being absent on the other network side does not cause the client to display the server as incompatible
        ModLoadingContext.get().registerExtensionPoint(DisplayTest.class,()->
                new IExtensionPoint.DisplayTest(() -> "Trans Rights Are Human Rights",
                        (remoteVersionString,networkBool) -> networkBool));
    }
}
