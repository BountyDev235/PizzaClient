// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.ai.pathfinding;

import qolskyblockmod.pizzaclient.features.macros.ai.pathfinding.pathfinder.AStarPathfinder;
import qolskyblockmod.pizzaclient.features.macros.ai.pathfinding.path.base.PathBase;
import qolskyblockmod.pizzaclient.features.macros.ai.pathfinding.path.Path;
import qolskyblockmod.pizzaclient.features.macros.ai.pathfinding.pathfinder.PathFinder;
import qolskyblockmod.pizzaclient.features.macros.ai.pathfinding.util.BetterBlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import qolskyblockmod.pizzaclient.util.RenderUtil;
import net.minecraftforge.common.MinecraftForge;
import qolskyblockmod.pizzaclient.features.macros.ai.pathfinding.pathfinder.BasePathfinder;
import net.minecraftforge.client.event.RenderWorldLastEvent;

public class Pathfinding
{
    public static Pathfinding instance;
    
    @SubscribeEvent
    public void onRenderWorldLast(final RenderWorldLastEvent event) {
        if (BasePathfinder.path == null || BasePathfinder.path.moves.size() == 0) {
            MinecraftForge.EVENT_BUS.unregister((Object)Pathfinding.instance);
            return;
        }
        RenderUtil.drawRainbowPath(BasePathfinder.path.moves, 2.0f);
    }
    
    public static void runPathfinder(final BetterBlockPos goal) {
        final PathFinder pathFinder;
        new Thread(() -> {
            new PathFinder(new Path(goal));
            pathFinder.run();
        }).start();
    }
    
    public static void runAStarPathfinder(final BetterBlockPos goal) {
        final AStarPathfinder aStarPathfinder;
        new Thread(() -> {
            new AStarPathfinder(new Path(goal));
            aStarPathfinder.run();
        }).start();
    }
    
    public static void unregister() {
        MinecraftForge.EVENT_BUS.unregister((Object)Pathfinding.instance);
    }
    
    public static void register() {
        MinecraftForge.EVENT_BUS.register((Object)Pathfinding.instance);
    }
}
