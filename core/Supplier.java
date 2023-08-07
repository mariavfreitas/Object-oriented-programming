package woo.core;

import java.io.Serializable;

/**
 * Class Supplier implements a supplier.
 */
public class Supplier implements Serializable {

  private String _id;
  private String _name;
  private String _address;

  //private Product _product;

  private String _active;


  public Supplier(String id, String name, String address){
  	_id = id;
    _name = name;
    _address = address;
    _active = "SIM";
  }

  public String getID(){
    return _id;
  }

  public String getName(){
    return _name;
  }

  public void setName(String name){
    _name = name;
  }

  public String getAddress(){
    return _address;
  }

  public void setAddress(String address){
    _address = address;
  }

  public String isActive(){
    return _active;
  }

  public void toggleTransaction(){
    if(_active.equals("NÃO")){
      _active = "SIM";
    } else {
      _active = "NÃO";
    }
  }

  public String toString(){
    String output= "";
    output = _id + "|"     //
            + _name + "|"   //
            + _address + "|" //
            + _active;
    return output;
  }


}