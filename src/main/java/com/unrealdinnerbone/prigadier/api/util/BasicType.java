package com.unrealdinnerbone.prigadier.api.util;

import com.destroystokyo.paper.brigadier.BukkitBrigadierCommandSource;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.unrealdinnerbone.prigadier.MapperFunction;
import com.unrealdinnerbone.prigadier.api.Commands;
import com.unrealdinnerbone.prigadier.api.util.Type;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Supplier;

public record BasicType<T>(Supplier<ArgumentType<?>> creator, MapperFunction<CommandSyntaxException, T, CommandContext<BukkitBrigadierCommandSource>, String> parse) implements Type<T>
{
    public static <T> BasicType<T> of(Supplier<ArgumentType<?>> supplier, MapperFunction<CommandSyntaxException, T, CommandContext<BukkitBrigadierCommandSource>, String> function) {
        return new BasicType<>(supplier, function);
    }
    public ArgumentType<?> create() {
        return creator.get();
    }

    @Override
    public T parse(CommandContext<BukkitBrigadierCommandSource> context, String name) throws CommandSyntaxException {
        return parse.get(context, name);
    }

    public RequiredArgumentBuilder<BukkitBrigadierCommandSource, ?> create(String name) {
        return Commands.argument(name, create());
    }
}
