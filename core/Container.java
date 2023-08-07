package woo.core;

import java.io.Serializable;

/**
 * Class Container implements a container.
 */
public class Container extends Box implements Serializable {

  private ServiceQuality _serviceQlt;

  public Container(String id, int price, int critValue, String supplierID, String level, String quality){
    super(id, price, critValue, supplierID, level);
    _serviceQlt = ServiceQuality.valueOf(quality);
  }

  public ServiceQuality getServiceQuality(){
    return _serviceQlt;
  }

  public void setServiceQuality(String quality){
    _serviceQlt = ServiceQuality.valueOf(quality);
  }
  
  public String toString(){
    String output = "CONTAINER|";
    output = output + //
            _id + "|" + //
            _supplierID + "|" + //
            _price + "|" +  //
            _critValue + "|" +  //
            _stock + "|" +  //
            _serviceLvL + "|" + //
            _serviceQlt;
    return output;
  }

}