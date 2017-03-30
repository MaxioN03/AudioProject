package project;

import java.util.InputMismatchException;
import java.util.Scanner;

import static audio.SongConstants.repeatEntering;

/**
 * Created by Егор on 23.03.17.
 */
//Утилитный класс для выбора чисел
public class MenuChoices {

    public static int chooseNumber(int min, int max) {
        Scanner in = new Scanner(System.in);
        int choise = min - 1;

        while (choise == min - 1) {
            try {
                choise = in.nextInt();
                if (choise < min || choise > max) {
                    System.out.println(repeatEntering);
                    choise = min - 1;
                }
            } catch (InputMismatchException e) {
                System.out.println(repeatEntering);
                in.next();
            }

        }
        return choise;
    }

    public static int chooseNumberAfterZero() {
        Scanner in = new Scanner(System.in);

        int result = -1;
        while (result < 0) {
            try {
                result = in.nextInt();
                if (result < 0) {
                    System.out.println(repeatEntering);
                }
            } catch (InputMismatchException e) {
                System.out.println(repeatEntering);
                in.next();
            }
        }
        return result;
    }
}
