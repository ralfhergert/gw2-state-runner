package de.ralfhergert.staterunner.gw2.model.predicate;

import de.ralfhergert.staterunner.gw2.model.attribution.BelongsToGroups;
import de.ralfhergert.staterunner.gw2.model.StateRunner;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "inGroup", namespace = StateRunner.NS)
@XmlAccessorType(XmlAccessType.FIELD)
public class SRInGroupPredicate extends SRPredicate {

    @XmlAttribute
    private String group;

    @Override
    public boolean appliesTo(Object obj) {
        return obj instanceof BelongsToGroups &&
            ((BelongsToGroups)obj).getGroups().anyMatch(group::equals);
    }
}
