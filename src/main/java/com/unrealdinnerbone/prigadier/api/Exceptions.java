package com.unrealdinnerbone.prigadier.api;

import com.destroystokyo.paper.brigadier.BukkitBrigadierCommandSource;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.BuiltInExceptionProvider;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;

public class Exceptions {
    public static final BuiltInExceptionProvider BUILT_IN_EXCEPTIONS = CommandSyntaxException.BUILT_IN_EXCEPTIONS;
    public static final SimpleCommandExceptionType ERROR_NOT_PLAYER = CommandSourceStack.ERROR_NOT_PLAYER;
    public static final SimpleCommandExceptionType ERROR_NOT_ENTITY = CommandSourceStack.ERROR_NOT_ENTITY;


    public static void assertPlayer(CommandContext<BukkitBrigadierCommandSource> context) throws CommandSyntaxException {
        if(context.getSource().getBukkitSender() == null) {
            throw ERROR_NOT_PLAYER.create();
        }
    }
}
    