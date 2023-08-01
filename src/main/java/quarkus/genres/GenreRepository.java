package quarkus.genres;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GenreRepository implements PanacheRepository<Genres> {

    public PanacheQuery<Genres> findPage(int page){
        Page p=new Page(page-1, 5);
        PanacheQuery query=findAll(Sort.descending("createAt"));
        query.page(p);
        return query;
    }

}
