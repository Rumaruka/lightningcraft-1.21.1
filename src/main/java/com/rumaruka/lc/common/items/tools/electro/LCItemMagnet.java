package com.rumaruka.lc.common.items.tools.electro;

import com.rumaruka.lc.common.items.tools.base.ItemLEBase;
import com.rumaruka.lc.init.LCDataComponent;
import com.rumaruka.lc.misc.LCUtils;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class LCItemMagnet extends ItemLEBase {
    public   int t1Range;
    @Setter
    @Getter
    private boolean isWorked = false;

    public static final int lpPerItemPerTick = 1;
    public LCItemMagnet( int t1Range) {
        super(new Item.Properties()
                .component(LCDataComponent.LE_ENERGY.get(), 0)

                .component(LCDataComponent.LE_ENERGY_MAX.get(), t1Range*1000));

        this.t1Range = t1Range;
    }
    public  int getMaxEnergy(){
        return t1Range*1000;
    }
    private void setGlint(ItemStack stack) {
        stack.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, isWorked());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (!level.isClientSide()) {

            if (player.isShiftKeyDown()) {
                setWorked(true);
                setGlint(player.getMainHandItem());
            } else {
                setWorked(false);
                setGlint(player.getMainHandItem());


                return InteractionResultHolder.success(player.getMainHandItem());
            }


        }
        return super.use(level, player, usedHand);
    }
//    @Override
//    public InteractionResult useOn(UseOnContext context) {
//        Player player = context.getPlayer();
//        Level level = context.getLevel();
//        if (!level.isClientSide()) {
//            if (player != null) {
//                if (player.isShiftKeyDown()){
//                    setWorked(true);
//                    setGlint(player.getMainHandItem());
//                }else {
//                    setWorked(false);
//                    setGlint(player.getMainHandItem());
//                }
//
//                return InteractionResult.SUCCESS;
//            }
//
//        }
//        return super.useOn(context);
//    }

    public double getRange(ItemStack stack) {

        return stack.isEmpty() ? 0 :  t1Range ;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (!level.isClientSide()) {
            if (entity instanceof Player player) {
                double range = getRange(stack);
                AABB box = new AABB(player.getX() - range, player.getY() - range, player.getZ() - range, player.getX() + range, player.getY() + range, player.getZ() + range);

                for (Entity e : level.getEntities(entity, box)) {
                    if (e instanceof ItemEntity item) {

                        if (getCurrentLE(stack) != 0 &&isWorked()) {
                            double dx = player.getX() - item.getX();
                            double dy = player.getY() - item.getY();
                            double dz = player.getZ() - item.getZ();
                            double dm = Mth.sqrt((float) (dx * dx + dy * dy + dz * dz));
                            double vel = 0.3D;

                            useLE(stack, lpPerItemPerTick);
                            item.setDeltaMovement(dx / dm * vel, dy / dm * vel, dz / dm * vel);

                        }


                    }
                }
            }
        }

    }



}



