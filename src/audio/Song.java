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
    public static void main(String[] args) {
        addSongFromConsole();
    }

    private String author;
    private String genre;

    public Song() {
        super();
        this.author = "author";
        this.genre = "genre";
    }

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

    public long getLengthLong(){
        String[] lengthString = this.getLength().split(":");
        long result = Integer.parseInt(lengthString[0])*60 + Integer.parseInt(lengthString[1]);
        return result;
    }

    //todo public String getLengthStr(){}
    public static String getLengthStr(long lengthLong){
        long hours = lengthLong/60;
        int minutes = (int)lengthLong%60;
        String result =hours+":";
        if(minutes<10){
            result+="0"+minutes;
        }
        else{
            result+=minutes;
        }
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

    public static Song addSongFromConsole() {
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
        while(!matcher.matches()){
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
        //in.close();
        return song;
    }
    //todo добавление из файла, xml, json

}
