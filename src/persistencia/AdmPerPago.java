package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Pago;

public class AdmPerPago extends AdministradorPersistencia {

	private static AdmPerPago instancia;
	private  AdmPerPago(){
		
	}
	
	public static AdmPerPago getInstancia()
	{
		if (instancia == null)
			instancia = new AdmPerPago();
		return instancia;
	}
	
	@Override
	public int insert(Object o) {
		int key = -1;
		try
		{
			Pago a = (Pago)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("insert into [API_GRUPO_25].[dbo].[Pagos] values (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			//agregar campos
			s.setInt(1,a.getListaDeRegalo().getIdLista());
			s.setInt(2, a.getParticipante().getUsuario().getIdUsuario());
			s.setFloat(3,a.getMonto());
			s.setDate(4,(Date) a.getFecha());

			s.execute();
			
			//Devuelve la clave generada en la tabla Lista
			ResultSet rs = s.getGeneratedKeys();
			if(rs.next()) {
				key = rs.getInt(1);
			}
			PoolConnection.getPoolConnection().realeaseConnection(con);
			
		}catch (Exception e)
		{
			System.out.println("Mensaje Error: " + e.getMessage());
		}
	
		return key;
	}
	
	

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object d) {
		// TODO Auto-generated method stub
		
	}

}
