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

import alohandes.negocio.Hostal;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto 
 * Hostal de Alohandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Miguel Parra
 */
public class SQLHostal {
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
	public SQLHostal (PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	public long adicionarHostal (PersistenceManager pm,long hostalId, long operadorId, int capacidad, String direccion, String nombre ) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHostal()  + "(hostalId,  operadorId,  capacidad,  direccion,  nombre) values (?, ?, ?, ?, ?)");
        q.setParameters(hostalId,  operadorId,  capacidad,  direccion,  nombre);
        return (long) q.executeUnique();
	}
	

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN BAR de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador del bar
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarHostalPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHostal() + " WHERE HostalID = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN BAR de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador del bar
	 * @return El objeto BAR que tiene el identificador dado
	 */
	public Hostal darHostalPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHostal () + " WHERE HostalID = ?");
		q.setResultClass(Hostal.class);
		q.setParameters(id);
		return (Hostal) q.executeUnique();
	}

	

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos BAR
	 */
	public List<Hostal> darHostals (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHostal ());
		q.setResultClass(Hostal.class);
		return (List<Hostal>) q.executeList();
	}
	
}
