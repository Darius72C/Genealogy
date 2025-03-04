/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liten.genealogy.webUtilities;

import com.google.maps.model.LatLng;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Logger;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

/**
 *
 * @author FACULTY
 */
public class GeoLocationServices {

    private static final String API_KEY = "AIzaSyBTH_rbCU1sbFiYdQg_blOEtkVsD_NYd7Y";

    public static LatLng getGeoPointFromAddress(String locationAddress) {
        HashMap<String, String> latLong = new HashMap<String, String>();
        LatLng curLatLong = null;
        try {
            locationAddress=locationAddress.replaceAll(" ", "+");
            URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + locationAddress + "&language=fr&key=" + API_KEY);
            Logger log = Logger.getLogger(GeoLocationServices.class.getName());
            //    GeoApiContext context = new Builder().apiKey(API_KEY).build();

            // InputStream is = url.openStream();
         //   HttpURLConnection httpURLConnection;
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();
          //  System.out.println(httpURLConnection.getInputStream());
            InputStream is = httpURLConnection.getInputStream();
            JsonReader rdr = Json.createReader(is);
            JsonObject obj = (JsonObject) rdr.readObject();
            JsonArray results = obj.getJsonArray("results");
            JsonObject geoMetryObject, locations;

            for (JsonObject result : results.getValuesAs(JsonObject.class)) {
                geoMetryObject = result.getJsonObject("geometry");
                locations = geoMetryObject.getJsonObject("location");
                log.info("Using Geocoordinates call - lat : lng value for " + locationAddress + " is - " + locations.get("lat") + " : " + locations.get("lng"));
                latLong.put("latitude", locations.get("lat").toString());
                latLong.put("longitude", locations.get("lng").toString());
                curLatLong = new LatLng(locations.getJsonNumber("lat").doubleValue(), locations.getJsonNumber("lng").doubleValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curLatLong;
    }

    public static double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
            dist = dist * 1.609344;
        } else if (unit == 'N') {
            dist = dist * 0.8684;
        }
        return (dist);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
 /*::  This function converts decimal degrees to radians             :*/
 /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
 /*::  This function converts radians to decimal degrees             :*/
 /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    /*System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, 'M') + " Miles\n");
    System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, 'K') + " Kilometers\n");
    System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, 'N') + " Nautical Miles\n");
     */
    public static void main(String[] args) {
        //String address1 = "Rosiers, Grand-Bassam, Sud-Comoé, Côte d'Ivoire";
        String address1 = "226 Branthaven street, Orleans, ON, K4A 0H6, Canada";
        String address2 = "International University of Grand Bassam, Grand-Bassam, Côte d'Ivoire";
        LatLng point1 = getGeoPointFromAddress(address1);
        LatLng point2 = getGeoPointFromAddress(address2);
        double distance = distance(point1.lat, point1.lng, point2.lat, point2.lng, 'K');
        System.out.println("latitude: " + point1.lat + "  longitude: " + point1.lng);
        System.out.println("latitude: " + point2.lat + "  longitude: " + point2.lng);
        System.out.println("distance (Km): " + distance);
    }
}
