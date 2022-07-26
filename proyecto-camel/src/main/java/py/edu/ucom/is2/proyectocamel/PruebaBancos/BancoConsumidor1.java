package py.edu.ucom.is2.proyectocamel.PruebaBancos;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import py.edu.ucom.is2.proyectocamel.routes.ProcessorTest;

//@Component
public class BancoConsumidor1 extends RouteBuilder{
	private JacksonDataFormat jsonDataFormat;
	@Autowired
	BancoService bancoService;
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		jsonDataFormat = new JacksonDataFormat(BancoRequest.class);
		from("activemq:QUINONEZ-ATLAS-IN")
			.unmarshal(jsonDataFormat)
			//.process(new ProcessorCola())
			.bean(bancoService)
			//.log("${body}")		
			.end();
	}
}


//class ProcessorCola implements Processor {
	//Logger logger = LoggerFactory.getLogger(ProcessorTest.class);

	//@Override
	//public void process(Exchange exchange) throws Exception {
		//String body  = exchange.getIn().getBody(String.class);
		//exchange.getIn().setBody("mensaje modificado mediante procesador");
	//}
//}

//from("activemq:QUINONEZ-ATLAS-IN")
//.log("${body}")
//.bean("${body}","getCuenta") //transformacion del Mensaje
//.log("${body}")
//.to("log:BEAN-DIEGO-1")
//.end();
//from("activemq:QUINONEZ-ATLAS-IN")
//.to("log:BEAN-DIEGO-1")
//.log("Mensaje recibido - BancoConsumidor1 - ATLAS ${body}")
//.end();
//
//from("activemq:QUINONEZ-ATLAS-IN").process((Processor) new Processor() {
//String payload = null;
//public void process(Exchange exchange) throws Exception {
		//payload = exchange.getMessage().getBody(String.class);
		// do something with the payload and/or exchange here
		//exchange.getMessage().setBody("Changed body");
		//System.out.print(payload);
	//}
//})

