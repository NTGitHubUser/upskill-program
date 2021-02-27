package com.upskill.cloudinaction;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@RestController
public class HealthCheckController
{
	@RequestMapping(value = "/healthcheck", method = RequestMethod.GET)
	public String healthCheck()
	{
		try
		{
			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI("https://www.google.com/"))
					.GET()
					.build();

			HttpResponse<String> response = HttpClient.newBuilder()
					.build()
					.send(request, HttpResponse.BodyHandlers.ofString());
			return "Service Google, status (changed message): " + response.statusCode();
		}
		catch (URISyntaxException | IOException | InterruptedException ex)
		{
			return "Health check configured incorrectly: " + ex;
		}
	}
}
