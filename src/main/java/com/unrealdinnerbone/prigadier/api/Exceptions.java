package com.unrealdinnerbone.prigadier.api;

import com.destroystokyo.paper.brigadier.BukkitBrigadierCommandSource;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.BuiltInExceptionProvider;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.unrealdinnerbone.prigadier.PrigadierUtils;
import net.minecraft.commands.CommandSourceStack;
import org.bukkit.entity.Player;

public interface Exceptions {
    BuiltInExceptionProvider BUILT_IN_EXCEPTIONS = CommandSyntaxException.BUILT_IN_EXCEPTIONS;
    SimpleCommandExceptionType ERROR_NOT_PLAYER = CommandSourceStack.ERROR_NOT_PLAYER;
    SimpleCommandExceptionType ERROR_NOT_ENTITY = CommandSourceStack.ERROR_NOT_ENTITY;

    static Player assertPlayer(CommandContext<BukkitBrigadierCommandSource> context) throws CommandSyntaxException {
        return PrigadierUtils.assertPlayer(context);
    }

}
    