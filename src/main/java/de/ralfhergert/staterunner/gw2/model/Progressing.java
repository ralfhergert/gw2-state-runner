package de.ralfhergert.staterunner.gw2.model;

import java.time.Duration;

public interface Progressing {

    void progress(Duration duration);
}
