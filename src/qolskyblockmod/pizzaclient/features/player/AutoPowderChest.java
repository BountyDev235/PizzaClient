// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.player;

import qolskyblockmod.pizzaclient.util.MathUtil;
import qolskyblockmod.pizzaclient.util.rotation.fake.FakeRotation;
import qolskyblockmod.pizzaclient.core.events.TickStartEvent;
import qolskyblockmod.pizzaclient.features.macros.mining.nuker.PowderMacro;
import net.minecraftforge.client.event.GuiOpenEvent;
import qolskyblockmod.pizzaclient.features.dungeons.ChestESP;
import net.minecraft.util.EnumParticleTypes;
import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.util.misc.Locations;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.network.play.server.S2APacketParticles;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import qolskyblockmod.pizzaclient.util.RenderUtil;
import java.awt.Color;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public class AutoPowderChest
{
    public static Vec3 particleVec;
    private static BlockPos chestPos;
    
    @SubscribeEvent
    public void onRenderWorldLast(final RenderWorldLastEvent event) {
        if (AutoPowderChest.particleVec != null) {
            RenderUtil.drawFilledEsp(new AxisAlignedBB(AutoPowderChest.particleVec.field_72450_a - 0.1, AutoPowderChest.particleVec.field_72448_b - 0.1, AutoPowderChest.particleVec.field_72449_c - 0.1, AutoPowderChest.particleVec.field_72450_a + 0.1, AutoPowderChest.particleVec.field_72448_b + 0.1, AutoPowderChest.particleVec.field_72449_c + 0.1), new Color(90, 0, 255));
            if (AutoPowderChest.chestPos != null) {
                RenderUtil.drawFilledEsp(AutoPowderChest.chestPos, Color.CYAN, 0.5f);
            }
        }
    }
    
    public static void onParticle(final S2APacketParticles packet) {
        if (!PizzaClient.config.powderChestMacro || PizzaClient.location != Locations.CHOLLOWS || PizzaClient.mc.field_71462_r != null) {
            return;
        }
        if (AutoPowderChest.chestPos != null) {
            if (Utils.getXandZDistanceSquared(new Vec3(AutoPowderChest.chestPos.func_177958_n() + 0.5, AutoPowderChest.chestPos.func_177956_o() + 0.5, AutoPowderChest.chestPos.func_177952_p() + 0.5)) >= 49.0) {
                AutoPowderChest.chestPos = null;
                AutoPowderChest.particleVec = null;
            }
            else if (!AutoPowderChest.chestPos.equals((Object)getBlockPos(packet))) {
                return;
            }
        }
        if (packet.func_179749_a() == EnumParticleTypes.CRIT && isPosValid(packet)) {
            final Vec3 vec = new Vec3(packet.func_149220_d(), packet.func_149226_e(), packet.func_149225_f());
            if (Utils.getXandZDistanceSquared(vec) < 36.0) {
                AutoPowderChest.particleVec = vec;
                AutoPowderChest.chestPos = getBlockPos(packet);
                ChestESP.clickedBlocks.add(AutoPowderChest.chestPos);
            }
        }
    }
    
    @SubscribeEvent
    public void onOpenGui(final GuiOpenEvent event) {
        AutoPowderChest.particleVec = null;
        PowderMacro.clicked.clear();
        AutoPowderChest.chestPos = null;
    }
    
    @SubscribeEvent
    public void onTick(final TickStartEvent event) {
        if (AutoPowderChest.particleVec != null) {
            FakeRotation.rotationVec = AutoPowderChest.particleVec;
        }
    }
    
    public static BlockPos getBlockPos(final S2APacketParticles packet) {
        double posX = packet.func_149220_d();
        double posZ = packet.func_149225_f();
        final float x = formatFloat((float)(MathUtil.abs(packet.func_149220_d()) % 1.0));
        final float z = formatFloat((float)(MathUtil.abs(packet.func_149225_f()) % 1.0));
        if (x == 0.9f) {
            ++posX;
        }
        if (x == 0.1f) {
            --posX;
        }
        if (z == 0.9f) {
            ++posZ;
        }
        if (z == 0.1f) {
            --posZ;
        }
        return new BlockPos(posX, packet.func_149226_e(), posZ);
    }
    
    private static boolean isPosValid(final S2APacketParticles packet) {
        final float x = formatFloat((float)(MathUtil.abs(packet.func_149220_d()) % 1.0));
        final float z = formatFloat((float)(MathUtil.abs(packet.func_149225_f()) % 1.0));
        return x == 0.1f || x == 0.9f || z == 0.1f || z == 0.9f;
    }
    
    private static float formatFloat(final float value) {
        return MathUtil.round(value * 10000.0f) / 10000.0f;
    }
}
