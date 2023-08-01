/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quarkus;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import java.util.Optional;

@Path("/saludar")
public class EcoResource {

    @GET
    public String saludar(@QueryParam("msj") String msj) {
        return Optional.ofNullable(msj).map(n -> "> " + n).orElse("No se que decir!!! :)");
    }

    @GET
    @Path("/{nombre}")
    public String saludo(@PathParam("nombre") String nombre) {
        return "Hola, " + nombre;
    }

    @GET
    @Path("/{nombre}/gritar")
    public String gritar(@PathParam("nombre") String nombre) {
        return "HOLA, " + nombre.toUpperCase();
    }

    @GET
    @Path("/dias")
    public String dias() {
        return "Buenos dias";
    }

    @GET
    @Path("/tardes")
    public String tardes() {
        return "Buenas tardes";
    }

    @GET
    @Path("/noches")
    public String noches() {
        return "Buenas noches";
    }
}
