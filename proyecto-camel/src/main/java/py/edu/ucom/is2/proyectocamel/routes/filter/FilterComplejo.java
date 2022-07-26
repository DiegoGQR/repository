package py.edu.ucom.is2.proyectocamel.routes.filter;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class FilterComplejo extends RouteBuilder {
	@Autowired
	GeneradorNumero generador;

	@Override
	public void configure() throws Exception {
		from("timer:mytimer?period=1000")
		.bean(generador) // transformacion del Mensaje
		.log("${body}")
		.filter().method(new Filtro(), "divisible")
		.to("log:mylogger");
	}
}

class Filtro {
	public boolean divisible(String valor) {
		Integer num = Integer.parseInt(valor);
		boolean esDivisible = num%5==0?true:false;
		//System.out.println("Numero: "+valor + ". Es divisible:"+esDivisible);
		return esDivisible;
	}
}