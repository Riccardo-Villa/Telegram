/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package riprova;
/**
 *
 * @author roncoroni_daniele
 */
public class Change {
    //vanno cambiati con lat e long
    private double lon;
    private double lat;

    public Change(double lat, double lon) {
        this.lon = lon;
        this.lat = lat;
    }

    public Change() {
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    
}
