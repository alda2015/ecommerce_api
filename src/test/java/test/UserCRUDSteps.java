package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class UserCRUDSteps{
	
	private CloseableHttpClient client;
	private HttpPost httpPost;
	private CloseableHttpResponse response;

	@Given("a user with email $email and mdp is $mdp")
	public void givenAUser(String email, String mdp) throws UnsupportedEncodingException{
		client = HttpClients.createDefault();
		httpPost = new HttpPost("http://localhost:8080/ecommerce_api/users/addUser");
	    String json = "{\"email\":\""+email+"\",\"mdp\":\""+mdp+"\"}";
	    StringEntity entity = new StringEntity(json);
	    httpPost.setEntity(entity);
	    httpPost.setHeader("Content-type", "application/json");
	}
	
	@When("I add user")
	public void addUser() throws ClientProtocolException, IOException{
		response = client.execute(httpPost);
	}
	
	@Then("a user with email $email should be added into the database")
	public void checkIfUserAdded(String email) throws IOException{
		HttpGet httpGet = new HttpGet("http://localhost:8080/ecommerce_api/users/"+email);
		response = client.execute(httpGet);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
		    result.append(line);
		}
		
		HttpDelete httpDelete = new HttpDelete("http://localhost:8080/ecommerce_api/users/"+email);

		client.execute(httpDelete);
		client.close();
	}
	
//	************************************************************************
//	@Given("a user with email $email and mdp is $mdp")
//	public void givenLogin(String email, String mdp) throws UnsupportedEncodingException{
//		client = HttpClients.createDefault();
//		httpPost = new HttpPost("http://localhost:8080/ecommerce_api/users/login");
//	    String json = "{\"email\":\""+mdp+"\",\"password\":\""+mdp+"\"}";
//	    StringEntity entity = new StringEntity(json);
//	    httpPost.setEntity(entity);
//	    httpPost.setHeader("Content-type", "application/json");
//	}
//	
//	@When("I'm login")
//	public void Login() throws ClientProtocolException, IOException{
//		response = client.execute(httpPost);
//	}
}
