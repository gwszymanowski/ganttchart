package ganttchart.model;


import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Created by gwszymanowski on 2017-05-13.
 */
public class User implements Comparable<User>{

    private ObjectId _id;
    private String firstname = "null";
    private String lastname = "null";

    public User() {
    }

    public User(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!firstname.equals(user.firstname)) return false;
        return lastname.equals(user.lastname);
    }

    @Override
    public int hashCode() {
        int result = firstname.hashCode();
        result = 31 * result + lastname.hashCode();
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
