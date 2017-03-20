package audio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Егор on 17.03.17.
 */
public class Song extends Audio {

    private String author;
    private String genre;

    public Song(String author, String title, String genre, String format, String length, int bitrate) {
        super(title, format, length, bitrate);
        this.author = author;
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public long getLengthLong() {
        final int SECONDS_IN_MINUTE = 60;
        String[] lengthString = this.getLength().split(":");
        long result = Integer.parseInt(lengthString[0]) * SECONDS_IN_MINUTE + Integer.parseInt(lengthString[1]);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;
        if (!super.equals(o)) return false;

        Song song = (Song) o;

        if (!author.equals(song.author)) return false;
        return genre.equals(song.genre);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + genre.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return author + " - " + getTitle() + "(" + getLength() + ")";
    }

    public static String getLengthStr(long lengthLong) {
        final int SECONDS_IN_MINUTE = 60;
        long hours = lengthLong / SECONDS_IN_MINUTE;
        int minutes = (int) lengthLong % SECONDS_IN_MINUTE;
        String result = hours + ":";
        result+= minutes < 10 ? "0" + minutes :  minutes;
        return result;
    }
}
