package com.unrealdinnerbone.prigadier;

import com.destroystokyo.paper.ParticleBuilder;
import com.destroystokyo.paper.brigadier.BukkitBrigadierCommandSource;
import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.arguments.*;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.unrealdinnerbone.crafty.api.ParticleOption;
import com.unrealdinnerbone.crafty.particle.CraftyParticle;
import com.unrealdinnerbone.prigadier.api.Suggestions;
import com.unrealdinnerbone.prigadier.api.util.Type;
import io.papermc.paper.math.Position;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.*;
import net.minecraft.commands.arguments.blocks.BlockStateArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.commands.synchronization.SuggestionProviders;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.scores.ScoreHolder;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_20_R3.CraftParticle;
import org.bukkit.craftbukkit.v1_20_R3.CraftSound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.ApiStatus;

import java.util.*;
import java.util.function.Supplier;

@ApiStatus.Internal
public class PrigadierArguments {

    private static final CommandBuildContext CONTEXT = Commands.createValidationContext(VanillaRegistries.createLookup());

    public static final BasicType<Team> TEAM = of(TeamArgument::team, (context, s) -> Conversions.convertTeam(TeamArgument.getTeam(Conversions.cast(context), s)));
    public static final BasicType<Component> COMPONENT = of(ComponentArgument::textComponent, (context, s) -> Conversions.convertComponent(ComponentArgument.getComponent(Conversions.cast(context), s)));
    public static final BasicType<Entity> ENTITY = of(EntityArgument::entity, (context, s) -> Conversions.convertEntity(EntityArgument.getEntity(Conversions.cast(context), s)));
    public static final BasicType<List<Entity>> ENTITIES = of(EntityArgument::entities, (context, s) -> Conversions.convertEntities(EntityArgument.getEntities(Conversions.cast(context), s)));
    public static final BasicType<Player> PLAYER = of(EntityArgument::player, (context, s) -> Conversions.convertPlayer(EntityArgument.getPlayer(Conversions.cast(context), s)));
    public static final BasicType<List<Player>> PLAYERS = of(EntityArgument::players, (context, s) -> Conversions.convertPlayers(EntityArgument.getPlayers(Conversions.cast(context), s)));
    public static final BasicType<NamedTextColor> COLOR = of(ColorArgument::color, (context, s) -> Conversions.convertNamedTextColor(ColorArgument.getColor(Conversions.cast(context), s)));
    public static final BasicType<Float> ANGLE = of(AngleArgument::angle, (context, s) -> (AngleArgument.getAngle(Conversions.cast(context), s)));
    public static final BasicType<ItemStack> ITEM = of(() -> ItemArgument.item(CONTEXT), (context, s) -> Conversions.convertItemStack(ItemArgument.getItem(Conversions.cast(context), s)));
    public static final BasicType<UUID> UUID = of(UuidArgument::uuid, (context, s) -> UuidArgument.getUuid(Conversions.cast(context), s));
    public static final BasicType<Integer> TIME = of(TimeArgument::time, IntegerArgumentType::getInteger);
    public static final BasicType<Integer> SLOT = of(SlotArgument::slot, (context, s) -> SlotArgument.getSlot(Conversions.cast(context), s));

    //Todo Update this
    public static final BasicType<String> SCORE_HOLDER = of(ScoreHolderArgument::scoreHolder, (context, s) -> ScoreHolderArgument.getName(Conversions.cast(context), s).getScoreboardName());

    //Todo
    public static final BasicType<Collection<String>> SCORE_HOLDERS = of(ScoreHolderArgument::scoreHolders, (context, s) -> ScoreHolderArgument.getNamesWithDefaultWildcard(Conversions.cast(context), s).stream().map(ScoreHolder::getScoreboardName).toList());
    public static final BasicType<DisplaySlot> SCOREBOARD_SLOT = of(ScoreboardSlotArgument::displaySlot, (context, s) -> Conversions.convertSlot(ScoreboardSlotArgument.getDisplaySlot(Conversions.cast(context), s)));
    public static final BasicType<NamespacedKey> NAMESPACE = of(ResourceLocationArgument::id, (context, s) -> Conversions.convertNamespacedKey(ResourceLocationArgument.getId(Conversions.cast(context), s)));
    public static final BasicType<Position> POSITION = of(BlockPosArgument::blockPos, Conversions::covnertPosition);
    public static final BasicType<Objective> OBJECTIVE = of(ObjectiveArgument::objective, ((context, s) -> Conversions.convertObjective(ObjectiveArgument.getObjective(Conversions.cast(context), s))));
    public static final BasicType<String> WORD = of(StringArgumentType::word, StringArgumentType::getString);
    public static final BasicType<String> STRING = of(StringArgumentType::string, StringArgumentType::getString);
    public static final BasicType<String> GREEDY_STRING = of(StringArgumentType::greedyString, StringArgumentType::getString);
    public static final BasicType<Integer> INTEGER = of(IntegerArgumentType::integer, IntegerArgumentType::getInteger);
    public static final BasicType<Boolean> BOOLEAN = of(BoolArgumentType::bool, BoolArgumentType::getBool);
    public static final BasicType<Float> FLOAT = of(FloatArgumentType::floatArg, FloatArgumentType::getFloat);
    public static final BasicType<Double> DOUBLE = of(DoubleArgumentType::doubleArg, DoubleArgumentType::getDouble);
    public static final BasicType<Long> LONG = of(LongArgumentType::longArg, LongArgumentType::getLong);
//    public static final BasicType<Particle> PARTICLE = of(() -> ParticleArgument.particle(CONTEXT), (context, s) -> CraftParticle.convertLegacy(ParticleArgument.getParticle(Conversions.cast(context), s)));
    public static final BasicType<BlockData> BLOCK_STATE = of(() -> BlockStateArgument.block(CONTEXT), (context, s) -> Conversions.convertBlockInput(BlockStateArgument.getBlock(Conversions.cast(context), s)));

    private static final Dynamic2CommandExceptionType NOT_ENTITY_TYPE = new Dynamic2CommandExceptionType((one, two) ->
            new LiteralMessage(one + " is not of entity type " + two));

    public static final BasicType<ParticleOption> PARTICLE_BUILDER = of(() -> ParticleArgument.particle(CONTEXT), (context, s) -> new CraftyParticle(ParticleArgument.getParticle(Conversions.cast(context), s)));


    public static <T extends Entity> BasicType<T> entity(Class<T> entities) {
        return of(EntityArgument::entity, (context, s) -> {
            CommandContext<CommandSourceStack> cast = Conversions.cast(context);
            net.minecraft.world.entity.Entity entity = EntityArgument.getEntity(cast, s);

            if(!entities.isInstance(entity)) {
                String string = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString();
                throw NOT_ENTITY_TYPE.create(string, entities.getSimpleName());
            }
            return entities.cast(entity);
        });
    }

    public static <T extends Entity> BasicType<List<T>> entities(Class<T> clazz) {
        return of(EntityArgument::entities, (context, string) -> {
            CommandContext<CommandSourceStack> cast = Conversions.cast(context);
            Collection<? extends net.minecraft.world.entity.Entity> entity = EntityArgument.getEntities(cast, string);

            List<T> entitiesList = new ArrayList<>();

            for (net.minecraft.world.entity.Entity entity1 : entity) {
                if(!clazz.isInstance(entity1)) {
                    String id = BuiltInRegistries.ENTITY_TYPE.getKey(entity1.getType()).toString();
                    throw NOT_ENTITY_TYPE.create(id, clazz.getSimpleName());
                }
                entitiesList.add(clazz.cast(entity1));
            }
            return entitiesList;
        });
    }

    public static final Type<Sound> SOUND = new Type<>() {

        private static final DynamicCommandExceptionType ERROR_UNKNOWN_SOUND = new DynamicCommandExceptionType((object) -> new LiteralMessage("Unknown sound: " + object.toString()));

        @Override
        public Sound parse(CommandContext<BukkitBrigadierCommandSource> context, String name) throws CommandSyntaxException {
            ResourceLocation sound = ResourceLocationArgument.getId(Conversions.cast(context), name);
            SoundEvent soundEvent = BuiltInRegistries.SOUND_EVENT.get(sound);
            if (soundEvent == null) {
                throw ERROR_UNKNOWN_SOUND.create(sound);
            }
            return CraftSound.minecraftToBukkit(soundEvent);
        }

        @Override
        public RequiredArgumentBuilder<BukkitBrigadierCommandSource, ?> create(String name) {
            return com.unrealdinnerbone.prigadier.api.Commands.argument(name, ResourceLocationArgument.id())
                    .suggests(((context, builder) -> SuggestionProviders.AVAILABLE_SOUNDS.getSuggestions(Conversions.cast(context), builder)));
        }
    };



    public static final BasicType<OfflinePlayer> OFFLINE_PLAYER = of(GameProfileArgument::gameProfile, (context, s) -> {
        Collection<GameProfile> gameProfiles = GameProfileArgument.getGameProfiles(Conversions.cast(context), s);
        if(gameProfiles.size() != 1) {
            throw EntityArgument.ERROR_NOT_SINGLE_PLAYER.create();
        }else {
            return Conversions.convertOfflinePlayer(gameProfiles.stream().findFirst().get());
        }
    });

    public static final BasicType<List<OfflinePlayer>> OFFLINE_PLAYERS = of(GameProfileArgument::gameProfile, (context, s) -> Conversions.convertOfflinePlayers(GameProfileArgument.getGameProfiles(Conversions.cast(context), s)));



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

    private static final SimpleCommandExceptionType RATE_LIMITED = new SimpleCommandExceptionType(new LiteralMessage("Rate limited, try again later"));

    public static final BasicType<OfflinePlayer> OFFLINE_PLAYER_COMPLETED = of(GameProfileArgument::gameProfile, (context, s) -> {
        Collection<GameProfile> gameProfiles = GameProfileArgument.getGameProfiles(Conversions.cast(context), s);
        if(gameProfiles.size() != 1) {
            throw EntityArgument.ERROR_NOT_SINGLE_PLAYER.create();
        }else {
            OfflinePlayer offlinePlayer = Conversions.convertOfflinePlayer(gameProfiles.stream().findFirst().get());
            if(!offlinePlayer.getPlayerProfile().isComplete()) {
                if(!offlinePlayer.getPlayerProfile().completeFromCache(false, true)) {
                    throw RATE_LIMITED.create();
                }
            }
            return offlinePlayer;
        }
    });

    public static final BasicType<List<OfflinePlayer>> OFFLINE_PLAYERS_COMPLETED = of(GameProfileArgument::gameProfile, (context, s) -> {
        List<OfflinePlayer> offlinePlayers = Conversions.convertOfflinePlayers(GameProfileArgument.getGameProfiles(Conversions.cast(context), s));
        for (OfflinePlayer offlinePlayer : offlinePlayers) {
            if(!offlinePlayer.getPlayerProfile().isComplete()) {
                if(!offlinePlayer.getPlayerProfile().completeFromCache(false, true)) {
                    throw RATE_LIMITED.create();
                }
            }
        }
        return offlinePlayers;
    });

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

    private static <T> BasicType<T> of(Supplier<ArgumentType<?>> supplier, MapperFunction<CommandSyntaxException, T, CommandContext<BukkitBrigadierCommandSource>, String> function) {
        return new BasicType<>(supplier, function);
    }
}
