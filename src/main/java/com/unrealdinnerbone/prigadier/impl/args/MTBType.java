package com.unrealdinnerbone.prigadier.impl.args;

import com.destroystokyo.paper.brigadier.BukkitBrigadierCommandSource;
import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.unrealdinnerbone.prigadier.Conversions;
import com.unrealdinnerbone.prigadier.api.Commands;
import com.unrealdinnerbone.prigadier.api.util.OneOrMany;
import com.unrealdinnerbone.prigadier.api.util.Type;
import net.minecraft.commands.arguments.ResourceArgument;
import net.minecraft.commands.arguments.ResourceOrTagArgument;
import net.minecraft.commands.arguments.ResourceOrTagKeyArgument;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MTBType {


    public static <V, B extends Keyed> OneOrMany<B> ofRegistry(net.minecraft.core.Registry<V> vRegistry, Registry<B> bRegistry, Function<V, B> convert) {
        return new OneOrMany<>(new SingleRegistry<>(vRegistry, bRegistry, convert), new ManyRegistry<>(vRegistry, bRegistry, convert));
    }

//    public record SingleKey<V, B extends Keyed>(net.minecraft.core.Registry<V> vRegistry, Registry<B> bRegistry, Function<V, B> convert) implements Type<B> {
//
//
//        public B parse(CommandContext<BukkitBrigadierCommandSource> context, String name) throws CommandSyntaxException {
//            ResourceKey<net.minecraft.core.Registry<V>> key = (ResourceKey<net.minecraft.core.Registry<V>>) vRegistry.key();
//            Holder.Reference<V> resourceOrTag = ResourceArgument.getResource(Conversions.cast(context), name, key);
//            return convert.apply(resourceOrTag.value());
//        }
//
//        @Override
//        public RequiredArgumentBuilder<BukkitBrigadierCommandSource, ?> create(String name) {
//            return Commands.argument(name, new ResourceArgument<>(PrigadierArguments.CONTEXT, vRegistry.key()));
//        }
//
//    }
//
//    public record ManyKey<V, B extends Keyed>(ResourceKey<? extends net.minecraft.core.Registry<V>> vRegistry, Registry<B> bRegistry, Function<NamespacedKey, B> convert) implements Type<List<B>> {
//
//
//        private static final DynamicCommandExceptionType INVALID_KEY = new DynamicCommandExceptionType((object) -> new LiteralMessage("Invalid Key: " + object.toString()));
//        public List<B> parse(CommandContext<BukkitBrigadierCommandSource> context, String name) throws CommandSyntaxException {
//            ResourceKey<net.minecraft.core.Registry<V>> vRegistry1 = (ResourceKey<net.minecraft.core.Registry<V>>) vRegistry;
//            ResourceOrTagKeyArgument.Result<V> resourceOrTagKey = ResourceOrTagKeyArgument.getResourceOrTagKey(Conversions.cast(context), name, vRegistry1, INVALID_KEY);
//            return resourceOrTagKey.unwrap().map((holder) -> List.of(convert.apply(holder.())), (holders) -> {
//                List<B> values = new ArrayList<>();
//                for (Holder<V> holder : holders) {
//                    values.add(convert.apply(holder.value()));
//                }
//                return values;
//            });
//        }
//
//        @Override
//        public RequiredArgumentBuilder<BukkitBrigadierCommandSource, ?> create(String name) {
//            return Commands.argument(name, new ResourceOrTagKeyArgument<>(vRegistry));
//        }
//
//    }

    public record SingleRegistry<V, B extends Keyed>(net.minecraft.core.Registry<V> vRegistry, Registry<B> bRegistry, Function<V, B> convert) implements Type<B> {


        public B parse(CommandContext<BukkitBrigadierCommandSource> context, String name) throws CommandSyntaxException {
            ResourceKey<net.minecraft.core.Registry<V>> key = (ResourceKey<net.minecraft.core.Registry<V>>) vRegistry.key();
            Holder.Reference<V> resourceOrTag = ResourceArgument.getResource(Conversions.cast(context), name, key);
            return convert.apply(resourceOrTag.value());
        }

        @Override
        public RequiredArgumentBuilder<BukkitBrigadierCommandSource, ?> create(String name) {
            return Commands.argument(name, new ResourceArgument<>(PrigadierArguments.CONTEXT, vRegistry.key()));
        }

    }

    public record ManyRegistry<V, B extends Keyed>(net.minecraft.core.Registry<V> vRegistry, Registry<B> bRegistry, Function<V, B> convert) implements Type<List<B>> {


        public List<B> parse(CommandContext<BukkitBrigadierCommandSource> context, String name) throws CommandSyntaxException {
            ResourceKey<net.minecraft.core.Registry<V>> key = (ResourceKey<net.minecraft.core.Registry<V>>) vRegistry.key();
            ResourceOrTagArgument.Result<V> resourceOrTag = ResourceOrTagArgument.getResourceOrTag(Conversions.cast(context), name, key);
            return resourceOrTag.unwrap().map((holder) -> List.of(convert.apply(holder.value())), (holders) -> {
                List<B> values = new ArrayList<>();
                for (Holder<V> holder : holders) {
                    values.add(convert.apply(holder.value()));
                }
                return values;
            });
        }

        @Override
        public RequiredArgumentBuilder<BukkitBrigadierCommandSource, ?> create(String name) {
            return Commands.argument(name, new ResourceOrTagArgument<>(PrigadierArguments.CONTEXT, vRegistry.key()));
        }

    }


}
