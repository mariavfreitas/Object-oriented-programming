package woo.core;

import java.io.Serializable;

/**
 * Class Book implements a book.
 */
public class Book extends Product implements Serializable {

  private String _title;
  private String _author;
  private String _isbn;

  public Book(String id, int price, int critValue, String supplierID,  // 
                      String title, String author, String isbn){
    super(id, price, critValue, supplierID);
    _title = title;
    _author = author;
    _isbn = isbn;
  }

  public String getTitle(){
    return _title;
  }

  public String getAuthor(){
    return _author;
  }

  public String getISBN(){
    return _isbn;
  }

  public String toString(){
    String output = "BOOK|";
    output = output + //
            _id + "|" + //
            _supplierID + "|" + //
            _price + "|" +  //
            _critValue + "|" +  //
            _stock + "|" +  //
            _title + "|" +  //
            _author + "|" + //
            _isbn;
    return output;
  }

}