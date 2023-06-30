package com.unrealdinnerbone.prigadier;

import com.destroystokyo.paper.brigadier.BukkitBrigadierCommandSource;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.mojang.brigadier.tree.RootCommandNode;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.dedicated.DedicatedServer;

import java.util.function.Function;

public class Prigadier {

    public static <T extends BukkitBrigadierCommandSource> LiteralCommandNode<T> register(LiteralArgumentBuilder<T> builder) {
        return (LiteralCommandNode<T>) DedicatedServer.getServer().getCommands().getDispatcher().register((LiteralArgumentBuilder<CommandSourceStack>) builder);
    }

    public static <T extends BukkitBrigadierCommandSource> com.mojang.brigadier.CommandDispatcher<T> getDispatcher() {
        return (CommandDispatcher<T>) DedicatedServer.getServer().getCommands().getDispatcher();
    }

    public static <T extends BukkitBrigadierCommandSource> Function<LiteralArgumentBuilder<T>, LiteralCommandNode<T>> getRegister() {
        return Prigadier::register;
    }

    @SuppressWarnings("unchecked")
    public static <T extends BukkitBrigadierCommandSource> RootCommandNode<T> getRoot() {
        return (RootCommandNode<T>) DedicatedServer.getServer().getCommands().getDispatcher().getRoot();
    }

    public static <T extends BukkitBrigadierCommandSource, B> RequiredArgumentBuilder<T, B> argument(String name, ArgumentType<B> type) {
        return RequiredArgumentBuilder.argument(name, type);
    }

    @SuppressWarnings("unchecked")
    public static <T extends BukkitBrigadierCommandSource> LiteralArgumentBuilder<T> literal(String name) {
        return (LiteralArgumentBuilder<T>) Commands.literal(name);
    }


}
