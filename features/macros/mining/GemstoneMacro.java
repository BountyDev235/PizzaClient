// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.macros.mining;

import qolskyblockmod.pizzaclient.util.misc.Locations;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.features.macros.builder.macros.Macro;

public class GemstoneMacro extends Macro
{
    @Override
    public void onTick() {
        if (PizzaClient.location == Locations.CHOLLOWS) {}
    }
    
    @Override
    public void onToggle(final boolean toggled) {
        this.addToggleMessage("Gemstone Macro");
    }
    
    @Override
    public boolean applyFailsafes() {
        return true;
    }
    
    @Override
    public boolean applyPositionFailsafe() {
        return true;
    }
    
    @Override
    public boolean applyBedrockFailsafe() {
        return false;
    }
    
    @Override
    public boolean applyPlayerFailsafes() {
        return PizzaClient.config.stopWhenNearPlayer;
    }
}
