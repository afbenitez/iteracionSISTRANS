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
 * Clase para modelar el concepto cliente del negocio de los ALOHANDES
 *
 * @author MIGUEL PARRA
 */
public class Cliente {
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * id del usuario
	 */
	private long clienteId;
	/**
	 * id de la reserva
	 */
	private long reservaId;
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * constructor 
	 */
	public Cliente()
	{
		this.clienteId = 0;
		this.reservaId = 0;
		
	}
	/**
	 * @param clienteId
	 * @param reservaId
	 */
	public Cliente(long clienteId, long reservaId) {
	
		this.clienteId = clienteId;
		this.reservaId = reservaId;
	}
	/**
	 * @return the clienteId
	 */
	public long getClienteId() {
		return clienteId;
	}
	/**
	 * @param clienteId the clienteId to set
	 */
	public void setClienteId(long clienteId) {
		this.clienteId = clienteId;
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
	@Override
	public String toString() {
		return "Cliente [clienteId=" + clienteId + ", reservaId=" + reservaId + "]";
	}
	
	
}
