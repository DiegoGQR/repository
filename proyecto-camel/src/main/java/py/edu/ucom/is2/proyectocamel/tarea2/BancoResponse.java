package py.edu.ucom.is2.proyectocamel.tarea2;

import java.io.Serializable;

import org.springframework.stereotype.Component;

public class BancoResponse implements Serializable{

	Integer id_Transaccion;			
	String mensaje;

	public Integer getIdTransaccion() {
		return id_Transaccion;
	}
	public void setIdTransaccion(Integer id_Transaccion) {
		this.id_Transaccion = id_Transaccion;
	}
	public BancoResponse(String mensaje, Integer id_Transaccion) {
		super();
		this.mensaje = mensaje;
		this.id_Transaccion = id_Transaccion;
	}
	public BancoResponse() {
		// TODO Auto-generated constructor stub
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
