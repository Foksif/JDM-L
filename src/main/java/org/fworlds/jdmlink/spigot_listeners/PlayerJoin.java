package org.fworlds.jdmlink.spigot_listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.fworlds.jdmlink.Jdm_link;

import java.awt.*;

public class PlayerJoin implements Listener {

    @EventHandler (priority = EventPriority.HIGH)
    public void onPlayerJoin (PlayerJoinEvent event) {
        TextChannel channel = Jdm_link.getJda().getTextChannelById(Jdm_link.MC_CHANNEL_ID);
        Player player = event.getPlayer();

        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(new Color(209, 255, 66));
        embed.setAuthor("[ + ] " + player.getName(), null, "https://crafatar.com/avatars/" + player.getUniqueId());
        channel.sendMessageEmbeds(embed.build()).queue();
    }
}
