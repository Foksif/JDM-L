package org.fworlds.jdmlink.spigot_listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.fworlds.jdmlink.Jdm_link;

import java.awt.*;

public class PlayerLeave implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerQuitEvent(PlayerQuitEvent event) {
        TextChannel channel = Jdm_link.getJda().getTextChannelById(Jdm_link.MC_CHANNEL_ID);
        Player player = event.getPlayer();

        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(new Color(225, 87, 0));
        embed.setAuthor("[ - ] " + player.getName(), null, "https://crafatar.com/avatars/" + player.getUniqueId());
        channel.sendMessageEmbeds(embed.build()).queue();
    }


}
