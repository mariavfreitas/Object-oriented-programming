package woo.app.products;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import woo.core.StoreManager;

import woo.app.exception.DuplicateProductKeyException;
import woo.app.exception.UnknownServiceTypeException;
import woo.app.exception.UnknownSupplierKeyException;

/**
 * Register box.
 */
public class DoRegisterProductBox extends Command<StoreManager> {

	private Input<String> _id;
	private Input<Integer> _price;
	private Input<Integer> _critValue;
	private Input<String> _supplierID;
	private Input<String> _serviceLvL;

  public DoRegisterProductBox(StoreManager receiver) {
    super(Label.REGISTER_BOX, receiver);
    _id = _form.addStringInput(Message.requestProductKey());
    _price = _form.addIntegerInput(Message.requestPrice());
    _critValue = _form.addIntegerInput(Message.requestStockCriticalValue());
    _supplierID = _form.addStringInput(Message.requestSupplierKey());
    _serviceLvL = _form.addStringInput(Message.requestServiceType());
  }

  @Override
  public final void execute() throws DialogException, DuplicateProductKeyException {
    _form.parse();

    String _pidCaps = _id.value().toUpperCase();
    String _sidCaps = _supplierID.value().toUpperCase();

    if(!_receiver.isProductIDAvailable(_id.value(), _pidCaps)){
      throw new DuplicateProductKeyException(_pidCaps);
    }

    if(_receiver.isSupplierIDAvailable(_sidCaps)){
      throw new UnknownSupplierKeyException(_sidCaps);
    }

    if(!_receiver.isServiceLvl(_serviceLvL.value())){
      throw new UnknownServiceTypeException(_serviceLvL.value());
    }
    
    _receiver.registerBox(_id.value(), _price.value(), _critValue.value(), 
                          _sidCaps, _serviceLvL.value());
  }
}
