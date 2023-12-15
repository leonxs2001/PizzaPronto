package de.thb.pizzaPronto.valueObjects;

import de.thb.pizzaPronto.businessObjects.IObservable;

public interface IObserver {
	
	public abstract void update(IObservable observable, String o);

}
