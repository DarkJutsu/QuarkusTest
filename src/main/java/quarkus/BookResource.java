package quarkus;

import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import quarkus.genres.GenreMapper;

import java.util.List;
import java.util.NoSuchElementException;

@Path("/books")
@Transactional
public class BookResource {

    @Inject
    private BookRepository repo;

    /* FILTRO
    @GET
    public List<Book> index(@QueryParam("numPag") Integer numPag) {
        if (numPag != null) return repo.list("numPage >= ?1", numPag);
        return repo.listAll();
    }
    */

    @GET
    public List<Book> index(@QueryParam("query") String query) {
        if (query != null) {
            String filter = "%" + query + "%";
            Sort sort = Sort.by("pubDate", Sort.Direction.Ascending);
            return repo.list("title ILIKE ?1 OR description ILIKE ?2", sort, filter, filter);
        }
        return repo.listAll(Sort.by("pubDate", Sort.Direction.Descending));
    }

    @GET
    @Path("{id}")
    public Book getBook(@PathParam("id") Long id) {
        Book book = repo.findById(id);
        if (book != null) return book;
        throw new NoSuchElementException("No hay libro con el ID " + id + ".");
    }

    @POST
    public Book setBook(Book b) {
        repo.persist(b);
        return b;
    }

    @PUT
    @Path("{id}")
    public Book updateBook(@PathParam("id") Long id, Book book) {
        Book updateBook = repo.findById(id);
        if (updateBook != null) {
            updateBook.setTitle(book.getTitle());
            updateBook.setGenre(book.getGenre());
            updateBook.setNumPage(book.getNumPage());
            updateBook.setPubDate(book.getPubDate());
            updateBook.setDescription(book.getDescription());
            repo.persist(updateBook);
            return updateBook;
        }
        throw new NoSuchElementException("No hay libro con el ID " + id + ".");
    }

    @DELETE
    @Path("{id}")
    public String deleteBook(Long id) {
        if (repo.deleteById(id)) {
            return "Se ha borrado el libron con el ID " + id + "!!!.";
        } else {
            return "No se a borrado nada!!!.";
        }
    }

}
