package com.robertx22.age_of_exile.database.data.spells.blocks.magma_flower;

import com.robertx22.library_of_exile.utils.SoundUtils;
import com.robertx22.age_of_exile.capability.entity.EntityCap;
import com.robertx22.age_of_exile.database.data.spells.blocks.base.BaseSpellTileEntity;
import com.robertx22.age_of_exile.database.data.spells.spell_classes.bases.configs.SC;
import com.robertx22.age_of_exile.mmorpg.ModRegistry;
import com.robertx22.age_of_exile.saveclasses.spells.EntitySpellData;
import com.robertx22.age_of_exile.uncommon.datasaving.Load;
import com.robertx22.age_of_exile.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.age_of_exile.uncommon.utilityclasses.EntityFinder;
import com.robertx22.age_of_exile.vanilla_mc.packets.particles.ParticleEnum;
import com.robertx22.age_of_exile.vanilla_mc.packets.particles.ParticlePacketData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class MagmaFlowerTileEntity extends BaseSpellTileEntity {

    public MagmaFlowerTileEntity() {
        super(ModRegistry.BLOCK_ENTITIES.MAGMA_FLOWER);
    }

    @Override
    public void onTick() {

        EntitySpellData sdata = getSpellData();
        int TICK_RATE = sdata.configs.get(SC.TICK_RATE)
            .intValue();
        int RADIUS = sdata.configs.get(SC.RADIUS)
            .intValue();

        if (this.data.ticksExisted > durationInTicks() == false) {

            if (data.ticksExisted % TICK_RATE == 0) {

                LivingEntity caster = data.getCaster(world);
                EntityCap.UnitData data = Load.Unit(caster);

                ParticleEnum.sendToClients(
                    pos, world, new ParticlePacketData(pos, ParticleEnum.AOE).radius(RADIUS)
                        .motion(new Vec3d(0, 0, 0))
                        .type(ParticleTypes.FLAME)
                        .amount((int) (15 * RADIUS)));

                List<LivingEntity> entities = EntityFinder.start(
                    caster, LivingEntity.class, new Vec3d(getPos().getX(), getPos().getY(), getPos().getZ()).add(0.5F, 0, 0.5F))
                    .radius(RADIUS)
                    .build();

                entities.forEach(x -> {
                    SpellDamageEffect dmg = getSetupSpellDamage(x);
                    dmg.Activate();
                    SoundUtils.playSound(x, SoundEvents.BLOCK_FIRE_EXTINGUISH, 1, 1);

                });

            }
        }

    }

}