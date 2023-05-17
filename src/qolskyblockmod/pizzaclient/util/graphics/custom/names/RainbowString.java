// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.graphics.custom.names;

import qolskyblockmod.pizzaclient.util.misc.MaxSizeHashMap;
import java.util.HashMap;
import java.util.Iterator;
import com.google.gson.JsonObject;
import java.util.Locale;
import com.google.gson.JsonElement;
import qolskyblockmod.pizzaclient.util.Utils;
import qolskyblockmod.pizzaclient.util.graphics.FontUtil;
import qolskyblockmod.pizzaclient.PizzaClient;
import net.minecraft.util.StringUtils;
import java.util.Map;
import qolskyblockmod.pizzaclient.util.graphics.custom.CustomString;

public class RainbowString implements CustomString
{
    public String stringBefore;
    public char[] rainbowChars;
    public String stringAfter;
    public static final Map<String, char[]> rgbNames;
    public static final Map<String, CustomString> rgbList;
    private static Thread fetcher;
    
    public RainbowString(final String msg, final String rainbowMessage, final String lowerCase) {
        this.rainbowChars = RainbowString.rgbNames.get(lowerCase);
        if (this.rainbowChars[0] == '§') {
            new NonRGBString(msg, rainbowMessage, lowerCase);
            return;
        }
        final int index = msg.indexOf(rainbowMessage);
        this.stringBefore = msg.substring(0, index);
        if (this.rainbowChars[1] == '[') {
            if (this.stringBefore.indexOf(93) == -1) {
                final String stripped = StringUtils.func_76338_a(this.stringBefore);
                if (stripped.contains("[VIP") || stripped.contains("[MVP")) {
                    this.replaceRankInChars();
                }
            }
            else if (this.stringBefore.indexOf(58) == -1) {
                this.stringBefore = this.stringBefore.split("\\[")[0];
            }
        }
        this.stringAfter = msg.substring(index + rainbowMessage.length());
        RainbowString.rgbList.put(msg, this);
    }
    
    private void replaceRankInChars() {
        for (int i = 2; i < this.rainbowChars.length; ++i) {
            final char ch = this.rainbowChars[i];
            if (ch == ']') {
                final int from = i + 2;
                final int newLength = this.rainbowChars.length - from;
                final char[] copy = new char[newLength + 1];
                System.arraycopy(this.rainbowChars, from, copy, 1, newLength);
                copy[0] = this.rainbowChars[0];
                this.rainbowChars = copy;
                break;
            }
        }
    }
    
    @Override
    public int render(final String text, final int x, final int y, final int color, final boolean shadow) {
        return PizzaClient.mc.field_71466_p.func_175065_a(this.stringAfter, FontUtil.drawRainbowName(this.rainbowChars, PizzaClient.mc.field_71466_p.func_175065_a(this.stringBefore, (float)x, (float)y, color, shadow), y, shadow), (float)y, color, shadow);
    }
    
    public static void updateList() {
        if (!RainbowString.fetcher.isAlive()) {
            RainbowString.rgbNames.clear();
            final JsonObject respose;
            final Iterator<Map.Entry<String, JsonElement>> iterator;
            Map.Entry<String, JsonElement> jsons;
            (RainbowString.fetcher = new Thread(() -> {
                respose = Utils.getJson("https://gist.githubusercontent.com/PizzaboiBestLegit/c65896b963454b679eb68a29435ccb19/raw/gistfile1.txt").getAsJsonObject();
                respose.entrySet().iterator();
                while (iterator.hasNext()) {
                    jsons = iterator.next();
                    RainbowString.rgbNames.put(jsons.getKey().toLowerCase(Locale.ROOT), jsons.getValue().getAsString().replace("\u00c2", "").toCharArray());
                }
            })).start();
        }
    }
    
    static {
        rgbNames = new HashMap<String, char[]>();
        rgbList = new MaxSizeHashMap<String, CustomString>(1500);
        RainbowString.fetcher = new Thread(() -> {});
    }
}
