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
public class ConsumidorBancoITAUin extends RouteBuilder{

	@Autowired
	BancoService bancoService;
	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		from("activemq:QUINONEZ-ITAU-IN")
			.unmarshal(new JacksonDataFormat(BancoRequest.class))
			.log("Leemos la cola y convertimos en Objeto Request")
			.process(new AgregarHeaderProcessATLAS())
			.log("Agregamos el Header - ATLAS")
			.filter().method(new FiltroDeFecha(),"ValidarFecha")
				.choice()
				.when(header("banco_origen").contains("ATLAS"))
					.log("Banco origen - ATLAS")
					.bean(bancoService,"TransaccionExitosa")
					.log("${body}")
					.setExchangePattern(ExchangePattern.InOnly)					
					.marshal(new JacksonDataFormat(BancoResponse.class))
					.log("${body}")
					.to("activemq:QUINONEZ-ATLAS-OUT")
					.log("Se encolo la transaccion a - QUINONEZ-ATLAS-OUT")
					.endChoice()
				.otherwise()
					.transform().constant("El valor enviado no es valido.")
			    .end()
			.to("log:Transaccion Aprobada")
			.stop()
			.end()
			.bean(bancoService,"TransaccionNoAprobada")
			.log("${body}")
			.setExchangePattern(ExchangePattern.InOnly)					
			.marshal(new JacksonDataFormat(BancoResponse.class))
			.log("${body}")
			.to("activemq:QUINONEZ-ATLAS-OUT")
			.to("log:Transaccion No Aprobada");
	}
}
class AgregarHeaderProcessATLAS implements Processor{
	Logger logger = (Logger) LoggerFactory.getLogger(ProcessorTest.class);
	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> headers = exchange.getIn().getHeaders();
		headers.put("banco_origen", "ATLAS");
		exchange.getIn().setHeaders(headers);
		System.out.print("Desde Process - Header ATLAS Agregado ");
	} 
}
