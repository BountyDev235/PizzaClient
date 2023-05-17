// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.plugins;

import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.lib.tree.ClassNode;
import java.util.List;
import java.util.Set;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;

public class PizzaClientMixinPlugin implements IMixinConfigPlugin
{
    public void onLoad(final String mixinPackage) {
    }
    
    public String getRefMapperConfig() {
        return null;
    }
    
    public boolean shouldApplyMixin(final String targetClassName, final String mixinClassName) {
        if (!mixinClassName.startsWith("qolskyblockmod.pizzaclient.mixins")) {
            System.out.println("Mixin " + mixinClassName + " for " + targetClassName + " is foreign, disabling.");
            return false;
        }
        return true;
    }
    
    public void acceptTargets(final Set<String> myTargets, final Set<String> otherTargets) {
    }
    
    public List<String> getMixins() {
        return null;
    }
    
    public void preApply(final String targetClassName, final ClassNode targetClass, final String mixinClassName, final IMixinInfo mixinInfo) {
    }
    
    public void postApply(final String targetClassName, final ClassNode targetClass, final String mixinClassName, final IMixinInfo mixinInfo) {
    }
}
