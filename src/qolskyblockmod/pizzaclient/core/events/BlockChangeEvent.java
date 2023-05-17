// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.core.events;

import net.minecraft.util.BlockPos;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.common.eventhandler.Event;

public class BlockChangeEvent extends Event
{
    public final IBlockState oldState;
    public final IBlockState currentState;
    public final BlockPos pos;
    
    public BlockChangeEvent(final IBlockState oldState, final IBlockState currentState, final BlockPos pos) {
        this.currentState = currentState;
        this.oldState = oldState;
        this.pos = pos;
    }
}
