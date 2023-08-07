package woo.core;

import java.io.Serializable;

import java.util.ArrayList;

/**
 * Class Notifications implements notifications.
 */
public class Notification implements Serializable {

 private String _pid;
 private String _description;
 private int _price;

 private ArrayList<String> _clientsShown = new ArrayList<String>();

 public Notification(String pid, String type, int price){
   _pid = pid;
   _description = type;
   _price = price;
 }

 public String getPid(){
  return _pid;
 }

 public String getDescription(){
 	return _description;
 }

 public void addClientShown(String cID){
 	_clientsShown.add(cID);
 }

 public boolean hasClientSeen(String cID){
 	int len = _clientsShown.size();
 	for(int i = 0; i < len; i++){
 		if(_clientsShown.get(i).equals(cID))
 			return true;
 	}
 	return false;
 }

 public String toString(){
	 String output;
	 output = _description + "|" + _pid + "|" + _price + "\n";
	 return output;
 }
}
