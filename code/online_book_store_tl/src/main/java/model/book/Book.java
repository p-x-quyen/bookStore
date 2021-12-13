package model.book;

import java.util.List;

/**
 *
 * @author Administrator
 */
public class Book {
    int id;
    String name;
    String summary;
    int numberOfPages;
    String language;
    String isbn;
    
    List<Author> listAuthors;
    Publisher publisher;

    public Book() {
    }

    public Book(String name, String summary, int numberOfPages, String language, String isbn, List<Author> listAuthors, Publisher publisher) {
        this.name = name;
        this.summary = summary;
        this.numberOfPages = numberOfPages;
        this.language = language;
        this.isbn = isbn;
        this.listAuthors = listAuthors;
        this.publisher = publisher;
    }

    public Book(int id, String name, String summary, int numberOfPages, String language, String isbn, List<Author> listAuthors, Publisher publisher) {
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.numberOfPages = numberOfPages;
        this.language = language;
        this.isbn = isbn;
        this.listAuthors = listAuthors;
        this.publisher = publisher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<Author> getListAuthors() {
        return listAuthors;
    }

    public void setListAuthors(List<Author> listAuthors) {
        this.listAuthors = listAuthors;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", name=" + name + ", summary=" + summary + ", numberOfPages=" + numberOfPages + ", language=" + language + ", isbn=" + isbn + ", listAuthors=" + listAuthors + ", publisher=" + publisher + '}';
    }

    
    
}
