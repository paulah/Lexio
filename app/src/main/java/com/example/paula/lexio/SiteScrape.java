package com.example.paula.lexio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Paula on 4/25/2015.
 */
public class SiteScrape {
    public static void main(String[]args) {
        try {
            System.out.println(getGospel());
        } catch(IOException e) {
            e.printStackTrace();
        }
        /*try {
            fetchURL("http://www.usccb.org/bible/readings/");
        } catch(IOException e) {}*/
    }

    private static HttpURLConnection fetchURL( String url ) throws IOException {
        URL dest = new URL(url);
        HttpURLConnection yc =  (HttpURLConnection) dest.openConnection();
        yc.setInstanceFollowRedirects( false );
        yc.setUseCaches(false);

        System.out.println( "url = " + url );

        int responseCode = yc.getResponseCode();
        if ( responseCode >= 300 && responseCode < 400 ) { // brute force check, far too wide
            return fetchURL( yc.getHeaderField( "Location") );
        }

        System.out.println( "yc.getResponseCode() = " + yc.getResponseCode() );

        return yc;
    }

    public static String getGospel() throws IOException {

        // Make a URL to the web page
        URL url = new URL("http://dailygospel.org/");

        // Get the input stream through URL Connection
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setInstanceFollowRedirects(true);
        InputStream is =con.getInputStream();

        // Once you have the Input Stream, it's just plain old Java IO stuff.

        // For this case, since you are interested in getting plain-text web page
        // I'll use a reader and output the text content to System.out.

        // For binary content, it's better to directly read the bytes from stream and write
        // to the target file.

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line = null;
        boolean gospelFirstLine = false, gospelLastLine = false;
        // read each line and write to System.out
        while ((line = br.readLine()) != null) {
            if(!gospelFirstLine) {
                if (line.contains("Holy Gospel of Jesus Christ"))
                    gospelFirstLine = true;
            }
            if(gospelFirstLine) {

                if(!gospelLastLine) {
                    stringBuilder.append(line);

                }
                if(line.contains("</p>"))
                    gospelLastLine = true;

            }
        }
        System.out.println(gospelFirstLine);
        return stringBuilder.toString();
    }
}