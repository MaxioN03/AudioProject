package disk;

import audio.Song;

import java.util.*;


/**
 * Created by Егор on 17.03.17.
 */
public class CD extends Disk {

    List<Song> songList = new LinkedList<Song>();

    public CD(int capacity, String name) {
        super(capacity, name);
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

    public LinkedList<Song> getSongsFromCD() {
        LinkedList<Song> songs = new LinkedList<Song>();

        Iterator it = songList.iterator();
        while (it.hasNext()) {
            songs.add((Song) it.next());
        }
        return songs;
    }

    public long getLength(){
        int result = 0;
        for(Song song: songList){
            result+=song.getLengthLong();
        }
        return result;
    }
}
