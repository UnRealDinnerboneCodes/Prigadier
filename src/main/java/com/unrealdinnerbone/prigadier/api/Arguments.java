package com.unrealdinnerbone.prigadier.api;

import com.unrealdinnerbone.crafty.api.ParticleOption;
import com.unrealdinnerbone.prigadier.PrigadierArguments;
import com.unrealdinnerbone.prigadier.api.util.Type;
import io.papermc.paper.math.Position;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
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

        static Type<Sound> sound() {
                return PrigadierArguments.SOUND;
        }

        static <E extends Entity> Type<List<E>> entities(Class<E> clazz) {
                return PrigadierArguments.entities(clazz);
        }

        static <E extends Entity> Type<E> entity(Class<E> clazz) {
                return PrigadierArguments.entity(clazz);
        }

        static <T extends Enum<T> & net.kyori.adventure.key.Keyed> Type<T> keyedEnum(Class<T> clazz) {
                return PrigadierArguments.enumArgument(clazz);
        }

        static Type<BlockData> blockState() {
                return PrigadierArguments.BLOCK_STATE;
        }

        static Type<Statistic> statistic() {
                return PrigadierArguments.STAT;
        }

        static <E extends Enum<E>> Type<E> enumArgument(Class<E> clazz) {
                return PrigadierArguments.createEnum(clazz);
        }




}