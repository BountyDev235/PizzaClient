// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.ai.pathfinding.pathfinder;

import java.util.List;
import java.util.Comparator;
import qolskyblockmod.pizzaclient.features.macros.ai.pathfinding.util.NodeCostComparator;
import net.minecraft.util.EnumChatFormatting;
import qolskyblockmod.pizzaclient.features.macros.ai.pathfinding.Pathfinding;
import net.minecraftforge.common.MinecraftForge;
import qolskyblockmod.pizzaclient.util.MathUtil;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Collections;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.features.macros.ai.pathfinding.path.base.PathBase;

public class PathFinderNoMovement extends BasePathfinder
{
    public PathFinderNoMovement(final PathBase path) {
        super(path);
    }
    
    @Override
    public boolean run(final boolean messages) {
        if (PathFinderNoMovement.path == null) {
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Path returned null. Please report this."));
            return false;
        }
        try {
            if (PathFinderNoMovement.path.currentPos.equals(PathFinderNoMovement.path.goalPos)) {
                this.shutdown();
                return true;
            }
            PathFinderNoMovement.nodes = new ArrayList<PathNode>(Collections.singletonList(new PathNode()));
            while (!PathFinderNoMovement.path.finished) {
                final List<PathNode> newNodes = new ArrayList<PathNode>(PathFinderNoMovement.nodes);
                for (int i = 0; i < MathUtil.min(newNodes.size(), 4); ++i) {
                    final PathNode node = newNodes.get(i);
                    PathFinderNoMovement.nodes.remove(node);
                    if (PathFinderNoMovement.path.addBlock(node)) {
                        MinecraftForge.EVENT_BUS.register((Object)Pathfinding.instance);
                        break;
                    }
                }
                if (PathFinderNoMovement.nodes.size() == 0) {
                    if (messages) {
                        PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > " + EnumChatFormatting.RED + "Failed to find a path."));
                    }
                    this.shutdown();
                    return false;
                }
                PathFinderNoMovement.nodes.sort(new NodeCostComparator());
                PathFinderNoMovement.path.onTick();
            }
            if (messages) {
                PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > " + EnumChatFormatting.GREEN + "Found Path!"));
            }
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > " + EnumChatFormatting.RED + "PizzaClient caught an logged an exception when calculating the path. Please report this."));
            return false;
        }
    }
    
    public PathBase calculateAndGetPath() {
        if (this.run(false)) {
            return PathFinderNoMovement.path;
        }
        return null;
    }
    
    public PathBase calculateAndGetPath(final boolean messages) {
        if (this.run(messages)) {
            return PathFinderNoMovement.path;
        }
        return null;
    }
    
    @Override
    public void shutdown() {
        Pathfinding.unregister();
    }
}
