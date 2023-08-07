package woo.core;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Class Order implements an order.
 */
public class Order extends Transaction implements Serializable {

	private String _supplierID;
	private boolean _paid;
  private ArrayList<String> _productIDList;
  private ArrayList<Integer> _amounts;
  private int _value;
  private int _date;

	public Order(int id, int date, String sID, ArrayList<String> productIDList, ArrayList<Integer> amounts, int tValue){
    super(id);
    _date = date;
    _supplierID = sID;
    _paid = true;
    _productIDList = new ArrayList<String>(productIDList);
    _amounts = new ArrayList<Integer>(amounts);
    _value = tValue;
  }

  public String getSupplierID(){
  	return _supplierID;
  }

  public boolean isPaid(){
  	return _paid;
  }

  public String toString(){
  	String output;
  	output = _id + "|"					 //
  				 + _supplierID + "|"	 //
  				 + _value + "|"				 //
  				 + _date + "\n";
  	int len = _productIDList.size();
  	for(int i = 0; i < len; i++){
  		output = output + "\n"
						+ _productIDList.get(i) + "|" //
						+ _amounts.get(i); 
  	}	

  	return output;
  }

}