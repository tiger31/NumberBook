package Book;

import java.util.Collection;
import java.util.List;

public interface Person {

    String getNumber();

    String getNumber(int index);
    List<String> getAllNumbers();

    String getName();

    void setName(String name);

    //Добавление номеров
    void addNumber(String number);
    void addAllNumbers(Collection<String> numbers);
    //Удаление номера
    void removeNumber(String number);
    void removeNumber(int index);
    //Изменение номера
    void replaceNumber(String old, String number);
    void replaceNumber(int index, String number);
    //Выбрать номер, как основной
    void setAsDefaultNumber(int index);
    void setNewNumberAsDefault(String number);

    boolean validateNum(String number);

}