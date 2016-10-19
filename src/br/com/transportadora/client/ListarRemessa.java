package br.com.transportadora.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.filter.HttpBasicAuthFilter;

import br.com.transportadora.entity.Remessa;
import br.com.transportadora.entity.Remessas;

public class ListarRemessa {

	public static void main(String[] args) {

		Client client = ClientBuilder.newClient();
		client.register(new HttpBasicAuthFilter("admin", "1357"));
		WebTarget webTarget = client.target("http://35.160.30.34:8080/Transportadora").path("remessa");
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.get();
		Remessas remessas = response.readEntity(Remessas.class);
		
		for(Remessa remessa : remessas.getRemessaList()){
			System.out.println("Doc destinatario: " + remessa.getDocumentoDestinatario() +
					"\nDoc remetente: " + remessa.getDocumentoRemetente() + 
					"\nValor do frete: " + remessa.getValorFrete() + 
					"\n-----------------------");
		}
		
	}

}
