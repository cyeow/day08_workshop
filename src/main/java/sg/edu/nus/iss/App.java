package sg.edu.nus.iss;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

public final class App {
    private App() {
    }

    public static void main(String[] args) throws IOException {

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("kill")) {
                System.out.println("Ending program...");
                System.exit(0);
            }
        }

        String dirName = "day08data";
        String fileName = "data.txt";
        String dirFileName = dirName + File.separator + fileName;

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

        try {
            FileOutputStream fos = new FileOutputStream(dirFileName);

            // for (int i = 0; i < 20; i++) {
            //     // writing string in char array
            //     fos.write(Integer.toString(i).getBytes());
            //     fos.write('\n');
            // }

            EmployeeService es = new EmployeeService();
            List<Employee> empList = es.generateEmployees();
            

            // clear the outputstream
            // force data to store to file
            fos.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}
