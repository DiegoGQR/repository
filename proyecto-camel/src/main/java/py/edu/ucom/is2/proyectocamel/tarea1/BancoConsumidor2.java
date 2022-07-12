package py.edu.ucom.is2.proyectocamel.tarea1;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BancoConsumidor2 extends RouteBuilder{
	private JacksonDataFormat jsonDataFormat;
	@Autowired
	BancoService bancoService;
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		jsonDataFormat = new JacksonDataFormat(BancoRequest.class);
		from("activemq:QUINONEZ-ITAU-IN")
			.unmarshal(jsonDataFormat)
			.bean(bancoService)
			.end();
	}
}