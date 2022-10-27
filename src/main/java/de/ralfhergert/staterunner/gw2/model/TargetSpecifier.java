package de.ralfhergert.staterunner.gw2.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "target", namespace = StateRunner.NS)
@XmlAccessorType(XmlAccessType.FIELD)
public class TargetSpecifier {

    enum Shape {
        @XmlEnumValue("circle")
        CIRCLE,
        @XmlEnumValue("cone")
        CONE
    }
    @XmlAttribute
    private boolean self = true;

    @XmlAttribute
    private int friends = 0;

    @XmlAttribute
    private int foes = 0;

    @XmlAttribute(name = "max-distance")
    private int maxDistance = 0;

    @XmlAttribute
    private Shape shape = Shape.CIRCLE;

    public boolean isSelf() {
        return self;
    }

    public void setSelf(boolean self) {
        this.self = self;
    }

    public int getFriends() {
        return friends;
    }

    public void setFriends(int friends) {
        this.friends = friends;
    }

    public int getFoes() {
        return foes;
    }

    public void setFoes(int foes) {
        this.foes = foes;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }
}
