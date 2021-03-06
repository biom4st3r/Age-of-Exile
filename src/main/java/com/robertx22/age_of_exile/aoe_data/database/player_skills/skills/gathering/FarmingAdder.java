package com.robertx22.age_of_exile.aoe_data.database.player_skills.skills.gathering;

import com.robertx22.age_of_exile.aoe_data.database.player_skills.PlayerSkillBuilder;
import com.robertx22.age_of_exile.database.data.player_skills.PlayerSkill;
import com.robertx22.age_of_exile.mmorpg.ModRegistry;
import com.robertx22.age_of_exile.player_skills.items.fishing.LureType;
import com.robertx22.age_of_exile.saveclasses.player_skills.PlayerSkillEnum;
import org.apache.commons.lang3.tuple.ImmutablePair;

import static com.robertx22.age_of_exile.mmorpg.ModRegistry.TIERED;

public class FarmingAdder {

    public static PlayerSkill createSkill() {

        PlayerSkillBuilder b = PlayerSkillBuilder.of(2, PlayerSkillEnum.FARMING);
        b.addBonusYieldMasteryLevelStats(PlayerSkillEnum.FARMING);
        b.addDefaultBonusExpRewards();


        /*
        DropRewardsBuilder skillDrops = DropRewardsBuilder.of(2);

        DropRewardsBuilder rareDrops = DropRewardsBuilder.of(0.25F);
        rareDrops.dropReward(new SkillDropReward(10, 10, Items.BONE_MEAL, new MinMax(1, 2)));
        rareDrops.dropReward(new SkillDropReward(20, 2, Items.GOLDEN_APPLE, new MinMax(1, 1)));
        rareDrops.dropReward(new SkillDropReward(30, 1, Items.EMERALD, new MinMax(1, 3)));
        rareDrops.dropReward(new SkillDropReward(40, 2, Items.EMERALD, new MinMax(2, 5)));
        rareDrops.dropReward(new SkillDropReward(50, 1, Items.ENCHANTED_GOLDEN_APPLE, new MinMax(1, 1)));

        b.skill.dropTables.add(skillDrops.build());
        b.skill.dropTables.add(rareDrops.build());

         */

        ModRegistry.BLOCKS.FARMING_PLANTS.entrySet()
            .forEach(x -> {
                b.blockExp(x.getValue(), (int) (x.getKey().statMulti * 10));
            });

        b.addTieredDrops(0.1F, tier -> {
            return TIERED.LURES.get(ImmutablePair.of(LureType.FISH, tier));
        });

        b.regens(10, 2);
        b.regens(20, 4);
        b.regens(30, 6);
        b.regens(40, 8);
        b.regens(50, 10);

        return b.build();
    }
}
