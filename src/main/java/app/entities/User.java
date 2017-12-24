package app.entities;


public class User {
    private String email;
    private String pass;
    private String wallet;

//---------------------------------------
    public User() {

    }

    public User(String email, String pass, String wallet) {

        this.email = email;
        this.pass = pass;
        this.wallet = wallet;
    }
//----------------------------------------------

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
//-----------------------------------------------
    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
//-----------------------------------------------


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        if(user.email == null || email == null || user.pass == null || pass == null) return false;

        if(user.email == email && user.pass == pass) return true;
        else return false;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (pass != null ? pass.hashCode() : 0);
        return result;
    }
}
