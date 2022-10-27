package de.ralfhergert.staterunner.gw2.model.attribution;

import java.time.Duration;

public interface HasCoolDown<Self> {

    Duration getCoolDownRemaining();

    Self setCoolDownRemaining(Duration duration);
}
