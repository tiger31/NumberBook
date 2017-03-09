import Book.*;
import com.google.gson.Gson;

public class Main {
    public static void main(String[] args){
        Gson g = new Gson();
        Book b = new Book();
        b.addPerson("Ilya", "+7-980-520-25-83");
        System.out.println(b.getPersonByName("Ilya").getNumber());
        System.out.println(g.toJson(b));
        Book c = g.fromJson(g.toJson(b), Book.class);
        System.out.println(g.toJson(c));
    }
}
