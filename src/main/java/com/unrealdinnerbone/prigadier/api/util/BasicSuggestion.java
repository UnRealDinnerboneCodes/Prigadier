package com.unrealdinnerbone.prigadier.api.util;

import com.unrealdinnerbone.prigadier.api.ISuggestionEntry;
import net.kyori.adventure.text.Component;

public record BasicSuggestion(String name, Component tooltip) implements ISuggestionEntry {}