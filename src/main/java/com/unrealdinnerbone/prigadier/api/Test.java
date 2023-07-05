//package com.unrealdinnerbone.prigadier.api;
//
//import com.mojang.brigadier.StringReader;
//import io.papermc.paper.adventure.PaperAdventure;
//import net.minecraft.commands.SharedSuggestionProvider;
//import net.minecraft.commands.arguments.selector.EntitySelectorParser;
//import net.minecraft.commands.arguments.selector.options.EntitySelectorOptions;
//import net.minecraft.network.chat.Component;
//import org.bukkit.entity.Entity;
//import org.jetbrains.annotations.Nullable;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.Arrays;
//import java.util.List;
//import java.util.function.Predicate;
//import java.util.function.Supplier;
//
//public class Test {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);
//    public static String METHOD_NAME = "a";
//    private static void registerI(String id, EntitySelectorOptions.Modifier handler, Predicate<EntitySelectorParser> condition, Component description) {
//        try {
//            Method register = EntitySelectorOptions.class.getDeclaredMethod(METHOD_NAME, String.class, EntitySelectorOptions.Modifier.class, Predicate.class, Component.class);
//            register.setAccessible(true);
//            register.invoke(null, id, handler, condition, description);
//        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static void register(String id, Test2 test2, Supplier<List<String>> supplier, net.kyori.adventure.text.Component description) {
//        registerI(id, reader -> {
//            boolean invert = reader.shouldInvertValue();
//            String string = reader.getReader().readUnquotedString();
////            reader.setSuggestions((suggestionsBuilder, suggestionsBuilderConsumer) -> {
////                SharedSuggestionProvider.suggest(supplier.get(), suggestionsBuilder);
////                return suggestionsBuilder.buildFuture();
////            });
//            reader.addPredicate(entity -> test2.test(invert, string, entity.getBukkitEntity()));
//        }, reader -> true, PaperAdventure.asVanilla(description));
//
//    }
//
//    public static interface Test2 {
//        public boolean test(boolean invert, String string, Entity entity);
//    }
//}
