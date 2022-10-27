package de.ralfhergert.staterunner.gw2.model;

import java.time.Duration;

public interface Elapsing {

    void elapseTime(Duration duration);

    boolean isElapsed();
}
