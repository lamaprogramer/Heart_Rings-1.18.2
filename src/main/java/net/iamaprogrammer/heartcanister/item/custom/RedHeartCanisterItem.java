package net.iamaprogrammer.heartcanister.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class RedHeartCanisterItem extends Item {

    public RedHeartCanisterItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            user.setHealth(user.getMaxHealth() + 1F);

        }
        //this.dataTracker.set(HEALTH, MathHelper.clamp(health, 0.0F, this.getMaxHealth()));
        return super.use(world, user, hand);
    }

}

