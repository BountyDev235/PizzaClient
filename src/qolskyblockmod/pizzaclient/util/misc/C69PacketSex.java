// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.misc;

import net.minecraft.network.INetHandler;
import java.io.IOException;
import net.minecraft.network.PacketBuffer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.network.Packet;

public class C69PacketSex implements Packet<INetHandlerPlayServer>
{
    protected boolean cum;
    protected EntityPlayer sender;
    protected EntityPlayer receiver;
    
    public C69PacketSex(final EntityPlayer senderIn, final EntityPlayer receiverIn, final boolean cum) {
        this.sender = senderIn;
        this.receiver = receiverIn;
        this.cum = cum;
    }
    
    public void func_148837_a(final PacketBuffer buf) throws IOException {
    }
    
    public void func_148840_b(final PacketBuffer buf) throws IOException {
    }
    
    public void processPacket(final INetHandlerPlayServer handler) {
    }
}
