package com.unrealdinnerbone.prigadier.api;

import com.unrealdinnerbone.crafty.api.CompletedProfile;
import com.unrealdinnerbone.crafty.api.ParticleOption;
import com.unrealdinnerbone.prigadier.api.util.OneOrMany;
import com.unrealdinnerbone.prigadier.api.util.Type;
import com.unrealdinnerbone.prigadier.impl.args.PrigadierArguments;
import io.papermc.paper.math.Position;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.*;
import org.bukkit.advancement.Advancement;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.block.banner.PatternType;
import org.bukkit.block.data.BlockData;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.generator.structure.StructureType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.loot.LootTables;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface Arguments {
        static Type<Entity> entity() {
                return PrigadierArguments.ENTITY;
        }

        static Type<List<Entity>> entities() {
                return PrigadierArguments.ENTITIES;
        }

        static Type<Player> player() {
                return PrigadierArguments.PLAYER;
        }

        static Type<List<Player>> players() {
                return PrigadierArguments.PLAYERS;
        }

        static Type<NamedTextColor> color() {
                return PrigadierArguments.COLOR;
        }

        static Type<Float> angle() {
                return PrigadierArguments.ANGLE;
        }

        static Type<ItemStack> item() {
                return PrigadierArguments.ITEM;
        }

        static Type<UUID> uuid() {
                return PrigadierArguments.UUID;
        }

        static Type<Integer> time() {
                return PrigadierArguments.TIME;
        }

        static Type<Integer> time(int min) {
                return PrigadierArguments.time(min);
        }

        static Type<Integer> slot() {
                return PrigadierArguments.SLOT;
        }


        static Type<String> scoreHolder() {
                return PrigadierArguments.SCORE_HOLDER;
        }

        static Type<Collection<String>> scoreHolders() {
                return PrigadierArguments.SCORE_HOLDERS;
        }

        static Type<DisplaySlot> scoreboardSlot() {
                return PrigadierArguments.SCOREBOARD_SLOT;
        }

        static Type<NamespacedKey> namespace() {
                return PrigadierArguments.NAMESPACE;
        }

        static Type<Position> position() {
                return PrigadierArguments.POSITION;
        }

        static Type<Objective> objective() {
                return PrigadierArguments.OBJECTIVE;
        }

        static Type<String> word() {
                return PrigadierArguments.WORD;
        }

        static Type<String> string() {
                return PrigadierArguments.STRING;
        }

        static Type<String> greedyString() {
                return PrigadierArguments.GREEDY_STRING;
        }

        static Type<Integer> integer() {
                return PrigadierArguments.INTEGER;
        }
        static Type<Integer> integer(int min, int max) {
                return PrigadierArguments.integer(min, max);
        }

        static Type<Long> longArg() {
                return PrigadierArguments.LONG;
        }

        static Type<Long> longArg(long min, long max) {
                return PrigadierArguments.longArg(min, max);
        }

        static Type<Double> doubleArg() {
                return PrigadierArguments.DOUBLE;
        }

        static Type<Double> doubleArg(double min, double max) {
                return PrigadierArguments.doubleArg(min, max);
        }

        static Type<Boolean> bool() {
                return PrigadierArguments.BOOLEAN;
        }

        static Type<Float> floatArg() {
                return PrigadierArguments.FLOAT;
        }

        static Type<Float> floatArg(float min, float max) {
                return PrigadierArguments.floatArg(min, max);
        }

        static Type<CompletedProfile> playerProfile() {
                return PrigadierArguments.GAME_PROFILE;
        }

        static Type<List<CompletedProfile>> playerProfiles() {
                return PrigadierArguments.GAME_PROFILES;
        }


        static Type<Criteria> criteria() {
                return PrigadierArguments.CRITERIA;
        }

        static Type<Component> component() {
                return PrigadierArguments.COMPONENT;
        }

        static Type<Component> miniMessage() {
                return PrigadierArguments.MINI_MESSAGE;
        }

        static Type<Team> team() {
                return PrigadierArguments.TEAM;
        }

        static Type<OfflinePlayer> offlinePlayer() {
                return PrigadierArguments.OFFLINE_PLAYER;
        }

        static Type<List<OfflinePlayer>> offlinePlayers() {
                return PrigadierArguments.OFFLINE_PLAYERS;
        }

        static Type<ParticleOption> particle() {
                return PrigadierArguments.PARTICLE_BUILDER;
        }

        static <E extends Entity> Type<List<E>> entities(Class<E> clazz) {
                return PrigadierArguments.entities(clazz);
        }

        static <E extends Entity> Type<E> entity(Class<E> clazz) {
                return PrigadierArguments.entity(clazz);
        }

        static Type<BlockData> blockState() {
                return PrigadierArguments.BLOCK_STATE;
        }

        static <E extends Enum<E>> Type<E> enumArgument(Class<E> clazz) {
                return PrigadierArguments.createEnum(clazz);
        }

        static Type<Advancement> advancement() {
                return PrigadierArguments.Registries.ADVANCEMENT;
        }

        static OneOrMany<Art> art() {
                return PrigadierArguments.Registries.ART;
        }

        static OneOrMany<Attribute> attribute() {
                return PrigadierArguments.Registries.ATTRIBUTE;
        }

        static OneOrMany<PatternType> bannerPattern() {
                return PrigadierArguments.Registries.BANNER_PATTERN;
        }

        static Type<Biome> biome() {
                return PrigadierArguments.Registries.BIOME;
        }

        static Type<KeyedBossBar> bossBar() {
                return PrigadierArguments.Registries.BOSS_BAR;
        }

        static OneOrMany<Cat.Type> catType() {
                return PrigadierArguments.Registries.CAT_VARIANT;
        }

        static OneOrMany<Enchantment> enchantment() {
                return PrigadierArguments.Registries.ENCHANTMENT;
        }

        static OneOrMany<EntityType> entityType() {
                return PrigadierArguments.Registries.ENTITY_TYPE;
        }

        static OneOrMany<MusicInstrument> instrument() {
                return PrigadierArguments.Registries.INSTRUMENT;
        }

        static Type<LootTables> lootTable() {
                return PrigadierArguments.Registries.LOOT_TABLE;
        }

        static Type<Material> material() {
                return PrigadierArguments.Registries.MATERIAL;
        }

        static Type<PotionEffectType> effect() {
                return PrigadierArguments.Registries.POTION_EFFECT_TYPE;
        }

        static Type<Particle> particleType() {
                return PrigadierArguments.Registries.PARTICLE;
        }

        static Type<PotionType> potion() {
                return PrigadierArguments.Registries.POTION;
        }

        static Type<Statistic> statisticType() {
                return PrigadierArguments.Registries.STATISTIC;
        }

        static OneOrMany<StructureType> structure() {
                return PrigadierArguments.Registries.STRUCTURE;
        }

        static OneOrMany<Sound> sound() {
                return PrigadierArguments.Registries.SOUND;
        }

        static Type<TrimMaterial> trimMaterial() {
                return PrigadierArguments.Registries.TRIM_MATERIAL;
        }

        static OneOrMany<Villager.Profession> villagerProfession() {
                return PrigadierArguments.Registries.VILLAGER_PROFESSION;
        }

        static OneOrMany<Villager.Type> villagerType() {
                return PrigadierArguments.Registries.VILLAGER_TYPE;
        }

        static OneOrMany<MemoryKey> memoryKey() {
                return PrigadierArguments.Registries.MEMORY_KEY;
        }

        static OneOrMany<Fluid> fluid() {
                return PrigadierArguments.Registries.FLUID;
        }

        static OneOrMany<Frog.Variant> frogVariant() {
                return PrigadierArguments.Registries.FROG_VARIANT;
        }

        static OneOrMany<GameEvent> gameEvent() {
                return PrigadierArguments.Registries.GAME_EVENT;
        }

        static Type<PotionEffectType> potionEffectType() {
                return PrigadierArguments.Registries.POTION_EFFECT_TYPE;
        }







}