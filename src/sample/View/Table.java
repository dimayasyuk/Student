package sample.View;


import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.Controller.ControllerStudents;
import sample.Model.Student;

/**
 * Created by dmitriy on 7.5.18.
 */
public class Table {
    private TableView<Student> table;
    private Label numberItemsOfPage;
    private Label numberPages;
    private TextField numberItemsT;
    private int itemsPerPage;
    private int pageIndex;
    private int pageNum;
    private int fromIndex;
    private int toIndex;

    public TableView<Student> getTable() {
        return table;
    }

    public Table(){
        itemsPerPage = 2;
        pageNum = 1;
        pageIndex = 0;
        fromIndex = 0;
        toIndex = 0;
        pageNum = 1;
    }

    public VBox createPage(ControllerStudents controllerStudents) {
            toIndex = Math.min(fromIndex + itemsPerPage, controllerStudents.getSizeStudentDB());
            VBox vBox = new VBox();
            table = new TableView<Student>();
            table.setEditable(true);
            TableColumn<Student, String> Fio = new TableColumn<>("Фио");
            Fio.setMinWidth(300);
            Fio.setCellValueFactory((TableColumn.CellDataFeatures<Student, String> p) -> {
                String val = p.getValue().getFirstName() + " " + p.getValue().getLastName()
                        + " " + p.getValue().getPatronymicName();
                return new ReadOnlyStringWrapper(val);
            });

            TableColumn<Student, String> group = new TableColumn<>("Группа");
            group.setMinWidth(100);
            group.setCellValueFactory(new PropertyValueFactory<>("group"));

            TableColumn<Student, String> work = new TableColumn<>("Общественная работа");
            TableColumn<Student, String> semester1 = createColumn(1);
            TableColumn<Student, String> semester2 = createColumn(2);
            TableColumn<Student, String> semester3 = createColumn(3);
            TableColumn<Student, String> semester4 = createColumn(4);
            TableColumn<Student, String> semester5 = createColumn(5);
            TableColumn<Student, String> semester6 = createColumn(6);
            TableColumn<Student, String> semester7 = createColumn(7);
            TableColumn<Student, String> semester8 = createColumn(8);
            TableColumn<Student, String> semester9 = createColumn(9);
            TableColumn<Student, String> semester10 = createColumn(10);
            work.getColumns().addAll(semester1, semester2, semester3, semester4, semester5, semester6, semester7, semester8, semester9, semester10);
            table.getColumns().addAll(Fio, group, work);

            if(controllerStudents.getSizeStudentDB() > (toIndex-fromIndex)& ((toIndex - fromIndex) != -1)){
                table.setItems(FXCollections.observableArrayList(controllerStudents.getStudents().subList(fromIndex, toIndex)));
            }else{
                table.setItems(FXCollections.observableArrayList(controllerStudents.getStudents()));
            }

            HBox hBox = new HBox();
            hBox.setAlignment(Pos.BASELINE_CENTER);
            Button firstPage = createButton("Первая страница","image/first.jpeg");
            firstPage.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    fromIndex = (0);
                    pageIndex = (0);
                    pageNum = (1);
                    toIndex = (Math.min(fromIndex + itemsPerPage, controllerStudents.getSizeStudentDB()));
                    table.setItems(FXCollections.observableArrayList(controllerStudents.getStudents().subList(fromIndex, toIndex)));
                    numberItemsOfPage.setText("Количество записей на странице:" + table.getItems().size() + "/" + controllerStudents.getSizeStudentDB());
                    if (controllerStudents.getSizeStudentDB() % itemsPerPage == 0) {
                        numberPages.setText("Количество страниц:" +pageNum + "/" + (controllerStudents.getSizeStudentDB()) / itemsPerPage);
                    } else {
                        numberPages.setText("Количество страниц:" + pageNum + "/" + (controllerStudents.getSizeStudentDB() / itemsPerPage + 1));
                    }
                }
            });
            Button lastPage = createButton("Последняя страница","image/last.jpeg");
            lastPage.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (controllerStudents.getSizeStudentDB() % itemsPerPage == 0) {
                        pageNum = (controllerStudents.getSizeStudentDB() / itemsPerPage);
                        pageIndex = (pageNum - 1);
                        numberPages.setText("Количество страниц:" + pageNum + "/" + controllerStudents.getSizeStudentDB() / itemsPerPage);
                    } else {
                        pageIndex  = (controllerStudents.getSizeStudentDB() / itemsPerPage);
                        pageNum = (pageIndex + 1);
                        numberPages.setText("Количество страниц:" + pageNum + "/" + (controllerStudents.getSizeStudentDB() / itemsPerPage + 1));
                    }
                    fromIndex = (pageIndex * itemsPerPage);
                    toIndex = (Math.min(fromIndex + itemsPerPage, controllerStudents.getSizeStudentDB()));
                    table.setItems(FXCollections.observableArrayList(controllerStudents.getStudents().subList(fromIndex, toIndex)));
                    numberItemsOfPage.setText("Количество записей на странице:" + table.getItems().size() + "/" + controllerStudents.getSizeStudentDB());
                }
            });
            Button nextPage = createButton("Следующая страница","image/next.jpeg");
            nextPage.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (controllerStudents.getSizeStudentDB() % itemsPerPage == 0) {
                        if ((controllerStudents.getSizeStudentDB() / itemsPerPage) > pageNum) {
                            pageIndex = (pageNum);
                            pageNum = (pageNum + 1);
                            fromIndex = (pageIndex * itemsPerPage);
                            toIndex = (Math.min(fromIndex + itemsPerPage, controllerStudents.getSizeStudentDB()));
                            table.setItems(FXCollections.observableArrayList(controllerStudents.getStudents().subList(fromIndex, toIndex)));
                            numberItemsOfPage.setText("Количество записей на странице:" + table.getItems().size() + "/" + controllerStudents.getSizeStudentDB());
                            if (controllerStudents.getSizeStudentDB() % itemsPerPage == 0) {
                                numberPages.setText("Количество страниц:" + (pageNum) + "/" + (controllerStudents.getSizeStudentDB() / itemsPerPage));
                            } else {
                                numberPages.setText("Количество страниц:" + (pageNum) + "/" + (controllerStudents.getSizeStudentDB() / itemsPerPage + 1));
                            }
                        }
                    } else {
                        if ((controllerStudents.getSizeStudentDB() / itemsPerPage) > pageIndex) {
                            pageIndex = (pageNum);
                            pageNum = (pageNum + 1);
                            fromIndex = (pageIndex * itemsPerPage);
                            toIndex = (Math.min(fromIndex + itemsPerPage, controllerStudents.getSizeStudentDB()));
                            table.setItems(FXCollections.observableArrayList(controllerStudents.getStudents().subList(fromIndex, toIndex)));
                            numberItemsOfPage.setText("Количество записей на странице:" + table.getItems().size() + "/" + controllerStudents.getSizeStudentDB());
                            if (controllerStudents.getSizeStudentDB() % itemsPerPage == 0) {
                                numberPages.setText("Количество страниц:" + (pageNum) + "/" + (controllerStudents.getSizeStudentDB() / itemsPerPage));
                            } else {
                                numberPages.setText("Количество страниц:" + (pageNum) + "/" + (controllerStudents.getSizeStudentDB() / itemsPerPage + 1));
                            }
                        }
                    }
                }
            });
            Button prevPage = createButton("Предыдущая страница","image/prev.jpeg");
            prevPage.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (pageIndex > 0) {
                        pageNum = (pageIndex);
                        pageIndex = (pageIndex - 1);
                        fromIndex = (pageIndex * itemsPerPage);
                        toIndex = (Math.min(fromIndex + itemsPerPage, controllerStudents.getSizeStudentDB()));
                        table.setItems(FXCollections.observableArrayList(controllerStudents.getStudents().subList(fromIndex, toIndex)));
                        numberItemsOfPage.setText("Количество записей на странице:" + table.getItems().size() + "/" + controllerStudents.getSizeStudentDB());
                        if (controllerStudents.getSizeStudentDB() % itemsPerPage == 0) {
                            numberPages.setText("Количество страниц:" + pageNum + "/" + (controllerStudents.getSizeStudentDB() / itemsPerPage));
                        } else {
                            numberPages.setText("Количество страниц:" + pageNum + "/" + ((controllerStudents.getSizeStudentDB() / itemsPerPage) + 1));
                        }
                    }
                }
            });


            HBox hboxNumberItems = new HBox();
            hboxNumberItems.setAlignment(Pos.BASELINE_CENTER);
            numberItemsOfPage = new Label("Количество записей на странице:" + table.getItems().size() + "/" + controllerStudents.getSizeStudentDB());
            hboxNumberItems.getChildren().add(numberItemsOfPage);


            HBox hboxNumberPages = new HBox();
            hboxNumberPages.setAlignment(Pos.BASELINE_CENTER);
            if(controllerStudents.getSizeStudentDB() > 0){
            if(controllerStudents.getSizeStudentDB() % itemsPerPage == 0) {
                numberPages = new Label("Количество страниц:" + pageNum  + "/" + (controllerStudents.getSizeStudentDB() / itemsPerPage));
            }else{
                numberPages = new Label("Количество страниц:" + pageNum + "/" + (controllerStudents.getSizeStudentDB() / itemsPerPage + 1));
            }}
            else{
                numberPages = new Label("Количество страниц:" + (pageNum -1)  + "/" + (controllerStudents.getSizeStudentDB() / itemsPerPage));
            }
            hboxNumberPages.getChildren().add(numberPages);

            HBox hboxText = new HBox();
            hboxText.setAlignment(Pos.BASELINE_CENTER);
            Label numberItemsL = new Label("Введите количество записей на странице:");
            numberItemsT = new TextField();
            numberItemsT.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String string = numberItemsT.getText();
                    for (int i = 0; i < string.length(); i++) {
                        if (!Character.isDigit(string.charAt(i))) {
                            numberItemsT.clear();
                            return;
                        }
                    }
                    itemsPerPage = (Integer.parseInt(string));
                    numberItemsT.clear();
                    pageIndex = 0;
                    pageNum = 1;
                    fromIndex = (pageIndex * itemsPerPage);
                    toIndex = (Math.min(fromIndex + itemsPerPage, controllerStudents.getSizeStudentDB()));
                    table.setItems(FXCollections.observableArrayList(controllerStudents.getStudents().subList(fromIndex, toIndex)));
                    numberItemsOfPage.setText("Количество записей на странице:" + table.getItems().size() + "/" + controllerStudents.getSizeStudentDB());
                    if (controllerStudents.getSizeStudentDB() % itemsPerPage == 0) {
                        numberPages.setText("Количество страниц:" + pageNum + "/" + (controllerStudents.getSizeStudentDB() / itemsPerPage));
                    } else {
                        numberPages.setText("Количество страниц:" + pageNum + "/" + ((controllerStudents.getSizeStudentDB() / itemsPerPage) + 1));
                    }
                }
            });
            numberItemsT.setMinSize(20,20);
            hboxText.getChildren().addAll(numberItemsL,numberItemsT);

            hBox.getChildren().addAll(firstPage,prevPage,nextPage,lastPage);

            vBox.getChildren().addAll(table,hBox,hboxNumberItems,hboxNumberPages,hboxText);

            return vBox;
    }
    public void updateTable(ControllerStudents controllerStudents) {
        if (controllerStudents.getSizeStudentDB() % itemsPerPage == 0) {
            pageNum =(controllerStudents.getSizeStudentDB() / itemsPerPage);
            pageIndex =(pageNum - 1);
            numberPages.setText("Количество страниц:" + pageNum + "/" + controllerStudents.getSizeStudentDB() / itemsPerPage);
        } else {
            pageIndex = (controllerStudents.getSizeStudentDB() / itemsPerPage);
            pageNum = (pageIndex + 1);
            numberPages.setText("Количество страниц:" + pageNum + "/" + (controllerStudents.getSizeStudentDB() / itemsPerPage + 1));
        }
        fromIndex = (pageIndex * itemsPerPage);
        toIndex = (Math.min(fromIndex + itemsPerPage, controllerStudents.getSizeStudentDB()));
        table.setItems(FXCollections.observableArrayList(controllerStudents.getStudents().subList(fromIndex, toIndex)));
        numberItemsOfPage.setText("Количество записей на странице:" + table.getItems().size() + "/" + controllerStudents.getSizeStudentDB());
    }
    private Button createButton(String text, String url){
        Button button = new Button(text,new ImageView(url));
        button.setTooltip(new Tooltip(text));
        button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        return button;
    }
    private TableColumn<Student, String> createColumn(int index) {
        TableColumn<Student, String> sem = new TableColumn<>(index + " семестр");
        sem.setMinWidth(100);
        sem.setCellValueFactory((TableColumn.CellDataFeatures<Student, String> p) -> {
            String val = p.getValue().getWork().get(index - 1).getNameWork();
            return new ReadOnlyStringWrapper(val);
        });
        return sem;
    }

}
