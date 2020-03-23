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

/**
 * Clase para modelar el concepto AGENDA del negocio de los ALOHANDES
 *
 * @author MIGUEL PARRA
 */
public class Agenda {
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/

	/**
	 * FECHA DE FIN 
	 */
	private Date fechaFin; 
	
	/**
	 * FECHA DE INICIO
	 */
	private Date fechaInicio;
	/**
	 * Identificador unico 
	 */
	private long agendaId;
	/**
	 * identificacior de su reserva 
	 */
	private long reservaId;
	/**
	 * id de la vivienda universitaria 
	 */
	private long viviendaUniveristariaId;
	/**
	 * id del hostal
	 */
	private long hostalid;
	/**
	 * id del hotel 
	 */
	private long hotelId;
	/**
	 * id de la habitacion
	 */
	private long habitacionId;
	/**
	 * id del apartamento 
	 */
	private long apartamentoId;
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * constructor
	 */
	public Agenda()
	{
		this.fechaFin = new Date(0);
		this.fechaInicio= new Date(0);
		this.agendaId = 0;
		this.reservaId = 0;
		this.viviendaUniveristariaId = 0;
		this.hostalid = 0;
		this.hotelId = 0;
		this.habitacionId = 0;
		this.apartamentoId = 0;
	}
	
	/**
	 * Constructor con valores
	 */
	public Agenda(Date pfechaFin, Date pfechaInicio, long pagendaId, long preservaId, long pviviendaUniversitariaId, long phostalId, long photelId, long phabitacionId, long papartamentoId )
	{
		this.fechaFin = pfechaFin;
		this.fechaInicio = pfechaInicio;
		this.agendaId = pagendaId;
		this.reservaId = preservaId;
		this.viviendaUniveristariaId = pviviendaUniversitariaId;
		this.hostalid = phostalId;
		this.hotelId = photelId;
		this.habitacionId = phabitacionId;
		this.apartamentoId = papartamentoId;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public long getAgendaId() {
		return agendaId;
	}

	public void setAgendaId(long agendaId) {
		this.agendaId = agendaId;
	}

	public long getReservaId() {
		return reservaId;
	}

	public void setReservaId(long reservaId) {
		this.reservaId = reservaId;
	}

	public long getViviendaUniveristariaId() {
		return viviendaUniveristariaId;
	}

	public void setViviendaUniveristariaId(long viviendaUniveristariaId) {
		this.viviendaUniveristariaId = viviendaUniveristariaId;
	}

	public long getHostalid() {
		return hostalid;
	}

	public void setHostalid(long hostalid) {
		this.hostalid = hostalid;
	}

	public long getHotelId() {
		return hotelId;
	}

	public void setHotelId(long hotelId) {
		this.hotelId = hotelId;
	}

	public long getHabitacionId() {
		return habitacionId;
	}

	public void setHabitacionId(long habitacionId) {
		this.habitacionId = habitacionId;
	}

	public long getApartamentoId() {
		return apartamentoId;
	}

	public void setApartamentoId(long apartamentoId) {
		this.apartamentoId = apartamentoId;
	}

	@Override
	public String toString() {
		return "Agenda [fechaFin=" + fechaFin + ", fechaInicio=" + fechaInicio + ", agendaId=" + agendaId
				+ ", reservaId=" + reservaId + ", viviendaUniveristariaId=" + viviendaUniveristariaId + ", hostalid="
				+ hostalid + ", hotelId=" + hotelId + ", habitacionId=" + habitacionId + ", apartamentoId="
				+ apartamentoId + "]";
	}
	
}
