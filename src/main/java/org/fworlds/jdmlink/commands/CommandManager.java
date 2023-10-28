package org.fworlds.jdmlink.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.fworlds.jdmlink.Jdm_link;
import org.jetbrains.annotations.NotNull;
import net.dv8tion.jda.api.entities.Member;

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

        } else if (command.equals("console")) {
            Member member = event.getMember();
            OptionMapping optionConsole = event.getOption("command");
            String cmdConsole = optionConsole.getAsString();

            if (member.hasPermission(Permission.ADMINISTRATOR)) {
                event.reply("Комманда успешно отправилась!").setEphemeral(true).queue();

                Bukkit.getScheduler().runTask(Jdm_link.getInstance(), () -> {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmdConsole);
                });

            } else {
                event.reply("У вас нет прав!").setEphemeral(true).queue();
            }
        }
    }


    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();

        commandData.add(Commands.slash("online", "Показать всех игроков кто в сети"));

        OptionData optionCommand = new OptionData(OptionType.STRING, "command", "Команда которую вы хотите отправить в консоль!", true);
        commandData.add(Commands.slash("console", "Выполнить команду от имени консоли").addOptions(optionCommand));


        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

}

