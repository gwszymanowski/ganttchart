package ganttchart.model;


import org.bson.types.ObjectId;

/**
 * Created by gwszymanowski on 2017-05-13.
 */
public class Person implements Comparable<Person>{

    private ObjectId _id;
    private String firstname = "null";
    private String lastname = "null";

    public Person() {
    }

    public Person(String firstname, String lastname) {
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

        Person person = (Person) o;

        if (!firstname.equals(person.firstname)) return false;
        return lastname.equals(person.lastname);
    }

    @Override
    public int hashCode() {
        int result = firstname.hashCode();
        result = 31 * result + lastname.hashCode();
        return result;
    }

    @Override
    public int compareTo(Person o) {
        return lastname.compareTo(o.getLastname());
    }

    @Override
    public String toString() {
        if(firstname.equals("null") || lastname.equals("null"))
            return "none";

        StringBuilder sb = new StringBuilder();
        sb.append(firstname);
        sb.append(" ");
        sb.append(lastname);

        return sb.toString();
    }
}
