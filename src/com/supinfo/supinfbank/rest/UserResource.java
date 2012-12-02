package com.supinfo.supinfbank.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


@Path("/user")
public class UserResource 
{
	@GET 
	@Path("/authenticate")
	@Produces(MediaType.APPLICATION_JSON)
	public String checkConnectivity()
	{
		JSONObject response = new JSONObject();
		
		try 
		{
			response.put("plop", "ok");
		} 
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
		
		return response.toString();
	}
}
