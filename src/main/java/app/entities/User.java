package app.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Serializable{
    private int userId;
    private String email;
    private String pass;
    private String wallet;

    public User() {
    }

    public User(String email, String pass, String wallet) {
        this.email = email;
        this.pass = pass;
        this.wallet = wallet;
    }

    @Id
    @Column(name = "userId", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "pass", nullable = false, length = 45)
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Basic
    @Column(name = "wallet", nullable = true, length = 45)
    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return userId == that.userId &&
                Objects.equals(email, that.email) &&
                Objects.equals(pass, that.pass) &&
                Objects.equals(wallet, that.wallet);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, email, pass, wallet);
    }
}
