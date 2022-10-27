package de.ralfhergert.staterunner.gw2.model.attribution;

import java.util.stream.Stream;

public interface BelongsToGroups {

    Stream<String> getGroups();
}
