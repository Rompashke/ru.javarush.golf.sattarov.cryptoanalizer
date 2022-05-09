import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Cryptographer {
    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

/*    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String strPath = scanner.nextLine();
        Path path = Path.of(strPath);
        createNewFile(path);
    } */
    protected static Path createNewFile(Path path) { // вводим путь к файлу в котором
        // лежит файл с незакодированной информацией.
        boolean choice = true;
        Path parentPathOfFileWithText = Paths.get(path.getParent().toUri()); //берем путь до файла
        Path pathOfNewFile;
        Path createNewFile = null;
        System.out.println("Введите название нового файла без расширения:");
        while (choice) {
            Scanner scanner = new Scanner(System.in);
            String nameOfNewFile = scanner.nextLine(); // имя нового файла
            // создаем новый путь к файлу
            String strPathOfNewFile = parentPathOfFileWithText + File.separator + nameOfNewFile + ".txt";
            // делаем из строки путь к новому файлу
            pathOfNewFile = Path.of(strPathOfNewFile);
            if (Files.notExists(pathOfNewFile)) {
                try {createNewFile = Files.createFile(pathOfNewFile);}
                catch (IOException e) {
                    System.out.println("При создании нового файла произошла ошибка");
                }
                choice = false;
            } else {
                System.out.println("Такой файл уже существует, введите название нового файла:");
            }
        }
        return createNewFile;
    }

    public static void encrypt(String strPath , int key) {
        // делаем из строки путь
        Path pathOfTextFile = Path.of(strPath);
        // проверяем существует ли такой файл (Файл с текстом для кодирования)
        if (pathOfTextFile.isAbsolute() && Files.exists(pathOfTextFile)) {
            // открываем доступ к файлам
            try (FileReader inputChar = new FileReader(strPath);
                 FileWriter outputChar = new FileWriter(createNewFile(pathOfTextFile).toString())) {
                // создаем буфер с размером 64kB
                char[] buffer = new char[65535];
                // проверяем остались ли еще не считанные символы в потоке
                while (inputChar.ready()) {
                    // создаем переменную хранящую количество прочитанных символов и записываем в буфер данные
                    int real = inputChar.read(buffer);

                    // записываем данные из буфера в файл для зашифрованного текста
                    outputChar.write(buffer, 0 , real);
                }
            } catch (FileNotFoundException exception) {
                System.out.println("Ошибка наличия файла в блоке шифрования FileNotFoundException");
            }
            catch (IOException exception) {
                System.out.println("Ошибка наличия файла в блоке шифрования IOException");
            }
        }
    }

}

