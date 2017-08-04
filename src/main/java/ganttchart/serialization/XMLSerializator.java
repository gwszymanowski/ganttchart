package ganttchart.serialization;

import ganttchart.gui.elements.alert.AlertFactory;
import ganttchart.gui.elements.alert.AlertReason;
import ganttchart.gui.elements.alert.ElementType;
import ganttchart.gui.elements.alert.OperationType;
import javafx.scene.control.Alert;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by gwszymanowski on 2017-08-03.
 */
public final class XMLSerializator<T> implements SerializeStrategy<T> {

    private Class<T> persistentClass;

    public XMLSerializator(Class persistentClass) {
        this.persistentClass = persistentClass;
    }

    @Override
    public void marshal(T object) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Open Resource File");

        File file = chooser.showDialog(null);
        String directory = file.getAbsolutePath();

        if(directory != null) {

            try {
                StringBuilder sb = new StringBuilder(file.getAbsolutePath());
                sb.append("\\");
                String simpleClassName = object.getClass().getSimpleName();
                sb.append(simpleClassName);
                sb.append(".xml");
                File toBeSerialized = new File(sb.toString());

                JAXBContext jaxbContext = JAXBContext.newInstance(persistentClass);

                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                jaxbMarshaller.marshal(object, toBeSerialized);
                jaxbMarshaller.marshal(object, System.out);

            } catch (JAXBException e) {
                e.printStackTrace();
                Alert error = AlertFactory.getErrorAlert(AlertReason.FILE_WRONG);
                error.showAndWait();
            }
            Alert alert = AlertFactory.getInformationAlert(ElementType.get(object.getClass()), OperationType.EXPORTED);
            alert.showAndWait();
        }
    }

    @Override
    public T unmarshal() {

        FileChooser chooser = new FileChooser();

        File file = chooser.showOpenDialog(null);

        T object = null;

        if(file != null) {
            try {

                File unmarshalled = new File(file.getAbsolutePath());
                JAXBContext jaxbContext = JAXBContext.newInstance(persistentClass);

                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                object = (T) jaxbUnmarshaller.unmarshal(unmarshalled);

            } catch (JAXBException e) {
                e.printStackTrace();
                Alert error = AlertFactory.getErrorAlert(AlertReason.FILE_WRONG);
                error.showAndWait();
                System.exit(1);
            }
            Alert alert = AlertFactory.getInformationAlert(ElementType.get(object.getClass()), OperationType.EXPORTED);
            alert.showAndWait();
        }

        return object;
    }

}
