package by.academy.it.student;


import java.io.Serializable;
import java.util.Objects;

public class StudentDTO implements Serializable {
    private int id;
    private String name;
    private String surname;
    private int age;
    private char gender;
    private int course;
    private String specialization;


    public StudentDTO(int id, String name, String surname, int age, char gender, int course, String specialization) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.course = course;
        this.specialization = specialization;
    }

    public StudentDTO(String name, String surname, int age, char gender, int course, String specialization) {
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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDTO that = (StudentDTO) o;
        return id == that.id &&
                age == that.age &&
                gender == that.gender &&
                course == that.course &&
                name.equals(that.name) &&
                surname.equals(that.surname) &&
                specialization.equals(that.specialization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, age, gender, course, specialization);
    }
}

