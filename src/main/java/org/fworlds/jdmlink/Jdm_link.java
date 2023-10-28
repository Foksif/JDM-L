package org.fworlds.jdmlink;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.fworlds.jdmlink.commands.CommandManager;
import org.fworlds.jdmlink.jda_listeners.MessageListener;
import org.fworlds.jdmlink.spigot_listeners.ChatListener;
import org.fworlds.jdmlink.spigot_listeners.PlayerDeath;
import org.fworlds.jdmlink.spigot_listeners.PlayerJoin;
import org.fworlds.jdmlink.spigot_listeners.PlayerLeave;

import java.awt.*;
import java.io.Console;
import java.util.Timer;
import java.util.TimerTask;

public final class Jdm_link extends JavaPlugin {

    public static long MC_CHANNEL_ID = 1166361244857544724L;
    public static long GUILD_ID = 1157195772115292252L;
    public static JDA jda;

    private static Jdm_link instance;

    @Override
    public void onEnable() {
        instance = this;

        loadConfig();

        try {
            jda = JDABuilder.createDefault(getConfig().getString("BOT-TOKEN"))
                    .setStatus(OnlineStatus.ONLINE)
                    .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT)
                    .addEventListeners(new MessageListener(), new CommandManager())
                    .build().awaitReady();


            Bukkit.getScheduler().runTaskTimer(this, () -> {
                Integer online = Bukkit.getOnlinePlayers().size();
                jda.getPresence().setActivity(Activity.watching("Онлайн " + online + "."));
            }, 0L, 20*60*1L);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeave(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);

        sendStartEmbed();
        System.out.println("[JDM] Плагин успешно загрузился!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        sendStopEmbed();
    }

    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    private void sendStartEmbed() {
        TextChannel channel = jda.getTextChannelById(MC_CHANNEL_ID);

        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(new Color(30, 255, 0));
        embed.setTitle("Сервер успешно запустился!");
        channel.sendMessageEmbeds(embed.build()).queue();
    }


    private void sendStopEmbed() {
        TextChannel channel = jda.getTextChannelById(MC_CHANNEL_ID);

        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(new Color(255, 0, 0));
        embed.setTitle("Сервер остановлен!");
        channel.sendMessageEmbeds(embed.build()).queue();

    }

    public static void sendConsole(String command) {
        ConsoleCommandSender conole = Bukkit.getServer().getConsoleSender();
        Bukkit.dispatchCommand(conole, command);
    }

    public static Jdm_link getInstance() {
        return instance;
    }

    public static JDA getJda() {
        return jda;
    }
}
