// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.player;

import net.minecraft.client.entity.EntityPlayerSP;
import qolskyblockmod.pizzaclient.features.macros.ai.pathfinding.pathfinder.BasePathfinder;
import qolskyblockmod.pizzaclient.features.macros.ai.pathfinding.Pathfinding;
import qolskyblockmod.pizzaclient.features.macros.ai.pathfinding.path.base.PathBase;
import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.features.macros.ai.pathfinding.pathfinder.PathFinderNoMovement;
import java.util.List;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.features.macros.ai.pathfinding.util.BetterBlockPos;
import qolskyblockmod.pizzaclient.util.MathUtil;
import qolskyblockmod.pizzaclient.features.macros.ai.pathfinding.path.TPAuraPath;

public class TPAuraHelper
{
    public static final int FLY_DURATION = 1300;
    public static TPAuraPath path;
    private static long lastFlyDisable;
    
    public static boolean isFlyActive() {
        return System.currentTimeMillis() - TPAuraHelper.lastFlyDisable < 1300L;
    }
    
    public static void update() {
        TPAuraHelper.lastFlyDisable = System.currentTimeMillis();
    }
    
    public static void teleport() {
        if (TPAuraHelper.path.moves.size() == 0) {
            TPAuraHelper.path.onEnd();
            disable();
            return;
        }
        final List<BetterBlockPos> subList = TPAuraHelper.path.moves.subList(0, MathUtil.min(TPAuraHelper.path.moves.size(), TPAuraHelper.path.getSpeed() - 1));
        final BetterBlockPos pos = subList.get(subList.size() - 1);
        PizzaClient.mc.field_71439_g.func_70107_b(pos.field_177962_a + 0.5, (double)pos.field_177960_b, pos.field_177961_c + 0.5);
        subList.clear();
    }
    
    public static void runPathfinder(final BetterBlockPos pos) {
        final PathFinderNoMovement pathFinderNoMovement;
        new Thread(() -> {
            new PathFinderNoMovement(new TPAuraPath(Utils.getClosestInRange(pos)));
            TPAuraHelper.path = (TPAuraPath)pathFinderNoMovement.calculateAndGetPath();
        }).start();
    }
    
    public static void runPathfinder(final BetterBlockPos pos, final Runnable runnable) {
        final PathFinderNoMovement pathFinderNoMovement;
        new Thread(() -> {
            new PathFinderNoMovement(new TPAuraPath(Utils.getClosestInRange(pos)).setRunnable(runnable));
            TPAuraHelper.path = (TPAuraPath)pathFinderNoMovement.calculateAndGetPath();
        }).start();
    }
    
    public static void disable() {
        Pathfinding.unregister();
        BasePathfinder.path = null;
        TPAuraHelper.path = null;
        final EntityPlayerSP field_71439_g = PizzaClient.mc.field_71439_g;
        final EntityPlayerSP field_71439_g2 = PizzaClient.mc.field_71439_g;
        final double n = 0.0;
        field_71439_g2.field_70179_y = n;
        field_71439_g.field_70159_w = n;
    }
}
