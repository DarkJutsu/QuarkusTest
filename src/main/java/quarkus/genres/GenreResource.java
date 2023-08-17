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
    public PaginatedResponse<GenreResponceDTO> getGenresPage(@QueryParam("page") @DefaultValue("1") int page, @QueryParam("q") String q) {
        PanacheQuery<Genres> panacheQuery= rGen.findPage(page);
        PanacheQuery<GenreResponceDTO> present= panacheQuery.project(GenreResponceDTO.class);
        if (q != null) {
            String nameLike = "%" + q + "%";
            present.filter("name.like", Parameters.with("name", nameLike));
        }
        return new PaginatedResponse<>(present);
    }

    @GET
    @Path("{id}")
    public GenreResponceDTO getGenre(@PathParam("id") Long id) {
        return rGen.findByIdOptional(id).map(mapper::present).orElseThrow(() -> new NoSuchElementException("Genre " + id + " no se a encontrado!!!"));
    }

    @POST
    public Response addGenre(CreateGenreDTO genre) {
        Genres entity=mapper.fromCreate(genre);
        rGen.persist(entity);
        var presentation=mapper.present(entity);
        return Response.created(URI.create("/genres/" + entity.getId())).entity(presentation).build();
    }

    @PUT
    @Path("{id}")
    public GenreResponceDTO updateGenre(@PathParam("id") Long id, UpdateGenreDTO gen) {
        Genres update = rGen.findByIdOptional(id).orElseThrow(() -> new NoSuchElementException("Genre " + id + " no se a encontrado!!!"));
        mapper.fromUpdate(gen, update);
        rGen.persist(update);
        return mapper.present(update);
    }

    @DELETE
    @Path("{id}")
    public void deleteGenre(@PathParam("id") Long id) {
        rGen.deleteById(id);
    }

}
