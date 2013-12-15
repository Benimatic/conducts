package conducts;	

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.achievement.GuiAchievement;
import net.minecraft.stats.Achievement;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.TickType;

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
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		Minecraft mc = Minecraft.getMinecraft();
		
		long currentCheevTime = ObfuscationReflectionHelper.getPrivateValue(GuiAchievement.class, mc.guiAchievement, "achievementTime");
		
		// the achievement time is non-zero whenever the box is on screen
		if (currentCheevTime > 0) {
			Achievement currentAchievement = ObfuscationReflectionHelper.getPrivateValue(GuiAchievement.class, mc.guiAchievement, "theAchievement");

			if (mod.getAchievementPage().getAchievements().contains(currentAchievement)) {
				ObfuscationReflectionHelper.setPrivateValue(GuiAchievement.class, mc.guiAchievement, "Conduct Broken", "achievementGetLocalText", "a");
			}
		}
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
