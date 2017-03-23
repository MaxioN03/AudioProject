package main;

/**
 * Created by Егор on 23.03.17.
 */
//Утилитный класс с менюшками
public class MenuPoints {
    final static int MAIN_MENU_ITEMS = 7;

    protected static void showMenuSearch(){
        System.out.println("Выберите характеристику поиска:\n" +
                "1. Автор песни\n" +
                "2. Название песни\n" +
                "3. Продолжительность песни\n" +
                "0. Назад\n");
    }

    protected static void showMenuSort(){
        System.out.println("Выберите характеристику сортировки:\n" +
                "1. Автор песни\n" +
                "2. Название песни\n" +
                "3. Продолжительность песни\n" +
                "4. Жанр\n" +
                "0. Назад\n");
    }

    protected static void showMenuMain(){
        System.out.println("Выберите пункт меню:\n" +
                "1. Добавить аудиозапись\n" +
                "2. Добавить CD\n" +
                "3. Просмотреть...\n" +
                "4. Записать аудиозапись на CD\n" +
                "5. Сортировка аудиозаписей\n" +
                "6. Поиск по аудизаписям\n" +
                "0. Выход\n");
    }

    protected static void showMenuShow(){
        System.out.println("Выберите, что надо просмотреть:\n" +
                "1.Все аудиозаписи\n" +
                "2.Все CD\n" +
                "3.Конкретный CD\n" +
                "4.Продолжительность аудио" +
                "0.Выход\n");
    }

    protected static void showMenuAddSong(){
        System.out.println("Выберите способ добавления:\n" +
                "1.Через консоль\n" +
                "2.Чтение из текстовго файла\n" +
                "3.Чтение из XML-файла\n" +
                "0.Назад\n");
    }

}
