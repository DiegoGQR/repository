package py.edu.ucom.is2.proyectocamel.PruebaBancos;

import java.util.Random;

public class BancoRequest {
	@Override
	public String toString() {
		return "BancoRequest [cuenta=" + cuenta + ", monto=" + monto + ", banco_origen=" + banco_origen
				+ ", banco_destino=" + banco_destino + ", id_transaccion=" + id_transaccion +", fecha=" + fecha +"]";
	}
	
	String cuenta;
	static Integer monto;
	String banco_origen;
	String banco_destino;
	Integer id_transaccion;
	String fecha;
	
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public Integer getMonto() {
		return monto;
	}
	public void setMonto(Integer monto) {
		this.monto = monto;
	}
	public String getBanco_origen() {
		return banco_origen;
	}
	public void setBanco_origen(String banco_origen) {
		this.banco_origen = banco_origen;
	}
	public String getBanco_destino() {
		return banco_destino;
	}
	public void setBanco_destino(String banco_destino) {
		this.banco_destino = banco_destino;
	}
	public Integer getId_transaccion() {
		return id_transaccion;
	}
	public void setId_transaccion(Integer id_transaccion) {
		this.id_transaccion = generadorNumero();
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int generadorNumero() {
		Random rand = new Random();
		int max = 100000;
		int min = 0;
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	public static boolean validarMonto() {
		boolean montoMenoraVeinteMillones = monto<20000000?true:false;
		return montoMenoraVeinteMillones;
	}
	
}