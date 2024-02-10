package com.unrealdinnerbone.prigadier.impl.args;

import com.destroystokyo.paper.brigadier.BukkitBrigadierCommandSource;
import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.arguments.*;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.unrealdinnerbone.crafty.api.CompletedProfile;
import com.unrealdinnerbone.crafty.api.ParticleOption;
import com.unrealdinnerbone.crafty.nms.CraftyParticle;
import com.unrealdinnerbone.prigadier.Conversions;
import com.unrealdinnerbone.prigadier.api.Arguments;
import com.unrealdinnerbone.prigadier.api.SuggestionHelper;
import com.unrealdinnerbone.prigadier.api.util.BasicType;
import com.unrealdinnerbone.prigadier.api.util.OneOrMany;
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
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.world.entity.animal.CatVariant;
import net.minecraft.world.scores.ScoreHolder;
import org.bukkit.*;
import org.bukkit.advancement.Advancement;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.block.banner.PatternType;
import org.bukkit.block.data.BlockData;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.craftbukkit.v1_20_R3.*;
import org.bukkit.craftbukkit.v1_20_R3.attribute.CraftAttribute;
import org.bukkit.craftbukkit.v1_20_R3.block.CraftBiome;
import org.bukkit.craftbukkit.v1_20_R3.enchantments.CraftEnchantment;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftEntityType;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftFrog;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftVillager;
import org.bukkit.craftbukkit.v1_20_R3.entity.memory.CraftMemoryKey;
import org.bukkit.craftbukkit.v1_20_R3.generator.structure.CraftStructureType;
import org.bukkit.craftbukkit.v1_20_R3.inventory.trim.CraftTrimMaterial;
import org.bukkit.craftbukkit.v1_20_R3.inventory.trim.CraftTrimPattern;
import org.bukkit.craftbukkit.v1_20_R3.potion.CraftPotionEffectType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.generator.structure.StructureType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.loot.LootTables;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.ApiStatus;

import java.util.*;

import static com.unrealdinnerbone.prigadier.api.util.BasicType.of;

@ApiStatus.Internal
public class PrigadierArguments {

    public static final CommandBuildContext CONTEXT = Commands.createValidationContext(VanillaRegistries.createLookup());

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

    public static final BasicType<Criteria> CRITERIA = of(ObjectiveCriteriaArgument::criteria, (context, s) -> Conversions.convertCriteria(ObjectiveCriteriaArgument.getCriteria(Conversions.cast(context), s)));

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
    public static final BasicType<BlockData> BLOCK_STATE = of(() -> BlockStateArgument.block(CONTEXT), (context, s) -> Conversions.convertBlockInput(BlockStateArgument.getBlock(Conversions.cast(context), s)));

    public static final BasicType<Component> MINI_MESSAGE = of(StringArgumentType::string, (context, s) -> Conversions.convertMiniMessage(StringArgumentType.getString(context, s)));

    public static class Registries {

        public static Type<Advancement> ADVANCEMENT = MinecraftRegistryType.of("advancement", Registry.ADVANCEMENT);
        public static OneOrMany<Art> ART = MTBType.ofRegistry(BuiltInRegistries.PAINTING_VARIANT, Registry.ART, CraftArt::minecraftToBukkit);
        public static OneOrMany<Attribute> ATTRIBUTE = MTBType.ofRegistry(BuiltInRegistries.ATTRIBUTE, Registry.ATTRIBUTE, CraftAttribute::minecraftToBukkit);
        public static OneOrMany<PatternType> BANNER_PATTERN = MTBType.ofRegistry(BuiltInRegistries.BANNER_PATTERN, Registry.BANNER_PATTERN, Conversions::convertPatternType);
        public static Type<Biome> BIOME = MinecraftRegistryType.of("biome", Registry.BIOME);
        public static Type<KeyedBossBar> BOSS_BAR = MinecraftRegistryType.of("boss_bar", Registry.BOSS_BARS);
        public static OneOrMany<Cat.Type> CAT_VARIANT = MTBType.ofRegistry(BuiltInRegistries.CAT_VARIANT, Registry.CAT_VARIANT, Conversions::convertCatVariant);
        public static OneOrMany<Enchantment> ENCHANTMENT = MTBType.ofRegistry(BuiltInRegistries.ENCHANTMENT, Registry.ENCHANTMENT, CraftEnchantment::minecraftToBukkit);


        public static OneOrMany<EntityType> ENTITY_TYPE = MTBType.ofRegistry(BuiltInRegistries.ENTITY_TYPE, Registry.ENTITY_TYPE, CraftEntityType::minecraftToBukkit);
        public static OneOrMany<MusicInstrument> INSTRUMENT = MTBType.ofRegistry(BuiltInRegistries.INSTRUMENT, Registry.INSTRUMENT, CraftMusicInstrument::minecraftToBukkit);
        public static OneOrMany<PotionEffectType> EFFECT = MTBType.ofRegistry(BuiltInRegistries.MOB_EFFECT, Registry.POTION_EFFECT_TYPE, CraftPotionEffectType::minecraftToBukkit);
        public static OneOrMany<StructureType> STRUCTURE = MTBType.ofRegistry(BuiltInRegistries.STRUCTURE_TYPE, Registry.STRUCTURE_TYPE, CraftStructureType::minecraftToBukkit);
        public static OneOrMany<Sound> SOUND = MTBType.ofRegistry(BuiltInRegistries.SOUND_EVENT, Registry.SOUNDS, CraftSound::minecraftToBukkit);
        public static OneOrMany<Villager.Profession> VILLAGER_PROFESSION = MTBType.ofRegistry(BuiltInRegistries.VILLAGER_PROFESSION, Registry.VILLAGER_PROFESSION, CraftVillager.CraftProfession::minecraftToBukkit);
        public static OneOrMany<Villager.Type> VILLAGER_TYPE = MTBType.ofRegistry(BuiltInRegistries.VILLAGER_TYPE, Registry.VILLAGER_TYPE, Conversions::convertVillagerType);
        public static OneOrMany<MemoryKey> MEMORY_KEY = MTBType.ofRegistry(BuiltInRegistries.MEMORY_MODULE_TYPE, Registry.MEMORY_MODULE_TYPE, CraftMemoryKey::minecraftToBukkit);
        public static OneOrMany<Fluid> FLUID = MTBType.ofRegistry(BuiltInRegistries.FLUID, Registry.FLUID, CraftFluid::minecraftToBukkit);
        public static OneOrMany<Frog.Variant> FROG_VARIANT = MTBType.ofRegistry(BuiltInRegistries.FROG_VARIANT, Registry.FROG_VARIANT, Conversions::convertFrogVariant);
        public static OneOrMany<GameEvent> GAME_EVENT = MTBType.ofRegistry(BuiltInRegistries.GAME_EVENT, Registry.GAME_EVENT, CraftGameEvent::minecraftToBukkit);




        public static Type<LootTables> LOOT_TABLE = MinecraftRegistryType.of("loot_table", Registry.LOOT_TABLES);
        public static Type<Material> MATERIAL = MinecraftRegistryType.of("material", Registry.MATERIAL);
        public static Type<Particle> PARTICLE = MinecraftRegistryType.of("particle", Registry.PARTICLE_TYPE);
        public static Type<PotionType> POTION = MinecraftRegistryType.of("potion", Registry.POTION);
        public static Type<Statistic> STATISTIC = MinecraftRegistryType.of("statistic", Registry.STATISTIC);
        public static Type<TrimMaterial> TRIM_MATERIAL = MinecraftRegistryType.of("trim_material", Registry.TRIM_MATERIAL);
        public static Type<PotionEffectType> POTION_EFFECT_TYPE = MinecraftRegistryType.of("potion_effect_type", Registry.POTION_EFFECT_TYPE);


    }



    private static final DynamicCommandExceptionType INVALID_ENUM = new DynamicCommandExceptionType((object) -> new LiteralMessage("Invalid Value: " + object.toString()));


    public static <E extends Enum<E>> Type<E> createEnum(Class<E> eClass) {
        E[] enumConstants = eClass.getEnumConstants();
        return new Type<>() {
            @Override
            public E parse(CommandContext<BukkitBrigadierCommandSource> commandContext, String s) throws CommandSyntaxException {
                String string = StringArgumentType.getString(commandContext, s);
                return Arrays.stream(enumConstants).filter(e -> e.name().equalsIgnoreCase(string)).findFirst().orElseThrow(() -> INVALID_ENUM.create(string));
            }

            @Override
            public RequiredArgumentBuilder<BukkitBrigadierCommandSource, ?> create(String s) {
                return Arguments.word().create(s).suggests((context, builder) -> SuggestionHelper.strings(builder, Arrays.stream(enumConstants).map(Enum::name).toList()));
            }
        };
    }

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

    public static final BasicType<OfflinePlayer> OFFLINE_PLAYER = of(GameProfileArgument::gameProfile, (context, s) -> {
        Collection<GameProfile> gameProfiles = GameProfileArgument.getGameProfiles(Conversions.cast(context), s);
        if(gameProfiles.size() != 1) {
            throw EntityArgument.ERROR_NOT_SINGLE_PLAYER.create();
        }else {
            return Conversions.convertOfflinePlayer(gameProfiles.stream().findFirst().get());
        }
    });

    public static final BasicType<List<OfflinePlayer>> OFFLINE_PLAYERS = of(GameProfileArgument::gameProfile, (context, s) -> Conversions.convertOfflinePlayers(GameProfileArgument.getGameProfiles(Conversions.cast(context), s)));


    public static final BasicType<CompletedProfile> GAME_PROFILE = of(GameProfileArgument::gameProfile, (context, s) -> {
        Collection<GameProfile> gameProfiles = GameProfileArgument.getGameProfiles(Conversions.cast(context), s);
        if(gameProfiles.size() != 1) {
            throw EntityArgument.ERROR_NOT_SINGLE_PLAYER.create();
        }else {
            return Conversions.convertGameProfile(gameProfiles.stream().findFirst().get());
        }
    });

    public static final BasicType<List<CompletedProfile>> GAME_PROFILES = of(GameProfileArgument::gameProfile, (context, s) -> Conversions.convertGameProfiles(GameProfileArgument.getGameProfiles(Conversions.cast(context), s)));



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
                            return SuggestionHelper.strings(builder, list);
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


}
