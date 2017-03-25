package main;

import audio.Song;
import audio.SongList;
import disk.CD;
import disk.CDList;

import java.util.LinkedList;
import java.util.List;

import static main.MenuChoices.chooseNumber;
import static main.MenuPoints.MAIN_MENU_ITEMS;

/**
 * Created by Егор on 18.03.17.
 */
public class Main {
    private List<Song> songListCommon = new LinkedList<Song>();
    private List<CD> cdListCommon = new LinkedList<CD>();
    private static boolean exit = false;

    public static void main(String[] args) {
        Main main = new Main();
        while (!exit) {
            MenuPoints.showMenuMain();
            main.doMainMenu(chooseNumber(0, MAIN_MENU_ITEMS));
        }
    }

    public void doMainMenu(int choise) {
        switch (choise) {
            case 1:
                MenuExecuter.addSongMenu(songListCommon);
                break;
            case 2:
                CDList.addCDFromConsole(cdListCommon);
                break;
            case 3:
                doMenuShow();
                break;
            case 4:
                MenuExecuter.addSongToCD(songListCommon, cdListCommon);
                break;
            case 5:
                MenuExecuter.sortSong(songListCommon);
                break;
            case 6:
                System.out.println(MenuExecuter.searchSong(songListCommon));
                break;
            case 0:
                exit=true;
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
                MenuExecuter.showCD(cdListCommon);
                break;
            case 4:
                MenuExecuter.showCommonLength(cdListCommon,songListCommon);
                break;
            case 0:
                break;

        }
    }












}
