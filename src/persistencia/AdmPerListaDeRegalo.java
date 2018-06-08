package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.ListaDeRegalo;
import model.Participante;
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
			s.setBoolean(6, a.isActivo());
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
		Participante part = null;
		
		try {
			Connection con = PoolConnection.getPoolConnection().getConnection();
			
			//Traigo la lista
			PreparedStatement s = con.prepareStatement("select * from [API_GRUPO_25].[dbo].[ListaDeRegalo] where IdLista = ?");
			s.setInt(1, id);
			ResultSet result = s.executeQuery();
			while(result.next()) {
				a = new ListaDeRegalo(
						result.getInt(1)
						,result.getString(2)
						,result.getDate(3)
						,result.getString(4)
						,result.getFloat(5)
						,result.getBoolean(6)
						,result.getBoolean(7)
						,result.getFloat(8));
			}
			
			//Luego me traigo los participantes de dicha lista
			PreparedStatement p = con.prepareStatement("select * from [API_GRUPO_25].[dbo].[Participantes] where IdLista = ?");
			p.setInt(1, a.getIdLista());
			ResultSet resultP = p.executeQuery();
			while(resultP.next()) {
				part = new Participante(
						resultP.getInt(1),
						resultP.getString(2),
						resultP.getBoolean(3)
						);
				a.addUser(part);
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			
			return a;
			
		}catch(Exception e) {
			System.out.println("Mensaje Error: " + e.getMessage());
		}
		return null;
	}
	
	public ListaDeRegalo getListaDeRegalo(String nombre) {
		ListaDeRegalo a = null;
		Participante part = null;
		try {
			Connection con = PoolConnection.getPoolConnection().getConnection();
			
			//Traigo la lista
			PreparedStatement s = con.prepareStatement("select * from [API_GRUPO_25].[dbo].[ListaDeRegalo] where Nombre = ?");
			s.setString(1, nombre);
			ResultSet result = s.executeQuery();
			while(result.next()) {
				a = new ListaDeRegalo(
						result.getInt(1)
						,result.getString(2)
						,result.getDate(3)
						,result.getString(4)
						,result.getFloat(5)
						,result.getBoolean(6)
						,result.getBoolean(7)
						,result.getFloat(8));
			}	
			
			//Luego me traigo los participantes de dicha lista
			PreparedStatement p = con.prepareStatement("select * from [API_GRUPO_25].[dbo].[Participantes] where IdLista = ?");
			p.setInt(1, a.getIdLista());
			ResultSet resultP = p.executeQuery();
			while(resultP.next()) {
				part = new Participante(
						resultP.getInt(1),
						resultP.getString(2),
						resultP.getBoolean(3)
						);
				a.addUser(part);
			}
			
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
				
			return a;
				
			
		}catch(Exception e) {
			System.out.println("Mensaje Error: " + e.getMessage());
		}
		return null;
	}
	
	public void insertParticipantesALista(ListaDeRegalo l){
		try{
			for (Participante p : l.getUsuarios()) {
				Connection con = PoolConnection.getPoolConnection().getConnection();
				PreparedStatement s = con.prepareStatement("insert into [API_GRUPO_25].[dbo].[Participante] values (?,?,?)");
				s.setInt(1, p.getIdLista());
				s.setString(2, p.getMailUsuario());
				s.setBoolean(3, p.isPagado());
				
				s.execute();
				PoolConnection.getPoolConnection().realeaseConnection(con);
			}
			
		}catch(Exception e){
			System.out.println("Mensaje Error: " + e.getMessage());
		}
		
	}

}
