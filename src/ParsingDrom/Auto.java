package ParsingDrom;

public class Auto {
    private String title; //название
    private String link;//ссылка
    private String price; //цена
    private String mileage; // пробег


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }



    @Override
    public String toString() {
        return title  + " " + link + " "  +  price + " " + mileage + "\n";
    }
}

