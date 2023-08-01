package quarkus;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class BookAR extends PanacheEntity {
    private String title;
    private int numPage;
    private LocalDate pubDate;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumPage() {
        return numPage;
    }

    public void setNumPage(int numPage) {
        this.numPage = numPage;
    }

    public LocalDate getPubDate() {
        return pubDate;
    }

    public void setPubDate(LocalDate pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookAR bookAR)) return false;
        return numPage == bookAR.numPage && Objects.equals(title, bookAR.title) && Objects.equals(pubDate, bookAR.pubDate) && Objects.equals(description, bookAR.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, numPage, pubDate, description);
    }

    @Override
    public String toString() {
        return "BookAR{" +
                "title='" + title + '\'' +
                ", numPage=" + numPage +
                ", pubDate=" + pubDate +
                ", description='" + description + '\'' +
                '}';
    }
}
