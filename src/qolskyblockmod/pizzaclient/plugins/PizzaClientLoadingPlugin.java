// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.plugins;

import java.lang.reflect.Method;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.UIManager;
import java.util.Map;
import java.io.File;
import org.apache.commons.lang3.StringUtils;
import org.spongepowered.asm.mixin.MixinEnvironment;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.Name(" ")
public class PizzaClientLoadingPlugin implements IFMLLoadingPlugin
{
    private static final String badMixinVersionMessage = "<html><p>Pizza Client has detected an older version of Mixin.<br>Many of my features require Mixin 0.7 or later!<br>In order to resolve this conflict you must update<br>any mods with a Mixin version below 0.7.<br>If you have already done this and are still getting this error,<br>ask me for support.";
    
    public PizzaClientLoadingPlugin() {
        if (!StringUtils.startsWithAny((CharSequence)MixinEnvironment.getCurrentEnvironment().getVersion(), new CharSequence[] { "0.7", "0.8" })) {
            this.showMessage("<html><p>Pizza Client has detected an older version of Mixin.<br>Many of my features require Mixin 0.7 or later!<br>In order to resolve this conflict you must update<br>any mods with a Mixin version below 0.7.<br>If you have already done this and are still getting this error,<br>ask me for support.<br>The Problem seems to be " + new File(MixinEnvironment.class.getProtectionDomain().getCodeSource().getLocation().toString()).getName() + "</p></html>");
        }
    }
    
    public String[] getASMTransformerClass() {
        return new String[0];
    }
    
    public String getModContainerClass() {
        return null;
    }
    
    public String getSetupClass() {
        return null;
    }
    
    public void injectData(final Map<String, Object> data) {
    }
    
    public String getAccessTransformerClass() {
        return null;
    }
    
    private void showMessage(final String errorMessage) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        final JFrame frame = new JFrame();
        frame.setUndecorated(true);
        frame.setAlwaysOnTop(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        final JButton close = new JButton("Close");
        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                PizzaClientLoadingPlugin.this.exit();
            }
        });
        final JButton[] totalOptions = { close };
        JOptionPane.showOptionDialog(frame, errorMessage, "PizzaClient Error", -1, 0, null, totalOptions, totalOptions[0]);
        this.exit();
    }
    
    private void exit() {
        try {
            final Class<?> clazz = Class.forName("java.lang.Shutdown");
            final Method m_exit = clazz.getDeclaredMethod("exit", Integer.TYPE);
            m_exit.setAccessible(true);
            m_exit.invoke(null, 0);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
