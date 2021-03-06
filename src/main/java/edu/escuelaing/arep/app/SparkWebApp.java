package edu.escuelaing.arep.app;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.delete;
import static spark.Spark.port;
import static spark.Spark.options;
import static spark.Spark.before;

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

		options("/*",
		        (request, response) -> {
		            String accessControlRequestHeaders = request
		                    .headers("Access-Control-Request-Headers");
		            if (accessControlRequestHeaders != null) {
		                response.header("Access-Control-Allow-Headers",
		                        accessControlRequestHeaders);
		            }

		            String accessControlRequestMethod = request
		                    .headers("Access-Control-Request-Method");
		            if (accessControlRequestMethod != null) {
		                response.header("Access-Control-Allow-Methods",
		                        accessControlRequestMethod);
		            }

		            return "OK";
		        });

		before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
		
		get("/users", (request, response) -> {
			response.type("application/json");	
			return new Gson().toJson(
					new StandardResponse("Accepted",new Gson()
					.toJsonTree(userServices.getAll())));
		});
		
		get("/users/:id", (request, response) -> {
			response.type("application/json");	
			long id = Long.parseLong(request.params(":id"));
			System.out.println(id);
			User user = userServices.getById(id);
			System.out.println(user);
			return new Gson().toJson(
					new StandardResponse("Accepted",new Gson()
					.toJsonTree(user)));
		});
		
		post("/users", (request, response) -> {
			response.type("application/json");
			User user = new Gson().fromJson(request.body(), User.class);
			System.out.println(user.toString());
			userServices.add(user);
			return new Gson().toJson(
					new StandardResponse("Accepted", new Gson()
							.toJsonTree(user)));
		});
		
		delete("/users/:id", (request, response) -> {
		    response.type("application/json");
		    long id = Long.parseLong(request.params(":id"));
		    userServices.delete(id);
		    return new Gson().toJson(
					new StandardResponse("Accepted"));
		});
		
	}

	static int getPort() {
		if (System.getenv("PORT") != null) {
			return Integer.parseInt(System.getenv("PORT"));
		}
		return 4567; // returns default port if heroku-port isn't set(i.e. on localhost)
	}
}