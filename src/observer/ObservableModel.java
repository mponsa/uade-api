package observer;

import java.util.ArrayList;
import java.util.List;

public class ObservableModel {
	private List<ObserverModel> observers = new ArrayList<ObserverModel>();
	
	private void add(ObserverModel obs){
		this.observers.add(obs);
	}
	
	private void remove(ObserverModel obs){
		this.observers.remove(obs);
	}
	
	private void notiAll(){
		for(ObserverModel e : this.observers){
			e.noti();
		}
	}
}
