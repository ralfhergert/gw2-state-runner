package de.ralfhergert.staterunner.gw2.model;

import de.ralfhergert.staterunner.gw2.model.transaction.SRTransaction;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;

/**
 * TransactionModifiers are used to get triggered after a transaction was performed.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class TransactionListener<TransactionType extends SRTransaction> {

    @XmlAttribute
    private String description;

    public abstract void acknowledge(TransactionType transaction);
}
