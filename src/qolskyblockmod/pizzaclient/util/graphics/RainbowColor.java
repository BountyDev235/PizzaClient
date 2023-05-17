// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.graphics;

import java.awt.Color;

public class RainbowColor extends Color
{
    private final int alpha;
    
    public RainbowColor(final int alpha) {
        super(alpha);
        this.alpha = alpha;
    }
    
    @Override
    public int getRGB() {
        return Color.HSBtoRGB(System.currentTimeMillis() % 3000L / 3000.0f, 1.0f, (float)this.alpha);
    }
}
