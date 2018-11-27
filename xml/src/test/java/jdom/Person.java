package jdom;

/**
 * ${DESCRIPTION}
 * <p>
 *
 * @author Feathers
 * @date 2017-12-05 14:59
 */
public class Person {

    private int id;

    private String username;

    private String password;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
