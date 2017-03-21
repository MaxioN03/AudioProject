package audio;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Егор on 20.03.17.
 */
public class SongList {
    public static void showSongListCommon(LinkedList<Song> songList) {
        if (songList.size() > 0) {
            Iterator it = songList.iterator();
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

    public static long getCommonLengthLong(LinkedList<Song> songList) {
        long result = 0;
        for (Song song : songList) {
            result += song.getLengthLong();
        }
        return result;
    }

    public static void addSongFromConsole(LinkedList<Song> songList) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите автора песни... ");
        String author = in.nextLine();
        System.out.println("Введите название... ");
        String title = in.nextLine();
        System.out.println("Введите жанр... ");
        String genre = in.nextLine();
        System.out.println("Введите формат... ");
        String format = in.nextLine();
        System.out.println("Введите продолжительность...\n" +
                "\tИспользуйте формат \"минуты:секунды\" ");
        Pattern patterLength = Pattern.compile("^([0-9]{1,5}):[0-5][0-9]$");
        String length = in.nextLine();
        Matcher matcher = patterLength.matcher(length);
        while (!matcher.matches()) {
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
        int i = 0;
        Iterator it = songList.iterator();
        while (it.hasNext()) {
            if (it.next().equals(song)) {
                i++;
            }
        }
        if (i == 0) {
            songList.add(song);
            System.out.println("Аудиозапись "+ song.toString() +" успешно добавлена");
        } else {
            System.out.println("Ошибка! Аудиозапись "+ song.toString() +" уже существует");
        }
    }

    //todo добавление из json

    public static void addSongFromTXT(LinkedList<Song> songList, String path){

        try {
            List<String> lines = Files.readAllLines(Paths.get(path), Charset.defaultCharset());

            for(String str:lines){
                String[] parseResult = str.split(";");
                String author =  parseResult[0];
                String title =  parseResult[1];
                String genre =  parseResult[2];
                String format =  parseResult[3];
                String length =  parseResult[4];
                int bitrate =  Integer.parseInt(parseResult[5]);

                Song song = new Song(author, title, genre, format, length, bitrate);
                int i = 0;
                Iterator it = songList.iterator();
                while (it.hasNext()) {
                    if (it.next().equals(song)) {
                        i++;
                    }
                }
                if (i == 0) {
                    songList.add(song);
                    System.out.println("Аудиозапись "+ song.toString() +" успешно добавлена");
                } else {
                    System.out.println("Ошибка! Аудиозапись "+ song.toString() +" уже существует");
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения из файла");
        }


    }

    public static void addSongFromXML(LinkedList<Song> songList, String path){
        final File xmlFile = new File(System.getProperty("user.dir")
                + File.separator + path);
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = null;
            db = dbf.newDocumentBuilder();
            Document doc = db.parse(xmlFile);

            //Нормализация
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("song");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    Element element = (Element) node;
                    String author =  element.getElementsByTagName("author").item(0).getTextContent();
                    String title =  element.getElementsByTagName("title").item(0).getTextContent();
                    String genre =  element.getElementsByTagName("genre").item(0).getTextContent();
                    String format = element.getElementsByTagName("format").item(0).getTextContent();
                    String length =  element.getElementsByTagName("length").item(0).getTextContent();
                    int bitrate =  Integer.parseInt(element.getElementsByTagName("bitrate").item(0).getTextContent());

                    Song song = new Song(author, title, genre, format, length, bitrate);
                    int j = 0;
                    Iterator it = songList.iterator();
                    while (it.hasNext()) {
                        if (it.next().equals(song)) {
                            j++;
                        }
                    }
                    if (j == 0) {
                        songList.add(song);
                        System.out.println("Аудиозапись "+ song.toString() +" успешно добавлена");
                    } else {
                        System.out.println("Ошибка! Аудиозапись "+ song.toString() +" уже существует");
                    }
                }
            }

        } catch (ParserConfigurationException e) {
            System.out.println("XML Parse ERROR!");
        } catch (SAXException e) {
            System.out.println("SAXException!");
        } catch (IOException e) {
            System.out.println("IO Error!");
        }


    }
}
