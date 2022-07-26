package py.edu.ucom.is2.proyectocamel.tarea2;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import py.edu.ucom.is2.proyectocamel.routes.ProcessorTest;

@Component
public class ConsumidorBancoGNBout extends RouteBuilder{

	@Autowired
	BancoService bancoService;
	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		from("activemq:QUINONEZ-GNB-OUT")
			.unmarshal(new JacksonDataFormat(BancoResponse.class))
			.log("Leemos la cola y convertimos en Objeto Response")
			.bean(bancoService,"DesencolarMensajeDelOut")
			.log("${body}")
			.log("Se recibio el mensaje de - QUINONEZ-GNB-OUT");
	}
}
