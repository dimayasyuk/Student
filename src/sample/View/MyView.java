package sample.View;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sample.Controller.ControllerStudents;
import sample.Controller.FileWorker;
import sample.View.Add.AddDialog;
import sample.View.Add.AddRandom;
import sample.View.Delete.DeleteFioAndGroup;
import sample.View.Delete.DeleteFioAndNumberWork;
import sample.View.Delete.DeleteFioAndWork;
import sample.View.Find.FindFioAndGroup;
import sample.View.Find.FindFioAndNumberWork;
import sample.View.Find.FindFioAndWork;

import java.io.File;

/**
 * Created by dmitriy on 4.6.18.
 */
public class MyView {
    private Table table;
    private MenuBar menuBar;
    private ToolBar toolBar;
    private Stage primaryStage;
    private File file;
    private BorderPane borderPane;
    private ControllerStudents controllerStudents;
    public MyView(ControllerStudents controllerStudents,Stage stage){
        this.controllerStudents = controllerStudents;
        this.primaryStage = stage;
        primaryStage.setTitle("StudentTable");
        borderPane = new BorderPane();
        table = new Table();
        menuBar = createMenuBar();
        toolBar = createToolBar();
        borderPane.setTop(menuBar);
        borderPane.setBottom(toolBar);
        borderPane.setCenter(table.createPage(controllerStudents));
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setScene(new Scene(borderPane,primaryScreenBounds.getWidth(),primaryScreenBounds.getHeight()));
        primaryStage.show();
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    private Button createToolButton(String text, String url){
        Button button = new Button(text,new ImageView(url));
        button.setTooltip(new Tooltip(text));
        button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        return button;
    }
    private ToolBar createToolBar(){
        ToolBar newToolBar = new ToolBar();

        Button addButton = createToolButton("Добавить","image/add.jpeg");
        addButton.setOnAction(new AddDialog(primaryStage,controllerStudents,table));


        Button findFioAndGroup = createToolButton("Поиск по Фио и группе","image/find1.jpeg");
        findFioAndGroup.setOnAction(new FindFioAndGroup(primaryStage,controllerStudents,table));

        Button findFioAndWork = createToolButton("Поиск по Фио и виду общественной работы","image/find2.png");
        findFioAndWork.setOnAction(new FindFioAndWork(primaryStage,controllerStudents,table));

        Button findFioAndNumberWork = createToolButton("Поиск по Фио и количеству работ","image/find3.png");
        findFioAndNumberWork.setOnAction(new FindFioAndNumberWork(primaryStage,controllerStudents,table));

        Button deleteFioAndGroup = createToolButton("Удаление по Фио и группе","image/delete1.jpeg");
        deleteFioAndGroup.setOnAction(new DeleteFioAndGroup(primaryStage,controllerStudents,table));

        Button deleteFioAndWork = createToolButton("Удаление по Фио и виду общественной работы","image/delete2.jpeg");
        deleteFioAndWork.setOnAction(new DeleteFioAndNumberWork(primaryStage,controllerStudents,table));

        Button deleteFioAndNumberWork = createToolButton("Удаление по Фио и количеству работ","image/delete3.jpeg");
        deleteFioAndNumberWork.setOnAction(new DeleteFioAndNumberWork(primaryStage,controllerStudents,table));

        newToolBar.getItems().addAll(addButton,findFioAndGroup,findFioAndWork,findFioAndNumberWork,deleteFioAndGroup,deleteFioAndWork,deleteFioAndNumberWork);

        return newToolBar;
    }

    private MenuBar createMenuBar(){
        MenuBar newMenuBar = new MenuBar();
        Menu file = new Menu("Файл");

        MenuItem open = new MenuItem("Открыть",new ImageView("image/open.png"));
        open.setOnAction(new FileWorker(primaryStage,controllerStudents,table));
        MenuItem save = new MenuItem("Сохранить",new ImageView("image/save.png"));
        save.setOnAction(new FileWorker(primaryStage,controllerStudents,table));
        MenuItem saveHow = new MenuItem("Сохранить как",new ImageView("image/savehow.jpg"));
        saveHow.setOnAction(new FileWorker(primaryStage,controllerStudents,table));
        MenuItem exit = new MenuItem("Выйти",new ImageView("image/exit.jpg"));
        exit.setOnAction(new FileWorker(primaryStage,controllerStudents,table));

        Menu edit = new Menu("Изменить");
        Menu addMenu = new Menu("Добавить");
        MenuItem addOne = new MenuItem("Добавить студента");
        addOne.setOnAction(new AddDialog(primaryStage,controllerStudents,table));
        MenuItem addRandom = new MenuItem("Добавить случайно");
        addRandom.setOnAction(new AddRandom(controllerStudents,table));

        addMenu.getItems().addAll(addOne,addRandom);

        Menu findMenu = new Menu("Поиск");
        MenuItem findFioAndGroup = new MenuItem("Поиск по Фио и группе");
        findFioAndGroup.setOnAction(new FindFioAndGroup(primaryStage,controllerStudents,table));
        MenuItem findFioAndWork = new MenuItem("Поиск по Фио и виду общественной работы");
        findFioAndWork.setOnAction(new FindFioAndWork(primaryStage,controllerStudents,table));
        MenuItem findFioAndNumberWork = new MenuItem("Поиск по Фио и количеству работ");
        findFioAndNumberWork.setOnAction(new FindFioAndNumberWork(primaryStage,controllerStudents,table));

        findMenu.getItems().addAll(findFioAndGroup,findFioAndWork,findFioAndNumberWork);


        Menu deleteMenu = new Menu("Удалить");
        MenuItem deleteFioAndGroup = new MenuItem("Удаление по Фио и группе");
        deleteFioAndGroup.setOnAction(new DeleteFioAndGroup(primaryStage,controllerStudents,table));
        MenuItem deleteFioAndWork = new MenuItem("Удаление по Фио и виду общественной работы");
        deleteFioAndWork.setOnAction(new DeleteFioAndWork(primaryStage,controllerStudents,table));
        MenuItem deleteFioAndNumberWork = new MenuItem("Удаление по Фио и количеству работ");
        deleteFioAndNumberWork.setOnAction(new DeleteFioAndNumberWork(primaryStage,controllerStudents,table));

        deleteMenu.getItems().addAll(deleteFioAndGroup,deleteFioAndWork,deleteFioAndNumberWork);


        file.getItems().addAll(open,save,saveHow,new SeparatorMenuItem(),exit);
        edit.getItems().addAll(addMenu,findMenu,deleteMenu);
        newMenuBar.getMenus().addAll(file,edit);

        return newMenuBar;
    }
}
