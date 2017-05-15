package model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by gwszymanowski on 2017-05-13.
 */
public class Group {

    private int id;
    private String name;
    private Set<User> members = new HashSet<>();

    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        return name.equals(group.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
