package org.fworlds.jdmlink.jda_listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.fworlds.jdmlink.Jdm_link;
import org.jetbrains.annotations.NotNull;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        if (event.getChannel().equals(Jdm_link.getJda().getTextChannelById(Jdm_link.MC_CHANNEL_ID))) {

            if (!event.getMember().getUser().isBot()) {
                String username = event.getMember().getEffectiveName();
                String message = event.getMessage().getContentRaw();

                if (event.getMessage().getContentRaw().equalsIgnoreCase("!hallo")) {
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',"&a" + username + " &eВсем привет!"));
                } else {
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "[&9DISCORD&f] " + username + ": " + message));
                }
            }
        }

    }
}
