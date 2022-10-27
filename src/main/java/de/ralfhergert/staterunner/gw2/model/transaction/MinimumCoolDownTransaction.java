package de.ralfhergert.staterunner.gw2.model.transaction;

import de.ralfhergert.staterunner.gw2.model.attribution.HasCoolDown;

import java.time.Duration;

/**
 * This transaction will raise the cool down to the given duration
 * if and only if the current target's cooldown is lower. If the
 * target's cool down is higher as the given duration, it will not
 * be altered.
 */
public class MinimumCoolDownTransaction implements SRTransaction {

    private HasCoolDown target;

    private Duration duration;

    public HasCoolDown getTarget() {
        return target;
    }

    public MinimumCoolDownTransaction setTarget(HasCoolDown target) {
        this.target = target;
        return this;
    }

    public Duration getDuration() {
        return duration;
    }

    public MinimumCoolDownTransaction setDuration(Duration duration) {
        this.duration = duration;
        return this;
    }

    @Override
    public void perform() {
        if (target.getCoolDownRemaining().minus(duration).isNegative()) {
            target.setCoolDownRemaining(duration);
        }
    }
}
