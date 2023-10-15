package com.unrealdinnerbone.prigadier.api;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_20_R1.CraftParticle;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;

public class Test {

    public static NamespacedKey getParticleKey(Particle particle) {
        ParticleOptions nms = CraftParticle.toNMS(particle);
        ParticleType<?> type = nms.getType();
        ResourceLocation key = BuiltInRegistries.PARTICLE_TYPE.getKey(type);
        return CraftNamespacedKey.fromMinecraft(key);
    }

}
