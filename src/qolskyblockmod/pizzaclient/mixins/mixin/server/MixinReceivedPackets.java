// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.mixins.mixin.server;

import qolskyblockmod.pizzaclient.features.macros.ai.Failsafes;
import qolskyblockmod.pizzaclient.util.MathUtil;
import net.minecraft.util.Vec3;
import qolskyblockmod.pizzaclient.util.PlayerUtil;
import qolskyblockmod.pizzaclient.features.macros.builder.MacroState;
import qolskyblockmod.pizzaclient.util.misc.Locations;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.features.macros.builder.MacroBuilder;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import qolskyblockmod.pizzaclient.features.player.AutoPowderChest;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.network.play.server.S2APacketParticles;
import net.minecraft.client.network.NetHandlerPlayClient;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = { NetHandlerPlayClient.class }, priority = 2000)
public abstract class MixinReceivedPackets
{
    @Inject(method = { "handleParticles" }, at = { @At("HEAD") })
    private void onParticle(final S2APacketParticles packetIn, final CallbackInfo ci) {
        AutoPowderChest.onParticle(packetIn);
    }
    
    @Inject(method = { "handlePlayerPosLook" }, at = { @At("HEAD") })
    private void onPacketPosLook(final S08PacketPlayerPosLook packetIn, final CallbackInfo ci) {
        if (MacroBuilder.toggled && PizzaClient.location != Locations.NULL && MacroBuilder.state == MacroState.ACTIVE) {
            float yaw = packetIn.func_148931_f();
            if (packetIn.func_179834_f().contains(S08PacketPlayerPosLook.EnumFlags.Y_ROT)) {
                yaw += PizzaClient.mc.field_71439_g.field_70177_z;
            }
            if (PlayerUtil.getPositionEyes().func_72436_e(new Vec3(packetIn.func_148932_c(), packetIn.func_148928_d(), packetIn.func_148933_e())) < 16.0) {
                if (PizzaClient.config.rotationFailsafe) {
                    final float diffYaw = MathUtil.abs(PizzaClient.mc.field_71439_g.field_70177_z - yaw);
                    if (diffYaw > 15.0f) {
                        Failsafes.onPacketPosLook(diffYaw);
                    }
                }
            }
            else {
                MacroBuilder.updatePosition();
                Failsafes.onChangePosition();
            }
        }
    }
}
