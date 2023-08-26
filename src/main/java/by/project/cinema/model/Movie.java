package by.project.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static by.project.cinema.controller.MovieController.dateFormat;

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

    @Override
    public String toString() {
        String str =  String.format("%-4s %-35s %-15s\n", id, title, dateFormat.format(date.getTime()));
        return str;

    }
}
