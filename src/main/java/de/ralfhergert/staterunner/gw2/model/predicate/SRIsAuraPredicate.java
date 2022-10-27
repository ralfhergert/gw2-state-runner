package de.ralfhergert.staterunner.gw2.model.predicate;

import de.ralfhergert.staterunner.gw2.model.effect.Aura;
import de.ralfhergert.staterunner.gw2.model.StateRunner;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "isAura", namespace = StateRunner.NS)
@XmlAccessorType(XmlAccessType.FIELD)
public class SRIsAuraPredicate extends SRPredicate {

    @Override
    public boolean appliesTo(Object obj) {
        return obj instanceof Aura;
    }
}
