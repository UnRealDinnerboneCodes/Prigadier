package com.unrealdinnerbone.prigadier.api;

import com.destroystokyo.paper.brigadier.BukkitBrigadierCommandSource;
import com.mojang.brigadier.CommandDispatcher;

public interface ICommand {
    void register(CommandDispatcher<BukkitBrigadierCommandSource> dispatcher);
}