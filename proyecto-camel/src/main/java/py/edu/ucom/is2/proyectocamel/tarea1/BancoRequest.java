package py.edu.ucom.is2.proyectocamel.tarea1;

public class BancoRequest {
	@Override
	public String toString() {
		return "BancoRequest [cuenta=" + cuenta + ", monto=" + monto + ", banco_origen=" + banco_origen
				+ ", banco_destino=" + banco_destino + "]";
	}
	String cuenta;
	String monto;
	String banco_origen;
	String banco_destino;
	
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
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
	
}