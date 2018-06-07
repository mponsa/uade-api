package view;

public class Parametros {
	private static Parametros instancia;
	private static String title = "Lista de Regalos v0.1";
	
	public static Parametros getInstancia(){
		if (instancia == null){
			instancia = new Parametros();
			return instancia;
		}else{
			return instancia;
		}
		
	}
	
	public String getTitle(){
		return title;
	}
}
