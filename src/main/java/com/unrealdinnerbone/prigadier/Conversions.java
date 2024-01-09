package com.unrealdinnerbone.prigadier;

import com.destroystokyo.paper.brigadier.BukkitBrigadierCommandSource;
import com.destroystokyo.paper.profile.CraftPlayerProfile;
import com.destroystokyo.paper.profile.PlayerProfile;
import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.papermc.paper.adventure.PaperAdventure;
import io.papermc.paper.math.Position;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.blocks.BlockInput;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.commands.arguments.item.ItemInput;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import org.bukkit.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.ApiStatus;
import org.joml.Vector3f;

import java.util.Collection;
import java.util.List;

@ApiStatus.Internal
public class Conversions
{
    public static BlockData convertBlockInput(BlockInput block) {
        return block.getState().createCraftBlockData();
    }

    public static Team convertTeam(PlayerTeam team) {
        return Bukkit.getScoreboardManager().getMainScoreboard().getTeam(team.getName());
    }

    public static Objective convertObjective(net.minecraft.world.scores.Objective objective) {
        return Bukkit.getScoreboardManager().getMainScoreboard().getObjective(objective.getName());
    }

    public static Position covnertPosition(CommandContext<BukkitBrigadierCommandSource> context, String s) {
        CommandContext<CommandSourceStack> newContext = cast(context);
        BlockPos blockPos = BlockPosArgument.getBlockPos(newContext, s);
        return Position.fine(blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public static NamespacedKey convertNamespacedKey(ResourceLocation rl) {
        return new NamespacedKey(rl.getNamespace(), rl.getPath());
    }
    public static DisplaySlot convertSlot(net.minecraft.world.scores.DisplaySlot slot) {
        return DisplaySlot.NAMES.value(slot.getSerializedName());
    }

    public static ItemStack convertItemStack(ItemInput itemInput) throws CommandSyntaxException {
        return itemInput.createItemStack(1, true).asBukkitMirror();
    }
    public static Entity convertEntity(net.minecraft.world.entity.Entity entity) {
        return entity.getBukkitEntity();
    }

    public static List<Entity> convertEntities(Collection<? extends net.minecraft.world.entity.Entity> entity) {
        return entity.stream().map(Conversions::convertEntity).toList();
    }

    public static Player convertPlayer(ServerPlayer entity) {
        return entity.getBukkitEntity();
    }

    public static List<Player> convertPlayers(Collection<? extends ServerPlayer> entity) {
        return entity.stream().map(Conversions::convertPlayer).toList();
    }

    public static NamedTextColor convertNamedTextColor(ChatFormatting formatting) {
        return NamedTextColor.nearestTo(PaperAdventure.asAdventure(formatting));
    }

    public static Component convertComponent(net.minecraft.network.chat.Component component) {
        return PaperAdventure.asAdventure(component);
    }

    public static PlayerProfile convertGameProfile(GameProfile gameProfile) {
        return CraftPlayerProfile.asBukkitMirror(gameProfile);
    }

    public static List<PlayerProfile> convertGameProfiles(Collection<com.mojang.authlib.GameProfile> gameProfiles) {
        return gameProfiles.stream().map(Conversions::convertGameProfile).toList();
    }

    public static OfflinePlayer convertOfflinePlayer(com.mojang.authlib.GameProfile gameProfile) {
        return Bukkit.getOfflinePlayer(gameProfile.getId());
    }

    public static List<OfflinePlayer> convertOfflinePlayers(Collection<com.mojang.authlib.GameProfile> gameProfiles) {
        return gameProfiles.stream().map(Conversions::convertOfflinePlayer).toList();
    }

    public static CommandContext<CommandSourceStack> cast(CommandContext<BukkitBrigadierCommandSource> stack) {
        return (CommandContext<CommandSourceStack>) (Object) stack;
    }

    public static Color fromVec3(Vector3f vec3) {
        return Color.fromRGB((int) (vec3.x() * 255), (int) (vec3.y() * 255), (int) (vec3.z() * 255));
    }

    public static Criteria convertCriteria(ObjectiveCriteria criteria) {
        return Criteria.create(criteria.getName());
    }
}
