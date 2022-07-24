package net.iamaprogrammer.heartcanister.item.custom;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class LifeRingItem extends TrinketItem implements Trinket{
    Random rand = new Random();
    float health;
    int effectStrength;

    public LifeRingItem(Settings settings, float health, int effectStrength) {
        super(settings);
        this.health = health;
        this.effectStrength = effectStrength;
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);
        modifiers.put(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(uuid, "generic.max_health", health, EntityAttributeModifier.Operation.ADDITION));
        return modifiers;
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.getItemCooldownManager().set(this, ToTick(10));
        ItemStack stack = user.getStackInHand(hand);

        // Check if ItemStack has the Fireworks tag, if not, add it and its sub-nbt
        if (!stack.getNbt().contains("Fireworks") || stack.getNbt() == null) {
            NbtCompound container = stack.getOrCreateSubNbt("Fireworks");
            container.put("Explosions", new NbtList());

            List<Integer> color = Lists.newArrayList();
            color.add(16735449);

            NbtCompound attr = new NbtCompound();
            attr.putIntArray("Colors", color);
            attr.putByte("Type", (byte) 1);

            container.getList("Explosions", 0).add(attr);
        }

        // Add firework particle
        NbtCompound nbtCompound = stack.isEmpty() ? null : stack.getSubNbt("Fireworks");
        for (int i = 0; i < 2; ++i) {
            world.addFireworkParticle(user.getX(),
                    user.getY(),
                    user.getZ(),
                    randomFloat(-0.500f, 0.500f),
                    randomFloat(-0.500f, 0.500f),
                    randomFloat(-0.500f, 0.500f),
                    nbtCompound
            );
        }

        // Get all entities within a 6x6x6 area of the player.
        List<Entity> entityBox = world.getOtherEntities(null, new Box(user.getX() + 6,
                user.getY(),
                user.getZ() + 6,
                user.getX() - 6,
                user.getY() + 6,
                user.getZ() - 6
        ));

        // check for living entities and apply the regeneration status effect.
        for (Entity i : entityBox) {
            LivingEntity living = i instanceof LivingEntity ? ((LivingEntity) i) : null;
            assert living != null;
            living.setStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, ToTick(10), effectStrength, true, true, true), living);
        }

        // Damage Tool
        stack.damage(1, user, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        return super.use(world, user, hand);

    }

    // Helper methods.
    public float randomFloat(float min, float max) {
        return min + rand.nextFloat((max - min) + 1);
    }
    public int ToTick(int seconds) {
        return seconds * 20;
    }
}

