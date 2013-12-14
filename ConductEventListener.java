package conducts;

import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
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
//		System.out.println("Player interacting!");
		if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR) {
			if (event.entityPlayer.getCurrentEquippedItem() != null && event.entityPlayer.getCurrentEquippedItem().getItemUseAction() == EnumAction.eat) {
//				System.out.println("Player interacting with a FOOD ! " + event.entityPlayer.getCurrentEquippedItem() + " player is " + event.entityPlayer);
				
				event.entityPlayer.triggerAchievement(ConductsPage.breatharian);
				
				ItemFood currentFood = event.entityPlayer.getCurrentEquippedItem().getItem() instanceof ItemFood ? (ItemFood)(event.entityPlayer.getCurrentEquippedItem().getItem()) : null;
				
				if (currentFood != null) {
					if (currentFood.isWolfsFavoriteMeat()) {
						event.entityPlayer.triggerAchievement(ConductsPage.vegan);
						event.entityPlayer.triggerAchievement(ConductsPage.vegetarian);
					} else {
						event.entityPlayer.triggerAchievement(ConductsPage.carnivore);
					}
				}
			}
		}
	}
	
	@ForgeSubscribe
	public void entityHurts(LivingHurtEvent event)
	{
		if (event.source.getSourceOfDamage() instanceof EntityPlayer)
		{
			// discover the cause of the damage
			EntityPlayer cause = (EntityPlayer)(event.source.getSourceOfDamage());

			cause.triggerAchievement(ConductsPage.pacifist);
			
			// check what the player is holding
			if (cause.getCurrentEquippedItem() != null) {
				Item itemUsed = cause.getCurrentEquippedItem().getItem();
				
				if (itemUsed instanceof ItemSword || itemUsed instanceof ItemTool) {
					cause.triggerAchievement(ConductsPage.neverSword);
				}
			}
			
			if (event.source.isProjectile())
			{
				cause.triggerAchievement(ConductsPage.neverRange);
			}
			
			
		}
	}
	
	
	@ForgeSubscribe
	public void entityDies(LivingDeathEvent event)
	{
		if (event.source.getSourceOfDamage() instanceof EntityPlayer)
		{
			// discover the cause of the death
			EntityPlayer cause = (EntityPlayer)(event.source.getSourceOfDamage());
			
			if (event.entityLiving instanceof EntityAnimal)
			{
				cause.triggerAchievement(ConductsPage.noKillAnimal);
			}	
			if (event.entityLiving instanceof IMob)
			{
				cause.triggerAchievement(ConductsPage.noKillMob);
			}
			if (event.entityLiving instanceof EntityPlayer)
			{
				cause.triggerAchievement(ConductsPage.noKillPlayers);
			}
		}
	}
}
