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

import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Clase para el manejador de persistencia del proyecto Alohandes
 * Traduce la información entre objetos Java y tuplas de la base de datos, en ambos sentidos
 * Sigue un patrón SINGLETON (Sólo puede haber UN objeto de esta clase) para comunicarse de manera correcta
 * con la base de datos
 * 
 * @author Miguel Parra
 */
public class PersistenciaAlohandes {
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersistenciaAlohandes.class.getName());

	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaAlohandes instance;

	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory pmf;

	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
	 * 
	 */
	private List <String> tablas;

	/**
	 * Atributo para el acceso a las sentencias SQL propias a Persistencia
	 */
	private SQLUtil sqlUtil;
	/**
	 * Atributo para el acceso a la tabla Agenda de la base de datos
	 */
	private SQLAgenda sqlAgenda;
	/**
	 * Atributo para el acceso a la tabla Alohandes de la base de datos
	 */
	private SQLAlohandes sqlAlohandes;
	/**
	 * Atributo para el acceso a la tabla Apartamento de la base de datos
	 */
	private SQLApartamento sqlApartamento;
	/**
	 * Atributo para el acceso a la tabla Cliente de la base de datos
	 */
	private SQLCliente sqlCliente;
	/**
	 * Atributo para el acceso a la tabla Habitacion de la base de datos
	 */
	private SQLHabitacion sqlHabitacion;
	/**
	 * Atributo para el acceso a la tabla Hostal de la base de datos
	 */
	private SQLHostal sqlHostal;
	/**
	 * Atributo para el acceso a la tabla Hotel de la base de datos
	 */
	private SQLHotel sqlHotel;
	/**
	 * Atributo para el acceso a la tabla Operador de la base de datos
	 */
	private SQLOperador sqlOperador;
	/**
	 * Atributo para el acceso a la tabla OperadorServicio de la base de datos
	 */
	private SQLOperadorServicio sqlOperadorServicio;
	/**
	 * Atributo para el acceso a la tabla resenia de la base de datos
	 */
	private SQLResenia sqlResenia;
	/**
	 * Atributo para el acceso a la tabla reserva de la base de datos
	 */
	private SQLReserva sqlReserva;
	/**
	 * Atributo para el acceso a la tabla Servicio de la base de datos
	 */
	private SQLServicio sqlServicio;
	/**
	 * Atributo para el acceso a la tabla Universidad de la base de datos
	 */
	private SQLUniversidad sqlUniversidad;
	/**
	 * Atributo para el acceso a la tabla Usuario de la base de datos
	 */
	private SQLUsuario sqlUsuario;
	/**
	 * Atributo para el acceso a la tabla ViviendaUniveristaria de la base de datos
	 */
	private SQLviviendaUniversitaria sqLviviendaUniversitaria;
	/* ****************************************************************
	 * 			Métodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patrón SINGLETON
	 */
	private PersistenciaAlohandes ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("Alohandes");		
		crearClasesSQL ();

		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("Alohandes_sequence");
		tablas.add ("AGENDA");
		tablas.add ("APARTAMENTO");
		tablas.add ("CLIENTE");
		tablas.add ("HABITACION");
		tablas.add ("HOSTAL");
		tablas.add ("HOTEL");
		tablas.add ("OPERADOR");
		tablas.add ("OPERADORSERVICIO");
		tablas.add ("RESENIA");
		tablas.add ("RESERVA");
		tablas.add ("SERVICIO");
		tablas.add ("UNIVERSIDAD");
		tablas.add ("USUARIO");
		tablas.add ("VIVIENDAUNIVERSITARIA");

	}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaAlohandes (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);

		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}
	/**
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaAlohandes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaAlohandes ();
		}
		return instance;
	}
	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaAlohandes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaAlohandes (tableConfig);
		}
		return instance;
	}
	/**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}
	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}
		
		return resp;
	}
	
	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL ()
	{
		sqlAgenda = new SQLAgenda(this);
		sqlApartamento = new SQLApartamento(this);
		sqlCliente = new SQLCliente(this);
		sqlHabitacion = new SQLHabitacion(this);
		sqlHostal = new SQLHostal(this);
		sqlHotel = new SQLHotel (this);
		sqlOperador = new SQLOperador(this);	
		sqlOperadorServicio = new SQLOperadorServicio(this);	
		sqlResenia = new SQLResenia(this);	
		sqlReserva = new SQLReserva(this);
		sqlServicio = new SQLServicio(this);
		sqlUniversidad = new SQLUniversidad(this);
		sqlUsuario = new SQLUsuario(this);
		sqLviviendaUniversitaria = new SQLviviendaUniversitaria(this);	
		sqlUtil = new SQLUtil(this);
	}
	/**
	 * @return La cadena de caracteres con el nombre del secuenciador de parranderos
	 */
	public String darSeqAlohandes ()
	{
		return tablas.get (0);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Agenda de alohandes
	 */
	public String darTablaAgenda ()
	{
		return tablas.get (1);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Apartamento de alohandes
	 */
	public String darTablaApartameto ()
	{
		return tablas.get (2);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Cliente de alohandes
	 */
	public String darTablaCliente ()
	{
		return tablas.get (3);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Habitacion de alohandes
	 */
	public String darTablaHabitacion ()
	{
		return tablas.get (4);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de hostal de alohandes
	 */
	public String darTablaHostal ()
	{
		return tablas.get (5);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Hotel de alohandes
	 */
	public String darTablaHotel ()
	{
		return tablas.get (6);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Operador de alohandes
	 */
	public String darTablaOperador ()
	{
		return tablas.get (7);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de operador-servicio de alohandes
	 */
	public String darTablaOperadorServicio ()
	{
		return tablas.get (8);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de resenia de alohandes
	 */
	public String darTablaResenia ()
	{
		return tablas.get (9);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de reserva de alohandes
	 */
	public String darTablaReserva ()
	{
		return tablas.get (10);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de servicio de alohandes
	 */
	public String darTablaServicio()
	{
		return tablas.get (11);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de universidad de alohandes
	 */
	public String darTablaUniversidad ()
	{
		return tablas.get (12);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de usuario de alohandes
	 */
	public String darTablaUsuario ()
	{
		return tablas.get (13);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de vivienda unuversitaria de alohandes
	 */
	public String darTablaViviendaUniversitaria()
	{
		return tablas.get (14);
	}
	/**
	 * Transacción para el generador de secuencia de alohandes
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de alohandes
	 */
	private long nextval ()
	{
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
    }
	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}
	
	
}
