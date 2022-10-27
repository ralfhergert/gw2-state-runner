package de.ralfhergert.staterunner.gw2.model.listener;

import de.ralfhergert.staterunner.gw2.model.StateRunner;
import de.ralfhergert.staterunner.gw2.model.TransactionListener;
import de.ralfhergert.staterunner.gw2.model.operation.CopyEffectOperation;
import de.ralfhergert.staterunner.gw2.model.operation.Operation;
import de.ralfhergert.staterunner.gw2.model.predicate.SRDelegatePredicate;
import de.ralfhergert.staterunner.gw2.model.transaction.ApplyEffectTransaction;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "effect-listener", namespace = StateRunner.NS)
@XmlAccessorType(XmlAccessType.FIELD)
public class EffectListener extends TransactionListener<ApplyEffectTransaction> {

    @XmlElement(name = "predicate", namespace = StateRunner.NS)
    private SRDelegatePredicate predicate;

    @XmlElementRefs(
        @XmlElementRef(type = CopyEffectOperation.class)
    )
    private Operation operation;

    @Override
    public void acknowledge(ApplyEffectTransaction transaction) {
        if (predicate.appliesTo(transaction.getEffect())) {
            operation.perform(transaction);
        }
    }
}
