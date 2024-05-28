package de.dasshorty.whycord.api.commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

import java.util.ArrayList;
import java.util.List;

public class CommandInteractionManager extends ListenerAdapter {

    private final List<CommandInteraction<CommandDataImpl, GenericCommandInteractionEvent>> interactionList = new ArrayList<>();

    public void updateCommandsToGuild(Guild guild) {
        guild.updateCommands().addCommands(this.interactionList.stream().map(CommandInteraction::command).toList()).queue();
    }

    public void updateCommandsToDiscord(JDA jda) {
        jda.updateCommands().addCommands(this.interactionList.stream().map(CommandInteraction::command).toList()).queue();
    }

    @SuppressWarnings("unchecked")
    public void addInteraction(CommandInteraction<?, ?> interaction) {
        this.interactionList.add((CommandInteraction<CommandDataImpl, GenericCommandInteractionEvent>) interaction);
    }

    private void processCommand(GenericCommandInteractionEvent event) {
        String commandName = event.getName();
        CommandInteraction<CommandDataImpl, GenericCommandInteractionEvent> first = this.interactionList.stream()
                .filter(interaction -> interaction.command().getName().equals(commandName)).toList().getFirst();
        first.onEvent(event);
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        this.processCommand(event);
    }

    @Override
    public void onUserContextInteraction(UserContextInteractionEvent event) {
        this.processCommand(event);
    }

    @Override
    public void onMessageContextInteraction(MessageContextInteractionEvent event) {
        this.processCommand(event);
    }
}
