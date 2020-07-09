package by.academy.it;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateLists {
    private static List<String> listMaleNames = new ArrayList<>();
    private static List<String> listFemaleNames = new ArrayList<>();
    private static List<String> listSurnames = new ArrayList<>();
    private static List<String> listSpecs = new ArrayList<>();
    private static List<String> engNamesList = new ArrayList<>();
    private static String pathMaleNames = "./src/main/resources/NameMale.txt";
    private static String pathFemaleNames = "./src/main/resources/NameFemale.txt";
    private static String pathSurnames = "./src/main/resources/Surname.txt";
    private static String pathSpec = "./src/main/resources/specializations.txt";
    private static String pathEngNames = "./src/main/resources/names.txt";
    private static Random random = new Random();


    public static void initializeLists() throws IOException {
        String readLine;
        if (listMaleNames.isEmpty() == true) {
            try (BufferedReader readMaleNames = new BufferedReader(new FileReader(pathMaleNames))) {
                while ((readLine = readMaleNames.readLine()) != null) {
                    listMaleNames.add(readLine);
                }
            }
        }
        if (listFemaleNames.isEmpty() == true) {
            try (BufferedReader readFemaleNames = new BufferedReader(new FileReader(pathFemaleNames))) {
                while ((readLine = readFemaleNames.readLine()) != null) {
                    listFemaleNames.add(readLine);
                }
            }
        }
        if (listSurnames.isEmpty() == true) {
            try (BufferedReader readSurnames = new BufferedReader(new FileReader(pathSurnames))) {
                while ((readLine = readSurnames.readLine()) != null) {
                    listSurnames.add(readLine);
                }
            }
        }

        if (listSpecs.isEmpty() == true) {
            try (BufferedReader readSpec = new BufferedReader(new FileReader(pathSpec))) {
                while ((readLine = readSpec.readLine()) != null) {
                    listSpecs.add(readLine);
                }
            }
        }
    }

    public static int getListEngNamesLength() throws IOException {
        return initialEngNames().size();
    }

    public static String getRandomEngName () throws IOException {

        return initialEngNames().get(Math.abs(random.nextInt(engNamesList.size())));
    }

    public static List<String> getEngNamesList () throws IOException {
        return initialEngNames();
    }

    private static List<String> initialEngNames () throws FileNotFoundException, IOException  {
        String readLine;
        if (engNamesList.isEmpty() == true) {
            try (BufferedReader readMaleNames = new BufferedReader(new FileReader(pathEngNames))) {
                while ((readLine = readMaleNames.readLine()) != null) {
                    engNamesList.add(readLine);
                }
            }
        }
        return engNamesList;
    }


    public void setPathMaleNames(String pathMaleNames) {
        this.pathMaleNames = pathMaleNames;
    }

    public void setPathFemaleNames(String pathFemaleNames) {
        this.pathFemaleNames = pathFemaleNames;
    }

    public void setPathSurnames(String pathSurnames) {
        this.pathSurnames = pathSurnames;
    }

    public void setPathSpec(String pathSpec) {
        this.pathSpec = pathSpec;
    }

    public String getPathMaleNames() {
        return pathMaleNames;
    }

    public String getPathFemaleNames() {
        return pathFemaleNames;
    }

    public String getPathSurnames() {
        return pathSurnames;
    }

    public String getPathSpec() {
        return pathSpec;
    }


    private static String getRandomMaleName() {
        return listMaleNames.get(Math.abs(random.nextInt(listMaleNames.size())));
    }

    private static  String getRandomFemaleName() {
        return listFemaleNames.get(Math.abs(random.nextInt(listFemaleNames.size())));
    }

    private static String getRandomSurname() {
        return listSurnames.get(Math.abs(random.nextInt(listSurnames.size())));
    }

    private static String getRandomSpec() {
        return listSpecs.get(Math.abs(random.nextInt(listSpecs.size())));
    }



    public static Student generateRandomStudent(){
        Student student;
        char gender;
        if (random.nextInt(2) == 1) gender = 'M';
        else gender = 'F';
            student = new Student(
                    1,
                    gender == 'M' ? getRandomMaleName() : getRandomFemaleName(),
                    gender == 'M' ? getRandomSurname() : getRandomSurname() + 'а',
                    (16 + Math.abs(random.nextInt(12))),
                    gender,
                    (1 + Math.abs(random.nextInt(5))),
                    getRandomSpec()
            );
            return student;
    }

}





