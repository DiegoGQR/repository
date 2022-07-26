package py.edu.ucom.is2.proyectocamel.tarea2;

import org.springframework.stereotype.Component;

//@Component
public class FiltroDeMonto {

	public boolean ValidarMonto(BancoRequest bancoRequest) {
	
		if (bancoRequest.getMonto() < 20000000) {
			return true;
		}else {
			return false;
		}
		
	}
}
