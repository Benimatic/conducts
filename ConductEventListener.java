package conducts;

import cpw.mods.fml.common.ICraftingHandler;
import net.minecraft.client.gui.achievement.GuiAchievement;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ConductEventListener implements ICraftingHandler {


	/**
	 * Forge event that fires when the player uses an item.  Currently we are using this to detect food eating.
	 * 
	 * TODO: detect food actually being consumed instead of just clicked
	 * 
	 * @param event
	 */
	@ForgeSubscribe
	public void interact(PlayerInteractEvent event)
	{
		if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR) {

			if (isItemUsedInRecipeFor(new ItemStack(Item.book), event.entityPlayer.getCurrentEquippedItem()) || isItemUsedInRecipeFor(new ItemStack(Item.paper), event.entityPlayer.getCurrentEquippedItem())) {
				event.entityPlayer.triggerAchievement(ConductsPage.illiterate);
			}
		}
	}
	
	/**
	 * Helper method used to find forbidden items.  Checks the recipe used to make an item for the taboo item.
	 * 
	 * @param checkFor
	 * @param checking
	 * @return
	 */
	public static boolean isItemUsedInRecipeFor(ItemStack checkFor, ItemStack checking)
	{
		System.out.println("I am looking for a recipe for " + checking);

		
		for (Object maybeARecipe : CraftingManager.getInstance().getRecipeList()) {
			if (maybeARecipe instanceof IRecipe) {
				IRecipe iRecipe = (IRecipe)maybeARecipe;
				
				if (iRecipe.getRecipeOutput() != null && iRecipe.getRecipeOutput().isItemEqual(checking))
				{
					System.out.println("I found a recipe for your current item. It is " + iRecipe);

					
					// shaped recipe checker
					if (maybeARecipe instanceof ShapedRecipes)
					{
						ShapedRecipes recipe = (ShapedRecipes)maybeARecipe;
						
						for (ItemStack input : recipe.recipeItems) {
							if (input != null && input.isItemEqual(checkFor))
							{
								return true;
							}
						}
					}
					
					// shapeless checker
					if (maybeARecipe instanceof ShapelessRecipes)
					{
						ShapelessRecipes recipe = (ShapelessRecipes)maybeARecipe;
						
						for (Object input : recipe.recipeItems) {
							
							System.out.println("Matching recipe contains " + ((ItemStack)input).getDisplayName());

							
							if (((ItemStack)input).isItemEqual(checkFor))
							{
								return true;
							}
						}
					}

					// shapeless ore checker
					if (maybeARecipe instanceof ShapelessOreRecipe)
					{
						ShapelessOreRecipe recipe = (ShapelessOreRecipe)maybeARecipe;
						
						for (Object input : recipe.getInput()) {
							
							System.out.println("Matching recipe contains " + input);

							
							if (input instanceof ItemStack && ((ItemStack)input).isItemEqual(checkFor))
							{
								return true;
							}
							if (input instanceof Item && checkFor.itemID == ((Item)input).itemID)
							{
								return true;
							}
						}
					}

				}
			}
		}
		
		return false;
	}
	
	/**
	 * Forge event that fires when an entity gets hurt.  Used for the pacifist tree conducts
	 * 
	 * @param event
	 */
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
	
	/**
	 *  Forge event that fires when an entity dies.  Used for the no-kill conducts
	 * 
	 * @param event
	 */
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
	
	/**
	 * Forge event that fires when the player is about to break a block.  Used for the block breaking conducts.
	 * 
	 * @param event
	 */
	@ForgeSubscribe
	public void blockBroken(BreakEvent event) {
		if (event.block != null) {
			event.getPlayer().triggerAchievement(ConductsPage.noHarvest);
		}
		
		if (OreDictionary.getOreID(new ItemStack(event.block, 1, event.blockMetadata)) == OreDictionary.getOreID("logWood")) {
			event.getPlayer().triggerAchievement(ConductsPage.noHarvest);
			event.getPlayer().triggerAchievement(ConductsPage.noTree);
		}
		
		if (OreDictionary.getOreID(new ItemStack(event.block, 1, event.blockMetadata)) == OreDictionary.getOreID("stone")) {
			event.getPlayer().triggerAchievement(ConductsPage.noHarvest);
			event.getPlayer().triggerAchievement(ConductsPage.noStone);
		}
		
		
	}

	/**
	 * Handler that triggers when the player crafts an item.  We currently check for taboo items for the illiterate conduct
	 */
	@Override
	public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix) {
		if (isItemUsedInRecipeFor(new ItemStack(Item.book), item) || isItemUsedInRecipeFor(new ItemStack(Item.paper), item)) {
			player.triggerAchievement(ConductsPage.illiterate);
		}
	}

	@Override
	public void onSmelting(EntityPlayer player, ItemStack item) {
		// not used
		
	}

}
