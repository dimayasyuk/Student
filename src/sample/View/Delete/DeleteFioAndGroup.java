package sample.View.Delete;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import sample.Controller.ControllerStudents;
import sample.Model.Student;
import sample.View.FindDelete.FioAndGroup;
import sample.View.Table;

import java.util.Iterator;

/**
 * Created by dmitriy on 6.5.18.
 */
public class DeleteFioAndGroup extends FioAndGroup implements EventHandler<ActionEvent>{
    public DeleteFioAndGroup(Stage stage, ControllerStudents controllerStudents,Table table){
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
        String textGroup = groupText.getText();
        int result = controllerStudents.deleteByGroup(textFio,textName,textPatronymic,textGroup);
        return result;
    }
}
