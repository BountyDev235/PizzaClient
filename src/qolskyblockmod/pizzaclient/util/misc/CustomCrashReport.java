// 
// Decompiled by Procyon v0.5.36
// 

package qolskyblockmod.pizzaclient.util.misc;

import net.minecraft.crash.CrashReport;

public class CustomCrashReport extends CrashReport
{
    public CustomCrashReport(final String descriptionIn) {
        super(descriptionIn, (Throwable)new Exception());
    }
}
