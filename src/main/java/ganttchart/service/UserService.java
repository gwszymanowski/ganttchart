package ganttchart.service;

import ganttchart.model.User;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Created by gwszymanowski on 2017-07-09.
 */
public class UserService {

    private UserService() {
    }

    public static Document toDocument(User user) {
        Document document = new Document();
        document.append("firstname", user.getFirstname());
        document.append("lastname", user.getLastname());
        return document;
    }

    public static User fromDocument(Document document) {
        User user = new User();

        ObjectId _id = (ObjectId) document.get("_id");
        String firstname = (String) document.get("firstname");
        String lastname = (String)document.get("lastname");

        user.set_id(_id);
        user.setFirstname(firstname);
        user.setLastname(lastname);

        return user;
    }
}
