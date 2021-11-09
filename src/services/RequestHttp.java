package services;

import com.google.gson.Gson;
import model.LocationStats;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

public class RequestHttp {

    private HttpClient client;
    private HttpRequest request;
    private HttpResponse<String> httpResponse;

    public RequestHttp() throws IOException, InterruptedException {
        String url = "https://corona-virus-realtime.herokuapp.com/api/v1/cases";
        // Make an http call to the url to get the data
        client = HttpClient.newHttpClient();
        request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

    }

    public LocationStats[] getResponse() {
        Gson gson = new Gson();
        LocationStats[] locationStatsList = gson.fromJson(httpResponse.body(),LocationStats[].class);
        System.out.println(Arrays.toString(locationStatsList));
        return locationStatsList;
    }
}
