package by.academy.it;


import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String name;
    private String surname;
    private int age;
    private char gender;
    private int course;
    private String specialization;


    public Student (int id, String name, String surname, int age, char gender,int course, String specialization) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.course = course;
        this.specialization = specialization;
    }





    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", course=" + course +
                ", specialization='" + specialization + '\'' +
                '}';
    }
    public String toStringJournal(int number) {
        return "Студент" +
                " №=" + number +
                ", Студенческий билет №=" + id +
                ", Имя=" + name +
                ", Фамилия=" + surname +
                ", Возраст=" + age +
                ", Пол=" + gender +
                ", Курс=" + course +
                ", Специальность=" + specialization
                ;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public char getGender() {
        return gender;
    }

    public int getCourse() {
        return course;
    }

    public String getSpecialization() {
        return specialization;
    }
}

