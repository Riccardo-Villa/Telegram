/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riprova;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 *
 * @author villa_riccardo
 */
public class Operazioni {
    private Document document;

    public Document getDocument() {
        return document;
    }

    public List parseDocument(String filename) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Element root, element;
        NodeList nodelist;
        // creazione dell’albero DOM dal documento XML
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        
        document = builder.parse(filename);
        root = document.getDocumentElement();
        List<Change> dati = new ArrayList();
        Change dato;
        nodelist = root.getElementsByTagName("place");
        if (nodelist != null && nodelist.getLength() > 0) {
            int numNode = nodelist.getLength();
            element = (Element) nodelist.item(1); 
            for (int i = 0; i < numNode; i++) {
                element = (Element) nodelist.item(i);
                dato = getInfo(element);
                dati.add(dato);
            }
        }
        return dati;
    }
    
    
    private Change getInfo(Element element) {
        
        Change valute = null;
        
        double str = Double.parseDouble(element.getAttribute("lat")) ;
        double num = Double.parseDouble(element.getAttribute("lon"));
        
        Change pippo= new Change(str,num);
        return pippo;
        
    }
    

     
}
