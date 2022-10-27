package de.ralfhergert.staterunner.gw2.model.modifier;

import de.ralfhergert.staterunner.gw2.model.StateRunner;
import de.ralfhergert.staterunner.gw2.model.TransactionModifier;
import de.ralfhergert.staterunner.gw2.model.function.DurationFunction;
import de.ralfhergert.staterunner.gw2.model.function.DurationMultiplyFunction;
import de.ralfhergert.staterunner.gw2.model.predicate.SRDelegatePredicate;
import de.ralfhergert.staterunner.gw2.model.transaction.MinimumCoolDownTransaction;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cooldown-modifier", namespace = StateRunner.NS)
@XmlAccessorType(XmlAccessType.FIELD)
public class CooldownModifier extends TransactionModifier<MinimumCoolDownTransaction> {

    @XmlElement(name = "predicate", namespace = StateRunner.NS)
    private SRDelegatePredicate predicate;

    @XmlElementRefs(
        @XmlElementRef(type = DurationMultiplyFunction.class)
    )
    private DurationFunction function = new DurationMultiplyFunction();

    @Override
    public MinimumCoolDownTransaction handle(MinimumCoolDownTransaction transaction) {
        if (predicate.appliesTo(transaction.getTarget())) {
            transaction.setDuration(function.apply(transaction.getDuration()));
        }
        return transaction;
    }
}
