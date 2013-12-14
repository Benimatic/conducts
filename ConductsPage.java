package conducts;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class ConductsPage extends AchievementPage {
	
	public static Achievement breatharian = new Achievement(6000000, "breatharian", 0, -2, Block.glass, (Achievement)null).setSpecial().registerAchievement();
	public static Achievement carnivore = new Achievement(6000001, "carnivore", -1, 0, Item.beefRaw, breatharian).registerAchievement();
	public static Achievement vegan = new Achievement(6000002, "vegan", 1, 0, Item.carrot, breatharian).registerAchievement();
	public static Achievement vegetarian = new Achievement(6000003, "vegetarian", 1, 2, Item.cake, vegan).registerAchievement();
	
	
	public static Achievement pacifist = new Achievement(6000004, "pacifist", 4, -2, Block.plantRed, (Achievement)null).setSpecial().registerAchievement();
	public static Achievement neverSword = new Achievement(6000005, "neverSword", 3, 0, Item.arrow, pacifist).registerAchievement();
	public static Achievement neverRange = new Achievement(6000006, "neverRange", 5, 0, Item.swordGold, pacifist).registerAchievement();
	public static Achievement noKillMob = new Achievement(6000007, "noKillMob", 3, -3, Item.skull, pacifist).registerAchievement();
	public static Achievement noKillAnimal = new Achievement(6000008, "noKillPassive", 4, -4, Item.leather, pacifist).registerAchievement();
	public static Achievement noKillPlayers = new Achievement(6000009, "noKillPlayers", 5, -3, Item.plateGold, pacifist).registerAchievement();

	
	

	public ConductsPage() {
		super(ConductsMod.NAME, breatharian, carnivore, vegan, vegetarian, pacifist, neverSword, neverRange, noKillMob, noKillAnimal, noKillPlayers);
		
        LanguageRegistry.instance().addStringLocalization("achievement.breatharian", "en_US", "Breatharian");
        LanguageRegistry.instance().addStringLocalization("achievement.breatharian.desc", "en_US", "Never eat.");

        LanguageRegistry.instance().addStringLocalization("achievement.carnivore", "en_US", "Carnivore");
        LanguageRegistry.instance().addStringLocalization("achievement.carnivore.desc", "en_US", "Never eat a vegetable!");

        LanguageRegistry.instance().addStringLocalization("achievement.vegan", "en_US", "Vegan");
        LanguageRegistry.instance().addStringLocalization("achievement.vegan.desc", "en_US", "Do not eat meat, milk, or eggs");

        LanguageRegistry.instance().addStringLocalization("achievement.vegetarian", "en_US", "Vegetarian");
        LanguageRegistry.instance().addStringLocalization("achievement.vegetarian.desc", "en_US", "Never eat a meat!");

        LanguageRegistry.instance().addStringLocalization("achievement.pacifist", "en_US", "Pacifist");
        LanguageRegistry.instance().addStringLocalization("achievement.pacifist.desc", "en_US", "First, do no harm");

        LanguageRegistry.instance().addStringLocalization("achievement.neverSword", "en_US", "Way of the Fistbow");
        LanguageRegistry.instance().addStringLocalization("achievement.neverSword.desc", "en_US", "Never strike with a wielded weapon");

        LanguageRegistry.instance().addStringLocalization("achievement.neverRange", "en_US", "Way of the Sword");
        LanguageRegistry.instance().addStringLocalization("achievement.neverRange.desc", "en_US", "Never strike at range");

        LanguageRegistry.instance().addStringLocalization("achievement.noKillMob", "en_US", "Save the Monsters!");
        LanguageRegistry.instance().addStringLocalization("achievement.noKillMob.desc", "en_US", "Never kill a monster");

        LanguageRegistry.instance().addStringLocalization("achievement.noKillPassive", "en_US", "Save the Animals!");
        LanguageRegistry.instance().addStringLocalization("achievement.noKillPassive.desc", "en_US", "Never kill an animal");

        LanguageRegistry.instance().addStringLocalization("achievement.noKillPlayers", "en_US", "Save the Players");
        LanguageRegistry.instance().addStringLocalization("achievement.noKillPlayers.desc", "en_US", "Never kill another player");

	}

	
    /**
     * A stub functions called to make the static initializer for this class run.
     */
    public static void init() {}
}
