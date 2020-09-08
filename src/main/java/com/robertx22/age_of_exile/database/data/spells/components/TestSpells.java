package com.robertx22.age_of_exile.database.data.spells.components;

import com.robertx22.age_of_exile.database.data.spells.components.ComponentPart.Builder;
import com.robertx22.age_of_exile.database.data.spells.components.actions.SpellAction;
import com.robertx22.age_of_exile.database.data.spells.components.selectors.BaseTargetSelector;
import com.robertx22.age_of_exile.database.data.spells.map_fields.MapField;
import com.robertx22.age_of_exile.mmorpg.ModRegistry;
import com.robertx22.age_of_exile.saveclasses.spells.calc.ValueCalculationData;
import com.robertx22.age_of_exile.uncommon.enumclasses.Elements;
import com.robertx22.age_of_exile.uncommon.utilityclasses.EntityFinder;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;

public class TestSpells {

    public static Spell FROSTBALL = Spell.Builder.of("frostball")
        .onCast(Builder.playSound(SoundEvents.ENTITY_SNOWBALL_THROW, 1D, 1D))
        .onCast(Builder.justAction(SpellAction.SUMMON_PROJECTILE.create(Items.SNOWBALL, 1D, 0.5D, ModRegistry.ENTITIES.SIMPLE_PROJECTILE, 80D, false)))
        .onTick(Builder.particleOnTick(3D, ParticleTypes.ITEM_SNOWBALL, 3D, 0.15D))
        .onHit(Builder.damage(ValueCalculationData.base(10), Elements.Water))
        .build();

    public static Spell FIREBALL = Spell.Builder.of("fireball")
        .onCast(Builder.playSound(SoundEvents.ITEM_FIRECHARGE_USE, 1D, 1D))
        .onCast(Builder.justAction(SpellAction.SUMMON_PROJECTILE.create(Items.FIRE_CHARGE, 1D, 0.5D, ModRegistry.ENTITIES.SIMPLE_PROJECTILE, 80D, false)))
        .onTick(Builder.particleOnTick(3D, ParticleTypes.FLAME, 3D, 0.15D))
        .onHit(Builder.damage(ValueCalculationData.base(10), Elements.Fire))
        .build();

    public static Spell POISONBALL = Spell.Builder.of("poisonball")
        .onCast(Builder.playSound(SoundEvents.ENTITY_SNOWBALL_THROW, 1D, 1D))
        .onCast(Builder.justAction(SpellAction.SUMMON_PROJECTILE.create(Items.SLIME_BALL, 1D, 0.5D, ModRegistry.ENTITIES.SIMPLE_PROJECTILE, 80D, false)))
        .onTick(Builder.particleOnTick(3D, ParticleTypes.ITEM_SLIME, 3D, 0.15D))
        .onHit(Builder.damage(ValueCalculationData.base(10), Elements.Nature))
        .build();

    public static Spell THROW_FLAMES = Spell.Builder.of("throw_flames")
        .onCast(Builder.playSound(SoundEvents.ITEM_FIRECHARGE_USE, 1D, 1D))
        .onCast(Builder.justAction(SpellAction.SUMMON_PROJECTILE.create(Items.BLAZE_POWDER, 3D, 0.5D, ModRegistry.ENTITIES.SIMPLE_PROJECTILE, 50D, false)
            .put(MapField.PROJECTILES_APART, 30D)))
        .onTick(Builder.particleOnTick(3D, ParticleTypes.FLAME, 5D, 0.15D))
        .onHit(Builder.damage(ValueCalculationData.base(7), Elements.Fire))
        .build();

    public static Spell TIDAL_WAVE = Spell.Builder.of("tidal_wave")
        .onCast(Builder.playSound(SoundEvents.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE, 1D, 1D))
        .onCast(Builder.justAction(SpellAction.SUMMON_PROJECTILE.create(Items.AIR, 5D, 0.6D, ModRegistry.ENTITIES.SIMPLE_PROJECTILE, 40D, true)
            .put(MapField.PROJECTILES_APART, 50D)))
        .onTick(Builder.particleOnTick(1D, ModRegistry.PARTICLES.BUBBLE, 15D, 0.15D))
        .onTick(Builder.particleOnTick(1D, ParticleTypes.BUBBLE_POP, 15D, 0.15D))
        .onHit(Builder.damage(ValueCalculationData.scaleWithAttack(0.25F, 4), Elements.Water))
        .build();

    public static Spell THUNDERSTORM = Spell.Builder.of("thunderstorm")
        .onCast(Builder.playSound(SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, 1D, 1D))
        .onCast(Builder.justAction(SpellAction.SUMMON_AT_SIGHT.create(ModRegistry.ENTITIES.SIMPLE_PROJECTILE, 100D, 4D)))
        .onTick(Builder.cloudParticle(2D, ParticleTypes.CLOUD, 20D, 4D))
        .onTick(Builder.cloudParticle(2D, ParticleTypes.FALLING_WATER, 20D, 4D))
        .onTick(Builder.onTickDamageInAoe(20D, ValueCalculationData.base(2), Elements.Thunder, 4D)
            .addChained(Builder.empty()
                .addTarget(BaseTargetSelector.AOE.create(4D, EntityFinder.SelectionType.RADIUS, EntityFinder.EntityPredicate.ENEMIES)
                    .put(MapField.SELECTION_CHANCE, 20D))
                .addActions(SpellAction.SUMMON_LIGHTNING_STRIKE.create())))
        .build();

    public static Spell WHIRLPOOL = Spell.Builder.of("whirlpool")
        .onCast(Builder.playSound(SoundEvents.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, 1D, 1D))
        .onCast(Builder.justAction(SpellAction.SUMMON_AT_SIGHT.create(ModRegistry.ENTITIES.SIMPLE_PROJECTILE, 100D, 0.5D)
            .put(MapField.EXPIRE_ON_HIT, false)
            .put(MapField.GRAVITY, true)))
        .onTick(Builder.groundParticle(1D, ParticleTypes.BUBBLE, 25D, 3.5D, 0.5D))
        .onTick(Builder.groundParticle(1D, ParticleTypes.BUBBLE_POP, 75D, 3.5D, 0.5D))
        .onTick(Builder.onTickDamageInAoe(20D, ValueCalculationData.base(3), Elements.Water, 3.5D)
            .addChained(Builder.playSoundPerTarget(SoundEvents.ENTITY_DROWNED_HURT, 1D, 1D)
                .addTarget(BaseTargetSelector.AOE.create(3.5D, EntityFinder.SelectionType.RADIUS, EntityFinder.EntityPredicate.ENEMIES))))
        .build();

    public static Spell BLIZZARD = Spell.Builder.of("blizzard")
        .onCast(Builder.playSound(SoundEvents.ENTITY_EVOKER_CAST_SPELL, 1D, 1D))
        .onCast(Builder.justAction(SpellAction.SUMMON_AT_SIGHT.create(ModRegistry.ENTITIES.SIMPLE_PROJECTILE, 100D, 4D)))
        .onTick(Builder.cloudParticle(2D, ParticleTypes.CLOUD, 20D, 4D))
        .onTick(Builder.cloudParticle(2D, ParticleTypes.ITEM_SNOWBALL, 20D, 4D))
        .onTick(Builder.onTickDamageInAoe(20D, ValueCalculationData.base(2), Elements.Water, 4D))
        .build();

}
