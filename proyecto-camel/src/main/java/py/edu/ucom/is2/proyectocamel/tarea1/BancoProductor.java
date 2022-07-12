package py.edu.ucom.is2.proyectocamel.tarea1;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import py.edu.ucom.is2.proyectocamel.routes.rest.tipos.AlumnoRequest;
import py.edu.ucom.is2.proyectocamel.routes.rest.tipos.AlumnoResponse;


@Component
public class BancoProductor extends RouteBuilder{

	@Autowired
	
	BancoService service1;
	
	@Override
	public void configure() throws Exception {
		
		// TODO Auto-generated method stub
		restConfiguration().component("servlet").bindingMode(RestBindingMode.off);
		
		rest().path("/api")
			.consumes("application/json")
			.produces("application/json")
			
		.post("/transferencia")
			.type(BancoRequest.class) 
			.outType(BancoResponse.class) 
			.to("direct:procesar-choice");
		
		//Encolamos los Mensajes recibidos del POST
		from("direct:procesar-choice")
			.choice()
			.when(header("banco_destino").contains("ATLAS"))
				.setExchangePattern(ExchangePattern.InOnly)
				.to("activemq:QUINONEZ-ATLAS-IN")
				.setExchangePattern(ExchangePattern.InOut).transform().simple("Mensaje Encolado - ATLAS")
				.endChoice()
			.when(header("banco_destino").contains("ITAU"))
				.setExchangePattern(ExchangePattern.InOnly)
				.to("activemq:QUINONEZ-ITAU-IN")
				.setExchangePattern(ExchangePattern.InOut).transform().simple("Mensaje Encolado - ITAU")
				.endChoice()
			.when(header("banco_destino").contains("SUDAMERIS"))
				.setExchangePattern(ExchangePattern.InOnly)
				.to("activemq:QUINONEZ-SUDAMERIS-IN")
				.setExchangePattern(ExchangePattern.InOut).transform().simple("Mensaje Encolado - SUDAMERIS")
				.endChoice()
			.otherwise()
				.transform().constant("El valor enviado no es valido");
		
	}

}