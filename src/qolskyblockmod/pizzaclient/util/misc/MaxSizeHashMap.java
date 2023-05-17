// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.misc;

import java.util.Map;
import java.util.LinkedHashMap;

public class MaxSizeHashMap<K, V> extends LinkedHashMap<K, V>
{
    private final int maxSize;
    
    public MaxSizeHashMap(final int maxSize) {
        this.maxSize = maxSize;
    }
    
    @Override
    protected boolean removeEldestEntry(final Map.Entry<K, V> eldest) {
        return this.size() > this.maxSize;
    }
}
