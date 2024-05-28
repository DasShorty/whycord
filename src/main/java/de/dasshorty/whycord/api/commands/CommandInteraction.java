package de.dasshorty.whycord.api;

import net.dv8tion.jda.internal.interactions.CommandDataImpl;

public interface DiscordInteraction<T extends CommandDataImpl, E extends > {

    T command();
    void onEvent(E event);

}
