// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.commands;

import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.PizzaClient;
import qolskyblockmod.pizzaclient.util.graphics.custom.names.RainbowString;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.CommandBase;

public class ReloadNamesCommand extends CommandBase
{
    public String func_71517_b() {
        return "reloadnames";
    }
    
    public String func_71518_a(final ICommandSender arg0) {
        return "/" + this.func_71517_b();
    }
    
    public int func_82362_a() {
        return 0;
    }
    
    public void func_71515_b(final ICommandSender sender, final String[] args) {
        RainbowString.updateList();
        PizzaClient.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(Utils.SUCCESS_MESSAGE + "Reloading names!"));
    }
}
