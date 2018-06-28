package sample.View.Find;

import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.*;
import sample.Controller.ControllerStudents;
import sample.Model.*;
import sample.View.FindDelete.FioAndFork;
import sample.View.Table;

import java.util.Iterator;

/**
 * Created by dmitriy on 22.5.18.
 */
public class FindFioAndWork extends FioAndFork implements EventHandler<ActionEvent>{
    private ControllerStudents controllerStudents;
    private ControllerStudents findControllerStudents;
    public FindFioAndWork(Stage stage,ControllerStudents controllerStudents,Table table) {
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
        String textWork = workText.getText();
        int result = controllerStudents.findByWork(findControllerStudents,textFio,textName,textPatronymic,textWork);
        return result;
    }
}
