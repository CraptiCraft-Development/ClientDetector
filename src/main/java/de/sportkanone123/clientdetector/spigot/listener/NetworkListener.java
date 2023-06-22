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

package de.sportkanone123.clientdetector.spigot.listener;

import com.github.retrooper.packetevents.event.PacketListenerPriority;
import com.github.retrooper.packetevents.event.SimplePacketListenerAbstract;
import com.github.retrooper.packetevents.event.simple.PacketLoginReceiveEvent;
import com.github.retrooper.packetevents.event.simple.PacketPlayReceiveEvent;
import de.sportkanone123.clientdetector.spigot.packet.PacketProcessor;

public class NetworkListener extends SimplePacketListenerAbstract {

    public NetworkListener() {
        super(PacketListenerPriority.HIGH);
    }


    @Override
    public void onPacketPlayReceive(PacketPlayReceiveEvent event) {
        PacketProcessor.handlePacket(event);
    }

    @Override
    public void onPacketLoginReceive(PacketLoginReceiveEvent event) {
        PacketProcessor.handleLoginPacket(event);
    }
}
