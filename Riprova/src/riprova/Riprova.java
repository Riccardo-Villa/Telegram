/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riprova;

import java.io.IOException;

/**
 *
 * @author villa_riccardo
 */
public class Riprova {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        VillaBot a= new VillaBot();
        a.readjsonFromUrl();
        a.Risposta();
        
    }
    
}
