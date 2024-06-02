package entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class Score extends PanacheEntity {
    public String name;
    public int position;
    public int gameId;
    public LocalDateTime dateTime;
}
