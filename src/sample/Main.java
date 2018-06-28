package sample;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sample.Controller.*;
import sample.Model.Student;
import sample.Model.StudentDb;
import sample.View.Add.AddDialog;
import sample.View.Add.AddRandom;
import sample.View.Delete.DeleteFioAndGroup;
import sample.View.Delete.DeleteFioAndNumberWork;
import sample.View.Delete.DeleteFioAndWork;
import sample.View.Find.FindFioAndGroup;
import sample.View.Find.FindFioAndNumberWork;
import sample.View.Find.FindFioAndWork;
import sample.View.MyView;

import java.io.File;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        StudentDb studentDb = new StudentDb();
        ControllerStudents controllerStudents = new ControllerStudents(studentDb);
        MyView myView = new MyView(controllerStudents,primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
