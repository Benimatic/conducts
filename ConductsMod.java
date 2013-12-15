package conducts;

import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid=ConductsMod.ID, name=ConductsMod.NAME, version=ConductsMod.VERSION)
public class ConductsMod {
	
	public static final String VERSION = "0.01";
	public static final String ID = "conducts";
	public static final String NAME = "Conducts for Minecraft";
	
	 ConductEventListener conductChecker;
	private ConductsPage page;

	@EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        page = new ConductsPage();
		AchievementPage.registerAchievementPage(page);

		
		MinecraftForge.EVENT_BUS.register(this);
        
		this.conductChecker = new ConductEventListener();
        
        MinecraftForge.EVENT_BUS.register(conductChecker);
        
        GameRegistry.registerCraftingHandler(conductChecker);
        
        
        TickRegistry.registerTickHandler(new ConductsClientTicker(this), Side.CLIENT);
        TickRegistry.registerTickHandler(new ConductsServerTicker(), Side.SERVER);

    }

	public ConductEventListener getConductChecker() {
		return conductChecker;
	}

	public ConductsPage getAchievementPage() {
		return page;
	}
	
	
}
