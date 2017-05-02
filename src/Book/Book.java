package Book;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Book {

    private List<Person> personList = new ArrayList<>();

    //Person
    private static final class PersonRecord implements Person {
        private transient Pattern rg = Pattern.compile("\\+?[0-9*#-]+");

        private String name;
        private List<String> numberList = new ArrayList<>();

        private PersonRecord(String name, Collection<String> numbers) {
            if(!(numbers == null || numbers.size() == 0) &&numbers.stream().allMatch(n -> validateNum(n)))
                this.numberList.addAll(numbers);
            this.name = name;
        }

        public String getNumber() {
            if (!numberList.isEmpty()) {
                return numberList.get(0);
            } else throw new IllegalArgumentException("Contact has no numbers");
        }
        public String getNumber(int index) {
            if (numberList.size() > index) {
                return numberList.get(index);
            } else throw new IllegalArgumentException("No number with such index found");
        }
        public List<String> getAllNumbers() {
            return numberList.stream().collect(Collectors.toList());
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        //Добавление номеров
        public void addNumber(String number) {
            if (validateNum(number))
                this.numberList.add(number);
        }
        public void addAllNumbers(Collection<String> numbers) {
            if (numbers.stream().allMatch(n -> validateNum(n)))
                this.numberList.addAll(numbers);
        }
        //Удаление номера
        public void removeNumber(String number) {
            if (validateNum(number)) {
                if(numberList.contains(number))
                    numberList.remove(number);
            }
        }
        public void removeNumber(int index) {
            if (numberList.size() > index)
                numberList.remove(index);
        }
        //Изменение номера
        public void replaceNumber(String old, String number) {
            if (validateNum(old) && validateNum(number)){
                if(numberList.contains(old)) {
                    numberList.remove(old);
                    numberList.add(number);
                } else throw new IllegalArgumentException("Can't find number to replace");
            }
        }
        public void replaceNumber(int index, String number) {
            if (numberList.size() > index && validateNum(number)) {
                numberList.remove(index);
                numberList.add(number);
            }
        }
        //Выбрать номер, как основной
        public void setAsDefaultNumber(int index) {
            if (numberList.size() > index) {
                String tmp = numberList.get(index);
                numberList.remove(index);
                numberList.add(0, tmp);
            }
        }
        public void setNewNumberAsDefault(String number) {
            if (validateNum(number)) {
                numberList.add(0,number);
            }
        }

        public boolean validateNum(String number) {
            Matcher m = rg.matcher(number);
            return m.matches();
        }

        @Override
        public boolean equals(Object other) {
            if (other.getClass() != this.getClass()) return false;
            Person person = (Person)other;
            return (person.getName().equals(this.getName()) && person.getAllNumbers().equals(this.getAllNumbers()));
        }

        @Override
        public String toString() {
            StringBuilder str = new StringBuilder();
            str.append(name).append("\r\n");
            this.numberList.forEach(N -> str.append("\t").append(N).append("\r\n"));
            return str.toString();
        }
    }

    //Добавление контакта
    public Person addPerson(String name, String... number) {
        if (getPersonByName(name) != null) throw new IllegalArgumentException("Person with name " + name + " already exists");
        Person newPerson = new PersonRecord(name, Arrays.asList(number));
        personList.add(newPerson);
        return newPerson;
    }
    //Удаление контакта
    public void removePerson(String name) {
        Person person = this.getPersonByName(name);
//System.out.print(person.equals(personList.get(0)));
        if (person != null && personList.contains(person))
            personList.remove(person);
        else throw new IllegalArgumentException("Person not exists");
    }
    public void removePerson(Person person) {
        if (personList.contains(person))
            personList.remove(person);
        else throw new
                IllegalArgumentException("Person not exists");
    }
    //Поиск понтактов
    public Person getPersonByName(String name) {
        return personList.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
    }
    //Теоретически, один номер может быть записан для двух разных имен
    public List<Person> getPersonByNumber(String number) {
        return personList.stream().filter(p -> p.getAllNumbers().contains(number)).collect(Collectors.toList());
    }

    public List<String> getNumbers(String name) {
        Person person = getPersonByName(name);
        if (person == null) throw new IllegalArgumentException("Trying to get numbers of not existing person");
        return person.getAllNumbers();
    }
    public List<String> getNumbers(Person person) {
        return person.getAllNumbers();
    }
    public String getNumber(String name) {
        Person person = getPersonByName(name);
        if (person == null) throw new IllegalArgumentException("Trying to get numbers of not existing person");
        return person.getNumber();
    }
    public String getNumber(Person person) {
        return person.getNumber();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        personList.forEach(P -> {
            str.append(P.getName()).append("\r\n");
            P.getAllNumbers().forEach(N -> str.append("\t").append(N).append("\r\n"));
        });
        return str.toString();
    }

}