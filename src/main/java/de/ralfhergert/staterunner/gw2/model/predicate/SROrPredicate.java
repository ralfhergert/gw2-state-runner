package de.ralfhergert.staterunner.gw2.model.predicate;

import de.ralfhergert.staterunner.gw2.model.StateRunner;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "or", namespace = StateRunner.NS)
@XmlAccessorType(XmlAccessType.FIELD)
public class SROrPredicate extends SRPredicate {

    private List<SRPredicate> predicates = new ArrayList<>();

    @Override
    public boolean appliesTo(Object obj) {
        for (SRPredicate predicate : predicates) {
            if (predicate.appliesTo(obj)) {
                return true;
            }
        }
        return false;
    }
}
