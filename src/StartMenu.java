import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class StartMenu {
    public static void startMenu() {
        boolean value = true;
        while (value) {
        System.out.println("\nВыберите режим:");
        System.out.println("1. Шифрование");
        System.out.println("2. Дешифровка");
        System.out.println("3. Криптоанализ (brute force)");
        System.out.println("4. Выход");
        System.out.println("Введите число:");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
                switch (scanner.nextInt()) {
                    case 1:
                        boolean condition1 = true;
                        String pathOfText = null;
                        boolean condition2 = true;
                        int keyOfEncrypt = -1;
                        // обработка адреса файла с текстом. (правильный ли адрес и существует ли файл)
                        while (condition1) {
                            System.out.println("Введите путь к файлу:");
                            Scanner scanner11 = new Scanner(System.in);
                            pathOfText = scanner11.nextLine();
                            if (Path.of(pathOfText).isAbsolute() && Files.exists(Path.of(pathOfText))) {
                                condition1 = false;
                            } else {
                                System.out.println("Путь к файлу неверный либо такого файла не существует!");
                            }
                        }
                        // условие обработки вводимого ключа (интовый ключ в диапазоне от 0 до макс. инт)
                        while (condition2) {
                            System.out.println("Введите ключ:");
                            Scanner scanner12 = new Scanner(System.in);
                            keyOfEncrypt = scanner12.nextInt();
                            if (keyOfEncrypt > 0 && keyOfEncrypt < Integer.MAX_VALUE) {
                                condition2 = false;
                            } else {
                                System.out.println("Данное значение не подходит для ключа!");
                            }
                        }
                        // Обращение к методу шифровальщика
                        Cryptographer.encrypt(pathOfText, keyOfEncrypt);
                        break;
                    case 2:
                        boolean condition3 = true;
                        String pathOfEncryptText = null;
                        boolean condition4 = true;
                        int keyOfDecrypt = -1;
                        // обработка адреса файла с текстом. (правильный ли адрес и существует ли файл)
                        while (condition3) {
                            System.out.println("Введите путь к файлу:");
                            Scanner scanner11 = new Scanner(System.in);
                            pathOfEncryptText = scanner11.nextLine();
                            if (Path.of(pathOfEncryptText).isAbsolute() && Files.exists(Path.of(pathOfEncryptText))) {
                                condition3 = false;
                            } else {
                                System.out.println("Путь к файлу неверный либо такого файла не существует!");
                            }
                        }
                        // условие обработки вводимого ключа (интовый ключ в диапазоне от 0 до макс. инт)
                        while (condition4) {
                            System.out.println("Введите ключ:");
                            Scanner scanner12 = new Scanner(System.in);
                            keyOfDecrypt = scanner12.nextInt();
                            if (keyOfDecrypt > 0 && keyOfDecrypt < Integer.MAX_VALUE) {
                                condition4 = false;
                            } else {
                                System.out.println("Данное значение не подходит для ключа!");
                            }
                        }
                        // Обращение к методу шифровальщика
                        Cryptographer.decrypt(pathOfEncryptText, keyOfDecrypt);
                        break;
                    case 3:
                        // ссылка на метод криптоанализатора;
                        break;
                    case 4:
                        value = false;
                        break;
                    default:
                        System.out.println("Необходимо ввести число от 1 до 4");
                }
            } else {
            System.out.println("Необходимо ввести число от 1 до 4");
        }
        }
    }
}
