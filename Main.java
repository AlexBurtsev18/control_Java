import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.io.File;

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

                toys[tempId].nameToy = nameToy;
                toys[tempId].idToy = tempId;
                toys[tempId].weightToy = CheckPercent(tempPercent, remainder) / 10;

                remainder = remainder - tempPercent;
                tempId++;
            }
            System.out.println("В розыгрыше участвуют: ");

            for (int i = 1; i < tempId; i++) {
                System.out.println(toys[i].idToy + ". " + toys[i].nameToy + ". " + "Количество/Шанс выпадения: " + toys[i].weightToy);
            }

            int[] id = new int[10];
            int[] weight = new int[10];
            String[] name = new String[10];


            int count = 1;
            int j = 1;

            for(int i = 0; i < id.length; i++) {
                if (count <= toys[j].weightToy) {
                    id[i] = toys[j].idToy;
                    name[i] = toys[j].nameToy;
                    weight[i] = toys[j].weightToy;
                    count++;
                }
                else {
                    j++;
                    count = 1;
                    i--;
                }
            }

            try {
                File file = new File("res.txt");

                if(!file.exists()) {
                    file.createNewFile();
                }
                PrintWriter pw = new PrintWriter(file);

                Random random = new Random();
                int num;
                System.out.println("Начинается розыгрыш подарков! Введите количество раундов: ");
                int round = Integer.parseInt(scanner.nextLine());
                for(int i = 0; i < round; i++) {
                    System.out.printf("В %d-м раунде розыгрыша приз - ", i+1);
                    num = random.nextInt(10);
                    System.out.printf("%s", name[num]);
                    pw.printf("%s (с ID %d и вероятностью выпадения %d процентов)", name[num], id[num], weight[num] * 10);
                }
                pw.close();
                System.out.println("Результаты розыгрыша успешно сохранены");
            } catch (IOException e) {
                System.out.println("Error:" + e);
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
