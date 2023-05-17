// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.ai.pathfinding.pathfinder;

import java.util.List;
import qolskyblockmod.pizzaclient.util.PlayerUtil;
import qolskyblockmod.pizzaclient.features.macros.ai.movement.Movement;
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

public final class AStarPathfinder extends BasePathfinder
{
    public AStarPathfinder(final PathBase path) {
        super(path);
    }
    
    @Override
    public boolean run(final boolean messages) {
        if (AStarPathfinder.path == null) {
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.ERROR_MESSAGE + "Path returned null. Please report this."));
            return false;
        }
        if (AStarPathfinder.path.currentPos.equals(AStarPathfinder.path.goalPos)) {
            this.shutdown();
            return true;
        }
        try {
            AStarPathfinder.nodes = new ArrayList<PathNode>(Collections.singletonList(new PathNode()));
            while (!AStarPathfinder.path.finished) {
                final List<PathNode> newNodes = new ArrayList<PathNode>(AStarPathfinder.nodes);
                for (int i = 0; i < MathUtil.min(newNodes.size(), 4); ++i) {
                    final PathNode node = newNodes.get(i);
                    AStarPathfinder.nodes.remove(node);
                    if (AStarPathfinder.path.addBlock(node)) {
                        MinecraftForge.EVENT_BUS.register((Object)Pathfinding.instance);
                        break;
                    }
                }
                if (AStarPathfinder.nodes.size() == 0) {
                    if (messages) {
                        PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > " + EnumChatFormatting.RED + "Failed to find a path."));
                    }
                    this.shutdown();
                    return false;
                }
                AStarPathfinder.nodes.sort(new NodeCostComparator());
                AStarPathfinder.path.onTick();
            }
            if (messages) {
                PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > " + EnumChatFormatting.GREEN + "Found Path!"));
            }
            AStarPathfinder.path.onBeginMove();
            while (AStarPathfinder.path.moves.size() != 0) {
                if (PizzaClient.mc.field_71462_r != null) {
                    Movement.disableMovement();
                    Utils.sleep(200);
                    PlayerUtil.closeScreen();
                }
                else {
                    AStarPathfinder.path.move();
                }
            }
            AStarPathfinder.path.onEndMove();
            Movement.disableMovement();
            this.shutdown();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("PizzaClient > " + EnumChatFormatting.RED + "PizzaClient caught an logged an exception when calculating the path. Please report this."));
            this.shutdown();
            return false;
        }
    }
}
