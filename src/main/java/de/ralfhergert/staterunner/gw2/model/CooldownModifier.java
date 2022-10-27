package de.ralfhergert.staterunner.gw2.model;

import java.time.Duration;

public interface CooldownModifier {

    Duration getForTimeElapsed(Duration time);
}
