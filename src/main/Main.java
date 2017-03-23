package main;

import audio.Song;
import audio.SongList;
import audio.comparatorsSong.SortedByAuthor;
import audio.comparatorsSong.SortedByGenre;
import audio.comparatorsSong.SortedByLength;
import audio.comparatorsSong.SortedByTitle;
import disk.CD;
import disk.CDList;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static main.MenuChoices.chooseNumber;
import static main.MenuChoices.chooseNumberAfterZero;
import static main.MenuPoints.MAIN_MENU_ITEMS;
import static main.MenuPoints.showMenuSort;

/**
 * Created by Егор on 18.03.17.
 */
public class Main {
    private LinkedList<Song> songListCommon = new LinkedList<Song>();
    private LinkedList<CD> cdListCommon = new LinkedList<CD>();

    public static void main(String[] args) {

        //runner
        Main main = new Main();
        while (true) {
            MenuPoints.showMenuMain();
            main.doMainMenu(chooseNumber(0, MAIN_MENU_ITEMS));
        }
    }

    public boolean isEmptySongListOrCDList() {
        if (songListCommon.isEmpty() || cdListCommon.isEmpty()) {
            return true;
        } else return false;
    }

    public void doMainMenu(int choise) {
        switch (choise) {
            case 1:
                addSongMenu();
                break;
            case 2:
                CDList.addCDFromConsole(cdListCommon);
                break;
            case 3:
                doMenuShow();
                break;
            case 4:
                addSongToCD();
                break;
            case 5:
                sortSong();
                break;
            case 6:
                System.out.println(searchSong());
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Неверное значение");
                break;
        }
    }

    private void doMenuShow() {
        MenuPoints.showMenuShow();
        switch (chooseNumber(0, 4)) {
            case 1:
                SongList.showSongListCommon(songListCommon);
                break;
            case 2:
                CDList.showCDList(cdListCommon);
                break;
            case 3:
                showCD();
                break;
            case 4:
                showCommonLength();
                break;
            case 0:
                break;

        }
    }

    private void addSongMenu() {
        MenuPoints.showMenuAddSong();
        switch (chooseNumber(0, 3)) {
            case 1:
                SongList.addSongFromConsole(songListCommon);
                break;
            case 2:
                SongList.addSongFromTXT(songListCommon, "resources/txt/songs.txt");
                break;
            case 3:
                SongList.addSongFromXML(songListCommon, "resources/xml/songs.xml");
                break;
            case 0:
                break;
        }
    }

    //Запись Song на CD
    public void addSongToCD() {
        if (isEmptySongListOrCDList()) {
            System.out.println("Нету доступных CD или аудиозаписей");
        } else {
            //Выбираем CD
            System.out.println("Выберите CD для записи...");
            CDList.showCDList(cdListCommon);
            int choiseCD = chooseNumber(1, cdListCommon.size()) - 1;

            //Выбираем аудиозапись
            System.out.println("Выберите аудиозапись для записи...");
            SongList.showSongListCommon(songListCommon);
            int choiseSong = chooseNumber(1, songListCommon.size()) - 1;


            cdListCommon.get(choiseCD).addSong(songListCommon.get(choiseSong));
            System.out.println("Аудиозапись " + songListCommon.get(choiseSong).getTitle() + " успешно добавлена на диск " +
                    cdListCommon.get(choiseCD).getName());
        }
    }

    //Вывод конкретного CD
    public void showCD() {
        System.out.println("Выберите CD для просмотра:");
        CDList.showCDList(cdListCommon);

        int choiseCD = chooseNumber(1, cdListCommon.size()) - 1;

        System.out.println("Список аудиозаписей для CD \"" + cdListCommon.get(choiseCD).getName() + "\":\n");
        System.out.println(cdListCommon.get(choiseCD).getSongsFromCD());
    }

    public void sortSong() {
        showMenuSort();

        switch (chooseNumber(0, 4)) {
            case 1:
                Collections.sort(songListCommon, new SortedByAuthor());
                break;
            case 2:
                Collections.sort(songListCommon, new SortedByTitle());
                break;
            case 3:
                Collections.sort(songListCommon, new SortedByLength());
                break;
            case 4:
                Collections.sort(songListCommon, new SortedByGenre());
            case 0:
                break;
        }


    }

    public List<Song> searchSong() {
        LinkedList<Song> result = new LinkedList<Song>();
        MenuPoints.showMenuSearch();
        Scanner in = new Scanner(System.in);
        String query;
        String queryStr = "Введите поисковый запрос... ";
        switch (chooseNumber(0, 3)) {
            case 1:
                System.out.println(queryStr);
                query = in.next();
                for (Song song : songListCommon) {
                    if (song.getAuthor().toLowerCase().contains(query.toLowerCase())) {
                        result.add(song);
                    }
                }
                break;
            case 2:

                System.out.println(queryStr);
                query = in.next();
                for (Song song : songListCommon) {
                    if (song.getTitle().toLowerCase().contains(query.toLowerCase())) {
                        result.add(song);
                    }
                }
                break;
            case 3:
                //Продолжительность
                System.out.println("Введите минимальную продолжительность... ");
                int lengthMin = chooseNumberAfterZero();
                System.out.println("Введите максмальную продолжительность... ");
                int lengthMax = chooseNumberAfterZero();
                for (Song song : songListCommon) {
                    if (song.getLengthLong() >= lengthMin && song.getLengthLong() <= lengthMax) {
                        result.add(song);
                    }
                }
                break;
            case 0:
                break;
        }
        return result;
    }

    //Вывод общей продолжительности и для каждого диска отдельно
    public void showCommonLength() {
        //Общая продолжительность
        System.out.println("Общая продолжительность на всех CD: " + Song.getLengthStr(SongList.getCommonLengthLong(songListCommon)));
        //Вывод для каждого диска
        System.out.println("Продолжительность на каждом диске: ");
        for (CD cd : cdListCommon) {
            System.out.println(cd.toString() + " - " + Song.getLengthStr(cd.getLength()));
        }
    }
}
