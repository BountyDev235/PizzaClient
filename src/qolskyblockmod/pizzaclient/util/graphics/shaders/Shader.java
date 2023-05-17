// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.graphics.shaders;

import java.util.Iterator;
import qolskyblockmod.pizzaclient.util.graphics.shaders.util.ShaderLoader;
import net.minecraft.client.renderer.OpenGlHelper;
import java.util.ArrayList;
import qolskyblockmod.pizzaclient.util.graphics.shaders.uniform.IUniform;
import java.util.List;

public abstract class Shader
{
    private final List<IUniform> uniforms;
    public final int program;
    
    public Shader(final String vertex, final String fragment) {
        this.uniforms = new ArrayList<IUniform>();
        this.program = OpenGlHelper.func_153183_d();
        ShaderLoader.load(this, vertex, fragment);
        OpenGlHelper.func_153179_f(this.program);
        this.registerUniforms();
    }
    
    public Shader(final String location) {
        this.uniforms = new ArrayList<IUniform>();
        this.program = OpenGlHelper.func_153183_d();
        ShaderLoader.load(this, location);
        OpenGlHelper.func_153179_f(this.program);
        this.registerUniforms();
    }
    
    public void endShader() {
        OpenGlHelper.func_153161_d(0);
    }
    
    public final void registerUniform(final IUniform uniform) {
        this.uniforms.add(uniform);
    }
    
    public void applyShader() {
        for (final IUniform uniform : this.uniforms) {
            uniform.update();
        }
        OpenGlHelper.func_153161_d(this.program);
    }
    
    public abstract void registerUniforms();
}
