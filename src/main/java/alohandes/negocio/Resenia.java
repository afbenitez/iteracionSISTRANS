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
 * Clase para modelar el concepto resenia del negocio de los ALOHANDES
 *
 * @author MIGUEL PARRA
 */
public class Resenia {
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * id unico
	 */
	private long reseniaId;
	/**
	 * comentario
	 */
	private String comentario;

	/**
	 * fecha de la resenia
	 */
	private Date fecha;
	/**
	 * puntuacion
	 */
	private int puntuacion;
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * contructor 
	 */
	public Resenia()
	{
		this.reseniaId =0;
		this.comentario = "";
		this.fecha = new Date(0);
		this.puntuacion = 0;
	}
	/**
	 * @param reseniaId
	 * @param comentario
	 * @param fecha
	 * @param puntuacion
	 */
	public Resenia(long reseniaId, String comentario, Date fecha, int puntuacion) {
	
		this.reseniaId = reseniaId;
		this.comentario = comentario;
		this.fecha = fecha;
		this.puntuacion = puntuacion;
	}
	/**
	 * @return the reseniaId
	 */
	public long getReseniaId() {
		return reseniaId;
	}
	/**
	 * @param reseniaId the reseniaId to set
	 */
	public void setReseniaId(long reseniaId) {
		this.reseniaId = reseniaId;
	}
	/**
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}
	/**
	 * @param comentario the comentario to set
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the puntuacion
	 */
	public int getPuntuacion() {
		return puntuacion;
	}
	/**
	 * @param puntuacion the puntuacion to set
	 */
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	@Override
	public String toString() {
		return "Resenia [reseniaId=" + reseniaId + ", comentario=" + comentario + ", fecha=" + fecha + ", puntuacion="
				+ puntuacion + "]";
	}
	
}
