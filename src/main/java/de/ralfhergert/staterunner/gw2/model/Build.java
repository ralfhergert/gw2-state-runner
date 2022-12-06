package de.ralfhergert.staterunner.gw2.model;

import de.ralfhergert.jaxb.adapers.DurationAdapter;
import de.ralfhergert.staterunner.gw2.model.effect.Effect;
import de.ralfhergert.staterunner.gw2.model.transaction.SRTransaction;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@XmlRootElement(name = "build", namespace = StateRunner.NS)
@XmlAccessorType(XmlAccessType.FIELD)
public class Build implements Progressing {

    @XmlAttribute(name = "position-in-time")
    @XmlJavaTypeAdapter(DurationAdapter.class)
    private Duration posInTime = Duration.ZERO;

    @XmlElement(name = "action", namespace = StateRunner.NS)
    private List<Action> actions = new ArrayList<>();

    private List<Modifier> modifiers = new ArrayList<>();

    public Stream<Action> getActions() {
        return actions.stream();
    }

    @XmlElement(name = "trait", namespace = StateRunner.NS)
    private List<Trait> traits = new ArrayList<>();

    public void progress(Duration duration) {
        final AtomicReference<Duration> cooldownDuration = new AtomicReference<>(duration);
        modifiers.stream()
            .filter(mod -> mod instanceof CooldownModifier)
            .forEach(mod -> cooldownDuration.set(((CooldownModifier)mod).getForTimeElapsed(cooldownDuration.get())));

        actions.forEach(action -> action.progress(duration));
        actions.forEach(action -> action.coolDown(cooldownDuration.get()));

        modifiers.stream()
            .filter(mod -> mod instanceof Elapsing)
            .forEach(mod -> ((Elapsing)mod).progress(duration));
    }

    public Optional<Action> findAction(String name) {
        return actions.stream().filter(action -> name.equals(action.getName())).findAny();
    }

    public void triggerAction(String name) {
        Optional<Action> maybeAction = findAction(name);
        if (maybeAction.isEmpty()) {
            return;
        }
        Action foundAction = maybeAction.get();
        if (!foundAction.getCoolDownRemaining().isZero() ||
            !foundAction.isVisible() ||
            !foundAction.isAvailable()) {
            return;
        }
        foundAction.trigger(this);
    }

    public Build transition(SRTransaction transaction) {
        List<TransactionModifier> modifiers = traits.stream()
            .flatMap(Trait::getModifiers)
            .collect(Collectors.toList());

        for (TransactionModifier modifier : modifiers) {
            transaction = modifier.handle(transaction);
        }

        transaction.perform();

        List<TransactionListener<?>> listeners = traits.stream()
            .flatMap(Trait::getListeners)
            .collect(Collectors.toList());

        for (TransactionListener listener : listeners) {
            listener.acknowledge(transaction);
        }

        return this;
    }

    public Stream<Build> findTargets(TargetSpecifier targetSpecifier) {
        List<Build> targets = new ArrayList<>();
        if (targetSpecifier.isSelf()) {
            targets.add(this);
        }
        // TODO find closest surrounding
        return targets.stream();
    }

    public Build addEffect(Effect<?> effect) {
        return this;
    }
}
