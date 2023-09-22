package com.unrealdinnerbone.prigadier.api.util;

import com.destroystokyo.paper.brigadier.BukkitBrigadierCommandSource;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

public interface Type<T> {

    T parse(CommandContext<BukkitBrigadierCommandSource> context, String name) throws CommandSyntaxException;
    RequiredArgumentBuilder<BukkitBrigadierCommandSource, ?> create(String name);
}
