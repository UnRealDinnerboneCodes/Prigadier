package com.unrealdinnerbone.prigadier;

import com.destroystokyo.paper.brigadier.BukkitBrigadierCommandSource;
import com.mojang.brigadier.arguments.*;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.unrealdinnerbone.prigadier.api.util.ExceptionBiFunction;
import com.unrealdinnerbone.prigadier.api.util.Type;
import io.papermc.paper.adventure.PaperAdventure;
import io.papermc.paper.math.Position;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.*;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.commands.arguments.coordinates.Coordinates;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.commands.arguments.item.ItemInput;
import net.minecraft.core.BlockPos;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.jetbrains.annotations.ApiStatus;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

import static com.unrealdinnerbone.prigadier.PrigadierArguments.Mappers.*;

@ApiStatus.Internal
public class PrigadierArguments {


    private static final CommandBuildContext CONTEXT =  Commands.createValidationContext(VanillaRegistries.createLookup());

    public static final Type<Entity> ENTITY = of(EntityArgument::entity, (context, s) -> fromEntity(EntityArgument.getEntity(cast(context), s)));
    public static final Type<List<org.bukkit.entity.Entity>> ENTITIES = of(EntityArgument::entities, (context, s) -> fromEntities(EntityArgument.getEntities(cast(context), s)));
    public static final Type<Player> PLAYER = of(EntityArgument::player, (context, s) -> fromPlayer(EntityArgument.getPlayer(cast(context), s)));
    public static final Type<List<Player>> PLAYERS = of(EntityArgument::players, (context, s) -> fromPlayers(EntityArgument.getPlayers(cast(context), s)));
    public static final Type<NamedTextColor> COLOR = of(EntityArgument::players, (context, s) -> fromColor(ColorArgument.getColor(cast(context), s)));
    public static final Type<Float> ANGLE = of(AngleArgument::angle, (context, s) -> (AngleArgument.getAngle(cast(context), s)));
    public static final Type<ItemStack> ITEM = of(() -> ItemArgument.item(CONTEXT), (context, s) -> fromItemInput(ItemArgument.getItem(cast(context), s)));
    public static final Type<UUID> UUID = of(UuidArgument::uuid, (context, s) -> UuidArgument.getUuid(cast(context), s));
    public static final Type<Integer> TIME = of(TimeArgument::time, IntegerArgumentType::getInteger);
    public static final Type<Integer> SLOT = of(SlotArgument::slot, (context, s) -> SlotArgument.getSlot(cast(context), s));
    public static final Type<String> SCORE_HOLDER = of(ScoreHolderArgument::scoreHolder, (context, s) -> ScoreHolderArgument.getName(cast(context), s));
    public static final Type<Collection<String>> SCORE_HOLDERS = of(ScoreHolderArgument::scoreHolders, (context, s) -> ScoreHolderArgument.getNamesWithDefaultWildcard(cast(context), s));
    public static final Type<DisplaySlot> SCOREBOARD_SLOT = of(ScoreboardSlotArgument::displaySlot, (context, s) -> fromSlotId(ScoreboardSlotArgument.getDisplaySlot(cast(context), s)));
    public static final Type<NamespacedKey> NAMESPACE = of(ResourceLocationArgument::id, (context, s) -> fromRL(ResourceLocationArgument.getId(cast(context), s)));
    public static final Type<Position> POSITION = of(BlockPosArgument::blockPos, PrigadierArguments.Mappers::getPosition);
    public static final Type<Objective> OBJECTIVE = of(ObjectiveArgument::objective, PrigadierArguments.Mappers::getObjective);

    public static final Type<String> WORD = of(StringArgumentType::word, StringArgumentType::getString);
    public static final Type<String> STRING = of(StringArgumentType::string, StringArgumentType::getString);
    public static final Type<String> GREEDY_STRING = of(StringArgumentType::greedyString, StringArgumentType::getString);
    public static final Type<Integer> INTEGER = of(IntegerArgumentType::integer, IntegerArgumentType::getInteger);
    public static final Type<Boolean> BOOLEAN = of(BoolArgumentType::bool, BoolArgumentType::getBool);
    public static final Type<Float> FLOAT = of(FloatArgumentType::floatArg, FloatArgumentType::getFloat);

    public static final Type<Double> DOUBLE = of(DoubleArgumentType::doubleArg, DoubleArgumentType::getDouble);

    public static final Type<Long> LONG = of(LongArgumentType::longArg, LongArgumentType::getLong);

    public static Type<Double> doubleArg(double min, double max) {
        return of(() -> DoubleArgumentType.doubleArg(min, max), DoubleArgumentType::getDouble);
    }
    public static Type<Long> longArg(long min, long max) {
        return of(() -> LongArgumentType.longArg(min, max), LongArgumentType::getLong);
    }
    public static Type<Float> floatArg(float min, float max) {
        return of(() -> FloatArgumentType.floatArg(min, max), FloatArgumentType::getFloat);
    }
    public static Type<Integer> integer(int min, int max) {
        return of(() -> IntegerArgumentType.integer(min, max), IntegerArgumentType::getInteger);
    }

    public static Type<Integer> time(int min) {
        return of(() -> TimeArgument.time(min), IntegerArgumentType::getInteger);
    }

    protected static class Mappers {

        private static Objective getObjective(CommandContext<BukkitBrigadierCommandSource> context, String s) throws CommandSyntaxException {
            CommandContext<CommandSourceStack> newContext = cast(context);
            net.minecraft.world.scores.Objective objective = ObjectiveArgument.getObjective(newContext, s);
            return Bukkit.getScoreboardManager().getMainScoreboard().getObjective(objective.getName());
        }

        private static Position getPosition(CommandContext<BukkitBrigadierCommandSource> context, String s) {
            CommandContext<CommandSourceStack> newContext = cast(context);
            BlockPos blockPos = newContext.getArgument(s, Coordinates.class).getBlockPos(newContext.getSource());
            return Position.fine(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        }

        protected static NamespacedKey fromRL(ResourceLocation rl) {
            return new NamespacedKey(rl.getNamespace(), rl.getPath());
        }
        protected static DisplaySlot fromSlotId(int slotId) {
            return DisplaySlot.NAMES.value(net.minecraft.world.scores.Scoreboard.getDisplaySlotName(slotId));
        }

        protected static ItemStack fromItemInput(ItemInput itemInput) throws CommandSyntaxException {
            return itemInput.createItemStack(1, true).asBukkitMirror();
        }
        protected static Entity fromEntity(net.minecraft.world.entity.Entity entity) {
            return entity.getBukkitEntity();
        }

        protected static List<Entity> fromEntities(Collection<? extends net.minecraft.world.entity.Entity> entity) {
            return entity.stream().map(Mappers::fromEntity).toList();
        }

        protected static Player fromPlayer(ServerPlayer entity) {
            return entity.getBukkitEntity();
        }

        protected static List<Player> fromPlayers(Collection<? extends ServerPlayer> entity) {
            return entity.stream().map(Mappers::fromPlayer).toList();
        }

        protected static NamedTextColor fromColor(ChatFormatting formatting) {
            return NamedTextColor.nearestTo(PaperAdventure.asAdventure(formatting));
        }

        protected static CommandContext<CommandSourceStack> cast(CommandContext<BukkitBrigadierCommandSource> stack) {
            return (CommandContext<CommandSourceStack>) (Object) stack;
        }
    }


    private static <T> Type<T> of(Supplier<ArgumentType<?>> supplier, ExceptionBiFunction<CommandSyntaxException, T, CommandContext<BukkitBrigadierCommandSource>, String> function) {
        return new Type<>() {

            @Override
            public ArgumentType<?> create() {
                return supplier.get();
            }

            @Override
            public T parse(CommandContext<BukkitBrigadierCommandSource> context, String name) throws CommandSyntaxException {
                return function.get(context, name);
            }
        };
    }
}
