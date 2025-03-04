/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liten.genealogy.webUtilities;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 *
 * @author FACULTY
 */
public class RetrieveHttpResponse {
    
    public static StringBuffer retrieveResponse(HttpURLConnection http) {
        StringBuffer response = null;
        try {          
            // handle error response code it occurs
            int responseCode = http.getResponseCode();
            InputStream inputStream;
            if (responseCode==200) {
                inputStream = http.getInputStream();
            } else {
                inputStream = http.getErrorStream();
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            response = new StringBuffer();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine + "\n");
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
    
}
