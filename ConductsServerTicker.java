package conducts;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ConductsServerTicker implements ITickHandler {

	/**
	 * Here we check if the player is about to finish a Hi
	 */
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
	
		EntityPlayerMP player = (EntityPlayerMP)tickData[0];
		
		//System.out.println("PLAYER TICK! " + player.getItemInUse());

		
		if (player.getItemInUse() != null && player.getItemInUse() == player.inventory.getCurrentItem() && player.getItemInUseCount() == 1)
		{
			// the player is about to finish using an item
			
			System.out.println("The player is about to finish an item." + player.getItemInUse() + " count = " + player.getItemInUseCount());
			
			
			if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItemUseAction() == EnumAction.eat) {
				
				player.triggerAchievement(ConductsPage.breatharian);
				
				ItemFood currentFood = player.getCurrentEquippedItem().getItem() instanceof ItemFood ? (ItemFood)(player.getCurrentEquippedItem().getItem()) : null;
				
				if (currentFood != null) {
					if (currentFood.isWolfsFavoriteMeat()) {
						player.triggerAchievement(ConductsPage.vegan);
						player.triggerAchievement(ConductsPage.vegetarian);
					} else {
						player.triggerAchievement(ConductsPage.carnivore);
					}
				}
				
				if (ConductEventListener.isItemUsedInRecipeFor(new ItemStack(Item.egg), new ItemStack(currentFood))) {
					player.triggerAchievement(ConductsPage.vegan);
				}
			}
			
			
		}
		
		
		
		
	
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.PLAYER);
	}

	@Override
	public String getLabel() {
		return "Conducts Mod server-side";
	}

}
