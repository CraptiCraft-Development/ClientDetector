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

package de.sportkanone123.clientdetector.spigot.mod.processor;

import com.tcoded.folialib.FoliaLib;
import de.sportkanone123.clientdetector.spigot.ClientDetector;
import de.sportkanone123.clientdetector.spigot.api.events.ModDetectedEvent;
import de.sportkanone123.clientdetector.spigot.manager.AlertsManager;
import de.sportkanone123.clientdetector.spigot.manager.ModManager;
import de.sportkanone123.clientdetector.spigot.mod.Mod;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class PluginMessageProcessor {

    private static FoliaLib foliaLib = ClientDetector.getFoliaLib();

    public static void handlePluginMessage(Player player, String channel, byte[] data){
        byte[] customData = new String(data, StandardCharsets.UTF_8).replace("(Velocity)", "").getBytes(StandardCharsets.UTF_8);

        if(ClientDetector.plugin.getConfig().getBoolean("mods.enableModDetection")){
            for(Mod mod : ClientDetector.MODS){
                if(mod.isMod(channel, customData)){
                    if(ClientDetector.playerMods.get(player.getUniqueId()) == null)
                        ClientDetector.playerMods.put(player.getUniqueId(), new ArrayList<String>());

                    ClientDetector.playerMods.get(player.getUniqueId()).add(mod.getModName());

                    foliaLib.getImpl().runAsync(() -> {
                        Bukkit.getPluginManager().callEvent(new ModDetectedEvent(true, player, mod.getModName()));
                    });

                    AlertsManager.handleModlistDetection(player, mod.getModName());

                    ModManager.handleDetection(player, mod.getModName());
                }
            }
        }
    }
}
