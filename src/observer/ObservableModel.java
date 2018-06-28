package observer;

import java.util.ArrayList;
import java.util.List;

public class ObservableSP {
	private List<ObserverSP> observers = new ArrayList<ObserverSP>();
	
	private void add(ObserverSP obs){
		this.observers.add(obs);
	}
	
	private void remove(ObserverSP obs){
		this.observers.remove(obs);
	}
	
	private void notiAll(){
		for(ObserverSP e : this.observers){
			e.noti();
		}
	}
}
