package de.thb.dim.pizzaPronto.valueObjects;

import de.thb.dim.pizzaPronto.businessObjects.IObservable;

public interface IObserver {
	
	public abstract void update(IObservable observable,String o);

}
