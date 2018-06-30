package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.ListaDeRegalo;
import model.Pago;
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
	public int insert(Object o) {	
		int key = -1;
		try
		{
			ListaDeRegalo a = (ListaDeRegalo)o;
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("insert into [API_GRUPO_25].[dbo].[ListaDeRegalo] values (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			//agregar campos
			s.setString(1,a.getNombre());
			s.setDate(2,(Date) a.getVigencia());
			s.setString(3, a.getAgasajado());
			s.setFloat(4,0);
			s.setBoolean(5, a.getEstado());
			s.setBoolean(6, a.isActivo());
			s.setFloat(7,a.getMontoPorParticipante());
			s.execute();
			//Devuelve la clave generada en la tabla Lista
			ResultSet rs = s.getGeneratedKeys();
			if(rs.next()) {
				key = rs.getInt(1);
			}
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}
		catch (Exception e)
		{
			System.out.println("Mensaje Error: " + e.getMessage());
		}
	
		return key;
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
					"set Nombre = ?," +
					" Vigencia = ?," +
					" Monto = ?," +
					" Estado = ? "  +
					"WHERE IdLista = ?");
			//agregar campos
			s.setString(1, a.getNombre());
			s.setDate(2,(Date) a.getVigencia());
			s.setFloat(3, a.getMonto());
			s.setBoolean(4, a.getEstado());
			s.setInt(5, a.getIdLista());
			
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
	
	public void insertParticipante(ListaDeRegalo l, Participante p){
		try{
				Connection con = PoolConnection.getPoolConnection().getConnection();
				PreparedStatement s = con.prepareStatement("insert into [API_GRUPO_25].[dbo].[Participantes] values (?,?,?,?,?)");
				s.setInt(1, l.getIdLista());
				s.setInt(2, p.getUsuario().getIdUsuario());
				s.setBoolean(3,p.isAdmin());
				s.setBoolean(4, p.isPagado());
				s.setBoolean(5, p.isActivo());
				s.execute();
				PoolConnection.getPoolConnection().realeaseConnection(con);
		}catch(Exception e){
			System.out.println("Mensaje Error: " + e.getMessage());
		}
		
	}
	
	public void updateParticipante(ListaDeRegalo l, Participante p){
		try {
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update [API_GRUPO_25].[dbo].[Participantes] set Pagado = ? where IdLista = ? AND IdUsuario = ?");
			s.setBoolean(1,p.isPagado());
			s.setInt(2, l.getIdLista());
			s.setInt(3, p.getUsuario().getIdUsuario());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}catch(Exception e) {
			System.out.println("Mensaje Error: " + e.getMessage());
		}
	}
	
	public void deleteParticipante(ListaDeRegalo l, Participante p) {
		try {
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update [API_GRUPO_25].[dbo].[Participantes] set Activo = ? where IdLista = ? AND IdUsuario = ?");
			s.setBoolean(1,p.isActivo());
			s.setInt(2, l.getIdLista());
			s.setInt(3, p.getUsuario().getIdUsuario());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}catch(Exception e) {
			System.out.println("Mensaje Error: " + e.getMessage());
		}
	}
	
	//Setea como inactivos todos los participantes de una lista de regalo. Utilizado cuando se borra una lista.
	public void deleteParticipantes(ListaDeRegalo l){
		try {
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update [API_GRUPO_25].[dbo].[Participantes] set Activo = ? where IdLista = ?");
			s.setBoolean(1,false);
			s.setInt(2, l.getIdLista());
			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);
		}catch(Exception e) {
			System.out.println("Mensaje Error: " + e.getMessage());
		}
	}
	

	//Trae una lista de la BD por ID..
	public ListaDeRegalo getListaDeRegalo(int id){
		ListaDeRegalo a = null;	
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
			PreparedStatement p = con.prepareStatement("select * from [API_GRUPO_25].[dbo].[Participantes] where IdLista = ? and Activo = 1");
			p.setInt(1, a.getIdLista());
			ResultSet resultP = p.executeQuery();
			while(resultP.next()) {
				a.addParticipante(AdmPerUsuario.getInstancia().getUsuario(resultP.getInt(2)), resultP.getBoolean(3),resultP.getBoolean(4));
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			
			return a;
			
		}catch(Exception e) {
			System.out.println("Mensaje Error: " + e.getMessage());
		}
		return null;
	}
	
	//Trae una lista de la BD por nombre
	public ListaDeRegalo getListaDeRegalo(String nombre){
		ListaDeRegalo a = null;	
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
			PreparedStatement p = con.prepareStatement("select * from [API_GRUPO_25].[dbo].[Participantes] where IdLista = ? and Activo = 1");
			p.setInt(1, a.getIdLista());
			ResultSet resultP = p.executeQuery();
			while(resultP.next()) {
				a.addParticipante(AdmPerUsuario.getInstancia().getUsuario(resultP.getInt(2)), resultP.getBoolean(3),resultP.getBoolean(4));
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			
			return a;
			
		}catch(Exception e) {
			System.out.println("Mensaje Error: " + e.getMessage());
		}
		return null;
	}

	
	//Devuelve las listas administradas por un usuario
	public List<ListaDeRegalo> getListasAdm(Usuario user) {
		List<ListaDeRegalo> result = new ArrayList<ListaDeRegalo>();
		try {
		Connection con = PoolConnection.getPoolConnection().getConnection();
		PreparedStatement s = con.prepareStatement("select * from [API_GRUPO_25].[dbo].[Participantes] where IdUsuario = ? AND isAdmin = '1' AND Activo = '1'");
		s.setInt(1,user.getIdUsuario());
		ResultSet resultP = s.executeQuery();
		while(resultP.next()){
			result.add(this.getListaDeRegalo(resultP.getInt(1)));
		}
		}catch(Exception e) {
			System.out.println("Mensaje Error: " + e.getMessage());
		}
		if (result.size() == 0) {
			return null;
		}else {
			return result;
		}
	}
	
	public List<ListaDeRegalo> getListasPar(Usuario user) {
		List<ListaDeRegalo> result = new ArrayList<ListaDeRegalo>();
		try {
		Connection con = PoolConnection.getPoolConnection().getConnection();
		PreparedStatement s = con.prepareStatement("select * from [API_GRUPO_25].[dbo].[Participantes] where IdUsuario = ? AND IsAdmin = '0' AND Activo = '1'");
		s.setInt(1,user.getIdUsuario());
		ResultSet resultP = s.executeQuery();
		while(resultP.next()){
			result.add(this.getListaDeRegalo(resultP.getInt(1)));
		}
		}catch(Exception e) {
			System.out.println("Mensaje Error: " + e.getMessage());
		}
		if (result.size() == 0) {
			return null;
		}else {
			return result;
		}
	}
	
	public List<ListaDeRegalo> getListasCalendar(int cantDias){
		List<ListaDeRegalo> result = new ArrayList<ListaDeRegalo>();
		ListaDeRegalo a = null;	
		try {
			Connection con = PoolConnection.getPoolConnection().getConnection();
			
			//Traigo la lista
			PreparedStatement s = con.prepareStatement("SELECT * FROM [API_GRUPO_25].[dbo].[ListaDeRegalo] WHERE datediff(DD, getDate(),Vigencia ) <  ?"+
														" AND Estado = 0 AND Activo = 1");
			s.setInt(1, cantDias);
			ResultSet resultS = s.executeQuery();
			while(resultS.next()) {
				a = new ListaDeRegalo(
						resultS.getInt(1)
						,resultS.getString(2)
						,resultS.getDate(3)
						,resultS.getString(4)
						,resultS.getFloat(5)
						,resultS.getBoolean(6)
						,resultS.getBoolean(7)
						,resultS.getFloat(8));
			}
			
			//Luego me traigo los participantes de dicha lista
			PreparedStatement p = con.prepareStatement("select * from [API_GRUPO_25].[dbo].[Participantes] where IdLista = ? and Activo = 1");
			p.setInt(1, a.getIdLista());
			ResultSet resultP = p.executeQuery();
			while(resultP.next()) {
				a.addParticipante(AdmPerUsuario.getInstancia().getUsuario(resultP.getInt(2)), resultP.getBoolean(3), resultP.getBoolean(4));
			}
			
			PoolConnection.getPoolConnection().realeaseConnection(con);
			
			result.add(a);
			
		}catch(Exception e) {
			System.out.println("Mensaje Error: " + e.getMessage());
		}
		
		return result;
	}
	
	public void registrarPago(Pago p){
		try
		{
			Connection con = PoolConnection.getPoolConnection().getConnection();		
			PreparedStatement s = con.prepareStatement("UPDATE [API_GRUPO_25].[dbo].[Participantes] " +
					"SET Pagado = 1 " +
					"SET Monto += ?" +
					"WHERE IdLista = ? " +
					" AND IdUsuario = (SELECT IdUsuario FROM [API_GRUPO_25].[dbo].[Usuarios] WHERE Mail = ? )");

			//agregar campos
			s.setInt(1,p.getListaDeRegalo().getIdLista());
			s.setInt(2, p.getParticipante().getUsuario().getIdUsuario());

			s.execute();
			PoolConnection.getPoolConnection().realeaseConnection(con);

		}catch (Exception e)
		{
			System.out.println("Mensaje Error: " + e.getMessage());
		}
	}
}
