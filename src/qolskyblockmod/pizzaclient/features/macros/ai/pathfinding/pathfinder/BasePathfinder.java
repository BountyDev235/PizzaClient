// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.ai.pathfinding.pathfinder;

import qolskyblockmod.pizzaclient.features.macros.ai.pathfinding.Pathfinding;
import net.minecraftforge.common.MinecraftForge;
import qolskyblockmod.pizzaclient.features.macros.ai.pathfinding.path.base.PathBase;
import java.util.List;

public abstract class BasePathfinder
{
    public static List<PathNode> nodes;
    public static PathBase path;
    
    public BasePathfinder(final PathBase path) {
        BasePathfinder.path = path;
        MinecraftForge.EVENT_BUS.unregister((Object)Pathfinding.instance);
        BasePathfinder.nodes = null;
    }
    
    public void run() {
        this.run(true);
    }
    
    public void runNewThread() {
        new Thread(this::run).start();
    }
    
    public void shutdown() {
        BasePathfinder.path = null;
        Pathfinding.unregister();
    }
    
    public abstract boolean run(final boolean p0);
}
