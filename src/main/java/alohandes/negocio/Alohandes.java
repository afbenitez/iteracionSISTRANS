/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: alohandes
 * @version 1.0
 * @author MIGUEL PARRA , ANDRES BENITEZ
 * MARZO 2020
 * 
 * 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package alohandes.negocio;

import java.sql.Date;

import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

import alohandes.persistencia.PersistenciaAlohandes;


/**
 * Clase para modelar el concepto AGENDA del negocio de los ALOHANDES
 *
 * @author MIGUEL PARRA
 */
public class Alohandes {
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(Alohandes.class.getName());
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia
	 */
	private PersistenciaAlohandes pp;
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * El constructor por defecto
	 */
	public Alohandes ()
	{
		pp = PersistenciaAlohandes.getInstance ();
	}
	/**
	 * El constructor qye recibe los nombres de las tablas en tableConfig
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad de persistencia
	 */
	public Alohandes (JsonObject tableConfig)
	{
		pp = PersistenciaAlohandes.getInstance(tableConfig) ;
	}
	/**
	 * Cierra la conexión con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia ()
	{
		pp.cerrarUnidadPersistencia ();
	}
	/* ****************************************************************
	 * 			Métodos para manejar los TIPOS DE AGENDA
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un tipo de bebida 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Agenda adicionarAgenda (Date fechaFin,Date fechaInicio,  long reservaId, long viviendaId, long hostalId, long hotelId,long habitacionId , long apartamentpId)
	{
        log.info ("Adicionando  agenda: " );
        Agenda objeto = pp.adicionarAgenda(fechaFin, fechaInicio, reservaId, viviendaId, hostalId, hotelId, habitacionId, apartamentpId);		
        log.info ("Adicionando agenda: " +objeto);
        return objeto;
	}
	

	
	/**
	 * Elimina un tipo de bebida por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del tipo de bebida a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarAgendaPorId (long id)
	{
		log.info ("Eliminando agenda por id: " + id);
        long resp = pp.eliminarAgendaPorId(id);		
        log.info ("Eliminando agenda por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Encuentra todos los tipos de bebida en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos TipoBebida con todos los tipos de bebida que conoce la aplicación, llenos con su información básica
	 */
	public List<Agenda> darAgenda ()
	{
		log.info ("Consultando Tipos de agenda");
        List<Agenda> objetos = pp.darAgendas();	
        log.info ("Consultando Agenda: " + objetos.size() + " existentes");
        return objetos;
	}

	public Agenda darAgendaPorId (long id)
	{
		log.info ("Buscando agenda por id: " +id);
	    Agenda objeto = pp.darAgendaPorId(id);
		return objeto ;
	}

	
	
	/* ****************************************************************
	 * 			Métodos para manejar los TIPOS DE Apartamento
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un tipo de bebida 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Apartamento adicionarApartamento(long operadorId, int capcidad, String direccion)
	{
        log.info ("Adicionando  Apartamento: " );
        Apartamento objeto = pp.adicionarApartamento(operadorId, capcidad, direccion);		
        log.info ("Adicionando Apartamento: " +objeto);
        return objeto;
	}
	

	
	/**
	 * Elimina un tipo de bebida por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del tipo de bebida a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarApartamentoPorId (long id)
	{
		log.info ("Eliminando Apartamento por id: " + id);
        long resp = pp.eliminarApartamentoPorId(id);		
        log.info ("Eliminando Apartamento por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Encuentra todos los tipos de bebida en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos TipoBebida con todos los tipos de bebida que conoce la aplicación, llenos con su información básica
	 */
	public List<Apartamento> darApartamento ()
	{
		log.info ("Consultando Apartamento");
        List<Apartamento> objetos = pp.darApartamentos();	
        log.info ("Consultando Apartamento: " + objetos.size() + " existentes");
        return objetos;
	}

	public Apartamento darApartamentoPorId (long id)
	{
		log.info ("Buscando Apartamento por id: " +id);
		Apartamento objeto = pp.darApartamentoPorId(id);
		return objeto ;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los TIPOS DE cliente
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un tipo de bebida 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Cliente adicionarCliente(long clienteId, long reservaId)
	{
        log.info ("Adicionando  Cliente: " );
        Cliente objeto = pp.adicionarCliente(clienteId, reservaId);		
        log.info ("Adicionando Cliente: " +objeto);
        return objeto;
	}
	

	
	/**
	 * Elimina un tipo de bebida por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del tipo de bebida a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarClientePorId (long id,long idReserva)
	{
		log.info ("Eliminando Cliente por id: " + id);
        long resp = pp.eliminarClientePorId(id, idReserva);		
        log.info ("Eliminando Cliente por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Encuentra todos los tipos de bebida en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos TipoBebida con todos los tipos de bebida que conoce la aplicación, llenos con su información básica
	 */
	public List<Cliente> darCliente ()
	{
		log.info ("Consultando Cliente");
        List<Cliente> objetos = pp.darClientes();	
        log.info ("Consultando Cliente: " + objetos.size() + " existentes");
        return objetos;
	}

	public Cliente darClientePorId (long id,long resenia)
	{
		log.info ("Buscando Cliente por id: " +id);
		Cliente objeto = pp.darclientePorId(id, resenia);
		return objeto ;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los  Habitacion
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un tipo de bebida 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Habitacion adicionarHabitacion(long operadorId, int capacidad, String direccion)
	{
        log.info ("Adicionando  Habitacion: " );
        Habitacion objeto = pp.adicionarHabitacion(operadorId, capacidad, direccion);		
        log.info ("Adicionando Habitacion: " +objeto);
        return objeto;
	}
	

	
	/**
	 * Elimina un tipo de bebida por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del tipo de bebida a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarHabitacionPorId (long id)
	{
		log.info ("Eliminando Habitacion por id: " + id);
        long resp = pp.eliminarHabitacionPorId(id);		
        log.info ("Eliminando Habitacion por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Encuentra todos los tipos de bebida en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos TipoBebida con todos los tipos de bebida que conoce la aplicación, llenos con su información básica
	 */
	public List<Habitacion> darHabitacion ()
	{
		log.info ("Consultando Habitacion");
        List<Habitacion> objetos = pp.darHabitacion();	
        log.info ("Consultando Habitacion: " + objetos.size() + " existentes");
        return objetos;
	}

	public Habitacion darHabitacionPorId (long id)
	{
		log.info ("Buscando Habitacion por id: " +id);
		Habitacion objeto = pp.darHabitacionPorId(id);
		return objeto ;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los  Hostal
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un tipo de bebida 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Hostal adicionarHostal(long operadorId, int capacidad, String direccion, String nombre)
	{
        log.info ("Adicionando Hostal: " );
        Hostal objeto = pp.adicionarHostal(operadorId, capacidad, direccion, nombre);		
        log.info ("Adicionando Hostal: " +objeto);
        return objeto;
	}
	

	
	/**
	 * Elimina un tipo de bebida por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del tipo de bebida a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarHostalPorId (long id)
	{
		log.info ("Eliminando Hostal por id: " + id);
        long resp = pp.eliminarHostalPorId(id);		
        log.info ("Eliminando Hostal por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Encuentra todos los tipos de bebida en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos TipoBebida con todos los tipos de bebida que conoce la aplicación, llenos con su información básica
	 */
	public List<Hostal> darHostal ()
	{
		log.info ("Consultando Hostal");
        List<Hostal> objetos = pp.darHostal();	
        log.info ("Consultando Hostal: " + objetos.size() + " existentes");
        return objetos;
	}

	public Hostal darHostalPorId (long id)
	{
		log.info ("Buscando Hostal por id: " +id);
		Hostal objeto = pp.darHostalPorId(id);
		return objeto ;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los  Hotel
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un tipo de bebida 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Hotel adicionarHotel(long operadorId, int capacidad, String direccion, String nombre)
	{
        log.info ("Adicionando Hotel: " );
        Hotel objeto = pp.adicionarHotel(operadorId, capacidad, direccion, nombre);		
        log.info ("Adicionando Hotel: " +objeto);
        return objeto;
	}
	

	
	/**
	 * Elimina un tipo de bebida por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del tipo de bebida a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarHotelPorId (long id)
	{
		log.info ("Eliminando Hotel por id: " + id);
        long resp = pp.eliminarHotelPorId(id);		
        log.info ("Eliminando Hotel por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Encuentra todos los tipos de bebida en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos TipoBebida con todos los tipos de bebida que conoce la aplicación, llenos con su información básica
	 */
	public List<Hotel> darHotel ()
	{
		log.info ("Consultando Hotel");
        List<Hotel> objetos = pp.darHotel();	
        log.info ("Consultando Hotel: " + objetos.size() + " existentes");
        return objetos;
	}

	public Hotel darHotelPorId (long id)
	{
		log.info ("Buscando Hotel por id: " +id);
		Hotel objeto = pp.darHotelPorId(id);
		return objeto ;
	}
	/* ****************************************************************
	 * 			Métodos para manejar los  Operador
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un tipo de bebida 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Operador adicionarOperador(long alohandesId, String email,String nombre,long numeroCel, String numeroId,String tipoId)
	{
        log.info ("Adicionando Operador: " );
        Operador objeto = pp.adicionarOperador(alohandesId, email, nombre, numeroCel, numeroId, tipoId);		
        log.info ("Adicionando Operador: " +objeto);
        return objeto;
	}
	

	
	/**
	 * Elimina un tipo de bebida por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del tipo de bebida a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarOperadorPorId (long id)
	{
		log.info ("Eliminando Operador por id: " + id);
        long resp = pp.eliminarOperadorPorId(id);		
        log.info ("Eliminando Operador por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Encuentra todos los tipos de bebida en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos TipoBebida con todos los tipos de bebida que conoce la aplicación, llenos con su información básica
	 */
	public List<Operador> darOperador ()
	{
		log.info ("Consultando Operador");
        List<Operador> objetos = pp.darOperador();	
        log.info ("Consultando Operador: " + objetos.size() + " existentes");
        return objetos;
	}

	public Operador darOperadorPorId (long id)
	{
		log.info ("Buscando Operador por id: " +id);
		Operador objeto = pp.darOperadorPorId(id);
		return objeto ;
	}
	/* ****************************************************************
	 * 			Métodos para manejar los  Operador-servicio
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un tipo de bebida 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public OperadorServicio adicionarOperadorServicio(long operadorId, long servicioId)
	{
        log.info ("Adicionando OperadorServicio: " );
        OperadorServicio objeto = pp.adicionarOperadorServicio(operadorId, servicioId);		
        log.info ("Adicionando OperadorServicio: " +objeto);
        return objeto;
	}
	

	
	/**
	 * Elimina un tipo de bebida por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del tipo de bebida a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarOperadorServicioPorId (long id, long idservicio)
	{
		log.info ("Eliminando OperadorServicio por id: " + id);
        long resp = pp.eliminarOperadorServicioPorId(id, idservicio);		
        log.info ("Eliminando Operador Servicio por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Encuentra todos los tipos de bebida en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos TipoBebida con todos los tipos de bebida que conoce la aplicación, llenos con su información básica
	 */
	public List<OperadorServicio> darOperadorServicio ()
	{
		log.info ("Consultando OperadorServicio");
        List<OperadorServicio> objetos = pp.darOperadorServicio();	
        log.info ("Consultando OperadorServicio: " + objetos.size() + " existentes");
        return objetos;
	}

	public OperadorServicio darOperadorServicioPorId (long id, long idServicio)
	{
		log.info ("Buscando OperadorServicio por id: " +id+"y servicio"+idServicio);
		OperadorServicio objeto = pp.darOperadorServicioPorId(id, idServicio);
		return objeto ;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los  Resenia
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un tipo de bebida 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Resenia adicionarResenia(String comentario, Date fecha, int puntuacion)
	{
        log.info ("Adicionando Resenia: " );
        Resenia objeto = pp.adicionarResenia(comentario, fecha, puntuacion);		
        log.info ("Adicionando Resenia: " +objeto);
        return objeto;
	}
	

	
	/**
	 * Elimina un tipo de bebida por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del tipo de bebida a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarReseniaPorId (long id)
	{
		log.info ("Eliminando Resenia por id: " + id);
        long resp = pp.eliminarReseniaPorId(id);		
        log.info ("Eliminando Resenia por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Encuentra todos los tipos de bebida en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos TipoBebida con todos los tipos de bebida que conoce la aplicación, llenos con su información básica
	 */
	public List<Resenia> darResenia ()
	{
		log.info ("Consultando Resenia");
        List<Resenia> objetos = pp.darResenia();	
        log.info ("Consultando Resenia: " + objetos.size() + " existentes");
        return objetos;
	}

	public Resenia darReseniaPorId (long id)
	{
		log.info ("Buscando Resenia por id: " +id);
		Resenia objeto = pp.darReseniaPorId(id);
		return objeto ;
	}
	/* ****************************************************************
	 * 			Métodos para manejar los  Reserva
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un tipo de bebida 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Reserva adicionarReserva( long reseniId, String id, Double costo, String contrato)
	{
        log.info ("Adicionando Reserva: " );
        Reserva objeto = pp.adicionarReserva(reseniId, id, costo, contrato);		
        log.info ("Adicionando Reserva: " +objeto);
        return objeto;
	}
	

	
	/**
	 * Elimina un tipo de bebida por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del tipo de bebida a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarReservaPorId (long id)
	{
		log.info ("Eliminando Reserva por id: " + id);
        long resp = pp.eliminarReservaPorId(id);		
        log.info ("Eliminando  Reserva por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Encuentra todos los tipos de bebida en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos TipoBebida con todos los tipos de bebida que conoce la aplicación, llenos con su información básica
	 */
	public List<Reserva> darReserva ()
	{
		log.info ("Consultando Reserva");
        List<Reserva> objetos = pp.darReservas();	
        log.info ("Consultando Reserva: " + objetos.size() + " existentes");
        return objetos;
	}

	public Reserva darReservaPorId (long id)
	{
		log.info ("Buscando Reserva por id: " +id);
		Reserva objeto = pp.darReservaPorId(id);
		return objeto ;
	}
	/* ****************************************************************
	 * 			Métodos para manejar los  Servico
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un tipo de bebida 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Servicio adicionarServicio( String id , String nombre)
	{
        log.info ("Adicionando Servicio: " );
        Servicio objeto = pp.adicionarServicio(id, nombre);		
        log.info ("Adicionando Servicio: " +objeto);
        return objeto;
	}
	

	
	/**
	 * Elimina un tipo de bebida por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del tipo de bebida a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarServicioPorId (long id)
	{
		log.info ("Eliminando Servicio por id: " + id);
        long resp = pp.eliminarServicioPorId(id);		
        log.info ("Eliminando Servicio por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Encuentra todos los tipos de bebida en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos TipoBebida con todos los tipos de bebida que conoce la aplicación, llenos con su información básica
	 */
	public List<Servicio> darServicio()
	{
		log.info ("Consultando Servicio");
        List<Servicio> objetos = pp.darServicios();	
        log.info ("Consultando Servicio: " + objetos.size() + " existentes");
        return objetos;
	}

	public Servicio darServicioPorId (long id)
	{
		log.info ("Buscando Servicio por id: " +id);
		Servicio objeto = pp.darServicioPorId(id);
		return objeto ;
	}
	/* ****************************************************************
	 * 			Métodos para manejar los  Universidad
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un tipo de bebida 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Universidad adicionarUniversidad( String direccion , String nombre, long nit)
	{
        log.info ("Adicionando Universidad: " );
        Universidad objeto = pp.adicionarUniversidad(direccion, nombre, nit);		
        log.info ("Adicionando Universidad: " +objeto);
        return objeto;
	}
	

	
	/**
	 * Elimina un tipo de bebida por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del tipo de bebida a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarUniversidadPorId (long id)
	{
		log.info ("Eliminando Universidad por id: " + id);
        long resp = pp.eliminarUniversidadPorId(id);		
        log.info ("Eliminando Universidad por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Encuentra todos los tipos de bebida en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos TipoBebida con todos los tipos de bebida que conoce la aplicación, llenos con su información básica
	 */
	public List<Universidad> darUniversidad()
	{
		log.info ("Consultando Universidad");
        List<Universidad> objetos = pp.darUniversidads();	
        log.info ("Consultando Universidad: " + objetos.size() + " existentes");
        return objetos;
	}

	public Universidad darUniversidadPorId (long id)
	{
		log.info ("Buscando Universidad por id: " +id);
		Universidad objeto = pp.darUniversidadPorId(id);
		return objeto ;
	}
	/* ****************************************************************
	 * 			Métodos para manejar los  Ususario
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un tipo de bebida 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Usuario adicionarUsusario( int edad, String email, String nombre, long numeroCel, String numeroid, String tipoId, long alohandesId, long univerisdadId )
	{
        log.info ("Adicionando Usuario: " );
        Usuario objeto = pp.adicionarUsuario(edad, email, nombre, numeroCel, numeroid, tipoId, alohandesId, univerisdadId);		
        log.info ("Adicionando Usuario: " +objeto);
        return objeto;
	}
	

	
	/**
	 * Elimina un tipo de bebida por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del tipo de bebida a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarUsuarioPorId (long id)
	{
		log.info ("Eliminando Usuario por id: " + id);
        long resp = pp.eliminarUsuarioPorId(id);		
        log.info ("Eliminando Usuario por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Encuentra todos los tipos de bebida en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos TipoBebida con todos los tipos de bebida que conoce la aplicación, llenos con su información básica
	 */
	public List<Usuario> darUsuario()
	{
		log.info ("Consultando Usuario");
        List<Usuario> objetos = pp.darUsuarios();	
        log.info ("Consultando Usuario: " + objetos.size() + " existentes");
        return objetos;
	}

	public Usuario darUsuarioPorId (long id)
	{
		log.info ("Buscando Usuario por id: " +id);
		Usuario objeto = pp.darUsuarioPorId(id);
		return objeto ;
	}
	/* ****************************************************************
	 * 			Métodos para manejar los  Vivienda Universitaria
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un tipo de bebida 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public ViviendaUniveristaria adicionarViviendaUniveristaria(long operadorId, int capacidad, String direccion, String nombre)
	{
        log.info ("Adicionando ViviendaUniveristaria: " );
        ViviendaUniveristaria objeto = pp.adicionarViviendaU(operadorId, capacidad, direccion, nombre);		
        log.info ("Adicionando ViviendaUniveristaria: " +objeto);
        return objeto;
	}
	

	
	/**
	 * Elimina un tipo de bebida por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param id - El id del tipo de bebida a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarViviendaUniveristariaPorId (long id)
	{
		log.info ("Eliminando ViviendaUniveristaria por id: " + id);
        long resp = pp.eliminarViviendaPorId(id);		
        log.info ("Eliminando ViviendaUniveristaria por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	/**
	 * Encuentra todos los tipos de bebida en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos TipoBebida con todos los tipos de bebida que conoce la aplicación, llenos con su información básica
	 */
	public List<ViviendaUniveristaria> darViviendaUniveristaria ()
	{
		log.info ("Consultando ViviendaUniveristaria");
        List<ViviendaUniveristaria> objetos = pp.darvViviendaUniveristarials();	
        log.info ("Consultando ViviendaUniveristaria: " + objetos.size() + " existentes");
        return objetos;
	}

	public ViviendaUniveristaria darViviendaUniveristariaPorId (long id)
	{
		log.info ("Buscando ViviendaUniveristaria por id: " +id);
		ViviendaUniveristaria objeto = pp.darViviendaUlPorId(id);
		return objeto ;
	}

	/* ****************************************************************
	 * 			Métodos para administración
	 *****************************************************************/

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de Parranderos
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long [] limpiarAlohandes ()
	{
        log.info ("Limpiando la BD de Alohandes");
        long [] borrrados = pp.limpiarAlohandes();	
        log.info ("Limpiando la BD de Alohandes: Listo!");
        return borrrados;
	}
}
