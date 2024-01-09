package com.unrealdinnerbone.prigadier;

import com.destroystokyo.paper.profile.CraftPlayerProfile;
import com.destroystokyo.paper.profile.PlayerProfile;
import com.mojang.authlib.GameProfile;
import com.unrealdinnerbone.prigadier.api.CompletedProfile;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class CraftyPlayerProfile extends CraftPlayerProfile implements CompletedProfile {

    public CraftyPlayerProfile(GameProfile profile) {
        super(profile);
        if(profile.getId() == null) {
            throw new IllegalArgumentException("GameProfile must have a valid UUID");
        }
    }

    @SuppressWarnings("DataFlowIssue")
    @NotNull
    @Override
    public UUID getId() {
        return super.getId();
    }
}
