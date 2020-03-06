package edu.escuelaing.arep.app;

import static spark.Spark.get;
import static spark.Spark.port;

import java.util.ArrayList;
import java.util.List;

import edu.escuelaing.arep.app.StandardResponse;
import com.google.gson.Gson;

public class SparkWebApp {

	public static void main(String[] args) {
		port(getPort());

		get("/hello", (request, response) -> {
			response.type("application/json");	
			
			ArrayList<String> strList = new ArrayList<String>();
			strList.add("hola1");
			strList.add("hola2");
			strList.add("hola3");
			strList.add("hola4");
			strList.add("hola5");
			
			return new Gson().toJson( new StandardResponse("accepted", new Gson().toJsonTree(strList)) );
		});
	}

	static int getPort() {
		if (System.getenv("PORT") != null) {
			return Integer.parseInt(System.getenv("PORT"));
		}
		return 4567; // returns default port if heroku-port isn't set(i.e. on localhost)
	}
}