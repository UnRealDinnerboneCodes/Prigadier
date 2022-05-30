package com.unrealdinnerbone.prigadier;

import com.destroystokyo.paper.brigadier.BukkitBrigadierCommandSource;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.commands.SharedSuggestionProvider;
import org.bukkit.entity.Player;

import java.util.List;

public class PrigadierHelpers
{
    public static boolean isPlayer(BukkitBrigadierCommandSource s) {
        return s.getBukkitSender() instanceof Player;
    }

    public static SuggestionProvider<BukkitBrigadierCommandSource> stringSuggestions(List<String> suggestions) {
        return (context, builder) -> SharedSuggestionProvider.suggest(suggestions, builder);
    }
}
