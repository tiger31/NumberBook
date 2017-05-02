package Book;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import static org.junit.Assert.*;

public class BookTest {
    //Book.addPerson method tests
    @Test
    public void addPerson() {
        Book book1 = new Book();
        book1.addPerson("Ilya", "123321", "123");
        assertTrue(book1.getPersonByName("Ilya") != null);
        assertTrue(book1.getPersonByName("Ilya2") == null);
        book1.addPerson("Ilya2", "321123", "321");
        assertTrue(book1.getPersonByName("Ilya2") != null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void addPersonException() {
        Book book1 = new Book();
        book1.addPerson("Ilya2", "321123", "321");
        book1.addPerson("Ilya2", "321123", "321");
    }
    //Book.getPersonByName method tests
    @Test
    public void getPersonByName() {
        Book book1 = new Book();
        book1.addPerson("Ilya2", "321123", "321");
        assertEquals(book1.getPersonByName("Ilya2").getName(), "Ilya2");
        assertEquals(book1.getPersonByName("Ilya3"), null);
    }

    //Person.getNumber method tests
    @Test
    public void getNumber() {
        Book book1 = new Book();
        book1.addPerson("Ilya", "123321", "123");
        book1.addPerson("Ilya2", "321123");
        assertEquals(book1.getPersonByName("Ilya").getNumber(), "123321");
        assertEquals(book1.getPersonByName("Ilya2").getNumber(), "321123");
        assertEquals(book1.getPersonByName("Ilya").getNumber(0), "123321");
        assertEquals(book1.getPersonByName("Ilya").getNumber(1), "123");
    }
    @Test(expected = IllegalArgumentException.class)
    public void getNumberException() {
        Book book1 = new Book();
        book1.addPerson("Ilya");
        book1.addPerson("Ilya2", "321123");
        book1.getPersonByName("Ilya").getNumber();
        book1.getPersonByName("Ilya").getNumber(0);
        book1.getPersonByName("Ilya2").getNumber(1);
    }
    @Test
    public void getAllNumbers() {
        Book book1 = new Book();
        book1.addPerson("Ilya");
        book1.addPerson("Ilya2", "321123");
        book1.addPerson("Ilya3", "123321", "123");
        assertEquals(book1.getPersonByName("Ilya").getAllNumbers(), Collections.emptyList());
        assertEquals(book1.getPersonByName("Ilya2").getAllNumbers().size(), 1);
        assertEquals(book1.getPersonByName("Ilya3").getAllNumbers().size(), 2);
    }

    //Person.setName method tests
    @Test
    public void setName() {
        Book book1 = new Book();
        book1.addPerson("Ilya");
        Person person = book1.getPersonByName("Ilya");
        person.setName("Ilya2");
        assertEquals(person.getName(), "Ilya2");
    }

    //Person.addNumber method tests
    @Test
    public void addNumber() {
        Book book1 = new Book();
        book1.addPerson("Ilya").addNumber("123");
        assertEquals(book1.getPersonByName("Ilya").getNumber(), "123");
        book1.getPersonByName("Ilya").addNumber("321123");
        assertEquals(book1.getPersonByName("Ilya").getAllNumbers().size(), 2);
    }
    @Test
    public void addAllNumbers() {
        Book book1 = new Book();
        Person person = book1.addPerson("Ilya");
        List<String> collection1 = Arrays.asList("123", "321", "123321");
        Set<String> collection2 = new HashSet<>(collection1);
        person.addAllNumbers(collection1);
        assertEquals(person.getAllNumbers().size(), 3);
        person.addAllNumbers(collection2);
        assertEquals(person.getAllNumbers().size(), 6);
    }

    //Person.removeNumber method tests
    @Test
    public void removeNumber() {
        Book book1 = new Book();
        Person person = book1.addPerson("Ilya", "123321", "123");
        person.removeNumber("321");
        assertEquals(person.getAllNumbers(), Arrays.asList("123321", "123"));
        person.removeNumber("123");
        assertEquals(person.getAllNumbers(), Arrays.asList("123321"));
        person.removeNumber(0);
        assertEquals(person.getAllNumbers(), Collections.emptyList());
    }

    //Person.replaceNumber method tests
    @Test
    public void replaceNumber() {
        Book book1 = new Book();
        Person person = book1.addPerson("Ilya", "123321", "123");
        person.replaceNumber("123", "321");
        assertTrue(person.getAllNumbers().contains("321"));
        person.replaceNumber(0, "321123");
        assertTrue(person.getAllNumbers().contains("321123"));
    }
    @Test(expected = IllegalArgumentException.class)
    public void replaceNumberException() {
        Book book1 = new Book();
        Person person = book1.addPerson("Ilya", "123321", "123");
        person.replaceNumber("321", "333");
    }

    //Person.setAsDefault... methods tests
    @Test
    public void setAsDefault() {
        Book book1 = new Book();
        Person person = book1.addPerson("Ilya", "123321", "123");
        //Before setting
        assertEquals(person.getNumber(), "123321");
        person.setAsDefaultNumber(1);
        //After setting
        assertEquals(person.getNumber(), "123");
        person.setNewNumberAsDefault("321");
        assertEquals(person.getNumber(), "321");
    }

    //Book.removePerson method tests
    @Test
    public void removePerson() {
        Book book1 = new Book();
        book1.addPerson("Ilya1");
        Person person = book1.addPerson("Ilya2");
        book1.removePerson("Ilya1");
        assertEquals(book1.getPersonByName("Ilya1"), null);
        book1.removePerson(person);
        assertEquals(book1.getPersonByName("Ilya2"), null);
    }
    //Book.getPersonByNumber method tests
    @Test
    public void getPersonByNumber() {
        Book book1 = new Book();
        Person person = book1.addPerson("Ilya", "123321", "123");
        assertEquals(Arrays.asList(person), book1.getPersonByNumber("123321"));
        assertEquals(Collections.emptyList(), book1.getPersonByNumber("321"));
        Person person1 = book1.addPerson("Ilya2", "321123", "123");
        assertEquals(Arrays.asList(person, person1), book1.getPersonByNumber("123"));
    }

    //Book.getNumbers method tests
    @Test
    public void getNumbers() {
        Book book1 = new Book();
        List<String> nums = Arrays.asList("123", "123321");
        Person person = book1.addPerson("Ilya", "123", "123321");
        assertEquals(book1.getNumbers("Ilya"), nums);
        assertEquals(book1.getNumbers(person), nums); //TODO check for exists in master
    }
    @Test(expected = IllegalArgumentException.class)
    public void getNumbersException() {
        Book book1 = new Book();
        book1.addPerson("Ilya", "123");
        book1.getNumbers("Ilya2");
    }
}