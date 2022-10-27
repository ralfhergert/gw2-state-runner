package de.ralfhergert.staterunner.gw2.model.operation;

import de.ralfhergert.staterunner.gw2.model.StateRunner;
import de.ralfhergert.staterunner.gw2.model.TargetSpecifier;
import de.ralfhergert.staterunner.gw2.model.transaction.ApplyEffectTransaction;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "copy-effect", namespace = StateRunner.NS)
@XmlAccessorType(XmlAccessType.FIELD)
public class CopyEffectOperation extends Operation<ApplyEffectTransaction> {

    @XmlElement(name = "target")
    private TargetSpecifier targetSpecifier;

    @Override
    public void perform(ApplyEffectTransaction transaction) {
        transaction.getTarget()
            .findTargets(targetSpecifier)
            .forEach(target -> new ApplyEffectTransaction()
                .setTarget(target)
                .setEffect(transaction.getEffect().createCopy())
                .perform()
            );
    }
}
