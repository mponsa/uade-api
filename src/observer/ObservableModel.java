package observer;

import java.util.ArrayList;
import java.util.List;

public class ObservableModel {
	private List<ObserverModel> observers = new ArrayList<ObserverModel>();
	
	public void add(ObserverModel obs){
		this.observers.add(obs);
	}
	
	public void remove(ObserverModel obs){
		this.observers.remove(obs);
	}
	
	public void notiAll(){
		for(ObserverModel e : this.observers){
			e.noti();
		}
	}
}
