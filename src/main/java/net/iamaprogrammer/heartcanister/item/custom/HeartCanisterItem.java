package net.iamaprogrammer.heartcanister.item.custom;

import com.ibm.icu.impl.Trie2;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class HeartCanisterItem extends Item {

    public HeartCanisterItem(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            user.setHealth(user.getHealth() + 1F);
        }
        //this.dataTracker.set(HEALTH, MathHelper.clamp(health, 0.0F, this.getMaxHealth()));
        return super.use(world, user, hand);
    }
}

