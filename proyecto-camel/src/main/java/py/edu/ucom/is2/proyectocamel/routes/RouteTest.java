package py.edu.ucom.is2.proyectocamel.routes;

import java.time.LocalDateTime;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class RouteTest extends RouteBuilder {

	@Autowired
	HoraActualProductorWired productorHora;

	@Autowired
	SimpleLog simpleLogger;

	@Override
	public void configure() throws Exception {
		from("timer:is2timer?period=5000") // cola
				.log("${body}")
				.bean(productorHora, "horaActual2") // se pasa isntancia y nombre de médodo(cuando la
																	// clase tiene más de 1 método)
				.bean(simpleLogger)
				.process(new ProcesadorSimple())
		.to("log:is2log");// base de datos
	}

}

class ProcesadorSimple implements Processor {
	Logger logger = LoggerFactory.getLogger(RouteTest.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		logger.info("SimpleProcessor {}", exchange.getMessage().getBody());

	}

}

@Component
class HoraActualProductorWired {
	public String horaActual() {
		return "hora actual con wired > " + LocalDateTime.now();
	}

	public String horaActual2() {
		return "hora actual con wired con nombre de metodo > " + LocalDateTime.now();
	}
}

@Component
class SimpleLog {
	Logger logger = LoggerFactory.getLogger(RouteTest.class);

	public void horaActualProductor(String msg) {
		logger.info("mensaje dentro del logger {}", msg);
	}
}
