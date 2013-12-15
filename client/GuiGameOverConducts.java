package conducts.client;

import java.util.ArrayList;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;

public class GuiGameOverConducts extends GuiGameOver {
	
    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
    	super.initGui();
    	
    	// move the existing buttons down 12 pixels
    	for (GuiButton existingButton : (ArrayList<GuiButton>)this.buttonList) {
    		existingButton.yPosition += 12;
    	}
    	
    	// add achievements button
        GuiButton achievementButton = new GuiButton(3, this.width / 2 - 100, this.height / 4 + 60, 98, 20, I18n.getString("gui.achievements"));
        achievementButton.enabled = false;
		this.buttonList.add(achievementButton);
		
        GuiButton statsButton = new GuiButton(4, this.width / 2 + 2, this.height / 4 + 60, 98, 20, I18n.getString("gui.stats"));
        statsButton.enabled = false;
		this.buttonList.add(statsButton);

    }
    
    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton par1GuiButton)
    {
        switch (par1GuiButton.id) {
        case 3:
            this.mc.displayGuiScreen(new GuiAchievements(this.mc.statFileWriter));
            break;
        case 4:
            this.mc.displayGuiScreen(new GuiStats(null, this.mc.statFileWriter));
            break;
        }
        
        super.actionPerformed(par1GuiButton);
    }

}
