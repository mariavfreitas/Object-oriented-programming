package woo.core;

import java.io.Serializable;

import java.util.ArrayList;

/**
 * Class Client implements a client.
 */
public class Client implements Serializable {

  private String _id;
  private String _name;
  private String _address;

  private ClientStatus _status;

  private ArrayList<String> _blockedNotifications = new ArrayList<String>();
  
  private int _valuePurchased;
  private int _valuePaid;
  private int _points;

  public Client(String id, String name, String address){
  	_id = id;
    _name = name;
    _address = address;
    _status = ClientStatus.valueOf("NORMAL");
    _points = 0;
    _valuePurchased = 0;
    _valuePaid = 0;
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

  public ClientStatus getClientStatus(){
    return _status;
  }

  public void setClientStatus(ClientStatus cs){
    _status = cs;
  }

  public int getPoints(){
    return _points;
  }

  public void addPoints(int value){
    _points += value*10;
  }

  public int getValuePurchased(){
    return _valuePurchased;
  }

  public void addValuePurchased(int value){
    _valuePurchased += value;
  }

  public int getValuePaid(){
    return _valuePaid;
  }

  public void addValuePaid(int value){
    _valuePaid -= value;
  }

  public void addBlockedID(String pid){
    _blockedNotifications.add(pid);
  }

  public void removeBlockedID(String pid){
    _blockedNotifications.remove(pid);
  }

  public boolean isIDBlocked(String pid){
    int len = _blockedNotifications.size(); 
    for(int i = 0; i < len; i++){
      if(_blockedNotifications.get(i).equals(pid)){
        return true;
      }
    }
    return false;
  }

  public String toString(){
    String output;
    output = _id + "|"      //
            + _name + "|"    //
            + _address + "|"   //
            + _status + "|"      //
            + _valuePurchased + "|" //
            + _valuePaid;

    return output;
  }

}