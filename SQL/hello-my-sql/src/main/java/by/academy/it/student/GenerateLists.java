package by.academy.it.student;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class GenerateLists {
    private static final List<String> listMaleNames = new ArrayList<>();
    private static final List<String> listFemaleNames = new ArrayList<>();
    private static final List<String> listSurnames = new ArrayList<>();
    private static final List<String> listSpecs = new ArrayList<>();
    private static final List<String> engNamesList = new ArrayList<>();
    private static final String pathMaleNames = "./src/main/resources/NameMale.txt";
    private static final String pathFemaleNames = "./src/main/resources/NameFemale.txt";
    private static final String pathSurnames = "./src/main/resources/Surname.txt";
    private static final String pathSpec = "./src/main/resources/specializations.txt";
    private static final String pathEngNames = "./src/main/resources/names.txt";
    private static final Random random = new Random();
    private static Logger logger = Logger.getLogger(GenerateLists.class.getName());


    public static void initializeLists() throws IOException {
        String readLine;
        if (listMaleNames.isEmpty()) {
            try (BufferedReader readMaleNames = new BufferedReader(new FileReader(pathMaleNames))) {
                while ((readLine = readMaleNames.readLine()) != null) {
                    listMaleNames.add(readLine);
                }
            }
        }
        if (listFemaleNames.isEmpty()) {
            try (BufferedReader readFemaleNames = new BufferedReader(new FileReader(pathFemaleNames))) {
                while ((readLine = readFemaleNames.readLine()) != null) {
                    listFemaleNames.add(readLine);
                }
            }
        }
        if (listSurnames.isEmpty()) {
            try (BufferedReader readSurnames = new BufferedReader(new FileReader(pathSurnames))) {
                while ((readLine = readSurnames.readLine()) != null) {
                    listSurnames.add(readLine);
                }
            }
        }

        if (listSpecs.isEmpty()) {
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

    private static List<String> initialEngNames () throws IOException  {
        String readLine;
        if (engNamesList.isEmpty()) {
            try (BufferedReader readMaleNames = new BufferedReader(new FileReader(pathEngNames))) {
                while ((readLine = readMaleNames.readLine()) != null) {
                    engNamesList.add(readLine);
                }
            }
        }
        return engNamesList;
    }



    public static String getPathMaleNames() {
        return pathMaleNames;
    }

    public static String getPathFemaleNames() {
        return pathFemaleNames;
    }

    public static String getPathSurnames() {
        return pathSurnames;
    }

    public static String getPathSpec() {
        return pathSpec;
    }


    private static String getRandomMaleName() {
        return listMaleNames.get(Math.abs(random.nextInt(listMaleNames.size())));
    }

    private static  String getRandomFemaleName() {
        return listFemaleNames.get(Math.abs(Math.abs(random.nextInt(listFemaleNames.size()))));
    }

    private static String getRandomSurname() {
        return listSurnames.get(Math.abs(random.nextInt(listSurnames.size())));
    }

    private static String getRandomSpec() {
        return listSpecs.get(Math.abs(random.nextInt(listSpecs.size())));
    }



    public static StudentDTO generateRandomStudent(){
        StudentDTO student;
        char gender;
        if (random.nextInt(2) == 1) gender = 'M';
        else gender = 'F';
            student = new StudentDTO(
                    (gender == 'M' ? getRandomMaleName() : getRandomFemaleName()),
                    (gender == 'M' ? getRandomSurname() : getRandomSurname() + 'а'),
                    (16 + Math.abs(random.nextInt(12))),
                    gender,
                    (1 + Math.abs(random.nextInt(5))),
                    getRandomSpec()
            );
            int id = Math.abs(student.hashCode());
            student.setId(Math.abs(id));
            return student;
    }

}





