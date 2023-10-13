package com.unrealdinnerbone.prigadier.api;

import com.destroystokyo.paper.brigadier.BukkitBrigadierCommandSource;
import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.arguments.*;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.unrealdinnerbone.prigadier.api.util.ExceptionBiFunction;
import com.unrealdinnerbone.prigadier.api.util.ExceptionFunction;
import com.unrealdinnerbone.prigadier.api.util.BasicType;
import com.unrealdinnerbone.prigadier.api.util.Type;
import io.papermc.paper.adventure.PaperAdventure;
import io.papermc.paper.math.Position;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.*;
import net.minecraft.commands.arguments.blocks.BlockInput;
import net.minecraft.commands.arguments.blocks.BlockStateArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.commands.arguments.coordinates.Coordinates;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.commands.arguments.item.ItemInput;
import net.minecraft.core.BlockPos;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.scores.PlayerTeam;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.ApiStatus;

import java.util.*;
import java.util.function.Supplier;

import static com.unrealdinnerbone.prigadier.api.PrigadierArguments.Mappers.*;

@ApiStatus.Internal
public class PrigadierArguments {

    private static final CommandBuildContext CONTEXT = Commands.createValidationContext(VanillaRegistries.createLookup());

    public static final BasicType<Team> TEAM = of(TeamArgument::team, (context, s) -> fromTeam(TeamArgument.getTeam(cast(context), s)));
    public static final BasicType<Component> COMPONENT = of(ComponentArgument::textComponent, (context, s) -> fromComponent(ComponentArgument.getComponent(cast(context), s)));
    public static final BasicType<Entity> ENTITY = of(EntityArgument::entity, (context, s) -> fromEntity(EntityArgument.getEntity(cast(context), s)));
    public static final BasicType<List<Entity>> ENTITIES = of(EntityArgument::entities, (context, s) -> fromEntities(EntityArgument.getEntities(cast(context), s)));
    public static final BasicType<Player> PLAYER = of(EntityArgument::player, (context, s) -> fromPlayer(EntityArgument.getPlayer(cast(context), s)));
    public static final BasicType<List<Player>> PLAYERS = of(EntityArgument::players, (context, s) -> fromPlayers(EntityArgument.getPlayers(cast(context), s)));
    public static final BasicType<NamedTextColor> COLOR = of(ColorArgument::color, (context, s) -> fromColor(ColorArgument.getColor(cast(context), s)));
    public static final BasicType<Float> ANGLE = of(AngleArgument::angle, (context, s) -> (AngleArgument.getAngle(cast(context), s)));
    public static final BasicType<ItemStack> ITEM = of(() -> ItemArgument.item(CONTEXT), (context, s) -> fromItemInput(ItemArgument.getItem(cast(context), s)));
    public static final BasicType<UUID> UUID = of(UuidArgument::uuid, (context, s) -> UuidArgument.getUuid(cast(context), s));
    public static final BasicType<Integer> TIME = of(TimeArgument::time, IntegerArgumentType::getInteger);
    public static final BasicType<Integer> SLOT = of(SlotArgument::slot, (context, s) -> SlotArgument.getSlot(cast(context), s));
    public static final BasicType<String> SCORE_HOLDER = of(ScoreHolderArgument::scoreHolder, (context, s) -> ScoreHolderArgument.getName(cast(context), s));
    public static final BasicType<Collection<String>> SCORE_HOLDERS = of(ScoreHolderArgument::scoreHolders, (context, s) -> ScoreHolderArgument.getNamesWithDefaultWildcard(cast(context), s));
    public static final BasicType<DisplaySlot> SCOREBOARD_SLOT = of(ScoreboardSlotArgument::displaySlot, (context, s) -> fromSlotId(ScoreboardSlotArgument.getDisplaySlot(cast(context), s)));
    public static final BasicType<NamespacedKey> NAMESPACE = of(ResourceLocationArgument::id, (context, s) -> fromRL(ResourceLocationArgument.getId(cast(context), s)));
    public static final BasicType<Position> POSITION = of(BlockPosArgument::blockPos, PrigadierArguments.Mappers::getPosition);
    public static final BasicType<Objective> OBJECTIVE = of(ObjectiveArgument::objective, PrigadierArguments.Mappers::getObjective);
    public static final BasicType<String> WORD = of(StringArgumentType::word, StringArgumentType::getString);
    public static final BasicType<String> STRING = of(StringArgumentType::string, StringArgumentType::getString);
    public static final BasicType<String> GREEDY_STRING = of(StringArgumentType::greedyString, StringArgumentType::getString);
    public static final BasicType<Integer> INTEGER = of(IntegerArgumentType::integer, IntegerArgumentType::getInteger);
    public static final BasicType<Boolean> BOOLEAN = of(BoolArgumentType::bool, BoolArgumentType::getBool);
    public static final BasicType<Float> FLOAT = of(FloatArgumentType::floatArg, FloatArgumentType::getFloat);
    public static final BasicType<Double> DOUBLE = of(DoubleArgumentType::doubleArg, DoubleArgumentType::getDouble);
    public static final BasicType<Long> LONG = of(LongArgumentType::longArg, LongArgumentType::getLong);
    public static final BasicType<BlockData> BLOCK_STATE = of(() -> BlockStateArgument.block(CONTEXT), (context, s) -> Mappers.fromBlockState(BlockStateArgument.getBlock(cast(context), s)));

    public static final BasicType<OfflinePlayer> OFFLINE_PLAYER = of(GameProfileArgument::gameProfile, (context, s) -> {
        Collection<GameProfile> gameProfiles = GameProfileArgument.getGameProfiles(cast(context), s);
        if(gameProfiles.size() != 1) {
            throw EntityArgument.ERROR_NOT_SINGLE_PLAYER.create();
        }else {
            return Mappers.fromGameProfile(gameProfiles.stream().findFirst().get());
        }
    });

    public static final BasicType<List<OfflinePlayer>> OFFLINE_PLAYERS = of(GameProfileArgument::gameProfile, (context, s) -> Mappers.fromGameProfiles(GameProfileArgument.getGameProfiles(cast(context), s)));


    public static <T> BasicType<T> createCustom(ExceptionFunction<CommandSyntaxException, T, String> mapper, Supplier<List<String>> suggestions) {
        return of(StringArgumentType::word, (context, s) -> mapper.get(StringArgumentType.getString(context, s)));
    }

    public static BasicType<Double> doubleArg(double min, double max) {
        return of(() -> DoubleArgumentType.doubleArg(min, max), DoubleArgumentType::getDouble);
    }
    public static BasicType<Long> longArg(long min, long max) {
        return of(() -> LongArgumentType.longArg(min, max), LongArgumentType::getLong);
    }
    public static BasicType<Float> floatArg(float min, float max) {
        return of(() -> FloatArgumentType.floatArg(min, max), FloatArgumentType::getFloat);
    }
    public static BasicType<Integer> integer(int min, int max) {
        return of(() -> IntegerArgumentType.integer(min, max), IntegerArgumentType::getInteger);
    }

    public static BasicType<Integer> time(int min) {
        return of(() -> TimeArgument.time(min), IntegerArgumentType::getInteger);
    }

    protected static final DynamicCommandExceptionType INVALID_KEY = new DynamicCommandExceptionType((object) -> new LiteralMessage("Unknown key: " + object.toString()));
    public static <T extends Enum<T> & net.kyori.adventure.key.Keyed> Type<T> enumArgument(Class<T> clazz) {

        return new Type<T>() {
            @Override
            public T parse(CommandContext<BukkitBrigadierCommandSource> context, String name) throws CommandSyntaxException {
                NamespacedKey namespacedKey = NAMESPACE.parse().get(context, name);
                for (T enumConstant : clazz.getEnumConstants()) {
                    Optional<Key> key = getKey(enumConstant);
                    if(key.isPresent()) {
                        if(key.get().equals(namespacedKey)) {
                            return enumConstant;
                        }
                    }
                }
                throw INVALID_KEY.create(namespacedKey.toString());
            }

            @Override
            public RequiredArgumentBuilder<BukkitBrigadierCommandSource, ?> create(String name) {
                return NAMESPACE.create(name)
                        .suggests((context, builder) -> {
                            List<String> list = Arrays.stream(clazz.getEnumConstants())
                                    .map(PrigadierArguments::getKey)
                                    .filter(Optional::isPresent)
                                    .map(Optional::get)
                                    .map(Key::asString)
                                    .toList();
                            return Suggestions.strings(builder, list);
                        });
            }
        };
    }

    private static <T extends Enum<T> & net.kyori.adventure.key.Keyed> Optional<Key> getKey(T value) {
        try {
            return Optional.of(value.key());
        }catch (Exception e) {
            return Optional.empty();
        }
    }

    protected static class Mappers {

        private static BlockData fromBlockState(BlockInput block) {
            return block.getState().createCraftBlockData();
        }

        protected static Team fromTeam(PlayerTeam team) {
            return Bukkit.getScoreboardManager().getMainScoreboard().getTeam(team.getName());
        }

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

        protected static Component fromComponent(net.minecraft.network.chat.Component component) {
            return PaperAdventure.asAdventure(component);
        }

        protected static OfflinePlayer fromGameProfile(com.mojang.authlib.GameProfile gameProfile) {
            return Bukkit.getOfflinePlayer(gameProfile.getId());
        }

        protected static List<OfflinePlayer> fromGameProfiles(Collection<com.mojang.authlib.GameProfile> gameProfiles) {
            return gameProfiles.stream().map(Mappers::fromGameProfile).toList();
        }

        protected static CommandContext<CommandSourceStack> cast(CommandContext<BukkitBrigadierCommandSource> stack) {
            return (CommandContext<CommandSourceStack>) (Object) stack;
        }
    }


    private static <T> BasicType<T> of(Supplier<ArgumentType<?>> supplier, ExceptionBiFunction<CommandSyntaxException, T, CommandContext<BukkitBrigadierCommandSource>, String> function) {
        return new BasicType<>(supplier, function);
    }
}
