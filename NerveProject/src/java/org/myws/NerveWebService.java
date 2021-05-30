/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.myws;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.ejb.Stateless;
import orders.Available;
import orders.Stock;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TMuja
 */
@WebService(serviceName = "NerveWebService")
@Stateless()
public class NerveWebService {

    /**
     * This is a sample web service operation
     *
     * @return
     */
    @WebMethod(operationName = "listStock")
    public List<Stock> listStock() {
        //This allows me to collect info from the getStockCollections list
        Available formXML = new Available();
        //Here it is unmarshalling the data from the shareList.xml
        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(formXML.getClass().getPackage().getName());
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            formXML = (Available) unmarshaller.unmarshal(new java.io.File("shareList.XML")); //NOI18N
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }

        List<Stock> showingNow = formXML.getStockCollection();
        for (Stock stock : showingNow) { //For every share I have in the XML, it will get keep on getting the rested API price and marshall that data 
            try {
                URL url = new URL("http://localhost:8080/restApi/webresources/generic?symbol=" + stock.getSymbol()); //This allows me to get any symbol that's currently listed in my XML
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                // conn.setConnectTimeout(4000);
                // conn.setReadTimeout(4000);

                int status = conn.getReadTimeout();
                System.out.println(status);

                if (conn.getResponseCode() != 200) {
                    System.out.println("not 200");
                    continue;
                }
                //This just reads the response from the API
                InputStream inputStream = conn.getInputStream();

                StringBuilder sb = new StringBuilder();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String read;
                System.out.println("before read");
                while ((read = br.readLine()) != null) {
                    System.out.println("start read");
                    sb.append(read);
                    System.out.println("Read:" + read);
                }

                br.close();
                //Attempt to turn api response to a double data type so it will then be stored in the XML
                double highValue = Double.parseDouble(sb.toString());
                System.out.println("High Value:" + highValue);
                stock.getStockDescription().setSharePrice(highValue);

            } catch (NumberFormatException ex) {
                Logger.getLogger(NerveWebService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(NerveWebService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ProtocolException ex) {
                Logger.getLogger(NerveWebService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(NerveWebService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullPointerException ex) {
                Logger.getLogger(NerveWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Marshalls updated values 
        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(formXML.getClass().getPackage().getName());
            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(formXML, System.out);
            marshaller.marshal(formXML, new File("shareList.XML"));
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        return showingNow;

    }

    @WebMethod(operationName = "buyFunction")
    public boolean buyFunction(int balance, double amountSpent, int amount, String ticker) {

        //This allows me to collect info from the getStockCollections list
        Available shares = new Available();
        //In the buy function, I needed to unmarshall all the data that's in the XML, because if I was to just create a buy function without unmarshalling then I would receive a continous null error
        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(shares.getClass().getPackage().getName());
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            shares = (Available) unmarshaller.unmarshal(new java.io.File("shareList.XML")); //NOI18N
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
            return false;
        }
        //Here I'm calling the entire stock collection which will then allow me to call any of the get functions
        List<Stock> available = shares.getStockCollection();
        //Here I will be marshalling the new data
        //Whenever I click on a row in the JTable to purchase a stock, then the buy function will be called in there and run accordingly.
        for (Stock o : available) {
            if (o.getSymbol().equalsIgnoreCase(ticker)) {
                if (balance >= amountSpent && o.getShares() >= amount) {
                    o.setShares(o.getShares() - amount);
                    try {
                        javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(shares.getClass().getPackage().getName());
                        javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
                        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
                        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                        marshaller.marshal(shares, System.out);
                        marshaller.marshal(shares, new File("shareList.XML"));
                        return true;
                    } catch (javax.xml.bind.JAXBException ex) {
                        // XXXTODO Handle exception
                        java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
                        return false;
                    }
                }
            }
        }
        return false;
    }

    @WebMethod(operationName = "sellFunction")
    public boolean sellFunction(int balance, double amountSpent, int amount, String ticker) {

        //This allows me to collect info from the getStockCollections list
        Available shares = new Available();
        //Same thing again, I have to unmarshall the data that's from the 
        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(shares.getClass().getPackage().getName());
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            shares = (Available) unmarshaller.unmarshal(new java.io.File("shareList.XML")); //NOI18N
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
            return false;
        }
        //Same thing here I'm calling the entire stock collection which will then allow me to call any of the get functions
        List<Stock> available = shares.getStockCollection();
        //Here I am marshalling the data again
        //So whenever the I want to sell one of the stocks he's purchased, then he can click on the same stocka gain and it will increase the number of shares +1 
        for (Stock o : available) {
            if (o.getSymbol().equalsIgnoreCase(ticker)) {
                o.setShares(o.getShares() + amount);
                try {
                    javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(shares.getClass().getPackage().getName());
                    javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
                    marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
                    marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                    marshaller.marshal(shares, System.out);
                    marshaller.marshal(shares, new File("shareList.XML"));
                    return true;
                } catch (javax.xml.bind.JAXBException ex) {
                    // XXXTODO Handle exception
                    java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
                    return false;
                }
            }

        }
        return false;

    }
    
    
//Here I was going to create a binary search algorithm which allows me to find the mid point of the elements in the JTable
    public int searchFunction(
            ArrayList<String> company, int l, int h, String k) {

        int m = (l + h) / 2;
        if (h < l) {
            return -1;
        }

        if (company.get(m).equals(k)) {
            return m;
        } else if ((k.compareTo(company.get(m))) < 0) {
            return searchFunction(company, l, m - 1, k);
        } else {
            return -1;
        }

    }

}
