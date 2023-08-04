package quarkus.genres;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
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
    private GenreRepository rGen;
    private GenreMapperImpl mapper;

    @Inject
    public GenreResource(GenreRepository rGen, GenreMapperImpl mapper){
        this.rGen=rGen;
        this.mapper=mapper;
    }

    /*@GET
    public List<Genres> getGenres(@QueryParam("page") @DefaultValue("1") int page){
        Page p=new Page(page-1, 5);
        return rGen.findAll(Sort.descending("createAt")).page(p).list();
    }*/
    @GET
    public PaginatedResponse<Genres> getGenresPage(@QueryParam("page") @DefaultValue("1") int page, @QueryParam("q") String q) {
        PanacheQuery panacheQuery= rGen.findPage(page);
        if (q != null) {
            String nameLike = "%" + q + "%";
            panacheQuery.filter("name.like", Parameters.with("name", nameLike));
        }
        return new PaginatedResponse<>(panacheQuery);
    }

    @GET
    @Path("{id}")
    public Genres getGenre(@PathParam("id") Long id) {
        return rGen.findByIdOptional(id).orElseThrow(() -> new NoSuchElementException("Genre " + id + " no se a encontrado!!!"));
    }

    @POST
    public Response addGenre(CreateGenreDTO genre) {
        Genres entity=mapper.fromCreate(genre);
        rGen.persist(entity);
        return Response.created(URI.create("/genres/" + entity.getId())).entity(entity).build();
    }

    @PUT
    @Path("{id}")
    public Genres updateGenre(@PathParam("id") Long id, UpdateGenreDTO gen) {
        Genres update = rGen.findByIdOptional(id).orElseThrow(() -> new NoSuchElementException("Genre " + id + " no se a encontrado!!!"));
        mapper.fromUpdate(gen, update);
        rGen.persist(update);
        return update;
    }

    @DELETE
    @Path("{id}")
    public void deleteGenre(@PathParam("id") Long id) {
        rGen.deleteById(id);
    }

}
