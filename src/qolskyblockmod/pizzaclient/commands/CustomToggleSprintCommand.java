// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.commands;

import net.minecraft.command.CommandException;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.client.entity.EntityPlayerSP;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.ICommand;
import net.minecraft.command.CommandBase;

public class CustomToggleSprintCommand extends CommandBase implements ICommand
{
    public String func_71517_b() {
        return "togglesprintmessage";
    }
    
    public String func_71518_a(final ICommandSender sender) {
        return EnumChatFormatting.RED + "Usage: /" + this.func_71517_b() + " [Message] or /" + this.func_71517_b() + " reset";
    }
    
    public List<String> func_71514_a() {
        return (List<String>)Lists.newArrayList((Object[])new String[] { "customtogglesprint" });
    }
    
    public int func_82362_a() {
        return 0;
    }
    
    public void func_71515_b(final ICommandSender sender, final String[] args) throws CommandException {
        final EntityPlayerSP player = (EntityPlayerSP)sender;
        if (args.length == 0) {
            player.func_145747_a((IChatComponent)new ChatComponentText(this.func_71518_a(sender)));
            return;
        }
        if (args[0].equalsIgnoreCase("reset")) {
            PizzaClient.config.customToggleSprintName = "ToggleSprint (Toggled)";
            player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.GREEN + "Reset ToggleSprint message back to " + EnumChatFormatting.GOLD + PizzaClient.config.customToggleSprintName));
            return;
        }
        final StringBuilder customMessage = new StringBuilder();
        for (final String arg : args) {
            customMessage.append(arg).append(" ");
        }
        PizzaClient.config.customToggleSprintName = customMessage.toString();
        player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.GREEN + "ToggleSprint message is now set to " + EnumChatFormatting.GOLD + PizzaClient.config.customToggleSprintName));
    }
}
