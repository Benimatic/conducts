package conducts;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class ConductsPage extends AchievementPage {
	
	public static Achievement breatharian = new Achievement(6000000, "breatharian", 0, -2, Block.glass, (Achievement)null).setIndependent().setSpecial().registerAchievement();
	public static Achievement carnivore = new Achievement(6000001, "carnivore", -1, 0, Item.beefRaw, breatharian).registerAchievement();
	public static Achievement vegan = new Achievement(6000002, "vegan", 1, 0, Item.carrot, breatharian).registerAchievement();
	public static Achievement vegetarian = new Achievement(6000003, "vegetarian", 1, 2, Item.cake, vegan).registerAchievement();
	
	
	public static Achievement pacifist = new Achievement(6000004, "pacifist", 4, 0, Block.plantRed, (Achievement)null).setIndependent().setSpecial().registerAchievement();
	public static Achievement neverSword = new Achievement(6000005, "neverSword", 3, 0, Item.swordIron, pacifist).registerAchievement();
	public static Achievement neverRange = new Achievement(6000006, "neverRange", 5, 0, Item.arrow, pacifist).registerAchievement();

	
	

	public ConductsPage() {
		super(ConductsMod.NAME, breatharian, carnivore, vegan, vegetarian);
		
        LanguageRegistry.instance().addStringLocalization("achievement.breatharian", "en_US", "Breatharian");
        LanguageRegistry.instance().addStringLocalization("achievement.breatharian.desc", "en_US", "Never eat.");

        LanguageRegistry.instance().addStringLocalization("achievement.carnivore", "en_US", "Carnivore");
        LanguageRegistry.instance().addStringLocalization("achievement.carnivore.desc", "en_US", "Never eat a vegetable!");

        LanguageRegistry.instance().addStringLocalization("achievement.vegan", "en_US", "Vegan");
        LanguageRegistry.instance().addStringLocalization("achievement.vegan.desc", "en_US", "Do not eat milk or eggs");

        LanguageRegistry.instance().addStringLocalization("achievement.vegetarian", "en_US", "Vegetarian");
        LanguageRegistry.instance().addStringLocalization("achievement.vegetarian.desc", "en_US", "Never eat a meat!");

	}

	
    /**
     * A stub functions called to make the static initializer for this class run.
     */
    public static void init() {}
}
