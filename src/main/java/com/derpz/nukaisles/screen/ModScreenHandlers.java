package com.derpz.nukaisles.screen;

import com.derpz.nukaisles.NukaIsles;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<NukaColaMachineScreenHandler> NUKA_COLA_MACHINE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(NukaIsles.MOD_ID, "nuka_cooling"),
                    new ExtendedScreenHandlerType<>(NukaColaMachineScreenHandler::new));

    public static void registerScreenHandlers() {
        NukaIsles.LOGGER.info("Registering Screen Handlers for " + NukaIsles.MOD_ID);
    }
}
