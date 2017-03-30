package audio.comparatorsSong;

import audio.Song;

import java.util.Comparator;

/**
 * Created by Егор on 19.03.17.
 */
public class SortedByTitle implements Comparator<Song> {

    @Override
    public int compare(Song o1, Song o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }

}
