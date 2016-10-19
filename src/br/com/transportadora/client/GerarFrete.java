package br.com.transportadora.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.filter.HttpBasicAuthFilter;

import br.com.transportadora.entity.Frete;
import br.com.transportadora.entity.RSResponse;
import br.com.transportadora.entity.Remessa;

public class GerarFrete {

	public static void main(String[] args) {
		Remessa remessa = new Remessa();
		remessa.setDocumentoDestinatario("dsgsdgdsgsdggf");
		remessa.setDocumentoRemetente("sdgsdfgdksljgldsjg");
		Frete frete = new Frete();
		frete.setNumeroDeProdutos(4);
		frete.setValorTotalDaRemessa(345);
		remessa.setFrete(frete);

		Client client = ClientBuilder.newClient();
		client.register(new HttpBasicAuthFilter("Maria", "123"));
		WebTarget webTarget = client.target("http://35.160.30.34:8080/Transportadora").path("frete").path("gerar");
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.post(Entity.entity(remessa, MediaType.APPLICATION_XML));
		RSResponse resp = response.readEntity(RSResponse.class);
		System.out.println(resp.getMessage());
		System.out.println(resp.getvalue());
	}

}
