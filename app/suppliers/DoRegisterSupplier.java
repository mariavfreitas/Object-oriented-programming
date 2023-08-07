package woo.app.suppliers;

import java.util.ArrayList;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import woo.core.StoreManager;

import woo.app.exception.DuplicateSupplierKeyException;

/**
 * Register supplier.
 */
public class DoRegisterSupplier extends Command<StoreManager> {

  private Input<String> _id;
	private Input<String> _name;
	private Input<String> _address;

  public DoRegisterSupplier(StoreManager receiver) {
    super(Label.REGISTER_SUPPLIER, receiver);
    _id = _form.addStringInput(Message.requestSupplierKey());
  	_name = _form.addStringInput(Message.requestSupplierName());
  	_address = _form.addStringInput(Message.requestSupplierAddress());
  }

  @Override
  public void execute() throws DialogException, DuplicateSupplierKeyException {
  	_form.parse();

    if(!_receiver.isSupplierIDAvailable(_id.value())){
   	  throw new DuplicateSupplierKeyException(_id.value());
    }
    
    _receiver.registerSupplier(_id.value(), _name.value(), _address.value());
  }
}
