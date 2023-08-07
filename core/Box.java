package woo.core;

import java.io.Serializable;

/**
 * Class Box implements a box.
 */
public class Box extends Product implements Serializable {

  protected ServiceLevel _serviceLvL;

  public Box(String id, int price, int critValue, String supplierID, String level){
    super(id, price, critValue, supplierID);
    _serviceLvL = ServiceLevel.valueOf(level);
  }

  public ServiceLevel getServiceLevel(){
    return _serviceLvL;
  }

  public void setServiceLevel(String level){
    _serviceLvL = ServiceLevel.valueOf(level);
  }
  
  public String toString(){
    String output = "BOX|";
    output = output + //
            _id + "|" + //
            _supplierID + "|" + //
            _price + "|" +  //
            _critValue + "|" +  //
            _stock + "|" +  //
            _serviceLvL;
    return output;
  }

}