package quarkus;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/books-active")
@Transactional
public class BookResourceAR {
    @GET
    public List<BookAR> index(){
        return BookAR.listAll();
    }

    @POST
    public BookAR addBook(BookAR b){
        b.persist();
        return b;
    }
}
