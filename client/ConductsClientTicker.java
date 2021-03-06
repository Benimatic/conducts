package conducts.client;	

import java.util.EnumSet;

import conducts.ConductsMod;
import conducts.ConductsPage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.achievement.GuiAchievement;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ConductsClientTicker implements ITickHandler {

	private ConductsMod mod;

	public ConductsClientTicker(ConductsMod conductsMod) {
		this.mod = conductsMod;
	}

	/**
	 * We are looking to catch when a new achievement window pops up
	 */
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		Minecraft mc = Minecraft.getMinecraft();

		// check for the achievement box
		long currentCheevTime = ObfuscationReflectionHelper.getPrivateValue(GuiAchievement.class, mc.guiAchievement, "achievementTime", "field_73851_g", "h");
		// the achievement time is non-zero whenever the box is on screen
		if (currentCheevTime > 0) {
			Achievement currentAchievement = ObfuscationReflectionHelper.getPrivateValue(GuiAchievement.class, mc.guiAchievement, "theAchievement", "field_73850_f", "g");

			if (mod.getAchievementPage().getAchievements().contains(currentAchievement)) {
				ObfuscationReflectionHelper.setPrivateValue(GuiAchievement.class, mc.guiAchievement, "Conduct Broken", "achievementGetLocalText", "field_73852_d", "e");
			}
		}
		
		// check for the achievements GUI
		if (mc.currentScreen instanceof GuiAchievements) {
			int currentPage = ObfuscationReflectionHelper.getPrivateValue(GuiAchievements.class, (GuiAchievements)mc.currentScreen, "currentPage");
			
			if (currentPage >= 0 && AchievementPage.getAchievementPage(currentPage) instanceof ConductsPage) {
				
				//System.out.println("player on our achievements page");

				
				// replace taken text in i18n database with "Conduct Broken"
				
				//System.out.println("replacing taken");
			}
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
	}
	
    /**
     * Returns the list of ticks this tick handler is interested in receiving at the minute
     */
	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

	@Override
	public String getLabel() {
		return "Conducts Mod client-side";
	}

}
