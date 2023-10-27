package org.fworlds.jdmlink.spigot_listeners;

import jdk.tools.jmod.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.fworlds.jdmlink.Jdm_link;

import java.awt.*;
import java.util.Random;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {

        Player player = e.getPlayer();

//        Random color embed
        Random random = new Random();
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        Color randomColor = new Color(r, g, b);
//        Create embed

        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(new Color(147, 147, 147));
        embed.setAuthor(player.getName(), null, "https://crafatar.com/avatars/" + player.getUniqueId());
        embed.setDescription(e.getMessage());

        TextChannel channel = Jdm_link.getJda().getTextChannelById(Jdm_link.MC_CHANNEL_ID);
        channel.sendMessageEmbeds(embed.build()).queue();
    }

}
