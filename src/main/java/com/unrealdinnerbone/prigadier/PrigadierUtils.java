package com.unrealdinnerbone.prigadier;

import com.destroystokyo.paper.brigadier.BukkitBrigadierCommandSource;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.unrealdinnerbone.prigadier.Conversions;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class PrigadierUtils {

    public static Player assertPlayer(CommandContext<BukkitBrigadierCommandSource> context) throws CommandSyntaxException {
        return Conversions.cast(context).getSource().getPlayerOrException().getBukkitEntity().getPlayer();
    }

}
