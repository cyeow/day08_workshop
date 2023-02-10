package sg.edu.nus.iss;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public final class App {
    private App() {
    }

    public static String dirName = "day08data";
    public static String fileName = "data.txt";
    public static String dirFileName = dirName + File.separator + fileName;

    public static void main(String[] args) throws IOException {

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("kill")) {
                System.out.println("Ending program...");
                System.exit(0);
            }
        }

        FileService fs = new FileService();
        Boolean directoryCreated = fs.createDirectory(dirName);

        if (directoryCreated) {
            System.out.println("directory created:" + dirName);
        } else {
            System.out.println("directory already exists:" + dirName);
        }

        Boolean fileCreated = fs.createFile(dirName, fileName);

        if (fileCreated) {
            System.out.println("file created:" + fileName);
        } else {
            System.out.println("file already exists:" + fileName);
        }

        Console con = System.console();
        String conInput = "";

        while (!conInput.equalsIgnoreCase("Q")) {
            displayMenu();
            conInput = con.readLine("Enter your selection:");

            switch (conInput) {
                case "1":
                    CSVExample();
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "Q":
                case "q":
                    message("Bye... bye...");
                    break;
                default:
                    break;
            }
        }

    }

    public static void CSVExample() throws IOException {
        try {
            // FileOutputStream fos = new FileOutputStream(dirFileName);

            // for (int i = 0; i < 20; i++) {
            // // writing string in char array
            // fos.write(Integer.toString(i).getBytes());
            // fos.write('\n');
            // }

            // clear the outputstream
            // force data to store to file
            // fos.flush();
            // fos.close();

            EmployeeService es = new EmployeeService();
            List<Employee> empList = es.generateEmployees();

            CSVService csvSvc = new CSVService();
            csvSvc.writeToCSV(empList, dirFileName);

            List<Employee> csvEmpList = csvSvc.readFromCSV(dirFileName);
            csvEmpList.forEach(emp -> System.out.println(emp));

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void displayMenu() {
        message("Welcome to My App");
        message("=============================");
        message("1. CSV Read and Write Test");
        message("2. Read Idioms File");
        message("3. Pick an idiom randomly");
        message("4. List all idioms");
        message("Q  Quit the program");
    }

    public static void message(String line) {
        System.out.println(line);
    }
}
