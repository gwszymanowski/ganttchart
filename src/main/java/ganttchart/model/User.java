package ganttchart.model;


import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by gwszymanowski on 2017-05-13.
 */
public class User implements Comparable<User>{

    private ObjectId _id;
    private String firstname = "null";
    private String lastname = "null";
    private String username = "null";
    private String password = "null";
    private Role role = Role.USER;
    private List<ProjectGroup> groups = new LinkedList<ProjectGroup>();

    public User() {
    }

    public User(String firstname, String lastname, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<ProjectGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<ProjectGroup> groups) {
        this.groups = groups;
    }

    public Document toDocument() {
        Document document = new Document();
        document.append("firstname", firstname);
        document.append("lastname", lastname);
        document.append("username", username);
        document.append("password", password);
        document.append("role", role);
        document.append("groups", groups);

        return document;
    }

    public static User fromDocument(Document document) {
        User user = new User();

        ObjectId _id = (ObjectId) document.get("_id");
        String firstname = (String) document.get("firstname");
        String lastname = (String)document.get("lastname");
        Role role = (Role) document.get("role");

        user.set_id(_id);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setRole(role);

        return user;
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

    @Override
    public String toString() {
        return firstname + " " + lastname;
    }
}
