import java.util.Scanner;

public class StartMenu {
    public static void startMenu() {
        boolean value = true;
        while (value) {
        System.out.println("\nВыберите режим:");
        System.out.println("1. Шифрование");
        System.out.println("2. Дешифровка");
        System.out.println("3. Криптоанализ (brute force)");
        System.out.println("4. Выход)");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
                switch (scanner.nextInt()) {
                    case 1:
                        Scanner scanner11 = new Scanner(System.in);
                        System.out.println("Введите путь к файлу:");
                        String pathOfText = scanner11.next();
                        System.out.println("Введите ключ:");
                        Scanner scanner12 = new Scanner(System.in);
                        int keyOfEncrypt = scanner12.nextInt();
                        Cryptographer.encrypt(pathOfText, keyOfEncrypt);
                        break;
                    case 2:
                        // ссылка на метод дешифратора;
                        break;
                    case 3:
                        // ссылка на метод криптоанализатора;
                        break;
                    case 4:
                        value = false;
                        break;
                }
            }
        }
    }
}
