package de.ralfhergert.staterunner.gw2.model.predicate;

import de.ralfhergert.staterunner.gw2.model.StateRunner;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlMixed;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "not", namespace = StateRunner.NS)
@XmlAccessorType(XmlAccessType.FIELD)
public class SRNotPredicate extends SRPredicate {

    @XmlMixed
    private SRPredicate predicate;

    @Override
    public boolean appliesTo(Object obj) {
        return predicate != null && !predicate.appliesTo(obj);
    }
}
