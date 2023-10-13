package com.unrealdinnerbone.prigadier.api;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.unrealdinnerbone.prigadier.api.util.BasicType;
import com.unrealdinnerbone.prigadier.api.util.ExceptionFunction;
import com.unrealdinnerbone.prigadier.api.util.Type;
import io.papermc.paper.math.Position;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

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

        static <T> Type<T> custom(ExceptionFunction<CommandSyntaxException, T, String> mapper, Supplier<List<String>> suggestions) {
                return PrigadierArguments.createCustom(mapper, suggestions);
        }

        static <T extends Enum<T> & net.kyori.adventure.key.Keyed> Type<T> keyedEnum(Class<T> clazz) {
                return PrigadierArguments.enumArgument(clazz);
        }

        static Type<BlockData> blockState() {
                return PrigadierArguments.BLOCK_STATE;
        }


}