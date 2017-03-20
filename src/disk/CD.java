package disk;

import audio.Song;

import java.util.*;

import static audio.Song.addSongFromConsole;

/**
 * Created by Егор on 17.03.17.
 */
public class CD extends Disk {
    public static void main(String[] args) {
        Song song1 = new Song();
        Song song2 = new Song("MaxioN", "Deadline", "Electronic", "mp3", "3:20", 256);

        System.out.println(song1.toString());
        System.out.println(song2.toString());

        CD cd = new CD("Моя подборочка", song1);
        System.out.println(cd.toString());
        cd.addSong(song1);
        cd.addSong(song2);
        System.out.println(cd.toString());
    }

    List<Song> songList = new LinkedList<Song>();

    public CD() {
        this.name = "CD name";
        this.capacity = 0;
        this.songList = new LinkedList<Song>();

    }

    public CD(String name, Song song) {
        this.name = name;
        this.songList = new LinkedList<Song>();
        this.songList.add(song);
    }

    public CD(int capacity, String name) {
        super(capacity, name);
    }

    public CD(int capacity, String name, List<Song> songList) {
        super(capacity, name);
        this.songList = songList;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    @Override
    public String toString() {
        return "CD:" +
                "Название: " + getName() + ", кол-во аудиозаписей: " + songList.size();
    }

    public void addSong(Song song) {
        songList.add(song);
    }
    public LinkedList<Song> getSongsFromCD(){
        LinkedList<Song> songs = new LinkedList<Song>();

        Iterator it = songList.iterator();
        while (it.hasNext()) {
            songs.add((Song)it.next());
        }
        return songs;
    }

    public static CD addCDFromConsole() {
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

        CD cd = new CD(capacity, name);

        return cd;
    }
}
