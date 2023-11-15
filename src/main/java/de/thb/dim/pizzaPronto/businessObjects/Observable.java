package de.thb.dim.pizzaPronto.businessObjects;
import java.util.ArrayList;

import de.thb.dim.pizzaPronto.valueObjects.IObserver;

public abstract class Observable  implements IObservable{
	
	private ArrayList <IObserver> observers;
	
	
	public Observable() {
		observers = new ArrayList <IObserver>();
	}
	
	@Override
	public  void attach(IObserver o) {
		observers.add(o);
	}
	
	@Override
	public void detach(IObserver o) {
		observers.remove(o);
	}
	
	@Override
	public  void notify(IObservable observable,String msg) { //notify ist in Java durch Object vorgegeben
		for(IObserver os : observers) {
			os.update(observable,msg);
		}
		
	}

	public ArrayList<IObserver> getObservers() {
		return observers;
	}

	public void setObservers(ArrayList<IObserver> observers) {
		this.observers = observers;
	}
}
