package com.unrealdinnerbone.prigadier.api.util;

import com.destroystokyo.paper.brigadier.BukkitBrigadierCommandSource;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

public interface Type<T>
{
    ArgumentType<?> create();

    T parse(CommandContext<BukkitBrigadierCommandSource> context, String name) throws CommandSyntaxException;
}
