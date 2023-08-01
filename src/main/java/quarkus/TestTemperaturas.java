/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quarkus;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author samll
 */
@Path("/temperaturas")
public class TestTemperaturas {

    private TemperaturaService tempS;

    @Inject
    public TestTemperaturas(TemperaturaService tempS) {
        this.tempS = tempS;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON) //@Produces especifica el tipo de serealizado que devolvera
    @Path("/city/{city}")
    public Temperatura medicion(@PathParam("city") String city) {
        return new Temperatura(city, 21, 35);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Temperatura> mediciones() {
        return tempS.getTemp();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Temperatura addTemp(Temperatura temp) {
        tempS.addTemp(temp);
        return temp;
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/maxima") 
    public Response maxima() {
        if(tempS.isEmpty()){
            return Response.status(404).entity("No hay temperaturas!!!").build();
        }else{
            return Response.ok(Integer.toString(tempS.maxima())).build();
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{cityy}") 
    public Temperatura getTempCity(@PathParam("cityy") String cityy) {
        return tempS.getTempCity(cityy).orElseThrow(()->new NoSuchElementException("No hay registros para la ciudad " + cityy));
    }
    
}
