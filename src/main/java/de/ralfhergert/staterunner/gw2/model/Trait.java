package de.ralfhergert.staterunner.gw2.model;

import de.ralfhergert.staterunner.gw2.model.modifier.CooldownModifier;

import de.ralfhergert.staterunner.gw2.model.operation.CopyEffectOperation;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElementRef;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@XmlAccessorType(XmlAccessType.FIELD)
public class Trait {

    @XmlAttribute(required = true)
    private String name;

    @XmlElementRef(type = CooldownModifier.class)
    private List<TransactionModifier<?>> modifiers = new ArrayList<>();

    @XmlElementRef(type = CopyEffectOperation.class)
    private List<TransactionListener<?>> listeners = new ArrayList<>();

    public Stream<TransactionModifier<?>> getModifiers() {
        return modifiers.stream();
    }

    public Stream<TransactionListener<?>> getListeners() {
        return listeners.stream();
    }
}
