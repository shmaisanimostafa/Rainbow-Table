import java.io.File;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.security.MessageDigest;

public class RainbowTable {

    public static void main(String[] args) {

        String checkedFileName;
        String encryptedPass = "";
        String decryptedPass = "";
        String table = "";
        String line;

        Scanner scanner = new Scanner(System.in);

        // SECTION - Introduction
        space(); // Leave some space before the app for clarity
        System.out.println(asciiArt + "\n"); // Output the drawing
        System.out.println(
                "Welcome to RainbowTable decryption Project \n"); // Welcome message

        // STUB - File Upload
        System.out.println("Enter File name here after making sure :"); // Filename
        System.out.println(
                "\t- The file is a PLAIN TEXT file which ends with .txt or .html or .xml (.csv format is accepted)");
        System.out.println("\t- Default will be .txt were you don't need to end it with .txt: ");

        // STUB - Check Filename
        String fileName = scanner.nextLine(); // Take filename from the user and check it
        if (!fileName.endsWith(".txt") && !fileName.endsWith(".html") && !fileName.endsWith(".xml")) {
            checkedFileName = fileName + ".txt";
        } else {
            checkedFileName = fileName;
        }

        try {
            // SECTION - Read File
            File file = new File(checkedFileName); // STUB - Read the File
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) { // STUB - Clone the File to a String

                encryptedPass += line;
            }

            // SECTION - Work with the File's Contents
            decryptedPass = decrypter(encryptedPass); // STUB - Decrypt the String
            table = tableGraphicalBuilder(tableBuilder(encryptedPass, decryptedPass)); // STUB - Create the table

            // SECTION - Download or Not
            System.out.println("Do you want to download output as a file? (Y/N)");// STUB - Ask user for download
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("Y")) { // STUB -If yes download the file
                createFile(table);
            } else { // STUB - If no Output the file on the command line
                System.out.println(table);
            }
            br.close();
            fr.close();
        } catch (Exception e) { // STUB - Handle the errors
            System.out.println(e.getMessage());
        }

        space(); // STUB -Leave some space after the answer
        scanner.close();
    }

    // SECTION - Drawing
    public static String asciiArt = "██████╗  █████╗ ██╗███╗   ██╗██████╗  ██████╗ ██╗    ██╗    \n██╔══██╗██╔══██╗██║████╗  ██║██╔══██╗██╔═══██╗██║    ██║    \n██████╔╝███████║██║██╔██╗ ██║██████╔╝██║   ██║██║ █╗ ██║    \n██╔══██╗██╔══██║██║██║╚██╗██║██╔══██╗██║   ██║██║███╗██║    \n██║  ██║██║  ██║██║██║ ╚████║██████╔╝╚██████╔╝╚███╔███╔╝    \n╚═╝  ╚═╝╚═╝  ╚═╝╚═╝╚═╝  ╚═══╝╚═════╝  ╚═════╝  ╚══╝╚══╝     \nMostafa     ████████╗ █████╗ ██████╗ ██╗     ███████╗       \n   Shmaisani ╚══██╔══╝██╔══██╗██╔══██╗██║     ██╔════╝       \n                ██║   ███████║██████╔╝██║     █████╗         \n                ██║   ██╔══██║██╔══██╗██║     ██╔══╝         \n                ██║   ██║  ██║██████╔╝███████╗███████╗  ";

    // SECTION - Methods
    public static String[][] tableBuilder(String Password, String decryptedPass) { // NOTE - Creates the table
        String[] encrypted = Password.split("\\s+");
        String[] decrypted = decryptedPass.split("\\s+");
        int numWords = encrypted.length;
        String[][] table = new String[numWords][3];

        try {

            for (int i = 0; i < table.length; i++) {
                for (int j = 0; j < 3; j++) {
                    if (j == 0) {
                        table[i][j] = String.valueOf(i);
                    }
                    if (j == 1) {
                        table[i][j] = encrypted[i];
                    }
                    if (j == 2) {
                        table[i][j] = decrypted[i];
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return table;
    }

    public static String tableGraphicalBuilder(String[][] table) {
        StringBuilder sb = new StringBuilder();
        // write the table header to the file
        sb.append(String.format("+%-5s+%-18s+%-18s+\n", "_____", "__________________", "__________________"));
        sb.append(String.format("|%-5s|%-18s|%-18s|\n", " ID ", "    ENcrypted     ", "    DEcrypted     "));
        sb.append(String.format("+%-5s+%-18s+%-18s+\n", "_____", "__________________", "__________________"));
        // write the table data to the file
        for (int i = 0; i < table.length; i++) {
            sb.append(String.format("|%-5s|%-18s|%-18s||\n", table[i][0], table[i][1], table[i][2]));
            sb.append(String.format("+%-5s+%-18s+%-18s+\n", "-----", "------------------", "------------------"));
        }
        sb.append("\n| ======================== | ===================================================== |");
        sb.append("\n| Name: Mostafa Shmaisani  |  Website: Shmaisanimostafa.github.io/shmaisanimostafa |");
        sb.append("\n| ======================== | ===================================================== |");

        return sb.toString();

    }

    public static String decrypter(String password) { // NOTE - Decrypt the hash code
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            switch (password.charAt(i)) {
                case '4':
                    sb.append('a');
                    break;
                case '6':
                    sb.append('h');
                    break;
                case '0':
                    sb.append('o');
                    break;
                case '!':
                    sb.append('i');
                    break;
                case '$':
                    sb.append('s');
                    break;
                default:
                    sb.append(password.charAt(i));
                    break;
            }
        }
        return sb.toString();
    }

    public static void createFile(String outputString) { // NOTE - Creates a file
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the name of the output file:");
        String outputFileName = scanner.nextLine();
        try {
            FileWriter writerf = new FileWriter(outputFileName);
            writerf.write(outputString);
            writerf.close();
            System.out.println("\nYour decrypted file: " + outputFileName + " has been downloaded SUCCESSFULLY!");
            System.out.println("You can find it in the same path of this program.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        scanner.close();
    }

    public static void space() { // NOTE - Generates some space for calrity
        for (int i = 0; i < 1; i++) {
            System.out.println("\n");
        }
    }

}
