package quarkus.genres;

import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@Path("/genres")
@Transactional
public class GenreResource {
    @Inject
    private GenreRepository rGen;

    /*@GET
    public List<Genres> getGenres(@QueryParam("page") @DefaultValue("1") int page){
        Page p=new Page(page-1, 5);
        return rGen.findAll(Sort.descending("createAt")).page(p).list();
    }*/
    @GET
    public PaginatedResponse<Genres> getGenresPage(@QueryParam("page") @DefaultValue("1") int page){
        Page p=new Page(page-1, 5);
        var panacheQuery=rGen.findAll(Sort.descending("createAt")).page(p);
        return new PaginatedResponse<>(panacheQuery);
    }
    @GET
    @Path("{id}")
    public Genres getGenre(@PathParam("id") Long id){
        return rGen.findByIdOptional(id).orElseThrow(()->new NoSuchElementException("Genre " + id + " no se a encontrado!!!"));
    }

    @POST
    public Response addGenre(Genres gen){
        rGen.persist(gen);
        return Response.created(URI.create("/genres/" + gen.getId())).entity(gen).build();
    }

    @PUT
    @Path("{id}")
    public Genres updateGenre(@PathParam("id") Long id, Genres gen){
        Genres update=rGen.findByIdOptional(id).orElseThrow(()->new NoSuchElementException("Genre " + id + " no se a encontrado!!!"));
        update.setName(gen.getName());
        rGen.persist(update);
        return update;
    }

    @DELETE
    @Path("{id}")
    public void deleteGenre(@PathParam("id") Long id){
        rGen.deleteById(id);
    }

}
