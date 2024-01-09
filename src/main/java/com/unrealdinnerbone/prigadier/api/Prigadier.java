package com.unrealdinnerbone.prigadier.api;

import com.destroystokyo.paper.brigadier.BukkitBrigadierCommandSource;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.dedicated.DedicatedServer;

import java.util.function.Consumer;

public class Prigadier {

    public static <T extends BukkitBrigadierCommandSource> CommandDispatcher<T> getDispatcher() {
        return (CommandDispatcher<T>) DedicatedServer.getServer().getCommands().getDispatcher();
    }

    public static void register(Consumer<CommandDispatcher<BukkitBrigadierCommandSource>> consumer) {
        consumer.accept(getDispatcher());
    }

    public static void register(ICommand command) {
        command.register(getDispatcher());
    }

}
