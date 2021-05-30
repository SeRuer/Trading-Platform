/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.my.ws;

import java.io.BufferedReader;
import java.net.URL;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


/**
 * REST Web Service
 *
 * @author TMuja
 */
@Path("generic")
public class GenericResource {

    BufferedReader reader;
    String line;
    StringBuffer responseContent = new StringBuffer();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String retrieveJSON(@QueryParam("symbol") String symbol) throws IOException { //getJason is called whenever a HTTP request is called
        try {
            //Here I used the API Alpha Vantage, which only allows for 1 stock for data, but I found a way to allowed to use any symbol in my library
	    //Add own API link here
            URL url = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + symbol + "&outputsize=full&apikey=");
            System.out.println("symbol" + symbol);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            //  conn.setConnectTimeout(4000);
            //conn.setReadTimeout(4000);
            int status = conn.getReadTimeout();
            System.out.println(status);

            if (conn.getResponseCode() != 200) {
                throw new IOException(conn.getResponseMessage());
            }
            //Created objects for the timeSeries and then used an iterator to get the first one of the doc
            JsonReader jsonReader = Json.createReader(conn.getInputStream());
            JsonObject jsonObject = jsonReader.readObject();
            JsonObject timeSeries = jsonObject.getJsonObject("Time Series (Daily)");

            String firstEntryKey = timeSeries.entrySet().iterator().next().getKey();
            JsonObject firstEntryJsonObject = timeSeries.getJsonObject(firstEntryKey);
            double highValue = Double.parseDouble(firstEntryJsonObject.getJsonString("2. high").getString());
            return Double.toString(highValue);
            
          
        } catch (NumberFormatException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "error";
    }
}
