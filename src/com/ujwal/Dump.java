package com.ujwal;

import java.net.MalformedURLException;
import java.net.URL;

public class Dump extends RetrieveLatLong {

    public Dump(URL url, String mainKey, String objKey1, String objKey2) {
        super(url, mainKey, objKey1, objKey2);
    }

    public static void main(String[] args) throws MalformedURLException {
        RetrieveLatLong retrieveLatLong = new Dump(new URL("http://www.dfki.uni-kl.de/~franke/hiwi_outline.json"),"geozone","latitude","longitude");
        retrieveLatLong.fetchJson();
        System.out.println(retrieveLatLong.getLatitude());
        System.out.println(retrieveLatLong.getLongitude());
    }

}
