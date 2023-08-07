package woo.core;

import java.io.Serializable;

/**
 * Class Product implements a product.
 */
public class Product implements Serializable {

  protected String _id;
  protected int _price;
  protected int _critValue;
  protected int _stock;

  protected String _supplierID;


  public Product(String id, int price, int critValue, String supplierID){
    _id = id;
    _price = price;
    _critValue = critValue;
    _supplierID = supplierID;
    _stock = 0;
  }

  public String getID(){
    return _id;
  }

  public int getPrice(){
    return _price;
  }

  public void setPrice(int price){
    _price = price;
  }

  public int getCritValue(){
    return _critValue;
  }

  public void setCritValue(int critValue){
    _critValue = critValue;
  }

  public String getSupplierID(){
    return _supplierID;
  }

  public int getStock(){
    return _stock;
  }

  public void setStock(int n){
    _stock = n;
  }

  public void addStock(int n){
    _stock = _stock + n;
  }

  public void reduceStock(int n){
    _stock = _stock - n;
  }

}