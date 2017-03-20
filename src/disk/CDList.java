package disk;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Егор on 20.03.17.
 */
public class CDList {
    public static void showCDList(LinkedList<CD> cdList) {
        if (cdList.size() > 0) {
            Iterator it = cdList.iterator();
            int i = 1;
            System.out.println("Список всех CD:");
            while (it.hasNext()) {
                System.out.println(i + ". " + it.next().toString());
                i++;
            }
            System.out.println("\n");
        } else {
            System.out.println("CD нет\n");
        }
    }

    public static void addCDFromConsole(LinkedList<CD> cdList) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите навзание CD... ");
        String name = in.nextLine();
        System.out.println("Введите объём памяти (Мб)... ");
        int capacity = 0;
        while (capacity == 0) {
            try {
                capacity = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Проверьте правильность ввода объёма памяти и повторите попытку");
                in.next();
            }
        }

        cdList.add(new CD(capacity, name));


    }
}
