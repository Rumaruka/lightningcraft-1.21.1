package com.rumaruka.lc;


import com.rumaruka.lc.common.config.LCConfig;
import com.rumaruka.lc.init.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.rumaruka.lc.LightningCraft.MODID;


@Mod(MODID)
public class LightningCraft {
    public static final String MODID = "lc";
    public static final Logger logger = LogManager.getLogger();

    public LightningCraft(IEventBus bus) {
        logger.info("Setup LightingCraft features");
        ModContainer modContainer = ModLoadingContext.get().getActiveContainer();
        modContainer.registerConfig(ModConfig.Type.COMMON, LCConfig.CONFIG_SPEC);
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);

        LCAttachmentTypes.setup(bus);
        LCDataComponent.setup(bus);


        LCBlocks.setup(bus);
        LCTiles.setup(bus);
        LCRecipes.setup(bus);
        LCItems.setup(bus);
        LCMenu.setup(bus);
        LCCreativeTabs.setup(bus);
        //LCHandler.registerHandler();

        // bus.addListener(ClientSetup::registerScreens);

    }


    //bus.addListener(DataComponentsEvent::modifyComponents);

}
