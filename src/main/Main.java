package main;

import audio.Song;
import audio.SongList;
import audio.comparatorsSong.*;
import disk.CD;
import disk.CDList;

import java.util.*;

/**
 * Created by Егор on 18.03.17.
 */
public class Main {
    private LinkedList<Song> songListCommon = new LinkedList<Song>();
    private LinkedList<CD> cdListCommon = new LinkedList<CD>();


    public static void main(String[] args) {
        final int MAIN_MENU_ITEMS = 7;
        //runner
        Main main = new Main();
        while (true) {
            main.showMainMenu();
            main.doMainMenu(main.chooseMenu(MAIN_MENU_ITEMS));
        }
    }

    public boolean isEmptySongListOrCDList() {
        if (songListCommon.isEmpty() || cdListCommon.isEmpty()) {
            return true;
        } else return false;
    }

    //Изменил меню - измени MAIN_MENU_ITEMS!
    public void showMainMenu() {
        System.out.println("Выберите пункт меню:\n" +
                "1. Добавить аудиозапись\n" +
                "2. Добавить CD\n" +
                "3. Просмотреть...\n" +
                "4. Записать аудиозапись на CD\n" +
                "5. Сортировка аудиозаписей\n" +
                "6. Поиск по аудизаписям\n" +
                "0. Выход\n");
    }

    public int chooseMenu(int max) {
        Scanner in = new Scanner(System.in);
        int choise = -1;
        while (choise == -1) {
            try {
                choise = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Проверьте правильность ввода и повторите попытку");
                in.next();
            }
            if (choise < 0 || choise > max) {
                System.out.println("Проверьте правильность ввода и повторите попытку");
                choise = -1;
            }
        }
        return choise;
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
                showMenu();
                break;
            case 4:
                addSongToCD();
                break;
            case 5:
                sortSong();
                break;
            case 6:
                searchSong();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Неверное значение");
                break;
        }
    }

    private void showMenu() {
        System.out.println("Выберите, что надо просмотреть:\n" +
                "1.Все аудиозаписи\n" +
                "2.Все CD\n" +
                "3.Конкретный CD\n" +
                "4.Продолжительность аудио" +
                "0.Выход\n");
        switch (chooseMenu(4)) {
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
                showCommonLength();;
                break;
            case 0:
                break;

        }
    }

    private void addSongMenu() {
        System.out.println("Выберите способ добавления:\n" +
                "1.Через консоль\n" +
                "2.Чтение из текстовго файла\n" +
                "3.Чтение из XML-файла\n" +
                "0.Назад\n");
        switch (chooseMenu(3)) {
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

    public void addSongToCD() {
        if (isEmptySongListOrCDList()) {
            System.out.println("Нету доступных CD или аудиозаписей");
        } else {
            //Выбираем CD
            System.out.println("Выберите CD для записи...");
            CDList.showCDList(cdListCommon);

            Scanner in = new Scanner(System.in);
            int choiseCD = getCDChoise();

            //Выбираем аудиозапись
            System.out.println("Выберите аудиозапись для записи...");
            SongList.showSongListCommon(songListCommon);
            int choiseSong = 0;
            while (choiseSong == 0) {
                try {
                    choiseSong = in.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Проверьте правильность ввода и повторите попытку");
                    in.next();
                }
                if (choiseSong < 1 || choiseSong > songListCommon.size()) {
                    System.out.println("Проверьте правильность ввода и повторите попытку");
                    choiseSong = 0;
                }
            }
            choiseSong--;

            cdListCommon.get(choiseCD).addSong(songListCommon.get(choiseSong));
            System.out.println("Аудиозапись " + songListCommon.get(choiseSong).getTitle() + " успешно добавлена на диск " +
                    cdListCommon.get(choiseCD).getName());

        }
    }

    public void showCD() {
        System.out.println("Выберите CD для просмотра:");
        CDList.showCDList(cdListCommon);

        Scanner in = new Scanner(System.in);
        int choiseCD = getCDChoise();

        System.out.println("Список аудиозаписей для CD \"" + cdListCommon.get(choiseCD).getName() + "\":\n");
        System.out.println(cdListCommon.get(choiseCD).getSongsFromCD());
    }

    public int getCDChoise() {
        Scanner in = new Scanner(System.in);
        int choiseCD = 0;
        while (choiseCD == 0) {
            try {
                choiseCD = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Проверьте правильность ввода и повторите попытку");
                in.next();
            }
            if (choiseCD < 1 || choiseCD > cdListCommon.size()) {
                System.out.println("Проверьте правильность ввода и повторите попытку");
                choiseCD = 0;
            }
        }
        choiseCD--;
        return choiseCD;
    }

    public void sortSong() {
        System.out.println("Выберите характеристику сортировки:\n" +
                "1. Автор песни\n" +
                "2. Название песни\n" +
                "3. Продолжительность песни\n" +
                "4. Жанр\n" +
                "0. Назад\n");
        Scanner in = new Scanner(System.in);
        int choise = -1;
        while (choise == -1) {
            try {
                choise = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Проверьте правильность ввода и повторите попытку");
                in.next();
            }
            if (choise < 0 || choise > 4) {
                System.out.println("Проверьте правильность ввода и повторите попытку");
                choise = -1;
            }
        }
        switch (choise) {
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

    //todo ввод продолжительности как мм:сс
    public List<Song> searchSong() {
        LinkedList<Song> result = new LinkedList<Song>();
        System.out.println("Выберите характеристику поиска:\n" +
                "1. Автор песни\n" +
                "2. Название песни\n" +
                "3. Продолжительность песни\n" +
                "0. Назад\n");
        Scanner in = new Scanner(System.in);
        int choise = -1;
        while (choise == -1) {
            try {
                choise = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Проверьте правильность ввода и повторите попытку");
                in.next();
            }
            if (choise < 0 || choise > 3) {
                System.out.println("Проверьте правильность ввода и повторите попытку");
                choise = -1;
            }
        }

        String query;
        String queryStr = "Введите поисковый запрос... ";
        switch (choise) {
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
                query = in.nextLine();
                for (Song song : songListCommon) {
                    if (song.getTitle().toLowerCase().contains(query.toLowerCase())) {
                        result.add(song);
                    }
                }
                break;
            case 3:
                //Продолжительность
                System.out.println("Введите минимальную продолжительность... ");
                int lengthMin = -1;
                while (lengthMin == -1) {
                    try {
                        lengthMin = in.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Проверьте правильность ввода минимальной продолжительности и повторите попытку");
                        in.next();
                    }
                }
                System.out.println("Введите максмальную продолжительность... ");
                int lengthMax = -1;
                while (lengthMax == -1) {
                    try {
                        lengthMax = in.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Проверьте правильность ввода максимальной продолжительности и повторите попытку");
                        in.next();
                    }
                }
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
