/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.List;
import orders.*;

/**
 *
 * @author TMuja
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) {

        orders.Available formXML = new orders.Available(); 
        
        List<Stock> stockList = formXML.getStockCollection();
        List<StockDescription> priceList;
        Stock stockWay;
        //Here I aam applying the information for each stock of the stocks
        stockWay = new Stock();
        stockWay.setStockName("Nio");
        stockWay.setSymbol("NIO");
        stockWay.setCost(60);
        stockWay.setShares(500);
        StockDescription cost1 = new StockDescription();
        cost1.setDate("2021-03-01");
        cost1.setCurrency("GBP");
        cost1.setSharePrice(40);
        stockWay.setStockDescription(cost1);
        stockList.add(stockWay);
        //By using .add I'll be adding all the .set data into a List
        //Here I am applying the information for each stock of the stocks
        Stock stockWay2;
        stockWay2= new Stock();
        stockWay2.setStockName("Tesla");
        stockWay2.setSymbol("TSLA");
        stockWay2.setCost(300);
        stockWay2.setShares(700);
        StockDescription cost2 = new StockDescription();
        cost2.setDate("2021-05-01");
        cost2.setCurrency("GBP");
        cost2.setSharePrice(735);
        stockWay2.setStockDescription(cost2);
        stockList.add(stockWay2);
        //Here I am applying the information for each stock of the stocks
        //By using .add I'll be adding all the .set data into a List
        
        Stock stockWay3;
        stockWay3 = new Stock();
        stockWay3.setStockName("Airbnb");
        stockWay3.setSymbol("ABNB");
        stockWay3.setCost(150);
        stockWay3.setShares(1200);
        StockDescription cost3 = new StockDescription();
        cost3.setDate("2021-06-01");
        cost3.setCurrency("GBP");
        cost3.setSharePrice(140);
        stockWay3.setStockDescription(cost3);
        stockList.add(stockWay3);
        
        Stock stockWay4;
        stockWay4 = new Stock();
        stockWay4.setStockName("Alibaba");
        stockWay4.setSymbol("BABA");
        stockWay4.setCost(300);
        stockWay4.setShares(600);
        StockDescription cost4 = new StockDescription();
        cost4.setDate("2021-14-01");
        cost4.setCurrency("GBP");
        cost4.setSharePrice(400);
        stockWay4.setStockDescription(cost4);
        stockList.add(stockWay4);
  
        Stock stockWay5;
        stockWay5 = new Stock();
        stockWay5.setStockName("Nvidia");
        stockWay5.setSymbol("NVDA");
        stockWay5.setCost(300);
        stockWay5.setShares(700);
        StockDescription cost5 = new StockDescription();
        cost5.setDate("2021-25-01");
        cost5.setCurrency("GBP");
        cost5.setSharePrice(400);
        stockWay5.setStockDescription(cost5);
        stockList.add(stockWay5);
       
        //Here the added data will be marshalled to the address below
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

    }

}
