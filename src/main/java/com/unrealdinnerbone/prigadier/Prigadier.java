package com.unrealdinnerbone.prigadier;

import com.destroystokyo.paper.brigadier.BukkitBrigadierCommandSource;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.dedicated.DedicatedServer;

public class Prigadier {

    public static void register(LiteralArgumentBuilder<BukkitBrigadierCommandSource> builder) {
        Object castMagic = builder;
        DedicatedServer.getServer().getCommands().getDispatcher().register(((LiteralArgumentBuilder<CommandSourceStack>) castMagic));
    }

    public static <T> RequiredArgumentBuilder<BukkitBrigadierCommandSource, T> argument(String name, ArgumentType<T> type) {
        return RequiredArgumentBuilder.argument(name, type);
    }

    public static <T extends BukkitBrigadierCommandSource> LiteralArgumentBuilder<T> literal(String name) {
        return (LiteralArgumentBuilder<T>) Commands.literal(name);
    }


}
