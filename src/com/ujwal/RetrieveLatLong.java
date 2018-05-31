package com.ujwal;

import com.ujwal.json.JSONException;
import com.ujwal.json.JSONObject;
import sun.rmi.runtime.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;

public class RetrieveLatLong {
    private double latitude;
    private double longitude;
    private URL Url;
    private  String mainKey;
    private  String objKey1;
    private  String objKey2;
    private HttpURLConnection connect;
    private JSONObject objList;
    private String jsonString;

    public JSONObject getObjList() {
        return objList;
    }

    public RetrieveLatLong(URL url, String mainKey, String objKey1, String objKey2) {
        Url = url;
        this.mainKey = mainKey;
        this.objKey1 = objKey1;
        this.objKey2 = objKey2;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public JSONObject fetchJson(){
            BufferedReader bfReader;
            StringBuilder sb = new StringBuilder();;
        try {
            connect = (HttpURLConnection) Url.openConnection();
            connect.setRequestMethod("GET");
            connect.setRequestProperty("Content-Type","application/json");
            String contentTyp = connect.getHeaderField("Content-Type");
            System.out.println("content Type: "+contentTyp);
            System.out.println("jsonUrl: "+Url.toString());
            connectionResponse(connect.getResponseCode());
            String dummyString;
            //BufferedReader
            bfReader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            while ((dummyString = bfReader.readLine())!=null) {
                sb.append(dummyString);
            }
            bfReader.close();
            connect.disconnect();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        jsonString = (sb.toString());

        //objList = jsonObject.getJSONArray(mainKey).getJSONObject(0);
        //longitude = objList.getDouble(objKey1);
        //latitude = objList.getDouble(objKey2);
        try {
            objList = new JSONObject(jsonString);
        } catch (JSONException e) {
            System.out.println("JSON Parser Error parsing data " + e.toString());
        }
        return objList;

    }
    private static void connectionResponse(int responseCode) {
        if (responseCode!= 200){
            System.err.print("Could not connect to the website");
        }else {
            System.out.println("Connected.");
        }
    }

    public static void main(String[] args) throws MalformedURLException {
        RetrieveLatLong retrieveLatLong = new RetrieveLatLong(new URL("http://www.dfki.uni-kl.de/~franke/hiwi_outline.json"),"geozone","latitude","longitude");
        retrieveLatLong.fetchJson();
        System.out.println(retrieveLatLong.latitude);
    }
}
