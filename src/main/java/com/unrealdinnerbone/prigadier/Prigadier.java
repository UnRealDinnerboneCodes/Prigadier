package com.unrealdinnerbone.prigadier;

import com.destroystokyo.paper.brigadier.BukkitBrigadierCommandSource;
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

    @SuppressWarnings("unchecked")
    private static <T extends BukkitBrigadierCommandSource> LiteralCommandNode<T> register(LiteralArgumentBuilder<BukkitBrigadierCommandSource> builder) {
        Object castMagic = builder;
        return (LiteralCommandNode<T>) DedicatedServer.getServer().getCommands().getDispatcher().register((LiteralArgumentBuilder<CommandSourceStack>) castMagic);
    }

    public static Function<LiteralArgumentBuilder<BukkitBrigadierCommandSource>, LiteralCommandNode<BukkitBrigadierCommandSource>> getRegister() {
        return Prigadier::register;
    }

    @SuppressWarnings("unchecked")
    public static <T extends BukkitBrigadierCommandSource> RootCommandNode<T> getRoot() {
        return (RootCommandNode<T>) DedicatedServer.getServer().getCommands().getDispatcher().getRoot();
    }

    public static <T> RequiredArgumentBuilder<BukkitBrigadierCommandSource, T> argument(String name, ArgumentType<T> type) {
        return RequiredArgumentBuilder.argument(name, type);
    }

    @SuppressWarnings("unchecked")
    public static <T extends BukkitBrigadierCommandSource> LiteralArgumentBuilder<T> literal(String name) {
        return (LiteralArgumentBuilder<T>) Commands.literal(name);
    }


}
