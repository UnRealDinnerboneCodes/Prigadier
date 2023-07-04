package com.unrealdinnerbone.prigadier.api;

import com.destroystokyo.paper.brigadier.BukkitBrigadierCommandSource;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;

public class Commands {

        public static <T extends BukkitBrigadierCommandSource, B> RequiredArgumentBuilder<T, B> argument(String name, ArgumentType<B> type) {
            return RequiredArgumentBuilder.argument(name, type);
        }

        public static <T extends BukkitBrigadierCommandSource> LiteralArgumentBuilder<T> literal(String name) {
            return Commands.literal(name);
        }

    }