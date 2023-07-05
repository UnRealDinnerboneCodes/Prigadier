package com.unrealdinnerbone.prigadier.api;

import com.unrealdinnerbone.prigadier.PrigadierArguments;
import com.unrealdinnerbone.prigadier.api.util.Type;
import io.papermc.paper.math.Position;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

import java.util.Collection;
import java.util.List;

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

        static Type<java.util.UUID> uuid() {
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


}