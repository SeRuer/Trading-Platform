
import java.util.List;
import javax.jws.WebMethod;
import orders.Stock;
import orders.Available;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author TMuja
 */
public class readXML {

    @WebMethod(operationName = "CollectTheMoney")
    public List<Stock> CollectTheMoney() {

        Available formXML = new Available();
        //Here I am unmarshalling the data from the main
        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(formXML.getClass().getPackage().getName());
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            formXML = (Available) unmarshaller.unmarshal(new java.io.File("shareList.XML")); //NOI18N
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }

        List<Stock> money = formXML.getStockCollection();    //Gets the XML and prints out collection for the user to see 
        return money;

        
    }
}
