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

import java.sql.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import alohandes.negocio.Agenda;
import alohandes.negocio.Apartamento;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto 
 * Apartamento de Alohandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Miguel Parra
 */
public class SQLApartamento {
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
	public SQLApartamento(PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	/**
	 * Crea y ejecuta la sentencia SQL
	 * @return El número de tuplas insertadas
	 */
	public long adicionarApartamento (PersistenceManager pm, long apartamentoId,long operadorId, int capacidad, String direccion ) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaApartameto()  + "(apartamentoId, operadorId,  capacidad,  direccion ) values (?, ?, ?, ?)");
        q.setParameters(apartamentoId, operadorId,  capacidad,  direccion);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN BAR de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador del bar
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarApartamentoPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaApartameto() + " WHERE APARTAMENTOID = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}
	public Apartamento darApartamentoPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaApartameto () + " WHERE APARTAMENTOID = ?");
		q.setResultClass(Apartamento.class);
		q.setParameters(id);
		return (Apartamento) q.executeUnique();
	}

	

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos BAR
	 */
	public List<Apartamento> darApartamentos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaApartameto ());
		q.setResultClass(Agenda.class);
		return (List<Apartamento>) q.executeList();
	}
}
