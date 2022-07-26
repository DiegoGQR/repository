package py.edu.ucom.is2.proyectocamel.routes.filter;

public class Filtro5 {
	public static boolean validarMonto(String valor) {
		Integer num = Integer.parseInt(valor);
		boolean esDivisible = num%5==0?true:false;
		return esDivisible;
	}

}