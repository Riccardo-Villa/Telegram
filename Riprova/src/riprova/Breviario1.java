/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riprova;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
/**
 *
 * @author villa_riccardo
 */
public class Breviario1 {
    public List<Change> PrendiCittà(String città){
        BufferedReader in = null;
        PrintWriter out;
        try {
            out = new PrintWriter("xml/Street.xml");

            URL url;
            // System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
            // url =  new URL("https://www.agenziaentrate.gov.it/portale/documents/20143/296358/Provvedimento+30+marzo+2017+Distributori+automatici_Allegato+Api+REST+Dispositivi+%28v.3.0%29.pdf/7cfe447f-5823-5873-e55d-d3fa825877fd");
            String base = "https://nominatim.openstreetmap.org/search?q=";
            String parametri= "&format=xml&addressdetails=1";
            url = new URL( base+ URLEncoder.encode(città, StandardCharsets.UTF_8) + parametri);
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                out.println(line);
            }
            in.close();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(Breviario1.class.getName()).log(Level.SEVERE, null, ex);
        }

        Operazioni xml = new Operazioni();
        // String xsd = "xml/grammatica.xsd";
        String xmlFile = "xml/Street.xml";
        
        try {
            List<Change> dati = new ArrayList<>();
            dati = xml.parseDocument(xmlFile);
            return dati;
        } catch (ParserConfigurationException | SAXException | IOException exception) {
            System.out.println(exception);
        }
        return null;
    }
}
