/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package quarkus;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author samll
 */
public interface ITemperaturaService {
    
    void addTemp(Temperatura t);
    
    List<Temperatura> getTemp();
    
    Optional<Temperatura> getTempCity(String city);
    
    boolean isEmpty();
    
    int maxima();
}
