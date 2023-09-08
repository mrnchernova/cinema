package by.project.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Movie {
    private int id;
    private String title;
    private LocalDateTime date;

    public Movie(String title, LocalDateTime date) {
        this.title = title;
        this.date = date;
    }
}
