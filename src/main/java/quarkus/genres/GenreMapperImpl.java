package quarkus.genres;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class GenreMapperImpl implements GenreMapper{
    @Override
    public Genres fromCreate(CreateGenreDTO dto) {
        Genres g=new Genres();
        g.setName(dto.name());
        return g;
    }

    @Override
    public void fromUpdate(UpdateGenreDTO dto, Genres genre) {
        genre.setName(dto.name());
    }

    @Override
    public GenreResponceDTO present(Genres g) {
        return new GenreResponceDTO(g.getId(), g.getName());
    }
}
