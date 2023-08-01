/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quarkus;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author samll
 */
@ApplicationScoped
public class TemperaturaService implements ITemperaturaService {

    private final List<Temperatura> listTemp = new ArrayList<>();

    @Override
    public void addTemp(Temperatura t) {
        listTemp.add(t);
    }

    @Override
    public List<Temperatura> getTemp() {
        return Collections.unmodifiableList(listTemp);
    }

    @Override
    public int maxima() {
        return listTemp.stream().mapToInt(Temperatura::getMaxima).max().getAsInt();
    }

    @Override
    public boolean isEmpty() {
        return listTemp.isEmpty();
    }

    @Override
    public Optional<Temperatura> getTempCity(String city) {
        return listTemp.stream().filter(t -> t.getCity().equals(city)).findAny();
    }

}
