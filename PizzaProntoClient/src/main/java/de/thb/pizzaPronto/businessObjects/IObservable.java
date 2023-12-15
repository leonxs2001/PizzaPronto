package de.thb.pizzaPronto.businessObjects;

import de.thb.pizzaPronto.valueObjects.IObserver;

public interface IObservable {
	
	
	public  void attach(IObserver o);
	
	
	public void detach(IObserver o);
	
	
	public void notify(IObservable observable,String msg);
}
