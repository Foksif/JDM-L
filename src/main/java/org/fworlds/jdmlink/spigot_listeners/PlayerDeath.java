package org.fworlds.jdmlink.spigot_listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.fworlds.jdmlink.Jdm_link;

import java.awt.*;

public class PlayerDeath implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getPlayer();
        TextChannel channel = Jdm_link.getJda().getTextChannelById(Jdm_link.MC_CHANNEL_ID);

        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(new Color(225, 87, 0));
        embed.setAuthor("Игрок " + player.getName() + " умер.", null, "https://crafatar.com/avatars/" + player.getUniqueId());
        channel.sendMessageEmbeds(embed.build()).queue();

    }
}
