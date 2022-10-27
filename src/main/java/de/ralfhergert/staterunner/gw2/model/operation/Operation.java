package de.ralfhergert.staterunner.gw2.model.operation;

import de.ralfhergert.staterunner.gw2.model.Build;

public abstract class Operation<Transaction> {

    public abstract void perform(Transaction transaction);
}
