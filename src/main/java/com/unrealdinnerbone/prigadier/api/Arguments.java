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

import java.util.Collection;
import java.util.List;

public class Arguments {
        public static final Type<Entity> ENTITY = PrigadierArguments.ENTITY;
        public static final Type<List<Entity>> ENTITIES = PrigadierArguments.ENTITIES;
        public static Type<Player> PLAYER = PrigadierArguments.PLAYER;
        public static Type<List<Player>> PLAYERS = PrigadierArguments.PLAYERS;
        public static Type<NamedTextColor> COLOR = PrigadierArguments.COLOR;
        public static Type<Float> ANGLE = PrigadierArguments.ANGLE;
        public static Type<ItemStack> ITEM = PrigadierArguments.ITEM;
        public static Type<java.util.UUID> UUID = PrigadierArguments.UUID;
        public static Type<Integer> TIME = PrigadierArguments.TIME;
        public static Type<Integer> SLOT = PrigadierArguments.SLOT;
        public static Type<String> SCORE_HOLDER = PrigadierArguments.SCORE_HOLDER;
        public static Type<Collection<String>> SCORE_HOLDERS = PrigadierArguments.SCORE_HOLDERS;
        public static Type<DisplaySlot> SCOREBOARD_SLOT = PrigadierArguments.SCOREBOARD_SLOT;
        public static Type<NamespacedKey> NAMESPACE = PrigadierArguments.NAMESPACE;
        public static Type<Position> POSITION = PrigadierArguments.POSITION;
    }