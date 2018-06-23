package persistencia;

import java.util.Vector;

public abstract class AdministradorPersistencia {
	public abstract int insert (Object o);
	public abstract void update (Object o);
	public abstract void delete (Object d);
}
