package de.ralfhergert.staterunner.gw2.model.predicate;

import de.ralfhergert.staterunner.gw2.model.StateRunner;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlMixed;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "and", namespace = StateRunner.NS)
@XmlAccessorType(XmlAccessType.FIELD)
public class SRAndPredicate extends SRPredicate {

    @XmlMixed
    @XmlElementRefs({
        @XmlElementRef(type = SRAndPredicate.class),
        @XmlElementRef(type = SRIsActionPredicate.class),
        @XmlElementRef(type = SRInGroupPredicate.class),
        @XmlElementRef(type = SRNotPredicate.class),
        @XmlElementRef(type = SROrPredicate.class)
    })
    private List<SRPredicate> predicates = new ArrayList<>();

    @Override
    public boolean appliesTo(Object obj) {
        for (SRPredicate predicate : predicates) {
            if (!predicate.appliesTo(obj)) {
                return false;
            }
        }
        return true;
    }
}
