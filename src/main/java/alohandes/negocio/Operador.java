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
 * Clase para modelar el concepto operador del negocio de los ALOHANDES
 *
 * @author MIGUEL PARRA
 */
public class Operador {
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * id unico
	 */
	private long operadorId;
	/**
	 * id de alohandes
	 */
	private long alohandesId;
	/**
	 * email
	 */
	private String email;
	/**
	 * nombre
	 */
	private String nombre;
	/*
	 * numero celular
	 */
	private long numeroCel;
	/**
	 * numero id en cadena
	 */
	private String numeroId;
	/**
	 * tipo de operador
	 */
	private String tipoId;
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * constructor
	 */
	public Operador()
	{
		this.operadorId =0;
		this.alohandesId =0;
		this.email = "";
		this.nombre = "";
		this.numeroCel = 0;
		this.numeroId = "";
		this.tipoId = "";
	}
	/**
	 * @param operadorId
	 * @param alohandesId
	 * @param email
	 * @param nombre
	 * @param numeroCel
	 * @param numeroId
	 * @param tipoId
	 */
	public Operador(long operadorId, long alohandesId, String email, String nombre, long numeroCel, String numeroId,
			String tipoId) {
	
		this.operadorId = operadorId;
		this.alohandesId = alohandesId;
		this.email = email;
		this.nombre = nombre;
		this.numeroCel = numeroCel;
		this.numeroId = numeroId;
		this.tipoId = tipoId;
	}
	/**
	 * @return the operadorId
	 */
	public long getOperadorId() {
		return operadorId;
	}
	/**
	 * @param operadorId the operadorId to set
	 */
	public void setOperadorId(long operadorId) {
		this.operadorId = operadorId;
	}
	/**
	 * @return the alohandesId
	 */
	public long getAlohandesId() {
		return alohandesId;
	}
	/**
	 * @param alohandesId the alohandesId to set
	 */
	public void setAlohandesId(long alohandesId) {
		this.alohandesId = alohandesId;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the numeroCel
	 */
	public long getNumeroCel() {
		return numeroCel;
	}
	/**
	 * @param numeroCel the numeroCel to set
	 */
	public void setNumeroCel(long numeroCel) {
		this.numeroCel = numeroCel;
	}
	/**
	 * @return the numeroId
	 */
	public String getNumeroId() {
		return numeroId;
	}
	/**
	 * @param numeroId the numeroId to set
	 */
	public void setNumeroId(String numeroId) {
		this.numeroId = numeroId;
	}
	/**
	 * @return the tipoId
	 */
	public String getTipoId() {
		return tipoId;
	}
	/**
	 * @param tipoId the tipoId to set
	 */
	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}
	@Override
	public String toString() {
		return "Operador [operadorId=" + operadorId + ", alohandesId=" + alohandesId + ", email=" + email + ", nombre="
				+ nombre + ", numeroCel=" + numeroCel + ", numeroId=" + numeroId + ", tipoId=" + tipoId + "]";
	}
	
	
}
