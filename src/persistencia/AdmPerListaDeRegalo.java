package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.ListaDeRegalo;
import model.Usuario;



public class AdmPerListaDeRegalo extends AdministradorPersistencia{

	private static AdmPerListaDeRegalo instancia;
	private  AdmPerListaDeRegalo()	{
		
	}
	public static AdmPerListaDeRegalo getInstancia()
	{
		if (instancia == null)
			instancia = new AdmPerListaDeRegalo();
		return instancia;
	}
	
	@Override
	public void insert(Object o) {
		try
		{
			ListaDeRegalo a = (ListaDeRegalo)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("insert into [API_GRUPO_25].[dbo].[ListaDeRegalo] values (?,?,?,?,?,?,?)");
			//agregar campos
			s.setString(1,a.getNombre());
			s.setDate(2,(Date) a.getVigencia());
			s.setString(3, a.getAgasajado());
			s.setFloat(4,0);
			s.setBoolean(5, a.getEstado());
			s.setBoolean(5, a.isActivo());
			s.setFloat(7,a.getMontoPorParticipante());
			
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			System.out.println("Mensaje Error: " + e.getMessage());
		}
	}

	@Override
	public void update(Object o) {
		try
		{
			/*DM-No se podrá actualizar el IdLista (Identity), 
			ni el nombre de Agasajado (se elimina y se crea una nueva)
			Monto (Se actualiza conforme se registren pagos)
			MontoXParticipante (Se deberia modificar aparte, validando que no hayan pagos registrados)
			Estado (Se modifica al completarse la lista o cerrarse por vigencia)
			Activo (Se elimina logicamente en el Delete)
			*/
			ListaDeRegalo a = (ListaDeRegalo)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update [API_GRUPO_25].[dbo].[ListaDeRegalo] " +
					"set Nombre = ?, " +
					" Vigencia = ?, " +

					"WHERE IdLista = ?");
			//agregar campos
			s.setString(1, a.getNombre());
			s.setDate(2,(Date) a.getVigencia());
			s.setInt(3, a.getIdLista());
			
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			System.out.println("Mensaje Error: " + e.getMessage());
		}
		
	}

	@Override
	public void delete(Object d) {
		try
		{
			ListaDeRegalo a = (ListaDeRegalo)d;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update [API_GRUPO_25].[dbo].[ListaDeRegalo] set Activo = ? where IdLista = ?");
			s.setBoolean(1, false);
			s.setInt(2, a.getIdLista());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			System.out.println("Mensaje Error: " + e.getMessage());
		}
	}
	
	public ListaDeRegalo getListaDeRegalo(int id){
		ListaDeRegalo a = null;
		try {
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("select * from [API_GRUPO_25].[dbo].[ListaDeRegalo] where IdLista = ?");
			s.setInt(1, id);
			ResultSet result = s.executeQuery();
			while(result.next()) {
				a = new ListaDeRegalo(
						result.getInt(1)
						,result.getString(2)
						,result.getDate(3)
						,result.getString(4)
						,result.getBoolean(5)
						,result.getBoolean(6)
						,result.getFloat(7));
				return a;
			}
		}catch(Exception e) {
			System.out.println();
		}
		return null;
	}

}
