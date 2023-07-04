package com.unrealdinnerbone.prigadier.api;

import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.commands.SharedSuggestionProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Suggestions {

    public static CompletableFuture<com.mojang.brigadier.suggestion.Suggestions> strings(SuggestionsBuilder builder, List<String> suggestions) {
        return SharedSuggestionProvider.suggest(suggestions, builder);
    }
}