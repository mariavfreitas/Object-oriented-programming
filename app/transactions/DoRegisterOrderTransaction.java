package woo.app.transactions;

import java.util.ArrayList;
import java.util.Arrays;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import woo.core.StoreManager;

import woo.app.exception.UnknownSupplierKeyException;
import woo.app.exception.UnknownProductKeyException;
import woo.app.exception.UnauthorizedSupplierException; //FALTA USAR
import woo.app.exception.WrongSupplierException; // FALTA USAR

/**
 * Register order.
 */
public class DoRegisterOrderTransaction extends Command<StoreManager> {

  private Input<String> _supplierKey;
  private Input<String> _more;
  private Input<String> _productKey;
  private Input<Integer> _amount;

  private ArrayList<Integer> _amounts = new ArrayList<Integer>();
  private ArrayList<String> _productKeyList = new ArrayList<String>();

  public DoRegisterOrderTransaction(StoreManager receiver) {
    super(Label.REGISTER_ORDER_TRANSACTION, receiver);
  }

  @Override
  public final void execute() throws DialogException {
    _supplierKey = _form.addStringInput(Message.requestSupplierKey());
    _productKey = _form.addStringInput(Message.requestProductKey());
    _amount = _form.addIntegerInput(Message.requestAmount());
    _more = _form.addStringInput(Message.requestMore());
    _form.parse();

    String _pidCaps = _productKey.value().toUpperCase();
    String _sidCaps = _supplierKey.value().toUpperCase();

  	if(_receiver.isSupplierIDAvailable(_sidCaps)){
  		throw new UnknownSupplierKeyException(_sidCaps);
  	}

  	if(_receiver.isProductIDAvailable(_productKey.value(), _pidCaps)){
  		throw new UnknownProductKeyException(_productKey.value());
  	}    

		_productKeyList.add(_pidCaps);
		_amounts.add(_amount.value());
    
    /*in case of more*/
  	while(_more.value().equals("s")){
      _form.clear();
      _productKey = _form.addStringInput(Message.requestProductKey());
      _amount = _form.addIntegerInput(Message.requestAmount());
      _more = _form.addStringInput(Message.requestMore());
      _form.parse();

      _pidCaps = _productKey.value().toUpperCase();
	    
      if(_receiver.isProductIDAvailable(_productKey.value(), _pidCaps)){
	  		throw new UnknownProductKeyException(_productKey.value());
	  	}
			
      _productKeyList.add(_pidCaps);
      _amounts.add(_amount.value());  
  	}
    
  	_receiver.registerOrder(_supplierKey.value(), _productKeyList, _amounts);
    _productKeyList.clear();
    _amounts.clear();
    _form.clear();
  }
}
