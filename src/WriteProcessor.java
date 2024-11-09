import java.io.*;

public class WriteProcessor {

    public void processFile(String inputFile, String outputFile) {
        int[] frequencyArray = new int[52]; //массив для подсчета частоты символов(26 заглавных и 26 строчных)

        try {
            readAndCountFrequencies(inputFile, frequencyArray); //чтение файла и подсчет кол-ва

            writeFrequenciesToFile(outputFile, frequencyArray); //запись кол-ва в файл

            System.out.println("Частотный словарь записан в файл " + outputFile);

        } catch (FileNotFoundException e) {
            System.err.println("Ошибка: входной файл " + inputFile + " не найден.");
        } catch (Exception e) {
            System.err.println("Произошла непредвиденная ошибка: " + e.getMessage());
        }
    }

    private void readAndCountFrequencies(String inputFile, int[] frequencyArray) throws IOException {
        File file = new File("inputFiles/" + inputFile);

        if (!file.exists()) {
            throw new FileNotFoundException("Файл " + inputFile + " не найден в папке inputFiles.");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int character;
            while ((character = reader.read()) != -1) {
                char ch = (char) character;

                //только латинские буквы
                if (ch >= 'A' && ch <= 'Z') {
                    frequencyArray[ch - 'A']++; //индексы 0-25 для заглавных букв
                } else if (ch >= 'a' && ch <= 'z') {
                    frequencyArray[ch - 'a' + 26]++; //индексы 26-51 для строчных букв
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла " + inputFile + ": " + e.getMessage());
            throw e;
        }
    }

    private void writeFrequenciesToFile(String outputFile, int[] frequencyArray) throws IOException {
        File outputDirectory = new File("outputFiles");
        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs(); //создание папки для вывода, если она не существует
        }

        File file = new File(outputDirectory, outputFile);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < 52; i++) {
                if (frequencyArray[i] > 0) {
                    char ch;
                    if (i < 26) {
                        ch = (char) ('A' + i); //для заглавных букв
                    } else {
                        ch = (char) ('a' + (i - 26)); //для строчных букв
                    }
                    writer.write(ch + ": " + frequencyArray[i]);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл " + outputFile + ": " + e.getMessage());
            throw e;
        }
    }
}
