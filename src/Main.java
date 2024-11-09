import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите имя входного файла(например, alp.txt): ");
        String inputFile = scanner.nextLine();

        System.out.print("Введите имя выходного файла(например, alp.txt): ");
        String outputFile = scanner.nextLine();

        WriteProcessor processor = new WriteProcessor();

        try {
            processor.processFile(inputFile, outputFile);
        } catch (Exception e) {
            System.err.println("Произошла ошибка при обработке файла: " + e.getMessage());
        }

        scanner.close();
    }
}
