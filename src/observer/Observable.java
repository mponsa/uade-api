package observer;

import java.util.ArrayList;
import java.util.List;

public class Observable {
	private List<IObserver> observers = new ArrayList<IObserver>();
	
	private void add(IObserver obs){
		this.observers.add(obs);
	}
	
	private void remove(IObserver obs){
		this.observers.remove(obs);
	}
	
	private void notiAll(){
		for(IObserver e : this.observers){
			e.noti();
		}
	}
}
