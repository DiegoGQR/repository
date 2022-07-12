package py.edu.ucom.is2.proyectocamel.routes.rest;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

//@Component
public class RestSimpleTest extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		restConfiguration().component("servlet").bindingMode(RestBindingMode.auto);

		rest().path("/api")
		.consumes("application/json")
		.produces("application/json")
		
			.get("/saludar")
			.to("direct:enviar")
			
			.post("/enviar-saludo")
			.to("direct:procesar-saludo")
		
			.post("encolar")
			.to("direct:encolar");
		
		
		from("direct:enviar").transform().constant("Hola mundo");
		from("direct:procesar-saludo").transform().simple("Recibido ${body}");
		
		
		
		//Route que va a procesar el post enviar
		from("direct:encolar")
		.setExchangePattern(ExchangePattern.InOnly) //Importante el Exchange que se le pasa al MQ debe ser tel tipo Evento o sea InOnly
	    .to("activemq:rest-profesor")
	    .setExchangePattern(ExchangePattern.InOut) 
	    .transform().simple("Mensaje encolado exitosamente");
		
	
	}

}

//http://localhost:8080/camel/api/enviar