package com.rumaruka.lc.misc;


import com.rumaruka.lc.LightningCraft;
import com.rumaruka.lc.api.lightning_energy_api.ILEStorage;
import com.rumaruka.lc.common.config.LCConfig;
import com.rumaruka.lc.common.events.LightningCheckEvent;
import com.rumaruka.lc.common.items.tools.electro.IElectro;
import com.rumaruka.lc.init.LCDataComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.ServiceLoader;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class LCUtils {
    private static final String TAG_LE = "le_energy";


    public static String getTag() {
        return TAG_LE;
    }

    public static void lightning(BlockPos pos, Level level, Player pPlayer, boolean imune) {

        LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(level);
        if (bolt != null) {
            bolt.setPos(pos.getX(), pos.getY(), pos.getZ());
            level.addFreshEntity(bolt);

            pPlayer.setInvulnerable(imune);
            if (!bolt.isAlive()) {
                LightningCheckEvent.isStuck = false;
            }
        }

    }

    public static void damageStack(int damage, @Nonnull ItemStack stack, LivingEntity user, EquipmentSlot slot) {
        if (!stack.isEmpty()) {
            stack.hurtAndBreak(damage, user, slot);


        }
    }

    public static void tooltipLE(List<Component> pTooltipComponents, int le, int leMax) {

        pTooltipComponents.add(Component.literal(le + "/" + leMax).append(" " + printLighting()));
    }


    public static String printLighting() {
        return "⚡";
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(LightningCraft.MODID, path);
    }


    public static boolean isSecondGone(Level level, long second) {

        return level.nextSubTickCount() % (20 * second) == 0;
    }

    public static int getMaxEnergyTools() {
        //TODO:fix to production
        // return 1_000_000;
         return LCConfig.CONFIG.capabiltyTools.get();
    }
    public static void leEnergyDontMore(ItemStack stack){
        Integer i = stack.get(LCDataComponent.LE_ENERGY.get());
        Integer j = stack.get(LCDataComponent.LE_ENERGY_MAX.get());
        if (stack.getItem() instanceof IElectro electro){
            if (i != null&&j!=null) {
                if (i>=j){
                    electro.setLE(stack, LCUtils.getMaxEnergyTools());
                }
            }
        }

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

    public static <T> T findService(Class<T> clazz, @Nullable Supplier<T> defaultImpl) {
        var providers = ServiceLoader.load(clazz).stream().toList();
        if (providers.isEmpty() && defaultImpl != null) {
            return defaultImpl.get();
        } else if (providers.size() != 1) {
            var names = providers.stream().map(p -> p.type().getName()).collect(Collectors.joining(",", "[", "]"));
            var msg = "There should be exactly one implementation of %s on the classpath. Found: %s"
                    .formatted(clazz.getName(), names);
            throw new IllegalStateException(msg);
        } else {
            var provider = providers.get(0);
            LightningCraft.logger.debug("Instantiating {} for service {}", provider.type().getName(), clazz.getName());
            return provider.get();
        }
    }
}
