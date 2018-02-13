package none;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Set path:");
        String path = in.nextLine();

        menuDisplay();
        System.out.print("Input:");
        String input = in.nextLine();



        while (!input.equals("exit")) {
            if (input.equals("1"))
                RemoveTargetStringOnADirectory(path);
            else if (input.equals("2"))
                AppendTargetStringOnFrontOfTheFileName(path);
            else if (input.equals("setting")) {
                System.out.print("Enter path:");
                path = in.nextLine();
            }

            menuDisplay();
            System.out.print("Input:");
            input = in.nextLine();
        }
    }

    public static void menuDisplay() {
        System.out.println("Menu:");
        System.out.println("1: Remove target string on a directory");
        System.out.println("2: Append target string on front of the file name");
        System.out.println("exit: Exit program");
    }

    public static void RemoveTargetStringOnADirectory(String path) {
        Scanner in = new Scanner(System.in);

        File dir = new File(path);

        System.out.print("Enter target string to remove:");
        String str = in.nextLine();

        if (dir.isDirectory()) {
            for (final File f : dir.listFiles())
                try {
                    if (f.isDirectory()) {
                        f.renameTo(new File(dir.getPath() + "\\" + f.getName().replace(str, "").trim()));
                    }
                    else if (f.isFile()) {
                        String[] splitStr = SplitNameAndFileType(f.getName());

                        f.renameTo(new File(dir.getPath() + "\\" + splitStr[0].replace(str, "").trim() + splitStr[1]));
                    }
                } catch (Exception e) { e.printStackTrace(); }

            System.out.println("\n" + "Done" + "\n");
        }
        else
            System.out.println("\n" + "It is not a directory. Move back to menu!" + "\n");
    }

    public static String[] SplitNameAndFileType(String input) {
        String[] result = {"", ""};
        String[] cur = input.split("[.]");

        if (cur.length > 2) {
            result[0] = result[0].concat(cur[0]);

            for (int i = 1; i < cur.length - 1; i++)
                result[0] = result[0].concat("." + cur[i]);

            result[1] = "." + result[1].concat(cur[cur.length - 1]);
        }
        else if (cur.length == 2) {
            result = cur;
            result[1] = "." + result[1];
        }

        return result;
    }

    public static void AppendTargetStringOnFrontOfTheFileName(String path) {
        Scanner in = new Scanner(System.in);

        File dir = new File(path);

        System.out.print("Enter target string to remove:");
        String str = in.nextLine();

        if (dir.isDirectory()) {
            for (final File f : dir.listFiles())
                try {
                    if (f.isDirectory()) {
                        f.renameTo(new File(dir.getPath() + "\\" + str + f.getName()));
                    }
                    else if (f.isFile()) {
                        String[] splitStr = SplitNameAndFileType(f.getName());

                        f.renameTo(new File(dir.getPath() + "\\" + str + splitStr[0] + splitStr[1]));
                    }
                } catch (Exception e) { e.printStackTrace(); }

            System.out.println("\n" + "Done" + "\n");
        }
        else
            System.out.println("\n" + "It is not a directory. Move back to menu!" + "\n");
    }
}
