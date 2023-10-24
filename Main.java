import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("На данный момент база пуста. Чтобы начать заполнение. введите 'y': ");

        String confirm = scanner.nextLine();
        if (confirm.equals('y')) {
            int tempPercent;
            int tempId = 1;
            int remainder = 100;
            Toy[] toys = new Toy[10];

            while (remainder > 0) {
                toys[tempId] = new Toy();
                System.out.printf("Введите название %d-й игрушки: ", tempId);
                String nameToy = scanner.nextLine();
                System.out.print("Введите вероятность выпадения игрушки (от 1 до 100): ");
                tempPercent = Integer.parseInt(scanner.nextLine());

                toys[tempId].name = nameToy;
                toys[tempId].id = tempId;
                toys[tempId].weight = CheckPercent(tempPercent, remainder) / 10;

            }
        }
    }
    static int CheckPercent(int percent, int remPercent){
        if ((remPercent - percent) < 0) {
            int toMach = remPercent - percent;
            int newPercent = percent + toMach;
            return newPercent;
        }
        return percent;
    }
}
