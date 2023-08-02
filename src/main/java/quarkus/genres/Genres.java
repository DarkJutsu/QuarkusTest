package quarkus.genres;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@FilterDef(name = "name.like", parameters = @ParamDef(name = "name", type = String.class))
@Filter(name = "name.like", condition = "LOWER(name) LIKE LOWER(:name)")
public class Genres {
    @Id
    @GeneratedValue
    private Long id;
    @JsonProperty("genreName")
    @JsonAlias({"name", "genreName"})
    private String name;
    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime createAt;
    @UpdateTimestamp
    @JsonIgnore
    private LocalDateTime updateAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genres genres)) return false;
        return Objects.equals(getId(), genres.getId()) && Objects.equals(getName(), genres.getName()) && Objects.equals(getCreateAt(), genres.getCreateAt()) && Objects.equals(getUpdateAt(), genres.getUpdateAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCreateAt(), getUpdateAt());
    }

    @Override
    public String toString() {
        return "Genres{" + "id=" + id + ", name='" + name + '\'' + ", createAt=" + createAt + ", updateAt=" + updateAt + '}';
    }
}
