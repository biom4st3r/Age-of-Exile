package com.robertx22.age_of_exile.mixin_methods;

import com.robertx22.age_of_exile.damage_hooks.LivingHurtUtils;
import com.robertx22.age_of_exile.damage_hooks.util.AttackInformation;

public class OnHurtEvent {

    public static float onHurtEvent(AttackInformation event) {

        if (event.getTargetEntity().world.isClient) {
            return event.getAmount();
        }

        try {
            // order matters here
            LivingHurtUtils.tryAttack(event);
            // order matters here

        } catch (Exception e) {
            e.printStackTrace();
        }

        return event.getAmount();

    }

}
