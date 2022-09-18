
/*Напишите парсер для сети ресторанов домино. Вот их страница https://dominodomoy.ru/catalog/. Ваша задача - сначала вытянуть информацию о всех категориях, затем перейти на страницу каждой категории и вытянуть полностью список всех позиций в категории.
 Так же сохраните ссылку на картинку для каждого продукта. Выведите все данные в консоль. Вывод должен иметь примерно следующий вид:

        Категория 1
        Название позиции 1 - цена 1 позиции - ссылка на картинку для 1 позиции
        Название позиции 2 - цена 2 позиции - ссылка на картинку для 2 позиции
        ...
        Категория 2
        Название позиции 1 - цена 1 позиции - ссылка на картинку для 1 позиции
        Название позиции 2 - цена 2 позиции - ссылка на картинку для 2 позиции
        ...
        Категория 3
        Название позиции 1 - цена 1 позиции - ссылка на картинку для 1 позиции
        Название позиции 2 - цена 2 позиции - ссылка на картинку для 2 позиции
        ...
        ...
 */

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class ParsDomino {
    public static void main(String[] args)  {
        try{
            Document doc1 = Jsoup.connect("https://dominodomoy.ru/catalog/").get();//получаем html-код страницы
            Elements allCategories = doc1.getElementsByClass("catalog-section-list-item-title intec-cl-text-hover"); //получаем все категории товаров
            for (Element element : allCategories) { //проходимся по всем элементам
                System.out.println("\n" + element.text() + ":"); //выводим на печать наименование  категории товара
                String link = element.attr("href"); //у каждого элемента получаем значение атрибута "href" т.е. ссылку
                Document category = Jsoup.connect("https://dominodomoy.ru" + link).get(); //подключаемся к ссылке для получения товаров
                Elements positions = category.getElementsByClass("catalog-section-item-background intec-ui-clearfix");//получаем все позиции товаров в каждой из категорий
                for (Element c : positions) { //проходим по каждой позиции товара
                    String title = c.getElementsByClass("catalog-section-item-name-wrapper intec-cl-text-hover").text(); //получаем название товара
                    String price = c.getElementsByClass("catalog-section-item-price-discount").text(); //получаем цену товара
                    String img = c.select("img").attr("data-original"); //получаем ссылку на картинку
                    System.out.println(title + " - " + price + " - " +  "https://dominodomoy.ru" + img);
                }
            }
        }catch(IOException exp){
            System.out.println(exp.getMessage());
        }
    }
}
