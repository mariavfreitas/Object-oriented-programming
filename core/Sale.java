package woo.core;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Class Sale implements a sale.
 */
public class Sale extends Transaction implements Serializable {

	private String _clientID;
	private boolean _paid;
  private String _productID;
  private int _value;
  private int _amount;
  private int _deadline;
  private int _payDate;
  private int _paymentLeft;


	public Sale(int id, int deadline, String cID, String pID, int amount, int tValue){
    super(id);
    _clientID = cID;
    _productID = pID;
    _amount = amount;
    _value = tValue;
    _paymentLeft = tValue;
    _deadline = deadline;
    _paid = false;
  }

  public String getClientID(){
  	return _clientID;
  }

  public boolean isPaid(){
  	return _paid;
  }

  public int pay(int date){
    if(!_paid){
      _paymentLeft = 0;
      _paid = true;
      _payDate = date;
      return _value;
    }
    return 0;
  }

  public void payAmount(int value){
    _paymentLeft -= value;
  }

  public String toString(){
  	String output;
    if(_paid){ /*pago*/
    	output = _id + "|"					 //
    				 + _clientID + "|"	 //
    				 + _productID + "|"   //
             + _amount + "|" //
             + _value + "|"
             + _paymentLeft + "|"
             + _deadline + "|"
             + _payDate;
      return output;
    } else { /*por pagar*/
      output = _id + "|"           //
             + _clientID + "|"   //
             + _productID + "|"   //
             + _amount + "|" //
             + _value + "|"
             + _paymentLeft + "|"
             + _deadline;
      return output;
    }
  }
}