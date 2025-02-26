package com.rumaruka.lc.misc;


import com.rumaruka.lc.LightningCraft;
import com.rumaruka.lc.api.lightning_energy_api.ILEStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;

import javax.annotation.Nonnull;
import java.util.List;

public class LCUtils {
    private static final String TAG_LE = "le_energy";


    public static String getTag() {
        return TAG_LE;
    }

    public static void lightning(BlockPos pos, Level level) {

        LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(level);
        if (bolt != null) {
            bolt.setPos(pos.getCenter());
            level.addFreshEntity(bolt);



        }

    }

    public static void damageStack(int damage, @Nonnull ItemStack stack, LivingEntity user, EquipmentSlot slot) {
        if (!stack.isEmpty()) {
            stack.hurtAndBreak(damage, user, slot);


        }
    }

    public static void tooltipLE(List<Component> pTooltipComponents, ILEStorage le) {

        pTooltipComponents.add(Component.literal(le.getLE() + "/" + le.getMaxLE()).append(" " + printLighting()));
    }


    public static String printLighting() {
        return "⚡";
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(LightningCraft.MODID, path);
    }

    
    public static boolean isSecondGone(Level level, long second){

        return  level.nextSubTickCount() % (20 * second) == 0;
    }

    public static int getMaxEnergyTools() {
        //TODO:fix to production
        return 1_000_000;
        // return LCConfig.CAPACITRY_ELECTRO_TOOLS.get();
    }

    public static int getMaxInfuserLE() {
        //TODO:fix to production
        return 1_000_000;
        // return LCConfig.CAPACITRY_INFUSER.get();
    }
    public static int getMaxMachineLE() {
        //TODO:fix to production
        return 1_000_000;
        // return LCConfig.CAPACITRY_INFUSER.get();
    }
    public static Vec2 rotatePointAbout(Vec2 in, Vec2 about, double degrees) {
        double rad = degrees * Math.PI / 180.0;
        double newX = Math.cos(rad) * (in.x - about.x) - Math.sin(rad) * (in.y - about.y) + about.x;
        double newY = Math.sin(rad) * (in.x - about.x) + Math.cos(rad) * (in.y - about.y) + about.y;
        return new Vec2((float) newX, (float) newY);
    }
}
