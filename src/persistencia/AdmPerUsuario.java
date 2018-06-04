package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.Vector;


import persistencia.PoolConnection;
import model.Usuario;
import persistencia.AdmPerUsuario;
import persistencia.AdministradorPersistencia;

public class AdmPerUsuario extends AdministradorPersistencia{

private static AdmPerUsuario instancia;
	private  AdmPerUsuario()	{
		
	}
	public static AdmPerUsuario getInstancia()
	{
		if (instancia == null)
			instancia = new AdmPerUsuario();
		return instancia;
	}
	
	@Override
	public void insert(Object o) {
		try
		{
			Usuario a = (Usuario)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("insert into Usuarios values (?,?,?,?,?,?)");
			//agregar campos
			s.setString(1,a.getNombre());
			s.setString(2, a.getApellido());
			s.setDate(3,(Date) a.getFechaNac());
			s.setString(4, a.getMail());
			s.setString(5,a.getPassword());
			s.setBoolean(6, a.getActivo());

			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			System.out.println();
		}
		
	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector<Object> select(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

}
