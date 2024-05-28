package de.dasshorty.whycord.api.commands;

import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

public interface CommandInteraction<T extends CommandDataImpl, E extends GenericCommandInteractionEvent> {

    T command();

    void onEvent(E event);

}
