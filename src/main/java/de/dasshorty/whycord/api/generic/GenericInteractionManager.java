package de.dasshorty.whycord.api.generic;

import net.dv8tion.jda.api.events.interaction.GenericInteractionCreateEvent;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.EntitySelectInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericInteractionManager extends ListenerAdapter {

    private final List<GenericInteraction<GenericInteractionCreateEvent>> genericInteractions = new ArrayList<>();

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        this.processInteraction(event.getButton().getId(), event);
    }

    private <E extends GenericInteractionCreateEvent> void processInteraction(String interactionId, E event) {

        if (interactionId == null) {
            throw new IllegalStateException("interaction id is null | interaction id is **not reachable**!");
        }

        List<String> data = new ArrayList<>();

        String[] split = interactionId.split("-");
        final String finalId = split[0];

        if (split.length != 1) {
            data.addAll(Arrays.stream(split[1].split(",")).toList());
        }

        GenericInteraction<GenericInteractionCreateEvent> first = this.genericInteractions.stream()
                .filter(interaction -> interaction.getId().equals(finalId)).toList().getFirst();

        first.onEvent(event, data);

    }

    @Override
    public void onStringSelectInteraction(StringSelectInteractionEvent event) {
        this.processInteraction(event.getInteraction().getId(), event);
    }

    @Override
    public void onEntitySelectInteraction(EntitySelectInteractionEvent event) {
        this.processInteraction(event.getInteraction().getId(), event);
    }

    @Override
    public void onModalInteraction(ModalInteractionEvent event) {
        this.processInteraction(event.getModalId(), event);
    }
}
