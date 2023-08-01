/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quarkus;

import java.util.Objects;

/**
 *
 * @author samll
 */
public class Temperatura {

    private String city;
    private int minima;
    private int maxima;

    public Temperatura() {
    }

    public Temperatura(String city, int minima, int maxima) {
        this.city = city;
        this.minima = minima;
        this.maxima = maxima;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getMinima() {
        return minima;
    }

    public void setMinima(int minima) {
        this.minima = minima;
    }

    public int getMaxima() {
        return maxima;
    }

    public void setMaxima(int maxima) {
        this.maxima = maxima;
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, minima, maxima);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Temperatura other = (Temperatura) obj;
        if (this.minima != other.minima) {
            return false;
        }
        if (this.maxima != other.maxima) {
            return false;
        }
        return Objects.equals(this.city, other.city);
    }
    
    @Override
    public String toString() {
        return "Temperatura{" + "city=" + city + ", minima=" + minima + ", maxima=" + maxima + '}';
    }
    
    
    
}
