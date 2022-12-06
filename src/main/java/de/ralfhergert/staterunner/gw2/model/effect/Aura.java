package de.ralfhergert.staterunner.gw2.model.effect;

import de.ralfhergert.staterunner.gw2.model.Elapsing;

import java.time.Duration;

public class Aura extends Effect<Aura> implements Elapsing {

    private Duration duration;

    public Aura() {
        setStacking(Stacking.DURATION);
    }

    @Override
    public Aura createCopy() {
        return null;
    }

    @Override
    public void progress(Duration duration) {
        this.duration = this.duration.minus(duration);
    }

    public boolean isElapsed() {
        return duration.isNegative() || duration.isZero();
    }
}
