// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.core.events;

import net.minecraft.network.Packet;
import net.minecraftforge.fml.common.eventhandler.Event;

public class PacketEvent extends Event
{
    public final Packet<?> packet;
    
    public PacketEvent(final Packet<?> packet) {
        this.packet = packet;
    }
    
    public static class ReceiveEvent extends PacketEvent
    {
        public ReceiveEvent(final Packet<?> packet) {
            super(packet);
        }
    }
    
    public static class SendEvent extends PacketEvent
    {
        public SendEvent(final Packet<?> packet) {
            super(packet);
        }
    }
}
