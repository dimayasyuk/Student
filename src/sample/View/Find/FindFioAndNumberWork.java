package sample.View.Find;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sample.Controller.ControllerStudents;
import sample.Model.Student;
import sample.Model.StudentDb;
import sample.Model.Work;
import sample.View.FindDelete.FioAndNumberWork;
import sample.View.Table;

import java.util.Iterator;

/**
 * Created by dmitriy on 22.5.18.
 */
public class FindFioAndNumberWork extends FioAndNumberWork implements EventHandler<ActionEvent>{
    private ControllerStudents findControllerStudents;
    private ControllerStudents controllerStudents;
    public FindFioAndNumberWork(Stage stage,ControllerStudents controllerStudents,Table table){
        this.stage = stage;
        this.controllerStudents = controllerStudents;
        findControllerStudents = new ControllerStudents(new StudentDb());
        this.table = table;
    }
    @Override
    public void handle(ActionEvent event) {
        newWindow = new Stage();
        newWindow.initOwner(stage);
        createDialog("Поиск");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int count = startAlgorithm();
                newWindow.close();
                if (count == 0) {
                    showAlert("Записей не найдено!");
                } else {
                    Table newTable = new Table();
                    BorderPane borderPane = new BorderPane();
                    borderPane.setCenter(newTable.createPage(findControllerStudents));
                    Button delete = new Button("Удалить найденные");
                    delete.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            for (Student s:findControllerStudents.getStudents()) {
                                controllerStudents.removeStudent(s);
                            }
                            table.updateTable(controllerStudents);
                            newWindow.close();
                        }
                    });
                    HBox deleteBox = new HBox();
                    deleteBox.setAlignment(Pos.BASELINE_CENTER);
                    deleteBox.getChildren().add(delete);
                    borderPane.setBottom(deleteBox);
                    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
                    newWindow.setScene(new Scene(borderPane, 600, 600));
                    newWindow.show();
                }
            }
        });

    }

    @Override
    protected int startAlgorithm() {
        String textFio = fioText.getText();
        String textName = nameText.getText();
        String textPatronymic = patronymicText.getText();
        int oneRang = Integer.parseInt(workText.getText());
        int twoRang = Integer.parseInt(workText1.getText());
        int result = controllerStudents.findByNumberWork(findControllerStudents,textFio,textName,textPatronymic,oneRang,twoRang);
        return result;
    }
}
