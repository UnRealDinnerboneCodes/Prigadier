package com.unrealdinnerbone.prigadier.api;

import com.destroystokyo.paper.brigadier.BukkitBrigadierCommandSource;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.minecraft.server.commands.DebugConfigCommand;
import net.minecraft.server.dedicated.DedicatedServer;

import java.util.function.Consumer;

public class Commands {

    public static <T extends BukkitBrigadierCommandSource, B> RequiredArgumentBuilder<T, B> argument(String name, ArgumentType<B> type) {
        return RequiredArgumentBuilder.argument(name, type);
    }

    @SuppressWarnings("unchecked")
    public static <T extends BukkitBrigadierCommandSource> LiteralArgumentBuilder<T> literal(String name) {
        return (LiteralArgumentBuilder<T>) net.minecraft.commands.Commands.literal(name);
    }

    public static class Vanilla {
        public static void registerDebugConfig() {
            DebugConfigCommand.register(DedicatedServer.getServer().getCommands().getDispatcher());
        }
    }

}