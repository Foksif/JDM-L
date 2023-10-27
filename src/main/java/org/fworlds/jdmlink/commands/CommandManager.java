package org.fworlds.jdmlink.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommandManager extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();
        Integer online = Bukkit.getOnlinePlayers().size();

        if (command.equals("online")) {
            List<Player> players = (List<Player>) Bukkit.getOnlinePlayers();
            List<String> names = players.stream().map(player -> player.getName()).collect(Collectors.toList());

            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(new Color(30, 255, 0));
            embed.addField("Количество человек:", String.valueOf(online) + "/40", false);
            embed.addField("Список игроков:", "```" + names + "```", false);
            embed.setTitle("Онлайн сервера");
            event.getChannel().sendMessageEmbeds(embed.build()).queue();
            embed.clear();

            event.getChannel().sendMessage("");

        }
    }


    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();

        commandData.add(Commands.slash("online", "Показать всех игроков кто в сети"));


        event.getGuild().updateCommands().addCommands(commandData).queue();
    }
}

