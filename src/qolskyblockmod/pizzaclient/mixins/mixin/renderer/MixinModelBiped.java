// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.mixins.mixin.renderer;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.client.entity.EntityPlayerSP;
import qolskyblockmod.pizzaclient.util.rotation.fake.FakeRotation;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelBiped;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.client.model.ModelBase;

@Mixin({ ModelBiped.class })
public abstract class MixinModelBiped extends ModelBase
{
    @Shadow
    public ModelRenderer field_78116_c;
    
    @Inject(method = { "setRotationAngles" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/util/MathHelper;cos(F)F", ordinal = 0) })
    private void changeRotationAngles(final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scaleFactor, final Entity entityIn, final CallbackInfo ci) {
        if (FakeRotation.lastPitch != 69420.0f && entityIn instanceof EntityPlayerSP) {
            this.field_78116_c.field_78795_f = FakeRotation.lastPitch / 57.29578f;
        }
    }
}
