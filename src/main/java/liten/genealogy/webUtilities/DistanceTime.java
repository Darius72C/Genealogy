/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liten.genealogy.webUtilities;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author FACULTY
 */
public class DistanceTime {

    private static final String API_KEY = "AIzaSyAS_SFp_Miikj0ratiWynlAaNLhC24D5O4";
    OkHttpClient client = new OkHttpClient();

    public String calculate(String source, String destination) throws IOException {
        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + source + "&destinations=" + destination + "&key=" + API_KEY;
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
