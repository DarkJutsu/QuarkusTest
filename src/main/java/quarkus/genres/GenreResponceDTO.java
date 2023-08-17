package quarkus.genres;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record GenreResponceDTO(Long id, String name) {
}
