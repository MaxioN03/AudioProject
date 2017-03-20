package audio;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Егор on 20.03.17.
 */
public class SongList {
    public static void showSongListCommon(LinkedList<Song> songList) {
        if (songList.size() > 0) {
            Iterator it = songList.iterator();
            int i = 1;
            System.out.println("Список всех аудиозаписей:");
            while (it.hasNext()) {
                System.out.println(i + ". " + it.next().toString());
                i++;
            }
        } else {
            System.out.println("Аудиозаписей нет\n");
        }
    }

    public static long getCommonLengthLong(LinkedList<Song> songList) {
        long result = 0;
        for (Song song : songList) {
            result += song.getLengthLong();
        }
        return result;
    }

    public static void addSongFromConsole(LinkedList<Song> songList) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите автора песни... ");
        String author = in.nextLine();
        System.out.println("Введите название... ");
        String title = in.nextLine();
        System.out.println("Введите жанр... ");
        String genre = in.nextLine();
        System.out.println("Введите формат... ");
        String format = in.nextLine();
        //todo Проверка на валидность продолжительности по формату "мм:сс"
        System.out.println("Введите продолжительность...\n" +
                "\tИспользуйте формат \"минуты:секунды\" ");
        Pattern patterLength = Pattern.compile("^([0-9]{1,5}):[0-5][0-9]$");
        String length = in.nextLine();
        Matcher matcher = patterLength.matcher(length);
        while (!matcher.matches()) {
            System.out.println("Неверный формат продолжительности!\n" +
                    "\tИспользуйте формат \"минуты:секунды\"");
            length = in.nextLine();
            matcher = patterLength.matcher(length);
        }

        System.out.println("Введите битрейт... ");
        int bitrate = 0;
        while (bitrate == 0) {
            try {
                bitrate = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Проверьте правильность ввода битрейта и повторите попытку");
                in.next();
            }
        }

        Song song = new Song(author, title, genre, format, length, bitrate);
        int i = 0;
        Iterator it = songList.iterator();
        while (it.hasNext()) {
            if (it.next().equals(song)) {
                i++;
            }
        }
        if (i == 0) {
            songList.add(song);
            System.out.println("Аудиозапись успешно добавлена");
        } else {
            System.out.println("Ошибка! Такая аудиозапись уже существует");
        }
    }
    //todo добавление из файла, xml, json
}
