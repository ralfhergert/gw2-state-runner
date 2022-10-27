package de.ralfhergert.staterunner.gw2.model.transaction;

import de.ralfhergert.staterunner.gw2.model.Build;
import de.ralfhergert.staterunner.gw2.model.effect.Effect;

import java.time.Duration;

/**
 *
 */
public class ApplyEffectTransaction implements SRTransaction {

    private Build target;

    private Effect effect;

    public Build getTarget() {
        return target;
    }

    public ApplyEffectTransaction setTarget(Build target) {
        this.target = target;
        return this;
    }

    public Effect getEffect() {
        return effect;
    }

    public ApplyEffectTransaction setEffect(Effect effect) {
        this.effect = effect;
        return this;
    }

    @Override
    public void perform() {
        target.addEffect(effect);
    }
}
