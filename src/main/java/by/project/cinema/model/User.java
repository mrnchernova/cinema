package by.project.cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private Role role;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(int id) {
        this.id = id;
    }
    public User(int id, String password) {
        this.id = id;
        this.password = password;
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        String str = String.format("\n%-4s %-15s %-15s %-15s %-10s", id, username,password,email, role);
        return str;
    }
}


