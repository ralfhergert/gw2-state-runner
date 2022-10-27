package de.ralfhergert.staterunner.gw2.model.instruction;

import de.ralfhergert.jaxb.adapers.DurationAdapter;
import de.ralfhergert.staterunner.gw2.model.Build;
import de.ralfhergert.staterunner.gw2.model.Instruction;
import de.ralfhergert.staterunner.gw2.model.StateRunner;
import de.ralfhergert.staterunner.gw2.model.transaction.MinimumCoolDownTransaction;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.Duration;

@XmlRootElement(name = "enforce-cooldown", namespace = StateRunner.NS)
@XmlAccessorType(XmlAccessType.FIELD)
public class EnforceCoolDownInstruction extends Instruction {

    @XmlAttribute(name = "action")
    private String actionName;

    @XmlAttribute(name = "value")
    @XmlJavaTypeAdapter(DurationAdapter.class)
    private Duration duration;

    public String getActionName() {
        return actionName;
    }

    public EnforceCoolDownInstruction setActionName(String actionName) {
        this.actionName = actionName;
        return this;
    }

    @Override
    public void execute(Build build) {
        build.findAction(actionName)
            .map(action -> new MinimumCoolDownTransaction().setTarget(action).setDuration(duration))
            .ifPresent(build::transition);
    }

    @Override
    public String toString() {
        return "EnforceCoolDownInstruction{" +
            "actionName='" + actionName + '\'' +
            ", duration=" + duration +
            '}';
    }
}
