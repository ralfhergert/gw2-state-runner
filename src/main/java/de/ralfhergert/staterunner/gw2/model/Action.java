package de.ralfhergert.staterunner.gw2.model;

import de.ralfhergert.jaxb.adapers.DurationAdapter;
import de.ralfhergert.staterunner.gw2.model.attribution.BelongsToGroups;
import de.ralfhergert.staterunner.gw2.model.attribution.HasCoolDown;
import de.ralfhergert.staterunner.gw2.model.instruction.EnforceCoolDownInstruction;
import de.ralfhergert.staterunner.gw2.model.instruction.HideInstruction;
import de.ralfhergert.staterunner.gw2.model.instruction.ShowInstruction;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@XmlRootElement(name = "action", namespace = StateRunner.NS)
@XmlAccessorType(XmlAccessType.FIELD)
public class Action implements Elapsing, BelongsToGroups, HasCoolDown<Action> {

    @XmlAttribute(required = true)
    private String name;

    /**
     * Visibility controls whether the player has access to this action.
     * Visibility is usually used for weapon skills making only the skills
     * of the current weapon set visible.
     * Visibility is not the only determining factor for whether an action
     * can be used: the action also must be {@link #available}.
     */
    @XmlAttribute
    private boolean visible = true;

    /**
     * Availability means whether the action is available to the player.
     * Availability is usually used for flipping skills like:
     * <ul>
     *     <li>Elementalist Water Dagger 4 shows "Frost Aura", but as soon
     *     as the Elementalist gets a Frost Aura effect, the skill "Frost Aura"
     *     becomes unavailable and flips out for the skill "Transmute Frost".
     *     </li>
     *     <li>Ranger Greatsword 4 the if the skill "Counterattack" did
     *     block an attack, then "Counterattack" is flipped out and the
     *     skill "Counterattack Kick" becomes available.</li>
     * </ul>
     * Availability is not the only determining factor for whether an action
     * can be used: the action also must be {@link #visible}.
     */
    @XmlAttribute
    private boolean available = true;

    @XmlElement(name = "group", namespace = StateRunner.NS)
    private List<String> groups = new ArrayList<>();

    @XmlAttribute(name = "cooldown-remaining", namespace = StateRunner.NS)
    @XmlJavaTypeAdapter(DurationAdapter.class)
    private Duration coolDownRemaining = Duration.ZERO;

    @XmlAttribute(name = "cooldown-typical", required = true, namespace = StateRunner.NS)
    @XmlJavaTypeAdapter(DurationAdapter.class)
    private Duration coolDownTypical;

    @XmlElementWrapper(name = "on-trigger", namespace = StateRunner.NS)
    @XmlElementRefs({
        @XmlElementRef(type = HideInstruction.class),
        @XmlElementRef(type = ShowInstruction.class),
        @XmlElementRef(type = EnforceCoolDownInstruction.class)
    })
    private List<Instruction> onTrigger = new ArrayList<>();

    @XmlElementWrapper(name = "on-hide", namespace = StateRunner.NS)
    @XmlElementRefs({
        @XmlElementRef(type = HideInstruction.class),
        @XmlElementRef(type = ShowInstruction.class),
        @XmlElementRef(type = EnforceCoolDownInstruction.class)
    })
    private List<Instruction> onHide = new ArrayList<>();

    @XmlElementWrapper(name = "on-show", namespace = StateRunner.NS)
    @XmlElementRefs({
        @XmlElementRef(type = HideInstruction.class),
        @XmlElementRef(type = ShowInstruction.class),
        @XmlElementRef(type = EnforceCoolDownInstruction.class)
    })
    private List<Instruction> onShow = new ArrayList<>();

    public Action() { /* nothing to do */ }

    public String getName() {
        return name;
    }

    public Action setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isVisible() {
        return visible;
    }

    public Action setVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public Action setVisible(boolean visible, Build build) {
        if (this.visible == visible) {
            return this;
        }
        this.visible = visible;
        if (this.visible) { // action did become visible
            onShow.forEach(instruction -> instruction.execute(build));
        } else {
            onHide.forEach(instruction -> instruction.execute(build));
        }
        return this;
    }

    public boolean isAvailable() {
        return available;
    }

    public Action setAvailable(boolean available) {
        this.available = available;
        return this;
    }

    @Override
    public Stream<String> getGroups() {
        return groups.stream();
    }

    public Action setGroups(List<String> groups) {
        this.groups.clear();
        this.groups.addAll(groups);
        return this;
    }

    public Action addGroup(String group) {
        this.groups.add(group);
        return this;
    }

    public boolean isInGroup(String group) {
        return groups.contains(group);
    }

    public Duration getCoolDownRemaining() {
        return coolDownRemaining;
    }

    public Action setCoolDownRemaining(Duration coolDownRemaining) {
        this.coolDownRemaining = coolDownRemaining;
        return this;
    }

    public Duration getCoolDownTypical() {
        return coolDownTypical;
    }

    public Action setCoolDownTypical(Duration coolDownTypical) {
        this.coolDownTypical = coolDownTypical;
        return this;
    }

    public Stream<Instruction> getOnHideInstructions() {
        return onHide.stream();
    }

    public Stream<Instruction> getOnShowInstructions() {
        return onShow.stream();
    }

    public void trigger(Build build) {
        onTrigger.forEach(instruction -> instruction.execute(build));
    }

    @Override
    public void progress(Duration duration) {

    }

    @Override
    public boolean isElapsed() {
        return false;
    }

    public void coolDown(Duration duration) {

    }

    @Override
    public String toString() {
        return "Action{" +
            "name='" + name + '\'' +
            ", visible=" + visible +
            ", available=" + available +
            ", groups=" + groups +
            ", coolDownRemaining=" + coolDownRemaining +
            ", coolDownTypical=" + coolDownTypical +
            ", onTrigger=" + onTrigger +
            ", onHide=" + onHide +
            ", onShow=" + onShow +
            '}';
    }
}
