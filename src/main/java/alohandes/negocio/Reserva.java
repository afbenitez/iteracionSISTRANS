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
/**
 * Clase para modelar el concepto reserva del negocio de los ALOHANDES
 *
 * @author MIGUEL PARRA
 */
public class Reserva {
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * id unico
	 */
	private long reservaId;
	/**
	 * id de resenia
	 */
	private long reseniaId;
	/**
	 * id (?)
	 */
	private String id;
	/**
	 * costo
	 */
	private Double costo ;
	/**
	 * Tipo de contrato
	 */
	private String contrato;
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * Constructor
	 */
	public Reserva()
	{
		this.reservaId = 0;
		this.reseniaId  =0;
		this.id = "";
		this.costo = 0.0;
		this.contrato = "";
	}
	/**
	 * @param reservaId
	 * @param reseniaId
	 * @param id
	 * @param costo
	 * @param contrato
	 */
	public Reserva(long reservaId, long reseniaId, String id, Double costo, String contrato) {

		this.reservaId = reservaId;
		this.reseniaId = reseniaId;
		this.id = id;
		this.costo = costo;
		this.contrato = contrato;
	}
	/**
	 * @return the reservaId
	 */
	public long getReservaId() {
		return reservaId;
	}
	/**
	 * @param reservaId the reservaId to set
	 */
	public void setReservaId(long reservaId) {
		this.reservaId = reservaId;
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
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the costo
	 */
	public Double getCosto() {
		return costo;
	}
	/**
	 * @param costo the costo to set
	 */
	public void setCosto(Double costo) {
		this.costo = costo;
	}
	/**
	 * @return the contrato
	 */
	public String getContrato() {
		return contrato;
	}
	/**
	 * @param contrato the contrato to set
	 */
	public void setContrato(String contrato) {
		this.contrato = contrato;
	}
	@Override
	public String toString() {
		return "Reserva [reservaId=" + reservaId + ", reseniaId=" + reseniaId + ", id=" + id + ", costo=" + costo
				+ ", contrato=" + contrato + "]";
	}
	
	
	
}
