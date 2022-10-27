package de.ralfhergert.staterunner.gw2.model.predicate;

import de.ralfhergert.staterunner.gw2.model.StateRunner;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "and", namespace = StateRunner.NS)
@XmlAccessorType(XmlAccessType.FIELD)
public class SRDelegatePredicate extends SRPredicate {

    @XmlElementRefs({
        @XmlElementRef(type = SRAndPredicate.class),
        @XmlElementRef(type = SRIsActionPredicate.class),
        @XmlElementRef(type = SRIsAuraPredicate.class),
        @XmlElementRef(type = SRInGroupPredicate.class),
        @XmlElementRef(type = SRNotPredicate.class),
        @XmlElementRef(type = SROrPredicate.class)
    })
    private SRPredicate predicate;

    @Override
    public boolean appliesTo(Object obj) {
        return predicate.appliesTo(obj);
    }
}
