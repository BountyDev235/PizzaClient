// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.graphics.shaders.blur;

import net.minecraft.client.renderer.OpenGlHelper;
import qolskyblockmod.pizzaclient.util.graphics.shaders.uniform.IUniform;
import qolskyblockmod.pizzaclient.util.graphics.shaders.uniform.FloatUniform;
import qolskyblockmod.pizzaclient.util.graphics.shaders.Shader;

public class BlurShader extends Shader
{
    public static BlurShader instance;
    
    public BlurShader() {
        super("blur/verticalBlurVertex", "blur/blurFragment");
    }
    
    @Override
    public void registerUniforms() {
        this.registerUniform(new FloatUniform(this.program, "targetHeight", () -> 10.0f));
    }
    
    @Override
    public void endShader() {
        OpenGlHelper.func_153161_d(0);
    }
    
    static {
        BlurShader.instance = new BlurShader();
    }
}
