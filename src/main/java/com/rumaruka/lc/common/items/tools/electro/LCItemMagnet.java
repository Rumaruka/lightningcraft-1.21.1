package com.rumaruka.lc.common.items.tools.electro;

import com.rumaruka.lc.common.items.tools.base.ItemLEBase;
import com.rumaruka.lc.init.LCDataComponent;
import com.rumaruka.lc.misc.LCUtils;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class LCItemMagnet extends ItemLEBase {
    public static final double t1Range = 8;
    private boolean isWorked = false;
    public LCItemMagnet() {
        super(new Item.Properties()
                .component(LCDataComponent.LE_ENERGY.get(), 0)

                .component(LCDataComponent.LE_ENERGY_MAX.get(), LCUtils.getMaxEnergyTools()));
    }

  private void setGlint(ItemStack stack){
        stack.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, isWorked());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (!level.isClientSide()) {
            setWorked(!player.isShiftKeyDown());
            setGlint(player.getMainHandItem());
            return InteractionResultHolder.success(player.getMainHandItem());
        }
        return super.use(level, player, usedHand);
    }

    public  double getRange(ItemStack stack) {
        Integer i = stack.get(LCDataComponent.LE_ENERGY.get());
        if (i == null) return 0;
        return stack.isEmpty() ? 0 : (getLE(stack) + 1) * t1Range;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (!level.isClientSide()) {
            if (entity instanceof Player player) {
                double range = getRange(stack);
                AABB box = new AABB(player.getX() - range, player.getY() - range, player.getZ() - range, player.getX() + range, player.getY() + range, player.getZ() + range);

                for (Entity e : level.getEntities(entity, box)) {
                    if (e instanceof ItemEntity item) {

                        if (getLE(stack) != 0 &&isWorked()) {
                            double dx = player.getX() - item.getX();
                            double dy = player.getY() - item.getY();
                            double dz = player.getZ() - item.getZ();
                            double dm = Mth.sqrt((float) (dx * dx + dy * dy + dz * dz));
                            double vel = 0.3D;

                            useLE(stack, 10);
                            item.setDeltaMovement(dx / dm * vel, dy / dm * vel, dz / dm * vel);

                        }


                    }
                }
            }
        }

    }


    public boolean isWorked() {
        return isWorked;
    }

    public void setWorked(boolean worked) {
        isWorked = worked;
    }
}
