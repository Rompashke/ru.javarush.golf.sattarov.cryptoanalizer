import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Cryptographer {
    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};


    protected static Path createNewFile(Path path) { // вводим путь к файлу в котором
        // лежит файл с незакодированной информацией.
        boolean choice = true;
        Path parentPathOfFileWithText = Paths.get(path.getParent().toUri()); //берем путь до файла
        Path pathOfNewFile;
        Path createNewFile = null;
        while (choice) {
            System.out.println("Введите название нового файла без расширения:");
            Scanner scanner = new Scanner(System.in);
            String nameOfNewFile = scanner.nextLine(); // имя нового файла
            // создаем новый путь к файлу
            String strPathOfNewFile = parentPathOfFileWithText + File.separator + nameOfNewFile + ".txt";
            // делаем из строки путь к новому файлу
            pathOfNewFile = Path.of(strPathOfNewFile);
            if (Files.notExists(pathOfNewFile)) {
                try {
                    createNewFile = Files.createFile(pathOfNewFile);
                } catch (IOException e) {
                    System.out.println("При создании нового файла произошла ошибка");
                }
                choice = false;
            } else {
                System.out.println("Такой файл уже существует, введите название нового файла:");
            }
        }
        return createNewFile;
    }

    public static void encrypt(String strPath, int key) {
        int keyNumber = key % ALPHABET.length;
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
                    // создаем массив для зашифрованного текста
                    char[] encryptBuffer = new char[65535];
                    // процесс зашировки текста в правую сторону
                    for (int i = 0; i < real; i++) {
                        // необходимо сделать проверку есть ли в алфавите такой символ.
                        boolean symbolBelongsAlphabet = false;
                        int indexOfSymbolInAlphabet = -1;
                        for (int j = 0; j < ALPHABET.length; j++) {
                            if (Character.toString(buffer[i]).equalsIgnoreCase(Character.toString(ALPHABET[j]))) {
                                symbolBelongsAlphabet = true;
                                indexOfSymbolInAlphabet = j;
                            }
                        }
                        if (symbolBelongsAlphabet) {
                            encryptBuffer[i] = ALPHABET[(indexOfSymbolInAlphabet + keyNumber) % ALPHABET.length];
                        } else {
                            encryptBuffer[i] = buffer[i];
                        }
                    }
                    // записываем данные из буфера в файл для зашифрованного текста
                    outputChar.write(encryptBuffer, 0, real);
                    System.out.println("Сообщение зашифровано!");
                }
            } catch (FileNotFoundException exception) {
                System.out.println("Ошибка наличия файла в блоке шифрования FileNotFoundException");
            } catch (IOException exception) {
                System.out.println("Ошибка наличия файла в блоке шифрования IOException");
            }
        }
    }

}

