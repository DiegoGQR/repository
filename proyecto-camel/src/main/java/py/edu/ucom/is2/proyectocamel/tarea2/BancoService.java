package py.edu.ucom.is2.proyectocamel.tarea2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import py.edu.ucom.is2.proyectocamel.routes.ProcessorTest;

@Component
public class BancoService {
	public BancoRequest genDateID(BancoRequest genDateID) {
		int int_random = ThreadLocalRandom.current().nextInt(1 , 1000000) ;  
		genDateID.setId_transaccion(int_random);
	
		if(genDateID.getFecha() == null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		    LocalDateTime now = LocalDateTime.now();
		    genDateID.setFecha(dtf.format(now));
		}
		return genDateID;
	}
	
	public BancoRequest getDate(BancoRequest getDate) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime now = LocalDateTime.now();
		getDate.setFecha(dtf.format(now));
		return getDate;
	}	
	
	public BancoResponse MontoPermitido(BancoRequest bancoRequest) {
		BancoResponse respuesta = new BancoResponse();
		respuesta.setIdTransaccion(bancoRequest.getId_transaccion());
		respuesta.setMensaje("El monto supera máximo permitido");
		
		//{"id_transaccion":123 "mensaje": "El monto supera máximo permitido"}
		
		return respuesta;
	}

	public BancoResponse TransaccionExitosa(BancoRequest bancoRequest) {
		BancoResponse respuesta = new BancoResponse();
		respuesta.setIdTransaccion(bancoRequest.getId_transaccion());
		respuesta.setMensaje("Transferencia procesada exitosamente");
		
		//{"id_transaccion":123 "mensaje": "Transferencia procesada exitosamente"}
		
		return respuesta;		
	}
	
	public BancoResponse MensajeParaElPost(BancoRequest bancoRequest) {
		BancoResponse respuesta = new BancoResponse();
		respuesta.setMensaje("Transaccion Encolada -  Banco destino: -> "+ bancoRequest.getBanco_destino() 
							+" ID de Transaccion: -> "+ bancoRequest.getId_transaccion() );
		return respuesta;
	}
	
	public void Encolado(BancoRequest bancoRequest) {
		System.out.println("Transaccion Encolada -  Banco destino: -> "+ bancoRequest.getBanco_destino() 
							+" -> ID de Transaccion: -> "+ bancoRequest.getId_transaccion());
	}
	
	public BancoResponse TransaccionAprobada(BancoRequest bancoRequest) {
		BancoResponse respuesta = new BancoResponse();
		respuesta.setIdTransaccion(bancoRequest.getId_transaccion());
		respuesta.setMensaje("Mensaje encolado");
		
		//{"id_transaccion":123 "mensaje": "Mensaje encolado"}
		
		return respuesta;
	}

	public BancoResponse TransaccionNoAprobada(BancoRequest bancoRequest) {
		BancoResponse respuesta = new BancoResponse();
		respuesta.setMensaje("Transaccion no aprobada - Mensaje caducado");
		respuesta.setIdTransaccion(bancoRequest.getId_transaccion());
		return respuesta;
	}
	
	public BancoResponse DesencolarMensajeDelOut(BancoResponse bancoResponse) {
		BancoResponse respuesta = new BancoResponse();
		respuesta.setMensaje(bancoResponse.getMensaje());
		respuesta.setIdTransaccion(bancoResponse.getIdTransaccion());
		return respuesta;
	}

}
