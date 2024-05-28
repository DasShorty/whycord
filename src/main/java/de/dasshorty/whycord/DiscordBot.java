package de.dasshorty.whycord;

import de.dasshorty.whycord.api.commands.CommandInteractionManager;
import de.dasshorty.whycord.api.generic.GenericInteractionManager;
import de.dasshorty.whycord.translation.TranslationManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.springframework.stereotype.Service;

@Service
public class DiscordBot {

    private final TranslationManager translations;
    private final JDABuilder builder;
    private final JDA jda;

    public TranslationManager getTranslations() {
        return translations;
    }

    public DiscordBot() {

        this.translations = new TranslationManager();

        this.builder = JDABuilder.createDefault(System.getenv("BOT_TOKEN"));

        CommandInteractionManager commandManager = new CommandInteractionManager();
        GenericInteractionManager interactionManager = new GenericInteractionManager();
        this.builder.addEventListeners(commandManager, interactionManager);

        this.builder.setActivity(Activity.of(Activity.ActivityType.COMPETING, "dasshorty.de"));

        try {
            this.jda = this.builder.setAutoReconnect(true)
                    .build().awaitReady();

//          for guild only commands
            commandManager.updateCommandsToGuild(this.jda.getGuilds().getFirst());

//            Use this if you want global commands
//            commandManager.updateCommandsToDiscord(this.jda);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public JDA getJda() {
        return jda;
    }

    public JDABuilder getBuilder() {
        return builder;
    }
}
