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

    public List<Song> getSongsFromCD() {
        List<Song> songs = new LinkedList<Song>();

        for (Song song : songList) {
            songs.add(song);
        }
        return songs;
    }

    public long getLength() {
        int result = 0;
        for (Song song : songList) {
            result += song.getLengthLong();
        }
        return result;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (songList != null ? songList.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CD)) return false;
        if (!super.equals(o)) return false;

        CD cd = (CD) o;

        return songList != null ? songList.equals(cd.songList) : cd.songList == null;
    }

    @Override
    public String toString() {
        return "CD:" +
                "Название: " + getName() + ", кол-во аудиозаписей: " + songList.size();
    }

    public void addSong(Song song) {
        songList.add(song);
    }


}
