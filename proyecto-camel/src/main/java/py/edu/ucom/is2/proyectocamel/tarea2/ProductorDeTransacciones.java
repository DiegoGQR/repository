package py.edu.ucom.is2.proyectocamel.tarea2;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductorDeTransacciones extends RouteBuilder{
//Posibles configuraciones para el Postman
//Valor POST : banco_destino ATLAS - Valor BODY : {"cuenta": "123456","monto": 5000000,"banco_origen": "ITAU","banco_destino": "ATLAS","id_transaccion": 0,"fecha": "25/07/2022"}
//Valor POST : banco_destino ITAU - Valor BODY : {"cuenta": "123456","monto": 5000000,"banco_origen": "ATLAS","banco_destino": "ITAU","id_transaccion": 0,"fecha": "25/07/2022"}
//Valor POST : banco_destino GNB - Valor BODY : {"cuenta": "123456","monto": 5000000,"banco_origen": "ITAU","banco_destino": "GNB","id_transaccion": 0,"fecha": "25/07/2022"}

	@Autowired
	BancoService bancoService;

	@Override
	public void configure() throws Exception {
			
		restConfiguration().component("servlet").bindingMode(RestBindingMode.auto);
		
		rest().path("/api")
			.consumes("application/json")
			.produces("application/json")
			
		.post("/transferencia")
			.type(BancoRequest.class)
			.outType(BancoResponse.class)
			.to("direct:procesarTransaccion");
					
		from("direct:procesarTransaccion")
			.bean(bancoService,"genDateID")
			.filter().method(FiltroDeMonto.class,"ValidarMonto")
			.to("direct:procesarChoice").stop()
			.end()
			.bean(bancoService,"MontoPermitido")
			.log("${body}");
		
		//Encolamos los Mensajes del que vienen del POS
		from("direct:procesarChoice")
			.choice()
			.when(header("banco_destino").contains("ATLAS"))
				.setExchangePattern(ExchangePattern.InOnly)
				.marshal(new JacksonDataFormat(BancoRequest.class))
				.to("activemq:QUINONEZ-ATLAS-IN")// Encolamos a ATLAS IN
				.setExchangePattern(ExchangePattern.InOut)
				.unmarshal(new JacksonDataFormat(BancoRequest.class))
				.bean(bancoService,"TransaccionAprobada")
				.log("${body}")
				.endChoice()
			.when(header("banco_destino").contains("ITAU"))
				.setExchangePattern(ExchangePattern.InOnly)
				.marshal(new JacksonDataFormat(BancoRequest.class))
				.to("activemq:QUINONEZ-ITAU-IN")// Encolamos a ITAU IN
				.setExchangePattern(ExchangePattern.InOut)
				.unmarshal(new JacksonDataFormat(BancoRequest.class))
				.bean(bancoService,"TransaccionAprobada")
				.log("${body}")
				.endChoice()
			.when(header("banco_destino").contains("GNB"))
				.setExchangePattern(ExchangePattern.InOnly)
				.marshal(new JacksonDataFormat(BancoRequest.class))
				.to("activemq:QUINONEZ-GNB-IN")// Encolamos a GNB IN
				.setExchangePattern(ExchangePattern.InOut)
				.unmarshal(new JacksonDataFormat(BancoRequest.class))
				.bean(bancoService,"TransaccionAprobada")
				.log("${body}")
				.endChoice()
			.otherwise()
				.transform().constant("El valor enviado no es valido.")
		    .end();
	}

}
