package py.edu.ucom.is2.proyectocamel.PruebaBancos;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

//@Component
public class BancoRest  extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		restConfiguration().component("servlet").bindingMode(RestBindingMode.auto);
		
		rest().path("/api")
			.get("/saludar")
			.produces("text/plain")
			.to("direct:enviar")
			.post("/transferencia")
			.to("direct:procesar-transferencia");
		
		from("direct:enviar").transform().constant("Hola Diego Q");
		from("direct:procesar-transferencia").transform().simple("Recibido ${body}");
		
		// Route que va a procesar en el post enviar.
		from("direct:encolar-transferencia")
			.setExchangePattern(ExchangePattern.InOnly)
			.to("activemq:rest-DiegoQ")
			.setExchangePattern(ExchangePattern.InOut)
			.transform().simple("Mensaje Encolado - DQ");
	}
}