package de.thb.pizzaPronto.generalGui;

import de.thb.pizzaPronto.authentication.rest.AuthenticatedUserVO;

import java.util.ArrayList;
import java.util.List;

public abstract class MainGUIObservable {
    List<IMainGUIObserver> observers;

    public MainGUIObservable() {
        observers = new ArrayList<>();
    }

    public void attach(IMainGUIObserver observer) {
        observers.add(observer);
    }

    public void detach(IMainGUIObserver observer) {
        observers.remove(observer);
    }

    public void notifyMainGUIObservers(){
        observers.forEach(observer -> observer.update());
    }
}
