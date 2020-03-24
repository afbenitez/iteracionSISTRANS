/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Miguel Parra y Andres benitez
 * Marzo 2020
 * 
 * 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package alohandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Miguel Parra
 */
class SQLUtil
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaParranderos.SQL;

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
	public SQLUtil (PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextval (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqAlohandes()  + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}

	/**
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 * @param pm - El manejador de persistencia
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long [] limpiarAlohandes (PersistenceManager pm)
	{
		
        Query qAgenda = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAgenda());          
        Query qApartamento = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaApartameto());
        Query qCliente = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCliente());
        Query qHabitacion = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitacion());
        Query qHostal = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHostal());
        Query qHotel = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHotel());
        Query qOperador = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOperador());
        Query qOperadorServicio = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOperadorServicio());
        Query qResenia = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaResenia());
        Query qReserva = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaReserva());
        Query qServicio = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServicio());
        Query qUniversidad = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaUniversidad());
        Query qUsuario = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaUsuario());
        Query qViviendauniversitaria = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaViviendaUniversitaria());
        
        long agendaEliminados = (long) qAgenda.executeUnique ();
        long apartamerntoEliminados = (long) qApartamento.executeUnique ();
        long clienteEliminadas = (long) qCliente.executeUnique ();
        long habitacionEliminadas = (long) qHabitacion.executeUnique ();
        long hostalEliminados = (long) qHostal.executeUnique ();
        long hotelEliminados = (long) qHotel.executeUnique ();
        long operadorEliminados = (long) qOperador.executeUnique ();
        long operadorServiciosEliminados = (long) qOperadorServicio.executeUnique ();
        long rseniaEliminados = (long) qResenia.executeUnique ();
        long reservaEliminados = (long) qReserva.executeUnique ();
        long servicioEliminados = (long) qServicio.executeUnique ();
        long universidadEliminados = (long) qUniversidad.executeUnique ();
        long usuarioEliminados = (long) qUsuario.executeUnique ();
        long viviendaUniversitariaEliminados = (long) qViviendauniversitaria.executeUnique ();
        return new long[] {agendaEliminados,apartamerntoEliminados,clienteEliminadas,habitacionEliminadas,hostalEliminados,hotelEliminados,operadorEliminados,operadorServiciosEliminados,rseniaEliminados,reservaEliminados,servicioEliminados,universidadEliminados, usuarioEliminados,viviendaUniversitariaEliminados};
	}

}
