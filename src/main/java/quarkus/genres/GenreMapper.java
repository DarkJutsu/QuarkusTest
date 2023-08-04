package quarkus.genres;

public interface GenreMapper {
    Genres fromCreate(CreateGenreDTO dto);
    void fromUpdate(UpdateGenreDTO dto, Genres genre);
}
