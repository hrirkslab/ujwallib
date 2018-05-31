package com.ujwal;

import com.ujwal.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by hrirks on 24.11.2017.
 * ParseJson parseJson = new ParseJson();
 ArrayList<Integer> ar = new ArrayList<>();
 ar.add(0);
 //ArrayListe brauchen wir wenn wir mit beliebiger vielen Zahlen arbeiten möchten
 parseJson.valWithIndex("geozone",ar,0 );
 parseJson.setObjKey1("longitude");
 parseJson.setObjKey2("latitude");
 parseJson.ParserTest("http://www.dfki.uni-kl.de/~franke/hiwi_outline.json");
 System.out.println("longitude: "+parseJson.getLongitudeVal());
 System.out.println("latitude: "+parseJson.getLatitudeVal());

 */

public class ParseJson{
    private static double longitudeVal;
    private static double latitudeVal;
    private static String longitude;
    private String jsonUrl;
    private static URL url;
    private static String objKey1;
    private static String objKey2;
    private static String mainKey;
    private static ArrayList<Integer> index;
    private static JSONObject objList;
    private static int wert;
    private HttpURLConnection connect;

    public static void main(String[] args)  {
        ParseJson parseJson = new ParseJson();
        ArrayList<Integer> ar = new ArrayList<>();
        ar.add(0);
        //ArrayListe brauchen wir wenn wir mit beliebiger vielen Zahlen arbeiten möchten
        parseJson.valWithIndex("geozone",ar,0 );
        parseJson.setObjKey1("longitude");
        parseJson.setObjKey2("latitude");
        parseJson.ParserTest("http://www.dfki.uni-kl.de/~franke/hiwi_outline.json");
        System.out.println("longitude: "+parseJson.getLongitudeVal());
        System.out.println("latitude: "+parseJson.getLatitudeVal());
        System.out.println("läuft");
    }
    public ParseJson ParserTest(String jsonUrl)  {

         //this.jsonUrl = jsonUrl;

            StringBuilder sb = new StringBuilder();
        try {
            url = new URL(jsonUrl);

            connect = (HttpURLConnection) url.openConnection();
            connect.setRequestMethod("GET");
            connect.setRequestProperty("Content-Type","application/json");
            String contentTyp = connect.getHeaderField("Content-Type");
            System.out.println("content Type: "+contentTyp);
            //System.out.println("jsonUrl: "+jsonUrl.toString());
            connectionResponse(connect.getResponseCode());
            //BufferedReader
            BufferedReader bfReader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            String dummyString;
            while ((dummyString = bfReader.readLine())!=null) {
                sb.append(dummyString);
            }
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject(sb.toString());
            Object value = null;
            Set s = jsonObject.keySet();
            Iterator i = s.iterator();
            while(i.hasNext()){
                String key = (String) i.next();
                value = jsonObject.get(key);
                //System.out.println(key+"="+value+"");
                //System.out.println("Keys: "+s.contains("latitude"));
                //System.out.println("k bhayo"+jsonObject.getJSONObject("geozone").getString("longitude"));
                //System.out.println(s.toString());
            }

            int temp=0;
            while(temp<index.size()) {
            objList = jsonObject.getJSONArray(mainKey).getJSONObject(index.get(temp));
            temp++;
            }
            objList = jsonObject.getJSONArray(mainKey).getJSONObject(index.get(wert));
            longitudeVal = objList.getDouble(getObjKey1());
            ParseJson parseJson = new ParseJson();
            parseJson.setLongitudeVal(objList.getDouble(getObjKey1()));
            latitudeVal = objList.getDouble(getObjKey2());
            parseJson.setLatitudeVal(objList.getDouble(getObjKey2()));
            System.out.println(index.get(0));
            System.out.println(parseJson.getLatitudeVal());
            System.out.println(parseJson.getLongitudeVal());
            connect.disconnect();
            return parseJson;
    }
    private static void connectionResponse(int responseCode) {
        if (responseCode!= 200){
            System.err.print("Could not connect to the website");
        }else {
            System.out.println("Connected.");
        }
    }
    public void valWithIndex(String mainKey, ArrayList<Integer> index,int wert){
        this.wert = wert;
        this.mainKey = mainKey;
        this.index = index;
    }
    //Getters and setters

    public double getLongitudeVal() {
        return longitudeVal;
    }

    public void setLongitudeVal(double longitudeVal) {
        this.longitudeVal = longitudeVal;
    }

    public double getLatitudeVal() {
        return latitudeVal;
    }

    public void setLatitudeVal(double latitudeVal) {
        this.latitudeVal = latitudeVal;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getObjKey1() {
        return objKey1;
    }

    public void setObjKey1(String objKey1) {
        this.objKey1 = objKey1;
    }

    public String getObjKey2() {
        return objKey2;
    }

    public void setObjKey2(String objKey2) {
        this.objKey2 = objKey2;
    }

    public JSONObject getObjList() {
        return objList;
    }
}
