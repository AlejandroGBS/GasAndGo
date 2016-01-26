package principal;

import java.nio.charset.Charset;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

public class CiscoRest {
	

	
	public void llamarAlServicioRest(){

		RestTemplate restTemplate = new RestTemplate();
		
		HttpEntity<String> request = new HttpEntity<String>(createHeaders("adminuccx", "adminHna"));
		
		ResponseEntity<String> resultado = restTemplate.exchange("http://192.168.33.203/adminapi/csq", HttpMethod.GET, request, String.class);
		//String resultado = restTemplate.getForObject("https://192.168.33.203/adminapi/agentstats", String.class);
		System.out.println(resultado);
	}
	
	private HttpHeaders createHeaders(final String username, final String password ){
	    HttpHeaders headers =  new HttpHeaders(){
	          {
	             String auth = username + ":" + password;
	             byte[] encodedAuth = Base64Utils.encode(
	                auth.getBytes(Charset.forName("US-ASCII")) );
	             String authHeader = "Basic " + new String( encodedAuth );
	             set( "Authorization", authHeader );
	          }
	       };
	       //headers.add("Content-Type", "application/xml");
	       //headers.add("Accept", "application/xml");

	       return headers;
	}

}
