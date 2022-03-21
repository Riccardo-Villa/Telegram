/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riprova;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.json.*;
import org.xml.sax.SAXException;
/**
 *
 * @author villa_riccardo
 */
public class VillaBot {
    static String token = "5239078780:AAGsYBCPWtz_JjrFeDm_KpwKt43kPa9phk0";
    static long chat_id = 0;
    Breviario1 b= null;
    List<Change> dati = new ArrayList<>();
    File file = new File("Comuni.csv");
    public VillaBot() {
        
    }
    
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
          sb.append((char) cp);
        }
        return sb.toString();
    }
    
    public void readjsonFromUrl() throws MalformedURLException, IOException{
        URL json = new URL("https://api.telegram.org/bot" + token + "/getUpdates");
        System.out.println(json.toString());
        Scanner s = new Scanner(json.openStream());
        s.useDelimiter("\u001a");
        String jsonString = s.next();
        JSONObject Oggetto = new JSONObject(jsonString);
        JSONArray arr = Oggetto.getJSONArray("result");
                //String a = arr.getJSONObject(i).getJSONObject("message").toString();
                //String b = arr.getJSONObject(i).getJSONObject("text").toString();
                //String c = arr.getJSONObject(i).getJSONObject("id").toString();
                long d = arr.getJSONObject(arr.length() - 1).getJSONObject("message").getInt("message_id");
                String e = arr.getJSONObject(arr.length() - 1).getJSONObject("message").getString("text");
                String g = arr.getJSONObject(arr.length() - 1).getJSONObject("message").getJSONObject("from").getString("first_name");
                long f = arr.getJSONObject(arr.length() - 1).getJSONObject("message").getJSONObject("chat").getInt("id");
                System.out.println(d);
                System.out.println(e);
                System.out.println(f);
                chat_id = f;
                String città = e;
                Street(città); 
                String nome=g;
                double lat = dati.get(0).getLat();
                double lon = dati.get(0).getLon();
                CSV(chat_id, nome, lat, lon);
    }
    public void Risposta()throws MalformedURLException, IOException{
        String Rispondiamo = "lat: "+ dati.get(0).getLat() + " long:"+ dati.get(0).getLon();
        //String Rispondiamo = "ciao";
        String urlString = "https://api.telegram.org/bot" + token + "/sendMessage?chat_id=" + chat_id + "&text=" + Rispondiamo;
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();
        StringBuilder sb = new StringBuilder();
        InputStream is = new BufferedInputStream(conn.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String inputLine = "";
        while ((inputLine = br.readLine()) != null) {
            sb.append(inputLine);
        }
    }
    public void Street(String città) throws MalformedURLException{
        
        BufferedReader in = null;
        PrintWriter out;
        try {
            out = new PrintWriter("Street.xml");

            URL url;
            // System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
            
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
        String xmlFile = "Street.xml";
        
        try {
            dati = xml.parseDocument(xmlFile);
        } catch (ParserConfigurationException | SAXException | IOException exception) {
            System.out.println(exception);
        }
    }

    public void CSV(long chat_id,String nome, double lat, double lon) throws IOException{
       String CSV = chat_id + ";" + nome + ";" + lat + ";" + lon ;
       BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        bw.append(CSV);
        bw.newLine();
        bw.close();
    }
    //salvo tutte le variabili e le metto nel csv 
    //prendo lat e long da xml 
    //una volta presi li salvo in due variabili e al posto che Rispondiamo metto le 2 variabili
}
