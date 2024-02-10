package com.unrealdinnerbone.prigadier.api;

import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import io.papermc.paper.adventure.PaperAdventure;
import net.minecraft.commands.SharedSuggestionProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SuggestionHelper {

    public static CompletableFuture<Suggestions> strings(SuggestionsBuilder builder, List<String> suggestions) {
        return SharedSuggestionProvider.suggest(suggestions, builder);
    }

    public static <T extends ISuggestionEntry> CompletableFuture<Suggestions> suggest(SuggestionsBuilder builder, Iterable<T> suggestions) {
        return SharedSuggestionProvider.suggest(suggestions, builder, ISuggestionEntry::name, suggestion -> PaperAdventure.asVanilla(suggestion.tooltip()));
    }

}