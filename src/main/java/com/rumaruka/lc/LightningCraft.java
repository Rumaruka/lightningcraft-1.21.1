package com.rumaruka.lc;


import com.rumaruka.lc.init.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.rumaruka.lc.LightningCraft.MODID;


@Mod(MODID)
public class LightningCraft {
    public static final String MODID = "lc";
    public static final Logger logger = LogManager.getLogger();

    public LightningCraft(IEventBus bus) {
        logger.info("Setup LightingCraft features");

        LCAttachment.setup(bus);
        LCData.setup(bus);


        LCBlocks.setup(bus);
        LCTiles.setup(bus);
        LCRecipes.setup(bus);
        LCItems.setup(bus);
        LCMenu.setup(bus);
        LCCreativeTabs.setup(bus);


        //LCHandler.registerHandler();
        //bus.addListener(DataComponentsEvent::modifyComponents);
        // bus.addListener(ClientSetup::registerScreens);
        //ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, LCConfig.SPEC);


    }


}
