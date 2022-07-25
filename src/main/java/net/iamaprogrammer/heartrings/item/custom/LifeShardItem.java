package net.iamaprogrammer.heartrings.item.custom;


import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class LifeShardItem extends Item {

    int effectStrength;
    public LifeShardItem(Settings settings, int effectStrength) {
        super(settings);
        this.effectStrength = effectStrength;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.getItemCooldownManager().set(this, ToTick(10));
        ItemUsage.exchangeStack(
                user.getStackInHand(hand),
                user,
                new ItemStack(Items.AIR),
                true
        );

        user.setStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, ToTick(3), effectStrength, false, false, false), user);
        return super.use(world, user, hand);
    }


    private static int ToTick(int seconds) {
        return seconds * 20;
    }
}