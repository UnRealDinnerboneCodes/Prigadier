package com.unrealdinnerbone.prigadier;

import com.destroystokyo.paper.brigadier.BukkitBrigadierCommandSource;
import org.bukkit.entity.Player;

public class PrigadierHelpers
{
    public static boolean isPlayer(BukkitBrigadierCommandSource s) {
        return s.getBukkitSender() instanceof Player;
    }
}
