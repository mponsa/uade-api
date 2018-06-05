package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import controlador.ControladorDeUsuarios;
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
			PreparedStatement s = con.prepareStatement("insert into [API_GRUPO_25].[dbo].[Usuarios] values (?,?,?,?,?,?)");
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
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void update(Object o) {
		try
		{
			Usuario a = (Usuario)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update [API_GRUPO_25].[dbo].[Usuarios] " +
					"set nombre = ?, " +
					" apellido = ?, " +
					" fechanac = ? " +
					"WHERE mail = ?");
			//agregar campos
			s.setString(1, a.getNombre());
			s.setString(2,a.getApellido());
			s.setDate(3,(Date) a.getFechaNac());
			s.setString(4,a.getMail());
			
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			System.out.println();
		}
		
	}

	@Override
	public void delete(Object d) {
		try
		{
			Usuario a = (Usuario)d;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update [API_GRUPO_25].[dbo].[Usuarios] set Activo = ? where mail = ?");
			s.setBoolean(1, false);
			s.setString(2, a.getMail());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public Vector<Object> select(Object o) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//Si mail es null, me trae todos, sino, al que corresponda
	public List<Usuario> buscarUsuarios(String mail)
	{
		try
		{
			List<Usuario> lista = new ArrayList<Usuario>();
			Usuario a = null;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			
			String query = "";
			if(mail != null)
				query = "select * from [API_GRUPO_25].[dbo].[Usuarios] where mail = ?";
			else
				query = "select * from [API_GRUPO_25].[dbo].[Usuarios]";
						
			PreparedStatement s = con.prepareStatement(query);
			
			//agregar campos
			if(mail != null)
				s.setString(1,mail);
			
			ResultSet result = s.executeQuery();
			while (result.next())
			{
				int cod = result.getInt(1);
				String nom = result.getString(2);
				String ape = result.getString(3);
				Date fec = result.getDate(4);
				String m = result.getString(5);
				String pas = result.getString(6);
				boolean act = result.getBoolean(7);


				a = new Usuario(cod,nom,ape,fec,m,pas, act);
				
				lista.add(a);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			return lista;
		}
		catch (Exception e)
		{
			System.out.println();
		}
		return null;
	}
	
	public Usuario getUsuario(String mail){
		Usuario a = null;
		try {
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from [API_GRUPO_25].[dbo].[Usuarios] where mail = ?");
			s.setString(1, mail);
			ResultSet result = s.executeQuery();
			while(result.next()) {
				a = new Usuario(result.getInt(1),result.getString(2),result.getString(3),result.getDate(4),result.getString(5),result.getString(6),result.getBoolean(7));
				return a;
			}
		}catch(Exception e) {
			System.out.println();
		}
		return null;
	}

}
