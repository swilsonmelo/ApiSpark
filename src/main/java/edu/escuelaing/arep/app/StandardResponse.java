package edu.escuelaing.arep.app;

import com.google.gson.JsonElement;

public class StandardResponse {
	
    private String status;
    private JsonElement data;
    
    public StandardResponse(String message, JsonElement data) {
		this.status = message;
		this.data = data;
	}

    public StandardResponse(String message) {
		this.status = message;
	}
    
	public String getStatus() {
		return status;
	}

	public void setStatus(String message) {
		this.status = message;
	}

	public JsonElement getData() {
		return data;
	}

	public void setData(JsonElement data) {
		this.data = data;
	}	
    
}