package de.thb.dim.pizzaPronto.businessObjects;

import de.thb.dim.pizzaPronto.valueObjects.IObserver;

public interface IObservable {
	
	
	public  void attach(IObserver o);
	
	
	public void detach(IObserver o);
	
	
	public void notify(IObservable observable,String msg);
}
