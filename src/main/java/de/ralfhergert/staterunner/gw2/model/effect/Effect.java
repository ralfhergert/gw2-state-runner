package de.ralfhergert.staterunner.gw2.model.effect;

import de.ralfhergert.staterunner.gw2.model.Modifier;
import de.ralfhergert.staterunner.gw2.model.StateRunner;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

import java.util.UUID;

@XmlRootElement(name = "effect", namespace = StateRunner.NS)
@XmlAccessorType(XmlAccessType.FIELD)
public class Effect<Self extends Effect> extends Modifier {

    enum Stacking {
        @XmlEnumValue("duration")
        DURATION,
        @XmlEnumValue("intensity")
        INTENSITY
    }

    @XmlTransient
    private UUID uuid = UUID.randomUUID();

    @XmlTransient
    private Stacking stacking = Stacking.DURATION;

    @XmlAttribute
    private String name;

    public UUID getUuid() {
        return uuid;
    }

    public Stacking getStacking() {
        return stacking;
    }

    public void setStacking(Stacking stacking) {
        this.stacking = stacking;
    }

    public String getName() {
        return name;
    }

    public Self setName(String name) {
        this.name = name;
        return (Self)this;
    }

    public Self createCopy() {
        return new Effect<Self>().setName(getName());
    }

    /**
     * The UUID will not be copied.
     */
    public void copyOnto(Effect effect) {
        effect.setName(getName());
    }
}
