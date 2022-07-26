package py.edu.ucom.is2.proyectocamel.PruebaBancos;

import org.springframework.stereotype.Component;

//@Component
public class BancoService {
	public BancoResponse mostrarDatos(BancoRequest bancoRequest) {
		BancoResponse bancoResponse = new BancoResponse();
		System.out.println(  " Cuenta --> "+bancoRequest.getCuenta()
							+" Monto --> "+bancoRequest.getMonto() 
							+" Banco Origen --> "+bancoRequest.getBanco_origen() 
							+" Banco Destino --> "+bancoRequest.getBanco_destino()
							+" Id Transaccion --> "+bancoRequest.getId_transaccion()
							+" Fecha --> "+bancoRequest.getFecha()
							+" Mensaje --> Desde BancoService");
		return bancoResponse;
	}
}