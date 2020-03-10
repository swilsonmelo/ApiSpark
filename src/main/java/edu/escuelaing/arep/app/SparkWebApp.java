package edu.escuelaing.arep.app;

import static spark.Spark.get;
import static spark.Spark.port;

import java.util.ArrayList;
import java.util.List;

import edu.escuelaing.arep.app.StandardResponse;
import edu.escuelaing.arep.model.User;
import edu.escuelaing.arep.services.UserServices;
import edu.escuelaing.arep.services.impl.UserServicesImpl;

import com.google.gson.Gson;

public class SparkWebApp {

	private static UserServicesImpl userServices; 
	
	public static void main(String[] args) {
		userServices = new UserServicesImpl(); 
		port(getPort());

		get("/users", (request, response) -> {
			response.type("application/json");	
			return new Gson().toJson(
					new StandardResponse("Accepted",new Gson()
					.toJsonTree(userServices.getAll())));
		});
	}

	static int getPort() {
		if (System.getenv("PORT") != null) {
			return Integer.parseInt(System.getenv("PORT"));
		}
		return 4567; // returns default port if heroku-port isn't set(i.e. on localhost)
	}
}