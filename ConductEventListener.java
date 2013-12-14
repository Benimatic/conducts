package conducts;

import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class ConductEventListener {

	
	@ForgeSubscribe
	public void itemDestroyed(PlayerDestroyItemEvent event)
	{
		System.out.println("Player destroyed an item : " + event.original.getDisplayName());
	}
	
	@ForgeSubscribe
	public void interact(PlayerInteractEvent event)
	{
		System.out.println("Player interacting!");
		if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR) {
			if (event.entityPlayer.getCurrentEquippedItem() != null && event.entityPlayer.getCurrentEquippedItem().getItemUseAction() == EnumAction.eat) {
				System.out.println("Player interacting with a FOOD ! " + event.entityPlayer.getCurrentEquippedItem());
				
				event.entityPlayer.addStat(ConductsPage.breatharian, 1);
				
				ItemFood currentFood = event.entityPlayer.getCurrentEquippedItem().getItem() instanceof ItemFood ? (ItemFood)(event.entityPlayer.getCurrentEquippedItem().getItem()) : null;
				
				if (currentFood != null) {
					if (currentFood.isWolfsFavoriteMeat()) {
						event.entityPlayer.addStat(ConductsPage.vegan, 1);
						event.entityPlayer.addStat(ConductsPage.vegetarian, 1);
					} else {
						event.entityPlayer.addStat(ConductsPage.carnivore, 1);
					}
				}
			}
		}
	}
	
	
	
	
}
