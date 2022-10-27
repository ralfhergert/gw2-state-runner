package de.ralfhergert.staterunner.gw2.model.instruction;

import de.ralfhergert.staterunner.gw2.model.Build;
import de.ralfhergert.staterunner.gw2.model.Instruction;
import de.ralfhergert.staterunner.gw2.model.StateRunner;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "show", namespace = StateRunner.NS)
@XmlAccessorType(XmlAccessType.FIELD)
public class ShowInstruction extends Instruction {

    @XmlAttribute(name = "action")
    private String actionName;

    @XmlAttribute(name = "group")
    private String groupName;

    public String getActionName() {
        return actionName;
    }

    public ShowInstruction setActionName(String actionName) {
        this.actionName = actionName;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public ShowInstruction setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    @Override
    public void execute(Build build) {
        if (actionName != null) {
            build.findAction(actionName)
                .ifPresent(value -> value.setVisible(true, build));
        }
        if (groupName != null) {
            build.getActions()
                .filter(action -> action.isInGroup(groupName))
                .forEach(action -> new ShowInstruction().setActionName(action.getName()).execute(build));
        }
    }

    @Override
    public String toString() {
        return "ShowInstruction{" +
            "actionName='" + actionName + '\'' +
            ", groupName='" + groupName + '\'' +
            '}';
    }
}
