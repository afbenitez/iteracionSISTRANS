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
 * Clase para modelar el concepto usuario del negocio de los ALOHANDES
 *
 * @author MIGUEL PARRA
 */
public class Usuario {
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * edad
	 */
	private int edad;
	/**
	 * email
	 */
	private String email;
	/**
	 * nombre
	 */
	private String nombre;
	/**
	 * numero cel
	 */
	private long numeroCel;
	/**
	 * numero id
	 */
	private String numeroId;
	/**
	 * id unico
	 */
	private long usuarioId;
	/**
	 * id alohandes
	 */
	private long alohandesId;
	/**
	 * id universidad
	 */
	private long universidadId;
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * constructor
	 */
	public Usuario() {
		this.edad =0;
		this.email ="";
		this.nombre ="";
		this.numeroCel =0;
		this.numeroId ="";
		this.usuarioId =0;
		this.alohandesId = 0;
		this.universidadId =0;
	}
	/**
	 * @param edad
	 * @param email
	 * @param nombre
	 * @param numeroCel
	 * @param numeroId
	 * @param usuarioId
	 * @param alohandesId
	 * @param universidadId
	 */
	public Usuario(int edad, String email, String nombre, long numeroCel, String numeroId, long usuarioId,long alohandesId, long universidadId) {
		
		this.edad = edad;
		this.email = email;
		this.nombre = nombre;
		this.numeroCel = numeroCel;
		this.numeroId = numeroId;
		this.usuarioId = usuarioId;
		this.alohandesId = alohandesId;
		this.universidadId = universidadId;
	}
	/**
	 * @return the edad
	 */
	public int getEdad() {
		return edad;
	}
	/**
	 * @param edad the edad to set
	 */
	public void setEdad(int edad) {
		this.edad = edad;
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
	 * @return the usuarioId
	 */
	public long getUsuarioId() {
		return usuarioId;
	}
	/**
	 * @param usuarioId the usuarioId to set
	 */
	public void setUsuarioId(long usuarioId) {
		this.usuarioId = usuarioId;
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
	 * @return the universidadId
	 */
	public long getUniversidadId() {
		return universidadId;
	}
	/**
	 * @param universidadId the universidadId to set
	 */
	public void setUniversidadId(long universidadId) {
		this.universidadId = universidadId;
	}
	@Override
	public String toString() {
		return "Usuario [edad=" + edad + ", email=" + email + ", nombre=" + nombre + ", numeroCel=" + numeroCel
				+ ", numeroId=" + numeroId + ", usuarioId=" + usuarioId + ", alohandesId=" + alohandesId
				+ ", universidadId=" + universidadId + "]";
	}
	

}
