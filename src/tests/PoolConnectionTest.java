package tests;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

import persistencia.PoolConnection;

public class PoolConnectionTest {
	
	@Test
	public void testGetConfiguration(){
		
		Connection con = PoolConnection.getPoolConnection().getConnection();
		
		assertTrue(con != null );
	}

}
