package de.ralfhergert.staterunner.gw2.model;

import de.ralfhergert.staterunner.gw2.model.transaction.SRTransaction;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;

/**
 * TransactionModifiers are used to manipulate a transaction before it is
 * performed.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class TransactionModifier<TransactionType extends SRTransaction> {

    @XmlAttribute
    private String description;

    public abstract TransactionType handle(TransactionType transaction);
}
