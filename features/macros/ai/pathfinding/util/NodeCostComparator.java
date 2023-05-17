// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.ai.pathfinding.util;

import qolskyblockmod.pizzaclient.features.macros.ai.pathfinding.pathfinder.PathNode;
import java.util.Comparator;

public class NodeCostComparator implements Comparator<PathNode>
{
    @Override
    public int compare(final PathNode o1, final PathNode o2) {
        return o1.cost - o2.cost;
    }
}
