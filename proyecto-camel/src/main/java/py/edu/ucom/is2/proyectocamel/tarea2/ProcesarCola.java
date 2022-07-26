package py.edu.ucom.is2.proyectocamel.tarea2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;


//@Component
public class ProcesarCola implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		
		BancoRequest bancoRequest = exchange.getIn().getBody(BancoRequest.class);
				
		if(bancoRequest != null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDateTime now = LocalDateTime.now();
			   
			System.out.println(dtf.format(now).equals(bancoRequest.getFecha()));
			
			System.out.println("Banco Origen: " + bancoRequest.getBanco_origen() 
							+ " Banco Destino: " + bancoRequest.getBanco_destino() 
							+ " Cuenta: " + bancoRequest.getCuenta()
							+ " Monto: " + bancoRequest.getMonto() 
							+ " Transaccion exitosa!");
		}
		else
			System.err.print("Procesar Cola NULL");
	}
}
