package audio;

import java.util.Scanner;
import java.util.*;

/**
 * Created by Egor Kutz on 17.03.17.
 */
public class Audio {
    private String title;
    private String format;
    private String length;
    private int bitrate;

    public Audio(String title, String format, String length, int bitrate){
        this.title = title;
        this. format = format;
        this.length = length;
        this.bitrate = bitrate;
    }

    public String getTitle() {
        return title;
    }

    public String getFormat() {
        return format;
    }

    public String getLength() {
        return length;
    }

    public int getBitrate() {
        return bitrate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public void setBitrate(int bitrate) {
        try{
            this.bitrate = bitrate;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Audio audio = (Audio) o;

        if (bitrate != audio.bitrate) return false;
        if (!title.equals(audio.title)) return false;
        if (!format.equals(audio.format)) return false;
        return length.equals(audio.length);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + format.hashCode();
        result = 31 * result + length.hashCode();
        result = 31 * result + bitrate;
        return result;
    }

    @Override
    public String toString() {
        return "Audio{" +
                "title='" + title + '\'' +
                ", format='" + format + '\'' +
                ", length='" + length + '\'' +
                ", bitrate=" + bitrate +
                '}';
    }

    public static Audio addAudioFromConsole(){
        Scanner in = new Scanner(System.in);
        System.out.println("Введите название... ");
        String title = in.nextLine();
        System.out.println("Введите формат... ");
        String format = in.nextLine();
        System.out.println("Введите продолжительность... ");
        String length = in.nextLine();
        System.out.println("Введите битрейт... ");
        int bitrate = 0;
        while(bitrate == 0 ){
            try
            {
                bitrate= in.nextInt();
            }
            catch (InputMismatchException e)
            {
                System.out.println("Проверьте правильность ввода и повторите попытку");
                in.next();
            }
        }

        Audio audio = new Audio(title,format,length,bitrate);
        return audio;
    }
}



