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
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import alohandes.negocio.Agenda;
import alohandes.negocio.Apartamento;
import alohandes.negocio.Cliente;
import alohandes.negocio.Habitacion;
import alohandes.negocio.Hostal;
import alohandes.negocio.Hotel;
import alohandes.negocio.Operador;
import alohandes.negocio.OperadorServicio;
import alohandes.negocio.Resenia;
import alohandes.negocio.Reserva;
import alohandes.negocio.Servicio;
import alohandes.negocio.Universidad;
import alohandes.negocio.Usuario;
import alohandes.negocio.ViviendaUniveristaria;

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
	/* ****************************************************************
	 * 			Métodos para manejar los AGENDA
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Agenda adicionarAgenda ( Date fechaFin,Date fechaInicio,  long reservaId, long viviendaId, long hostalId, long hotelId,long habitacionId,long apartamentpId)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlAgenda.adicionarAgenda(pm, fechaFin, fechaInicio, id, reservaId, viviendaId, hostalId, hotelId, habitacionId,apartamentpId);
            tx.commit();
            
            log.trace ("Inserción de agenda: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Agenda(fechaFin, fechaInicio, id, reservaId, viviendaId, hostalId, hotelId, habitacionId, apartamentpId);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}



	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla TipoBebida, dado el identificador del tipo de bebida
	 * Adiciona entradas al log de la aplicación
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarAgendaPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlAgenda.eliminarAgendaPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de la tabla TIPOBEBIDA
	 */
	public List<Agenda> darAgendas ()
	{
		return sqlAgenda.darAgendas (pmf.getPersistenceManager());
	}
 

 
	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida con un identificador dado
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El objeto TipoBebida, construido con base en las tuplas de la tabla TIPOBEBIDA con el identificador dado
	 */
	public Agenda darAgendaPorId (long id)
	{
		return sqlAgenda.darAgendaPorId (pmf.getPersistenceManager(), id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar los clientes
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Cliente adicionarCliente ( long clienteId, long reservaId )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlCliente.adicionarCliente(pm, clienteId, reservaId);
            tx.commit();
            
            log.trace ("Inserción de apartamento: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Cliente(clienteId, reservaId);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}



	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla TipoBebida, dado el identificador del tipo de bebida
	 * Adiciona entradas al log de la aplicación
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarClientePorId (long id,long idReserva) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlCliente.eliminarClientePorId(pm, id, idReserva);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de la tabla TIPOBEBIDA
	 */
	public List<Cliente> darClientes ()
	{
		return sqlCliente.darClientes(pmf.getPersistenceManager());
	}
 

 
	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida con un identificador dado
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El objeto TipoBebida, construido con base en las tuplas de la tabla TIPOBEBIDA con el identificador dado
	 */
	public Cliente darclientePorId (long id,long idreserva)
	{
		return sqlCliente.darClientePorId(pmf.getPersistenceManager(), id, idreserva);
	}
	/* ****************************************************************
	 * 			Métodos para manejar los Habitacion
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Habitacion adicionarHabitacion ( long operadorId, int capacidad, String direccion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlHabitacion.adicionarHabitacion(pm, id, operadorId, capacidad, direccion);
            tx.commit();
            
            log.trace ("Inserción de apartamento: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Habitacion(id, operadorId, direccion, capacidad);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}



	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla TipoBebida, dado el identificador del tipo de bebida
	 * Adiciona entradas al log de la aplicación
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarHabitacionPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacion.eliminarHabitacionPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de la tabla TIPOBEBIDA
	 */
	public List<Habitacion> darHabitacion ()
	{
		return sqlHabitacion.darHabitacions(pmf.getPersistenceManager());
	}
 

 
	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida con un identificador dado
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El objeto TipoBebida, construido con base en las tuplas de la tabla TIPOBEBIDA con el identificador dado
	 */
	public Habitacion darHabitacionPorId (long id)
	{
		return sqlHabitacion.darHabitacionPorId(pmf.getPersistenceManager(), id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar los APARTAMENTO
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Apartamento adicionarApartamento ( long operadorId, int capacidad, String direccion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlApartamento.adicionarApartamento(pm, id, operadorId, capacidad, direccion);
            tx.commit();
            
            log.trace ("Inserción de apartamento: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Apartamento(id, operadorId, capacidad, direccion);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}



	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla TipoBebida, dado el identificador del tipo de bebida
	 * Adiciona entradas al log de la aplicación
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarApartamentoPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlApartamento.eliminarApartamentoPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de la tabla TIPOBEBIDA
	 */
	public List<Apartamento> darApartamentos ()
	{
		return sqlApartamento.darApartamentos(pmf.getPersistenceManager());
	}
 

 
	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida con un identificador dado
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El objeto TipoBebida, construido con base en las tuplas de la tabla TIPOBEBIDA con el identificador dado
	 */
	public Apartamento darApartamentoPorId (long id)
	{
		return sqlApartamento.darApartamentoPorId(pmf.getPersistenceManager(), id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar los Hostal
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Hostal adicionarHostal ( long operadorId, int capacidad, String direccion,String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlHostal.adicionarHostal(pm, id, operadorId, capacidad, direccion, nombre);
            tx.commit();
            
            log.trace ("Inserción de hotel: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Hostal(id, operadorId, capacidad, direccion, nombre);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}



	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla TipoBebida, dado el identificador del tipo de bebida
	 * Adiciona entradas al log de la aplicación
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarHostalPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHostal.eliminarHostalPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de la tabla TIPOBEBIDA
	 */
	public List<Hostal> darHostal ()
	{
		return sqlHostal.darHostals(pmf.getPersistenceManager());
	}
 

 
	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida con un identificador dado
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El objeto TipoBebida, construido con base en las tuplas de la tabla TIPOBEBIDA con el identificador dado
	 */
	public Hostal darHostalPorId (long id)
	{
		return sqlHostal.darHostalPorId(pmf.getPersistenceManager(), id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar los Hotel
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Hotel adicionarHotel ( long operadorId, int capacidad, String direccion,String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlHotel.adicionarHotel(pm, id, operadorId, capacidad, direccion, nombre);
            tx.commit();
            
            log.trace ("Inserción de hotel: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Hotel(id, operadorId, capacidad, direccion, nombre);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}



	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla TipoBebida, dado el identificador del tipo de bebida
	 * Adiciona entradas al log de la aplicación
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarHotelPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHotel.eliminarHotelPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de la tabla TIPOBEBIDA
	 */
	public List<Hotel> darHotel ()
	{
		return sqlHotel.darHotels(pmf.getPersistenceManager());
	}
 

 
	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida con un identificador dado
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El objeto TipoBebida, construido con base en las tuplas de la tabla TIPOBEBIDA con el identificador dado
	 */
	public Hotel darHotelPorId (long id)
	{
		return sqlHotel.darHotelPorId(pmf.getPersistenceManager(), id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar los Operador
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Operador adicionarOperador ( long alohandesId, String email,String nombre,long numeroCel, String numeroId,String tipoId )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlOperador.adicionarOperador(pm, id, alohandesId, email, nombre, numeroCel, numeroId, tipoId);
            tx.commit();
            
            log.trace ("Inserción de operador: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Operador(id, alohandesId, email, nombre, numeroCel, numeroId, tipoId);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}



	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla TipoBebida, dado el identificador del tipo de bebida
	 * Adiciona entradas al log de la aplicación
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarOperadorPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlOperador.eliminarOperadorPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de la tabla TIPOBEBIDA
	 */
	public List<Operador> darOperador ()
	{
		return sqlOperador.darOperadors(pmf.getPersistenceManager());
	}
 

 
	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida con un identificador dado
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El objeto TipoBebida, construido con base en las tuplas de la tabla TIPOBEBIDA con el identificador dado
	 */
	public Operador darOperadorPorId (long id)
	{
		return sqlOperador.darOperadorPorId(pmf.getPersistenceManager(), id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar los Operador-servicio
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public OperadorServicio adicionarOperadorServicio ( long operadorId, long servicioId )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
           
            long tuplasInsertadas = sqlOperadorServicio.adicionarOperadorServicio(pm, operadorId, servicioId);
            tx.commit();
            
            log.trace ("Inserción de operador: " + operadorId +"con servicio"+servicioId+ ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new OperadorServicio(servicioId, operadorId);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}



	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla TipoBebida, dado el identificador del tipo de bebida
	 * Adiciona entradas al log de la aplicación
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarOperadorServicioPorId (long id, long servicioId) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlOperadorServicio.eliminarOperadorServicioPorId(pm, id, servicioId);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de la tabla TIPOBEBIDA
	 */
	public List<OperadorServicio> darOperadorServicio ()
	{
		return sqlOperadorServicio.darOperadorServicios(pmf.getPersistenceManager());
	}
 

 
	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida con un identificador dado
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El objeto TipoBebida, construido con base en las tuplas de la tabla TIPOBEBIDA con el identificador dado
	 */
	public OperadorServicio darOperadorServicioPorId (long id, long idservicio)
	{
		return sqlOperadorServicio.darOperadorServicioPorId(pmf.getPersistenceManager(), id,idservicio);
	}
	/* ****************************************************************
	 * 			Métodos para manejar los Resenia 
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Resenia adicionarResenia(String comentario, Date fecha, int puntuacion )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlResenia.adicionarResenia(pm, id, comentario, fecha, puntuacion);
            tx.commit();
            
            log.trace ("Inserción de resenia: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Resenia(id, comentario, fecha, puntuacion);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}



	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla TipoBebida, dado el identificador del tipo de bebida
	 * Adiciona entradas al log de la aplicación
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarReseniaPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlResenia.eliminarReseniaPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de la tabla TIPOBEBIDA
	 */
	public List<Resenia> darResenia ()
	{
		return sqlResenia.darResenias(pmf.getPersistenceManager());
	}
 

 
	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida con un identificador dado
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El objeto TipoBebida, construido con base en las tuplas de la tabla TIPOBEBIDA con el identificador dado
	 */
	public Resenia darReseniaPorId (long id)
	{
		return sqlResenia.darReseniaPorId(pmf.getPersistenceManager(), id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar los Reserva
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Reserva adicionarReserva(long reseniId, String idp, Double costo, String contrato )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlReserva.adicionarReserva(pm, id, reseniId, idp, costo, contrato);
            tx.commit();
            
            log.trace ("Inserción de reserva: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Reserva(id, reseniId, idp, costo, contrato);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}



	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla TipoBebida, dado el identificador del tipo de bebida
	 * Adiciona entradas al log de la aplicación
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarReservaPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlReserva.eliminarReservaPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de la tabla TIPOBEBIDA
	 */
	public List<Reserva> darReservas ()
	{
		return sqlReserva.darReservas(pmf.getPersistenceManager());
	}
 

 
	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida con un identificador dado
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El objeto TipoBebida, construido con base en las tuplas de la tabla TIPOBEBIDA con el identificador dado
	 */
	public Reserva darReservaPorId (long id)
	{
		return sqlReserva.darReservaPorId(pmf.getPersistenceManager(), id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar los Servicio
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Servicio adicionarServicio(String idp , String nombre )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlServicio.adicionarServicio(pm, id, idp, nombre);
            tx.commit();
            
            log.trace ("Inserción de servicio: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Servicio(id, idp, nombre);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}



	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla TipoBebida, dado el identificador del tipo de bebida
	 * Adiciona entradas al log de la aplicación
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarServicioPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlServicio.eliminarServicioPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de la tabla TIPOBEBIDA
	 */
	public List<Servicio> darServicios ()
	{
		return sqlServicio.darServicios(pmf.getPersistenceManager());
	}
 

 
	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida con un identificador dado
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El objeto TipoBebida, construido con base en las tuplas de la tabla TIPOBEBIDA con el identificador dado
	 */
	public Servicio darServicioPorId (long id)
	{
		return sqlServicio.darServicioPorId(pmf.getPersistenceManager(), id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar los Universidad
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Universidad adicionarUniversidad(String direccion , String nombre, long nit )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlUniversidad.adicionarUniverisdad(pm, id, direccion, nombre, nit);
            tx.commit();
            
            log.trace ("Inserción de universidad: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Universidad(id, direccion, nombre, nit);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}



	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla TipoBebida, dado el identificador del tipo de bebida
	 * Adiciona entradas al log de la aplicación
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarUniversidadPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlUniversidad.eliminarUniverisdadPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de la tabla TIPOBEBIDA
	 */
	public List<Universidad> darUniversidads ()
	{
		return sqlUniversidad.darUniversidads(pmf.getPersistenceManager());
	}
 

 
	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida con un identificador dado
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El objeto TipoBebida, construido con base en las tuplas de la tabla TIPOBEBIDA con el identificador dado
	 */
	public Universidad darUniversidadPorId (long id)
	{
		return sqlUniversidad.darUniversidadPorId(pmf.getPersistenceManager(), id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar los Usuario
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Usuario adicionarUsuario(int edad, String email, String nombre, long numeroCel, String numeroid, String tipoId, long alohandesId, long univerisdadId )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlUsuario.adicionarUsuario(pm, edad, email, nombre, numeroCel, numeroid, tipoId, id, alohandesId, univerisdadId);
            tx.commit();
            
            log.trace ("Inserción de usuario: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Usuario(edad, email, nombre, numeroCel, numeroid, id, alohandesId, univerisdadId);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}



	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla TipoBebida, dado el identificador del tipo de bebida
	 * Adiciona entradas al log de la aplicación
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarUsuarioPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlUsuario.eliminarUsuarioPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de la tabla TIPOBEBIDA
	 */
	public List<Usuario> darUsuarios ()
	{
		return sqlUsuario.darUsuarios(pmf.getPersistenceManager());
	}
 

 
	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida con un identificador dado
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El objeto TipoBebida, construido con base en las tuplas de la tabla TIPOBEBIDA con el identificador dado
	 */
	public Usuario darUsuarioPorId (long id)
	{
		return sqlUsuario.darUsuarioPorId(pmf.getPersistenceManager(), id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar los Vienda Universitaria
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public ViviendaUniveristaria adicionarViviendaU ( long operadorId, int capacidad, String direccion,String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqLviviendaUniversitaria.adicionarviviendauniversitaria(pm, id, operadorId, capacidad, direccion, nombre);
            tx.commit();
            
            log.trace ("Inserción de vivienda universitaria: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new ViviendaUniveristaria(id, direccion, nombre, capacidad, operadorId);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}



	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla TipoBebida, dado el identificador del tipo de bebida
	 * Adiciona entradas al log de la aplicación
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarViviendaPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqLviviendaUniversitaria.eliminarviviendaUniversitariaPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de la tabla TIPOBEBIDA
	 */
	public List<ViviendaUniveristaria> darvViviendaUniveristarials ()
	{
		return sqLviviendaUniversitaria.darViviendaUniveristarias(pmf.getPersistenceManager());
	}
 

 
	/**
	 * Método que consulta todas las tuplas en la tabla TipoBebida con un identificador dado
	 * @param idTipoBebida - El identificador del tipo de bebida
	 * @return El objeto TipoBebida, construido con base en las tuplas de la tabla TIPOBEBIDA con el identificador dado
	 */
	public ViviendaUniveristaria darViviendaUlPorId (long id)
	{
		return sqLviviendaUniversitaria.darViviendaUniveristariaPorId(pmf.getPersistenceManager(), id);
	}
}
