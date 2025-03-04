/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liten.genealogy.webUtilities;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author FACULTY
 */
public class SendHttpRequest {

    static final String ENCODE_SCHEME = "UTF-8";
    static final String CONTENT_TYPE = "application/x-www-form-urlencoded; charset=UTF-8";

    public static HttpsURLConnection sendPostData(String urlAPI, String sentData, String contentType) {
        HttpsURLConnection httpConnection = null;
        try {
            URL obj = new URL(urlAPI);
            httpConnection = (HttpsURLConnection) obj.openConnection();
//add request header
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", contentType);
            httpConnection.setRequestProperty("Accept", "application/json");//Response Contest-Type Sent to Server
// Send post request
            httpConnection.setDoOutput(true);
            httpConnection.connect();

            DataOutputStream wr = new DataOutputStream(httpConnection.getOutputStream());
            wr.writeBytes(sentData);
            wr.flush();
            wr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpConnection;
    }

    public static String getURLPostDataString(HashMap<String, String> requestParamsMap) {
        String resultString = "";
        try {
            StringBuilder result = new StringBuilder();
            for (HashMap.Entry<String, String> entry : requestParamsMap.entrySet()) {
                result.append(URLEncoder.encode(entry.getKey(), ENCODE_SCHEME));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), ENCODE_SCHEME));
                result.append("&");
                System.out.println(result.toString());
            }

            resultString = result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }
}
