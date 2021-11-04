package br.com.educamil.webservice;

import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;

import br.com.educamil.entity.Endereco;
import br.com.educamil.webservice.GensonProvider;

public class WebServiceEndereco {

	private Client client;
	private WebResource webResource;

	public WebServiceEndereco() {
		ClientConfig clientConfig = new DefaultClientConfig(GensonProvider.class);
		client = Client.create(clientConfig);
		// Utilizado para imprimir as operacoes no console
		client.addFilter(new LoggingFilter(System.out));
		webResource = client.resource("https://viacep.com.br/ws/");

	}

	public Endereco pesquisarCep(String cep) {
		return webResource.path(cep).path("/json").get(Endereco.class);
	}

		

}
