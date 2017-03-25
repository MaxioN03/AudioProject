package disk;

import java.util.*;

import static audio.SongConstants.repeatEntering;
import static disk.CDConstants.addingCD;
import static disk.CDConstants.allCD;
import static disk.CDConstants.noCD;

/**
 * Created by Егор on 20.03.17.
 */

//Класс для работы со списком CD
public class CDList {

    public static void showCDList(List<CD> cdList) {
        if (cdList.size() > 0) {
            int i = 1;
            System.out.println(allCD);
            for(CD cd: cdList){
                System.out.println(i + ". " + cd.toString());
                i++;
            }
            System.out.println("\n");
        } else {
            System.out.println(noCD);
        }
    }

    public static void addCDFromConsole(List<CD> cdList) {
        Scanner in = new Scanner(System.in);
        System.out.println(addingCD[0]);
        String name = in.nextLine();
        System.out.println(addingCD[1]);
        int capacity = 0;
        while (capacity == 0) {
            try {
                capacity = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(repeatEntering);
                in.next();
            }
        }

        cdList.add(new CD(capacity, name));
    }
}
