package com.unrealdinnerbone.prigadier.api;

import com.destroystokyo.paper.profile.PlayerProfile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface CompletedProfile extends PlayerProfile {

    @Override
    @NotNull UUID getId();
}
