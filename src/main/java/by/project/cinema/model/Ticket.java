package by.project.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ticket {
    private int id;
    private int personId; //Если место не куплено, тогда значение пользователя должно быть пустым
    private int seat;
    private double price;
    private boolean inStock; //by default will be true
    private int movieId;




}

