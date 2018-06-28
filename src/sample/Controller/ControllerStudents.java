package sample.Controller;
import sample.Model.Student;
import sample.Model.StudentDb;
import sample.Model.Work;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dmitriy on 4.6.18.
 */
public class ControllerStudents {
    private StudentDb studentDb;
    public ControllerStudents(StudentDb studentDb){
        this.studentDb = studentDb;
    }
    public void addStudent(Student student){
        studentDb.addStudent(student);
    }
    public List<Student> getStudents(){
        return studentDb.getStudents();
    }
    public int getSizeStudentDB(){
        return studentDb.getStudentsSize();
    }
    public void setStudents(List<Student> students){
        studentDb.setStudents(students);
    }
    public File getFile(){
        return studentDb.getFile();
    }
    public void setFile(File file){
        studentDb.setFile(file);
    }
    public void removeStudent(Student student){
        studentDb.removeStudent(student);
    }

    public int deleteByNumberWork(String textFio,String textName,String textPatronymic,int oneRang,int twoRang) {
        int count = 0;
        int students = 0;
        Iterator<Student> it  = this.getStudents().iterator();
        while (it.hasNext()) {
            Student student = it.next();
            if (student.getFirstName().equals(textFio) && student.getLastName().equals(textName)
                    && student.getPatronymicName().equals(textPatronymic)) {
                for (Work work:student.getWork()) {
                    if (!work.getNameWork().equals("")) {
                        count++;
                    }
                }
                if((oneRang<=count) && (count<=twoRang)){
                    it.remove();
                    students++;
                }
            }
            count = 0;
        }
        return students;
    }
    public int deleteByGroup(String textFio,String textName,String textPatronymic,String group){
        int count = 0;
        Iterator<Student> it  = this.getStudents().iterator();
        while (it.hasNext()){
            Student student = it.next();
            if (student.getFirstName().equals(textFio) && student.getLastName().equals(textName)
                    && student.getPatronymicName().equals(textPatronymic) && student.getGroup().equals(group)) {
                it.remove();
                count++;
            }
        }
        return count;
    }
    public int deleteByWork(String textFio,String textName,String textPatronymic,String textWork){
        int count = 0;
        Iterator<Student> it  = this.getStudents().iterator();
        while (it.hasNext()) {
            Student student = it.next();
            if (student.getFirstName().equals(textFio) && student.getLastName().equals(textName)
                    && student.getPatronymicName().equals(textPatronymic)) {
                for (Work work:student.getWork()) {
                    if (work.getNameWork().equals(textWork)) {
                        it.remove();
                        count++;
                        break;
                    }
                }
            }
        }
        return count;
    }
    public int findByGroup(ControllerStudents findController,String textFio,String textName,String textPatronymic,String group){
        int count = 0;
        Iterator<Student> it  = this.getStudents().iterator();
        while (it.hasNext()){
            Student student = it.next();
            if (student.getFirstName().equals(textFio) && student.getLastName().equals(textName)
                    && student.getPatronymicName().equals(textPatronymic) && student.getGroup().equals(group)) {
                findController.addStudent(student);
                count++;
            }
        }
        return count;
    }
    public int findByNumberWork(ControllerStudents findController,String textFio,String textName,String textPatronymic,int oneRang,int twoRang){
        int count = 0;
        int numberStudents = 0;
        Iterator<Student> it  = this.getStudents().iterator();
        while (it.hasNext()) {
            Student student = it.next();
            if (student.getFirstName().equals(textFio) && student.getLastName().equals(textName)
                    && student.getPatronymicName().equals(textPatronymic)) {
                for (Work work:student.getWork()) {
                    if (!work.getNameWork().equals("")) {
                        count++;
                    }
                }
                if((oneRang<=count) && (count<=twoRang)){
                    findController.addStudent(student);
                    numberStudents++;
                }
            }
            count = 0;
        }
        return numberStudents;
    }
    public int findByWork(ControllerStudents findController,String textFio,String textName,String textPatronymic,String textWork){
        int count = 0;
        Iterator<Student> it = this.getStudents().iterator();
        while (it.hasNext()) {
            Student student = it.next();
            if (student.getFirstName().equals(textFio) && student.getLastName().equals(textName)
                    && student.getPatronymicName().equals(textPatronymic)) {
                for (Work work : student.getWork()) {
                    if (work.getNameWork().equals(textWork)) {
                        findController.addStudent(student);
                        count++;
                        break;
                    }
                }
            }
        }
        return count;
    }
}
