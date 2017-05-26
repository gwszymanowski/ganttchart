package ganttchart.model;


import org.bson.Document;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by gwszymanowski on 2017-05-13.
 */
public class User implements Comparable<User>{

    private int id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private List<ProjectGroup> groups = new LinkedList<ProjectGroup>();

    public User() {
    }

    public User(String firstname, String lastname, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ProjectGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<ProjectGroup> groups) {
        this.groups = groups;
    }

    public Document toDocument() {
        Document document = new Document();
        document.append("id", id);
        document.append("firstname", firstname);
        document.append("lastname", lastname);
        document.append("username", username);
        document.append("password", password);

        int[] array = new int[groups.size()];

        for(int i = 0; i < array.length; i++)
            array[i] = groups.get(i).getId();

        document.append("groups", array);

        return document;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!firstname.equals(user.firstname)) return false;
        if (!lastname.equals(user.lastname)) return false;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        int result = firstname.hashCode();
        result = 31 * result + lastname.hashCode();
        result = 31 * result + username.hashCode();
        return result;
    }

    @Override
    public int compareTo(User o) {
        return lastname.compareTo(o.getLastname());
    }
}
