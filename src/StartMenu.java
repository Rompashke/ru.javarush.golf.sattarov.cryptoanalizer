import java.util.Scanner;

public class StartMenu {
    public static void startMenu() {
        System.out.println("Выберите режим:");
        System.out.println("1. Шифрование");
        System.out.println("2. Дешифровка");
        System.out.println("3. Криптоанализ (brute force)");
        System.out.println("4. Выход)");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            boolean value = true;
            while (value) {
                switch (scanner.nextInt()) {
                    case 1:
                        System.out.println("Введите путь к файлу:");
                        try () {

                        }
                        System.out.println("Введите ключ:");
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
