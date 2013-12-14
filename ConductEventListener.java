package conducts;

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
			
		}
	}
	
	
	
	
}
