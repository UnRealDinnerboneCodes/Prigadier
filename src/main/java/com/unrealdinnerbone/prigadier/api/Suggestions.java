package com.unrealdinnerbone.prigadier.api;

import com.destroystokyo.paper.brigadier.BukkitBrigadierCommandSource;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.commands.SharedSuggestionProvider;

import java.util.List;

public class Suggestions {

        public static SuggestionProvider<BukkitBrigadierCommandSource> stringSuggestions(List<String> suggestions) {
            return (context, builder) -> SharedSuggestionProvider.suggest(suggestions, builder);
        }
    }