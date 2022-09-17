package ParsingDrom;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;


public class ParsingWeb {
    public static void main(String[] args) throws IOException  {
        JFrame frame = new JFrame("Парсинг"); //cоздаем окно
        frame.setLayout(new FlowLayout()); //FlowLayout используется для размещения компонентов в линию, один за другим
        frame.setSize(1200, 600);
        frame.setLocationRelativeTo(null); // выравниваем окно по центру
        Container container = frame.getContentPane();
        container.setLayout(new FlowLayout()); //последовательное расположение с выравниванием компонентов по центру
        container.setBackground(Color.pink.brighter()); //устанавливаем цвет окна
        List<Auto> list = new ArrayList<>(); //создаем список для внесения данных
        Document doc = Jsoup.connect("https://auto.drom.ru/").get();//получаем html-код страницы
        Elements allElements = doc.getElementsByAttributeValue("data-ftid", "bulls-list_bull"); //получаем все элементы, у которых есть атрибут data-ftid со значением bulls-list_bull
            for (Element element : allElements) { //проходимся по всем элементам
                String details = element.attr("href"); //у каждого элемента получаем значение атрибута "href" т.е. ссылку
                Auto advert = new Auto(); //создаем объект класса Advert
                advert.setLink(details);//устанавливаем для поля "link" значение - ссылку
                Document detailsDoc = Jsoup.connect(details).get(); //подключаемся к ссылке для получения дополнительной информации
                Elements el1 = detailsDoc.getElementsByTag("h1"); //из ссылки на объявление получаем тег h1
                advert.setTitle(el1.text()); //из тега h1 извлекаем текст - название объявления и устанавливаем это значение для поля "title"
                Elements el2 = detailsDoc.getElementsByClass("css-eazmxc e162wx9x0"); //для получения цены выбираем элементы с классом "css-eazmxc e162wx9x0"
                advert.setPrice(el2.text()); //полученный текст устанавливаем для поля "price"
                Elements el3 = detailsDoc.getElementsByClass("css-1osyw3j ei6iaw00");
                advert.setMileage(el3.text()); //полученный текст устанавливаем для поля "mileage"
                list.add(advert);
            }
        DefaultTableModel tableModel = new DefaultTableModel(); //создаем модель  по умолчанию, которая является таблицей нулевых столбцов и нулевых строк
        JTable table = new JTable(); //создаем объект JTable
        table.setModel(tableModel); //устанавливаем для JTable модель
        table.setPreferredScrollableViewportSize(new Dimension(1100, 500)); //устанавливаем размер JTable
        table.setFillsViewportHeight(true); // чтобы таблица никогда не была меньше окна просмотра
        frame.add(new JScrollPane(table)); //JScrollPane обеспечивает представление с возможностью прокрутки компонент
        Object[] column = new String[]{"Название", "Ссылка", "Цена", "Пробег"};
        tableModel.setColumnIdentifiers(column);
        for (int i = 0; i < list.size(); i++) {
            String title = list.get(i).getTitle();
            String link = list.get(i).getLink();
            String price = list.get(i).getPrice();
            String mileage = list.get(i).getMileage();
            Object[] data = new Object[]{title, link, price, mileage};
            tableModel.addRow(data);
        }
        frame.setVisible(true); //делаем окно видимым
    }
}
