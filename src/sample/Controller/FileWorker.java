package sample.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import sample.Model.Student;
import sample.Model.Work;
import sample.View.Table;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitriy on 8.5.18.
 */
public class FileWorker implements EventHandler<ActionEvent>{
    private ControllerStudents controllerStudents;
    private Stage stage;
    private Table table;

    public FileWorker(Stage stage, ControllerStudents controllerStudents,Table table){
        this.controllerStudents = controllerStudents;
        this.stage = stage;
        this.table = table;
    }

    @Override
    public void handle(ActionEvent event) {
        if(((MenuItem)event.getSource()).getText().equals("Выйти")){
            System.exit(0);
        }else if(((MenuItem)event.getSource()).getText().equals("Открыть")){
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Text Files", "*.txt", "*.xml"));
            File files = fileChooser.showOpenDialog(stage);
            if(files!=null){
                try {
                    SAXParserFactory spfac = SAXParserFactory.newInstance();
                    SAXParser sp = spfac.newSAXParser();

                    DefaultHandler handler = new DefaultHandler(){
                        private Student student;
                        private String temp;
                        @Override
                        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                            temp="";
                            if (qName.equalsIgnoreCase("STUDENT")) {
                                student = new Student();
                            }

                        }

                        @Override
                        public void endElement(String uri, String localName, String qName) throws SAXException {
                            if (qName.equalsIgnoreCase("students")) {
                                table.updateTable(controllerStudents);
                            }else if (qName.equalsIgnoreCase("STUDENT")) {
                                controllerStudents.addStudent(student);
                            }
                            else if (qName.equalsIgnoreCase("FIRSTNAME")) {
                                student.setFirstName(temp);
                            }

                            else if (qName.equalsIgnoreCase("LASTNAME")) {
                                student.setLastName(temp);
                            }
                            else if (qName.equalsIgnoreCase("patronymicName")) {
                                student.setPatronymicName(temp);
                            }
                            else if (qName.equalsIgnoreCase("GROUP")) {
                                student.setGroup(temp);
                            }

                            else if (qName.equalsIgnoreCase("sem1")) {
                                student.addWork(temp);
                            }
                            else if (qName.equalsIgnoreCase("sem2")) {
                                student.addWork(temp);
                            }
                            else if (qName.equalsIgnoreCase("sem3")) {
                                student.addWork(temp);
                            }
                            else if (qName.equalsIgnoreCase("sem4")) {
                                student.addWork(temp);
                            }
                            else if (qName.equalsIgnoreCase("sem5")) {
                                student.addWork(temp);
                            }
                            else if (qName.equalsIgnoreCase("sem6")) {
                                student.addWork(temp);
                            }
                            else if (qName.equalsIgnoreCase("sem7")) {
                                student.addWork(temp);
                            }
                            else if (qName.equalsIgnoreCase("sem8")) {
                                student.addWork(temp);
                            }
                            else if (qName.equalsIgnoreCase("sem9")) {
                                student.addWork(temp);
                            }
                            else if (qName.equalsIgnoreCase("sem10")) {
                                student.addWork(temp);
                            }
                        }

                        @Override
                        public void characters(char[] ch, int start, int length) throws SAXException {
                            temp = new String(ch, start, length);
                        }
                    };
                    sp.parse(files,handler);
                    controllerStudents.setFile(files);
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                catch (SAXException e){
                    e.printStackTrace();
                }catch (ParserConfigurationException e){
                    e.printStackTrace();
                }
            }
        }else
        if(((MenuItem)event.getSource()).getText().equals("Сохранить")) {
            if (controllerStudents.getFile() == null) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save Resource File");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Text Files","*.xml"));
                File file = fileChooser.showSaveDialog(stage);
                controllerStudents.setFile(file);
            }
            if (controllerStudents.getFile() != null) {
                DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder;

                try {
                    documentBuilder = builderFactory.newDocumentBuilder();
                    Document doc = documentBuilder.newDocument();
                    Element rootElement = doc.createElement("students");
                    doc.appendChild(rootElement);
                    for (Student s : controllerStudents.getStudents()) {
                        rootElement.appendChild(createStudent(doc, s));

                    }
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transf = transformerFactory.newTransformer();

                    transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                    transf.setOutputProperty(OutputKeys.INDENT, "yes");

                    DOMSource source = new DOMSource(doc);
                    StreamResult file = new StreamResult(controllerStudents.getFile());

                    transf.transform(source, file);

                } catch (ParserConfigurationException ex) {
                    ex.printStackTrace();
                } catch (TransformerException ex) {
                    ex.printStackTrace();
                }
            }
        } else if(((MenuItem)event.getSource()).getText().equals("Сохранить как")) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Resource File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Text Files", "*.xml"));
            File files = fileChooser.showSaveDialog(stage);
            controllerStudents.setFile(files);
            if (controllerStudents.getFile() != null) {
                DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder;

                try {
                    documentBuilder = builderFactory.newDocumentBuilder();
                    Document doc = documentBuilder.newDocument();
                    Element rootElement = doc.createElement("students");
                    doc.appendChild(rootElement);
                    for (Student s : controllerStudents.getStudents()) {
                        rootElement.appendChild(createStudent(doc, s));

                    }
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transf = transformerFactory.newTransformer();

                    transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                    transf.setOutputProperty(OutputKeys.INDENT, "yes");

                    DOMSource source = new DOMSource(doc);
                    StreamResult file = new StreamResult(controllerStudents.getFile());

                    transf.transform(source, file);

                } catch (ParserConfigurationException ex) {
                    ex.printStackTrace();
                } catch (TransformerException ex) {
                    ex.printStackTrace();
                }
            }
        }
        }
    private Node createStudent(Document doc, Student s){
        Element student = doc.createElement("student");
        student.appendChild(createStudentElement(doc,"firstname",s.getFirstName()));
        student.appendChild(createStudentElement(doc,"lastname",s.getLastName()));
        student.appendChild(createStudentElement(doc,"patronymicname",s.getPatronymicName()));
        student.appendChild(createStudentElement(doc,"group",s.getGroup()));
        Element work = doc.createElement("work");
        int index = 1;
        for(Work w:s.getWork()){
            String name =  "sem"+index;
            work.appendChild(createStudentElement(doc,name,w.getNameWork()));
            index++;
        }
        student.appendChild(work);
        return student;
    }
    private static Node createStudentElement(Document doc, String name,
                                          String value) {

        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));

        return node;
    }
}
