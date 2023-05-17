// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.features.misc;

import java.util.HashSet;
import java.util.Iterator;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonElement;
import java.io.FileWriter;
import java.util.Collection;
import java.util.Arrays;
import qolskyblockmod.pizzaclient.util.Utils;
import java.io.Reader;
import com.google.gson.JsonArray;
import java.io.FileReader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import qolskyblockmod.pizzaclient.features.keybinds.KeybindFeatures;
import qolskyblockmod.pizzaclient.features.dungeons.ChestESP;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import qolskyblockmod.pizzaclient.util.ItemUtil;
import net.minecraft.item.ItemStack;
import qolskyblockmod.pizzaclient.PizzaClient;
import java.util.Set;
import java.io.File;

public class BlockAbility
{
    private static File saveFile;
    public static Set<String> blockedItems;
    
    public BlockAbility() {
        BlockAbility.saveFile = new File(PizzaClient.modDir, "blockability.json");
        reloadSave();
    }
    
    public static boolean blockAbility(final ItemStack item) {
        final String extraAttr = ItemUtil.getSkyBlockItemID(item);
        if (extraAttr.equals("GYROKINETIC_WAND")) {
            return PizzaClient.config.blockAlignment;
        }
        if (PizzaClient.mc.field_71439_g.func_110143_aJ() >= PizzaClient.mc.field_71439_g.func_110138_aP() && extraAttr.contains("ZOMBIE_SWORD")) {
            return PizzaClient.config.blockUselessZombieSword;
        }
        return BlockAbility.blockedItems.contains(extraAttr);
    }
    
    @SubscribeEvent
    public void onInteract(final PlayerInteractEvent event) {
        if (event.entityPlayer != PizzaClient.mc.field_71439_g) {
            return;
        }
        switch (event.action) {
            case RIGHT_CLICK_BLOCK: {
                final Block block = event.world.func_180495_p(event.pos).func_177230_c();
                if (block == Blocks.field_150486_ae) {
                    ChestESP.clickedBlocks.add(event.pos);
                    final ItemStack item = event.entityPlayer.field_71071_by.func_70448_g();
                    if (item != null && !KeybindFeatures.notGhostBlockable.contains(block)) {
                        event.setCanceled(blockAbility(item));
                    }
                    break;
                }
                break;
            }
            case RIGHT_CLICK_AIR: {
                final ItemStack item = event.entityPlayer.field_71071_by.func_70448_g();
                if (item != null) {
                    event.setCanceled(blockAbility(item));
                    break;
                }
                break;
            }
        }
    }
    
    public static void reloadSave() {
        BlockAbility.blockedItems.clear();
        try (final FileReader in = new FileReader(BlockAbility.saveFile)) {
            final JsonArray dataArray = (JsonArray)PizzaClient.gson.fromJson((Reader)in, (Class)JsonArray.class);
            BlockAbility.blockedItems.addAll(Arrays.asList(Utils.getStringArrayFromJsonArray(dataArray)));
        }
        catch (Exception e) {
            final JsonArray dataArray = new JsonArray();
            try (final FileWriter writer = new FileWriter(BlockAbility.saveFile)) {
                PizzaClient.gson.toJson((JsonElement)dataArray, (Appendable)writer);
            }
            catch (Exception ex) {}
        }
    }
    
    public static void writeSave() {
        try (final FileWriter writer = new FileWriter(BlockAbility.saveFile)) {
            final JsonArray arr = new JsonArray();
            for (final String itemId : BlockAbility.blockedItems) {
                arr.add((JsonElement)new JsonPrimitive(itemId));
            }
            PizzaClient.gson.toJson((JsonElement)arr, (Appendable)writer);
        }
        catch (Exception ex) {}
    }
    
    static {
        BlockAbility.blockedItems = new HashSet<String>();
    }
}
