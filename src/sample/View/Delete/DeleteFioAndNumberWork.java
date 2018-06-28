package sample.View.Delete;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import sample.Controller.ControllerStudents;
import sample.Model.Student;
import sample.Model.Work;
import sample.View.FindDelete.FioAndNumberWork;
import sample.View.Table;

import java.util.Iterator;

/**
 * Created by dmitriy on 7.5.18.
 */
public class DeleteFioAndNumberWork extends FioAndNumberWork implements EventHandler<ActionEvent>{

    public DeleteFioAndNumberWork(Stage stage, ControllerStudents controllerStudents, Table table){
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
        int oneRang = Integer.parseInt(workText.getText());
        int twoRang = Integer.parseInt(workText1.getText());
        int result = controllerStudents.deleteByNumberWork(textFio,textName,textPatronymic,oneRang,twoRang);
        return result;
    }
}
