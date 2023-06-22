/*
 * This file is part of ClientDetector - https://github.com/Sportkanone123/ClientDetector
 * Copyright (C) 2021 Sportkanone123
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.sportkanone123.clientdetector.spigot.command;

import de.sportkanone123.clientdetector.spigot.command.impl.Forge;
import de.sportkanone123.clientdetector.spigot.command.impl.Help;
import de.sportkanone123.clientdetector.spigot.command.impl.Player;
import de.sportkanone123.clientdetector.spigot.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("clientdetector")){
            if(sender instanceof org.bukkit.entity.Player){
                if(!sender.hasPermission("clientdetector.command")){
                    String prefix = ConfigManager.getConfig("message").getString("prefix");

                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&m-------&7" + prefix + "&7&m-------&7"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfig("message").getString("prefix") + " &7ClientDetector(" + Bukkit.getServer().getPluginManager().getPlugin("ClientDetector").getDescription().getVersion() + ") by Sportkanone123 & Loving11ish"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&m-------&7" + prefix + "&7&m-------&7"));

                    return false;
                }
            }

            if(args.length == 0){
                String prefix = ConfigManager.getConfig("message").getString("prefix");

                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&m-------&7" + prefix + "&7&m-------&7"));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfig("message").getString("prefix") + " &7ClientDetector(" + Bukkit.getServer().getPluginManager().getPlugin("ClientDetector").getDescription().getVersion() + ") by Sportkanone123 & Loving11ish"));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&m-------&7" + prefix + "&7&m-------&7"));
            }else{
                if(args[0].equalsIgnoreCase("help")) {
                    Help.handle(sender, command, label, args);
                }else if(args[0].equalsIgnoreCase("player")){
                    Player.handle(sender, command, label, args);
                }else if(args[0].equalsIgnoreCase("forge")){
                    Forge.handle(sender, command, label, args);
                }
            }
        }else if(command.getName().equalsIgnoreCase("client")){
            if(sender instanceof org.bukkit.entity.Player)
                if(!sender.hasPermission("clientdetector.command"))
                    return false;

            if(args.length == 1){
                String[] args_custom = new String[]{"player", "client", args[0]};
                Player.handle(sender, command, label, args_custom);
            }else{
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfig("message").getString("prefix") + " &cPlease use: /client <player>"));
            }
        }else if(command.getName().equalsIgnoreCase("forge")){
            if(sender instanceof org.bukkit.entity.Player)
                if(!sender.hasPermission("clientdetector.command"))
                    return false;

            if(args.length == 1){
                String[] args_custom = new String[]{"forge", args[0]};
                Forge.handle(sender, command, label, args_custom);
            }else{
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfig("message").getString("prefix") + " &cPlease use: /forge <player>"));
            }

        }else if(command.getName().equalsIgnoreCase("mods")){
            if(sender instanceof org.bukkit.entity.Player)
                if(!sender.hasPermission("clientdetector.command"))
                    return false;

            if(args.length == 1){
                String[] args_custom = new String[]{"player", "mods", args[0]};
                Player.handle(sender, command, label, args_custom);
            }else{
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigManager.getConfig("message").getString("prefix") + " &cPlease use: /mods <player>"));
            }
        }

        return false;
    }
}
