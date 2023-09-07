package by.project.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static by.project.cinema.util.Util.formatter;

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

//    @Override
//    public String toString() {
//        return String.valueOf(System.out.format("%-4s %-35s %-15s\n+++", id, title, formatter.format(date)));
//    }
}
