package de.dasshorty.whycord.api.generic;

import net.dv8tion.jda.api.events.interaction.GenericInteractionCreateEvent;

import java.util.List;

public interface GenericInteraction<E extends GenericInteractionCreateEvent> {

    String getId();

    void onEvent(E event, List<String> parsedData);

}
