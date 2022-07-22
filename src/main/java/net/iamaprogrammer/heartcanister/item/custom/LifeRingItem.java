package net.iamaprogrammer.heartcanister.item.custom;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class LifeRingItem extends TrinketItem implements Trinket{
    Random rand = new Random();
    float health;
    public LifeRingItem(Settings settings, float health) {
        super(settings);
        this.health = health;
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);
        modifiers.put(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(uuid, "generic.max_health", health, EntityAttributeModifier.Operation.ADDITION));
        return modifiers;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        for (int i = 0; i < 500; ++i) {
            world.addParticle(ParticleTypes.DRAGON_BREATH,
                    user.getX(),
                    user.getY(),
                    user.getZ(),
                    randomFloat(-1.00f, 1.00f),
                    randomFloat(-1.00f, 1.00f),
                    randomFloat(-1.00f, 1.00f));
        }
        List<Entity> entityBox = world.getOtherEntities(null, new Box(user.getX() + 10,
                user.getY(),
                user.getZ() + 10,
                user.getX() - 10,
                user.getY() + 10,
                user.getZ() - 10
        ));
        for (Entity i : entityBox) {
            LivingEntity living = i instanceof LivingEntity ? ((LivingEntity) i) : null;
            assert living != null;
            living.setStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, ToTick(10), 2, true, true, true), living);
        }
        return super.use(world, user, hand);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        ItemStack entityHand = entity.getStackInHand(Hand.OFF_HAND);
        ItemStack userHand = user.getStackInHand(hand);

        entity.setStackInHand(Hand.OFF_HAND, userHand);
        user.setStackInHand(Hand.MAIN_HAND, entityHand);
        return super.useOnEntity(stack, user, entity, hand);

    }

    public float randomFloat(float min, float max) {
        return min + rand.nextFloat((max - min) + 1);
    }
    public int ToTick(int seconds) {
        return seconds * 20;
    }
}

