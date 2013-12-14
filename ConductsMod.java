package conducts;

import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=ConductsMod.ID, name=ConductsMod.NAME, version=ConductsMod.VERSION)
public class ConductsMod {
	
	public static final String VERSION = "0.01";
	public static final String ID = "conducts";
	public static final String NAME = "Conducts for Minecraft";

	@EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
        
        AchievementPage.registerAchievementPage(new ConductsPage());

    }
	
	
}
