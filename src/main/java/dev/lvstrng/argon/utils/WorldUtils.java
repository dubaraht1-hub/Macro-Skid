package dev.lvstrng.argon.utils;

import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.*;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.chunk.WorldChunk;

import java.util.Objects;
import java.util.stream.Stream;

import static dev.lvstrng.argon.Argon.mc;

public final class WorldUtils {
    public static boolean isDeadBodyNearby() {
        return mc.world.getPlayers().parallelStream()
                .filter(e -> e != mc.player)
                .filter(e -> e.squaredDistanceTo(mc.player) <= 36)
                .anyMatch(LivingEntity::isDead);
    }

    public static Entity findNearestEntity(PlayerEntity toPlayer, float radius, boolean seeOnly) {
        float mr = Float.MAX_VALUE;
        Entity entity = null;
        for (Entity e : mc.world.getEntities()) {
            float d = e.distanceTo(toPlayer);
            if (e != toPlayer && d <= radius && mc.player.canSee(e) == seeOnly) {
                if (d < mr) {
                    mr = d;
                    entity = e;
                }
            }
        }
        return entity;
    }

    public static PlayerEntity findNearestPlayer(PlayerEntity toPlayer, float range, boolean seeOnly) {
        float minRange = Float.MAX_VALUE;
        PlayerEntity minPlayer = null;
        for (PlayerEntity player : mc.world.getPlayers()) {
            float distance = player.distanceTo(toPlayer);
            if (player != toPlayer && distance <= range && player.canSee(toPlayer) == seeOnly) {
                if (distance < minRange) {
                    minRange = distance;
                    minPlayer = player;
                }
            }
        }
        return minPlayer;
    }

    public static void hitEntity(Entity entity, boolean swingHand) {
        mc.interactionManager.attackEntity(mc.player, entity);
        if (swingHand) mc.player.swingHand(Hand.MAIN_HAND);
    }
}
