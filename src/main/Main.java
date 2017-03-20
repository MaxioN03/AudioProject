package main;

import audio.Song;
import audio.comparatorsSong.*;
import disk.CD;

import java.util.*;

/**
 * Created by Егор on 18.03.17.
 */
public class Main {
    private List<Song> songListCommon = new LinkedList<Song>();
    private List<CD> cdListCommon = new LinkedList<CD>();
    private final int MAIN_MENU_ITEMS = 10;

    public static void main(String[] args) {

        Main main = new Main();
        while (true) {
            main.showMainMenu();
            main.doMainMenu(main.chooseMenu());
        }
    }

    public boolean isEmptySongListOrCDList() {
        if (songListCommon.isEmpty() || cdListCommon.isEmpty()) {
            return true;
        } else return false;
    }

    //Изменил меню - измени MAIN_MENU_ITEMS!
    //todo Многоуровневое меню для добавления
    public void showMainMenu() {
        System.out.println("Выберите пункт меню:\n" +
                "1. Добавить аудиозапись\n" +
                "2. Добавить CD\n" +
                "3. Вывод всех аудиозаписей\n" +
                "4. Вывод всех CD\n" +
                "5. Добавить аудиозапись на CD\n" +
                "6. Просмотреть CD\n" +
                "7. Сортировка аудиозаписей\n" +
                "8. Поиск по аудизаписям\n" +
                "9. Вывод продолжительности всех аудиозаписей\n" +
                "0. Выход\n");
    }

    public int chooseMenu() {
        Scanner in = new Scanner(System.in);
        int choise = -1;
        while (choise == -1) {
            try {
                choise = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Проверьте правильность ввода и повторите попытку");
                in.next();
            }
            if (choise < 0 || choise > MAIN_MENU_ITEMS) {
                System.out.println("Проверьте правильность ввода и повторите попытку");
                choise = -1;
            }
        }
        return choise;
    }

    public void doMainMenu(int choise) {
        switch (choise) {
            case 1:
                addSongInCommon();

                break;
            case 2:
                cdListCommon.add(CD.addCDFromConsole());
                break;
            case 3:
                showSongListCommon();
                break;
            case 4:
                showCDListCommon();
                break;
            case 5:
                addSongToCD();
                break;
            case 6:
                showCD();
                break;
            case 7:
                sortSong();
                break;
            case 8:
                System.out.println(searchSong());
                break;
            case 9:
                System.out.println(getCommonLengthLong());
                System.out.println(Song.getLengthStr(getCommonLengthLong()));
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Неверное значение");
                break;
        }
    }

    public void addSongInCommon() {
        Song song = Song.addSongFromConsole();
        int i = 0;
        Iterator it = songListCommon.iterator();
        while (it.hasNext()) {
            if (it.next().equals(song)) {
                i++;
            }
        }
        if (i == 0) {
            songListCommon.add(song);
            System.out.println("Аудиозапись успешно добавлена");
        } else {
            System.out.println("Ошибка! Такая аудиозапись уже существует");
        }
    }

    public void showSongListCommon() {
        if (songListCommon.size() > 0) {
            Iterator it = songListCommon.iterator();
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

    public void showCDListCommon() {
        if (cdListCommon.size() > 0) {
            Iterator it = cdListCommon.iterator();
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

    public void addSongToCD() {
        if (isEmptySongListOrCDList()) {
            System.out.println("Нету доступных CD или аудиозаписей");
        } else {
            //Выбираем CD
            System.out.println("Выберите CD для записи...");
            showCDListCommon();

            Scanner in = new Scanner(System.in);
            int choiseCD = getCDChoise();

            //Выбираем аудиозапись
            System.out.println("Выберите аудиозапись для записи...");
            showSongListCommon();
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
        showCDListCommon();

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
                sortBy(songListCommon, new SortedByAuthor());
                break;
            case 2:
                sortBy(songListCommon, new SortedByTitle());
                break;
            case 3:
                sortBy(songListCommon, new SortedByLength());
                break;
            case 4:
                sortBy(songListCommon, new SortedByGenre());
            case 0:
                break;
        }


    }

    private void sortBy(List<Song> songListCommon, Comparator comp) {
        Collections.sort(songListCommon, comp);
    }

    //todo рефактор switch и поиск по продолжительности
    public List<Song> searchSong(){
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

        switch (choise) {
            case 1:
                System.out.println("Введите поисковый запрос... ");
                query = in.next();
                for(Song song: songListCommon){
                    if(song.getAuthor().toLowerCase().contains(query.toLowerCase())){
                        result.add(song);
                    }
                }
                break;
            case 2:
                //название
                System.out.println("Введите поисковый запрос... ");
                query = in.next();
                query = in.nextLine();
                for(Song song: songListCommon){
                    if(song.getTitle().toLowerCase().contains(query.toLowerCase())){
                        result.add(song);
                    }
                }
                break;
            case 3:
                //жанр
                System.out.println("Введите поисковый запрос... ");
                query = in.next();
                query = in.nextLine();
                for(Song song: songListCommon){
                    if(song.getGenre().toLowerCase().contains(query.toLowerCase())){
                        result.add(song);
                    }
                }
            case 0:
                break;
        }
        return result;
    }

    //todo Сделать выбор конкретного CD
    public long getCommonLengthLong(){
        long result=0;
        for(Song song: songListCommon){
            result+=song.getLengthLong();
        }
        return result;
    }
}
