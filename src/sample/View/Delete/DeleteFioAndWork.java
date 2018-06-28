package sample.View.Delete;

import javafx.event.*;
import javafx.stage.Stage;
import sample.Controller.ControllerStudents;
import sample.Model.*;
import sample.View.FindDelete.FioAndFork;
import sample.View.Table;

import java.util.Iterator;

/**
 * Created by dmitriy on 6.5.18.
 */
public class DeleteFioAndWork extends FioAndFork implements EventHandler<ActionEvent>{

    public DeleteFioAndWork(Stage stage, ControllerStudents controllerStudents, Table table) {
        this.controllerStudents = controllerStudents;
        this.stage = stage;
        this.table = table;
    }

    @Override
    public void handle(ActionEvent event) {
        newWindow = new Stage();
        newWindow.initOwner(stage);
        createDialog("Удаление");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int count = startAlgorithm();
                newWindow.close();
                newWindow.close();
                if(count == 0){
                    showAlert("Записей не найдено!");
                }
                else{
                    table.updateTable(controllerStudents);
                    showAlert("Удалено " + count + " записей");
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
        int result = controllerStudents.deleteByWork(textFio,textName,textPatronymic,textWork);
        return result;
    }
}
