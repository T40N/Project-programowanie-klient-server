import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;

public class User {
    private ObjectId id;
    private String name;
    private String surname;
    private String password;
    private String email;

    public User(String name, String surname, String password, String email){
        this.id = new ObjectId();
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = BCrypt.hashpw(password,BCrypt.gensalt());
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
