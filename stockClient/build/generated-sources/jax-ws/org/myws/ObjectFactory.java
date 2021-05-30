
package org.myws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.myws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SellFunction_QNAME = new QName("http://myws.org/", "sellFunction");
    private final static QName _BuyFunctionResponse_QNAME = new QName("http://myws.org/", "buyFunctionResponse");
    private final static QName _BuyFunction_QNAME = new QName("http://myws.org/", "buyFunction");
    private final static QName _ListStockResponse_QNAME = new QName("http://myws.org/", "listStockResponse");
    private final static QName _SellFunctionResponse_QNAME = new QName("http://myws.org/", "sellFunctionResponse");
    private final static QName _SearchFunction_QNAME = new QName("http://myws.org/", "searchFunction");
    private final static QName _SearchFunctionResponse_QNAME = new QName("http://myws.org/", "searchFunctionResponse");
    private final static QName _ListStock_QNAME = new QName("http://myws.org/", "listStock");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.myws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BuyFunctionResponse }
     * 
     */
    public BuyFunctionResponse createBuyFunctionResponse() {
        return new BuyFunctionResponse();
    }

    /**
     * Create an instance of {@link BuyFunction }
     * 
     */
    public BuyFunction createBuyFunction() {
        return new BuyFunction();
    }

    /**
     * Create an instance of {@link SellFunction }
     * 
     */
    public SellFunction createSellFunction() {
        return new SellFunction();
    }

    /**
     * Create an instance of {@link SearchFunction }
     * 
     */
    public SearchFunction createSearchFunction() {
        return new SearchFunction();
    }

    /**
     * Create an instance of {@link SearchFunctionResponse }
     * 
     */
    public SearchFunctionResponse createSearchFunctionResponse() {
        return new SearchFunctionResponse();
    }

    /**
     * Create an instance of {@link ListStock }
     * 
     */
    public ListStock createListStock() {
        return new ListStock();
    }

    /**
     * Create an instance of {@link ListStockResponse }
     * 
     */
    public ListStockResponse createListStockResponse() {
        return new ListStockResponse();
    }

    /**
     * Create an instance of {@link SellFunctionResponse }
     * 
     */
    public SellFunctionResponse createSellFunctionResponse() {
        return new SellFunctionResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SellFunction }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myws.org/", name = "sellFunction")
    public JAXBElement<SellFunction> createSellFunction(SellFunction value) {
        return new JAXBElement<SellFunction>(_SellFunction_QNAME, SellFunction.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuyFunctionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myws.org/", name = "buyFunctionResponse")
    public JAXBElement<BuyFunctionResponse> createBuyFunctionResponse(BuyFunctionResponse value) {
        return new JAXBElement<BuyFunctionResponse>(_BuyFunctionResponse_QNAME, BuyFunctionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuyFunction }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myws.org/", name = "buyFunction")
    public JAXBElement<BuyFunction> createBuyFunction(BuyFunction value) {
        return new JAXBElement<BuyFunction>(_BuyFunction_QNAME, BuyFunction.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListStockResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myws.org/", name = "listStockResponse")
    public JAXBElement<ListStockResponse> createListStockResponse(ListStockResponse value) {
        return new JAXBElement<ListStockResponse>(_ListStockResponse_QNAME, ListStockResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SellFunctionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myws.org/", name = "sellFunctionResponse")
    public JAXBElement<SellFunctionResponse> createSellFunctionResponse(SellFunctionResponse value) {
        return new JAXBElement<SellFunctionResponse>(_SellFunctionResponse_QNAME, SellFunctionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchFunction }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myws.org/", name = "searchFunction")
    public JAXBElement<SearchFunction> createSearchFunction(SearchFunction value) {
        return new JAXBElement<SearchFunction>(_SearchFunction_QNAME, SearchFunction.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchFunctionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myws.org/", name = "searchFunctionResponse")
    public JAXBElement<SearchFunctionResponse> createSearchFunctionResponse(SearchFunctionResponse value) {
        return new JAXBElement<SearchFunctionResponse>(_SearchFunctionResponse_QNAME, SearchFunctionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListStock }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myws.org/", name = "listStock")
    public JAXBElement<ListStock> createListStock(ListStock value) {
        return new JAXBElement<ListStock>(_ListStock_QNAME, ListStock.class, null, value);
    }

}
