package sample.View.Add;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import sample.Controller.ControllerStudents;
import sample.Model.Student;
import sample.Model.Work;
import sample.View.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitriy on 29.5.18.
 */

public class AddRandom implements EventHandler<ActionEvent> {
    private Table table;
    private ControllerStudents controllerStudents;
    public AddRandom(ControllerStudents controllerStudents, Table table){
        this.controllerStudents = controllerStudents;
        this.table = table;
    }
    @Override
    public void handle(ActionEvent event) {
        String[] families = {"Ясюкевич","Анищик","Вабищевич","Сумар","Пышкин"};
        String[] names = {"Дмитрий","Андрей","Евгений","Николай","Капитон"};
        String[] patronymic = {"Сергеевич","Иванович","Капитонович","Андреевич","Владимирович"};
        String[] groups = {"621702","621701","721701","721702","721703"};
        String[] workNames = {"","груши","огурцы","помидоры","сливы","смородина","яблоки","свекла","лук","вишни"};
        for(int i = 0;i < 100 ;i++){
            List<Work> list = new ArrayList<>();
            for (int j = 0;j<10;j++) {
                list.add(new Work(workNames[(int) (Math.random() * 10)]));
            }
            controllerStudents.addStudent(new Student(families[(int) (Math.random() * 5)],
                    names[(int) (Math.random() * 5)],patronymic[(int) (Math.random() * 5)],groups[(int) (Math.random() * 5)],list));
        }
        table.updateTable(controllerStudents);
    }
}
