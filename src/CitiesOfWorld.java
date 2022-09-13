
//Воспользуйтесь этим сайтом: https://www.krylatskoye.ru/content/ratings/2021/09/0928.html для получения списка из 100 городов мира. Выведите эти города в консоль по алфавиту

import java.util.Arrays;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CitiesOfWorld {
    public static void main(String[] args) throws IOException {
        String [] mas = new String[100]; //создаем массив для добавления городов
        Document doc = Jsoup.connect("https://www.krylatskoye.ru/content/ratings/2021/09/0928.html").get();//получаем html-код страницы
        Element table = doc.select("table").first(); //находим первую таблицу в документе
        Elements rows = table.select("tr");// разбиваем таблицу на строки по тегу
        for (int i = 2; i < rows.size(); i++) {
            Element row = rows.get(i); //по номеру индекса получаем строку
            Elements cols = row.select("td");// разбиваем полученную строку по тегу  на столбцы
            mas[i-2] = cols.get(1).text(); //получаем данные из столбца "Города" и добавляем в массив
        }
        Arrays.sort(mas); //сортируем полученный массив
        for (String s : mas) {
            System.out.println(s);
        }
    }
}
