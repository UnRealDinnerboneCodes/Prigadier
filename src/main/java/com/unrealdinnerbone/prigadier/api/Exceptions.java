package com.unrealdinnerbone.prigadier.api;

import com.mojang.brigadier.exceptions.BuiltInExceptionProvider;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;

public class Exceptions {
        public static final BuiltInExceptionProvider BUILT_IN_EXCEPTIONS = CommandSyntaxException.BUILT_IN_EXCEPTIONS;
        public static final SimpleCommandExceptionType ERROR_NOT_PLAYER = CommandSourceStack.ERROR_NOT_PLAYER;
        public static final SimpleCommandExceptionType ERROR_NOT_ENTITY = CommandSourceStack.ERROR_NOT_ENTITY;
    }
    