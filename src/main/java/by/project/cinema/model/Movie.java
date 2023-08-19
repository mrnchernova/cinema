package by.project.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Movie {
    private int id;
    private String title;
    private Timestamp date;
//    private LocalDateTime date;

    public Movie(String title, Timestamp date) {
        this.title = title;
        this.date = date;
    }


}
