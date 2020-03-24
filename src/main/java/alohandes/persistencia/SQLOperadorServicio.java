/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Miguel Parra y Andres Benitez
 * Marzo de 2020
 * 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package alohandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import alohandes.negocio.OperadorServicio;



/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto 
 * operador - servicio de Alohandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Miguel Parra
 */
public class SQLOperadorServicio {
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaAlohandes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaAlohandes pp;
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLOperadorServicio (PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	public long adicionarOperadorServicio (PersistenceManager pm,long operadorId, long servicioId ) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHostal()  + "(operadorId,  servicioId) values (?, ?)");
        q.setParameters(operadorId,  servicioId);
        return (long) q.executeUnique();
	}
	public long eliminarOperadorServicioPorId (PersistenceManager pm, long operadorId, long servicioId)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOperadorServicio() + " WHERE operadorID = ? AND  servicioId = ?");
        q.setParameters(operadorId,servicioId);
        return (long) q.executeUnique();
	}
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN BAR de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador del bar
	 * @return El objeto BAR que tiene el identificador dado
	 */
	public OperadorServicio darOperadorServicioPorId (PersistenceManager pm, long operadorId, long servicioId) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperadorServicio () + " WHERE operadorID = ? AND  servicioId = ?");
		q.setResultClass(OperadorServicio.class);
		q.setParameters(operadorId, servicioId);
		return (OperadorServicio) q.executeUnique();
	}

	

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos BAR
	 */
	public List<OperadorServicio> darOperadorServicios (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOperadorServicio ());
		q.setResultClass(OperadorServicio.class);
		return (List<OperadorServicio>) q.executeList();
	}
}
