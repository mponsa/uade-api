package controlador;

import java.util.ArrayList;
import java.util.List;

public class ControladorDeamons {
	private static ControladorDeamons instancia;
	private List<Thread> deamons = new ArrayList<Thread>();
	
	public static ControladorDeamons getInstancia() {
		if (instancia == null) {
			instancia = new ControladorDeamons();
			return instancia;
		}else{
			return instancia;
		}
	}
	
	public ControladorDeamons() {

	}
	
	public void addDeamon(Thread deamon){
		this.deamons.add(deamon);
	}
	
	public void removeDeamon(Thread deamon) {
		this.deamons.remove(deamon);
	}
	
	public void startDeamons() {
		for (Thread h : deamons) {
			h.start();
		}
	}
	
	public void interruptDeamons(){
		for (Thread h : deamons) {
			h.interrupt();;
		}
	}
}
