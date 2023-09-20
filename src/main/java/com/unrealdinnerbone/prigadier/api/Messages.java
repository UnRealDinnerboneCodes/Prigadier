package com.unrealdinnerbone.prigadier.api;

import com.destroystokyo.paper.brigadier.BukkitBrigadierCommandSource;
import io.papermc.paper.adventure.PaperAdventure;
import net.minecraft.commands.CommandSourceStack;

import java.util.function.Supplier;

public class Messages
{
    public static void sendSuccess(BukkitBrigadierCommandSource source, Supplier<net.kyori.adventure.text.Component> feedbackSupplier, boolean broadcastToOps) throws IllegalArgumentException {
        if(source instanceof CommandSourceStack stack) {
            stack.sendSuccess(() -> PaperAdventure.asVanilla(feedbackSupplier.get()), broadcastToOps);
        }else {
            throw new IllegalArgumentException("Source must be a CommandSourceStack");
        }
    }

    public static void sendSuccessMessage(BukkitBrigadierCommandSource source, Supplier<String> feedbackSupplier, boolean broadcastToOps) throws IllegalArgumentException {
        sendSuccess(source, () -> net.kyori.adventure.text.Component.text(feedbackSupplier.get()), broadcastToOps);
    }

    public static void sendSuccess(BukkitBrigadierCommandSource source, Supplier<net.kyori.adventure.text.Component> feedbackSupplier) throws IllegalArgumentException {
        sendSuccess(source, feedbackSupplier, true);
    }

    public static void sendSuccessMessage(BukkitBrigadierCommandSource source, Supplier<String> feedbackSupplier) throws IllegalArgumentException {
        sendSuccessMessage(source, feedbackSupplier, true);
    }
}
