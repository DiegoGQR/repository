package py.edu.ucom.is2.proyectocamel.PruebaBancos;

public class ValidarMonto {
	public static boolean validarMonto(String valor) {
		Integer num = Integer.parseInt(valor);
		boolean montoMenoraVeinteMillones = num<20000000?true:false;
		return montoMenoraVeinteMillones;
	}

}