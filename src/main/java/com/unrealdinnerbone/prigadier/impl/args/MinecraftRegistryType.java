package com.unrealdinnerbone.prigadier.impl.args;

import com.destroystokyo.paper.brigadier.BukkitBrigadierCommandSource;
import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.unrealdinnerbone.prigadier.api.Arguments;
import com.unrealdinnerbone.prigadier.api.SuggestionHelper;
import com.unrealdinnerbone.prigadier.api.util.BasicSuggestion;
import com.unrealdinnerbone.prigadier.api.util.Type;
import net.kyori.adventure.text.Component;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public record MinecraftRegistryType<B extends Keyed, T extends Registry<B>>(String registryType, T value, @Nullable Function<T, Component> displayName) implements Type<B> {

    public static <B extends Keyed, T extends Registry<B>> Type<B> of(String registryType, T value) {
        return new MinecraftRegistryType<>(registryType, value, null);
    }

    public static <B extends Keyed, T extends Registry<B>> Type<B> of(String registryType, T value, Function<T, Component> displayName) {
        return new MinecraftRegistryType<>(registryType, value, displayName);
    }

    private static final Dynamic2CommandExceptionType INVALID_ENTRY = new Dynamic2CommandExceptionType((object, object1) -> new LiteralMessage(object.toString() + " does not exist in " + object1.toString()));
    @Override
    public B parse(CommandContext<BukkitBrigadierCommandSource> context, String name) throws CommandSyntaxException {
        NamespacedKey namespacedKey = Arguments.namespace().parse(context, name);
        B b = value.get(namespacedKey);
        if(b == null) {
            throw INVALID_ENTRY.create(name, registryType);
        }
        return b;
    }

    @Override
    public RequiredArgumentBuilder<BukkitBrigadierCommandSource, ?> create(String s) {
        return Arguments.namespace().create(s)
                .suggests((context, builder) -> {
                    if(displayName == null) {
                        return SuggestionHelper.strings(builder, value.stream()
                                .map(MinecraftRegistryType::getKeyedName)
                                .toList());
                    }else {
                        List<BasicSuggestion> suggestions = new ArrayList<>();
                        for (B b : value) {
                            suggestions.add(new BasicSuggestion(getKeyedName(b), displayName.apply(value)));
                        }
                        return SuggestionHelper.suggest(builder, suggestions);
                    }
                });
    }


    private static String getKeyedName(Keyed keyed) {
        return keyed.key().asString();
    }


}
