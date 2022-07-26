package py.edu.ucom.is2.proyectocamel.PruebaBancos;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.camel.ExchangePattern;
import org.apache.camel.model.DataFormatDefinition;
import org.apache.camel.model.dataformat.JsonDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import py.edu.ucom.is2.proyectocamel.routes.ProcessorTest;


//@Component
public class BancoProductor extends RouteBuilder{

	@Autowired
	BancoService bancoService;
	GeneradorId generadorId;
	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		restConfiguration().component("servlet").bindingMode(RestBindingMode.auto);
		
		rest().path("/api")
			.consumes("application/json")
			.produces("application/json")
			
		.post("/transferencia")
			.type(BancoRequest.class) 
			.outType(BancoResponse.class)
			.to("direct:procesarTransaccion");
	
		//Encolamos los Mensajes recibidos del POST
		from("direct:procesarTransaccion")
			.bean(bancoService)
			.filter().method(new BancoRequest(),"validarMonto")
				.log("log:El monto es menor a 20 millones")
				.process(new AgregarHeaderProcess())
				.log("log:Se agrego el Header al objeto")
				.choice()
				.when(header("banco_destino").contains("ATLAS"))
					.log("log:En el Header el Banco_destino = ATLAS")
					.setExchangePattern(ExchangePattern.InOnly)
					.marshal(new JacksonDataFormat(BancoRequest.class))
					.to("activemq:QUINONEZ-ATLAS-IN")				
					.setExchangePattern(ExchangePattern.InOut).transform().simple("Mensaje : Mensaje Encolado en - QUINONEZ-ATLAS-IN")
					.stop()
					.endChoice()
				.otherwise()
					.log("log:En el Header el Banco_destino = No encontrado")
					.stop()
				.end()
			.log("${body}")
			.log("log:El monto es mayor o igual a 20 millones")
			.end();
		
	}
	

}
class AgregarHeaderProcess implements Processor{
	Logger logger = (Logger) LoggerFactory.getLogger(ProcessorTest.class);
	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> headers = exchange.getIn().getHeaders();
		headers.put("banco_destino", "ATLAS");
		exchange.getIn().setHeaders(headers);
		//System.out.print("Header Agregado");
	} 
}