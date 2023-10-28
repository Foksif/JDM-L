package org.fworlds.jdmlink.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

public class ConsoleSend {
    public void sendConsole(String command) {
        ConsoleCommandSender conole = Bukkit.getServer().getConsoleSender();
        Bukkit.dispatchCommand(conole, command);
    }

}
