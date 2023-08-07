package woo.core;

import java.io.Serializable;

/**
 * Class Transaction implements a transactions.
 */
public class Transaction implements Serializable {

 protected int _id;
 
  public Transaction(int id){
    _id = id;
  }

  public int getID(){
    return _id;
  }

  public String getClientID(){
  	return "";
  }

  public String getSupplierID(){
    return "";
  }

  public boolean isPaid(){
  	return false;
  }

  public int pay(int date){
    return 0;
  }
}