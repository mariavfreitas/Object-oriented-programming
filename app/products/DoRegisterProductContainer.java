package woo.app.products;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import woo.core.StoreManager;

import woo.app.exception.DuplicateProductKeyException;
import woo.app.exception.UnknownSupplierKeyException;
import woo.app.exception.UnknownServiceLevelException;
import woo.app.exception.UnknownServiceTypeException;

/**
 * Register container.
 */
public class DoRegisterProductContainer extends Command<StoreManager> {

	private Input<String> _id;
	private Input<Integer> _price;
	private Input<Integer> _critValue;
	private Input<String> _supplierID;
  private Input<String> _serviceLvl;
	private Input<String> _serviceQlt;

  public DoRegisterProductContainer(StoreManager receiver) {
    super(Label.REGISTER_CONTAINER, receiver);
    _id = _form.addStringInput(Message.requestProductKey());
    _price = _form.addIntegerInput(Message.requestPrice());
    _critValue = _form.addIntegerInput(Message.requestStockCriticalValue());
    _supplierID = _form.addStringInput(Message.requestSupplierKey());
    _serviceLvl = _form.addStringInput(Message.requestServiceType());
    _serviceQlt = _form.addStringInput(Message.requestServiceLevel());
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

    if(!_receiver.isServiceLvl(_serviceLvl.value())){
      throw new UnknownServiceTypeException(_serviceLvl.value());
    }

    if(!_receiver.isServiceQlt(_serviceQlt.value())){
      throw new UnknownServiceLevelException(_serviceQlt.value());
    }
    
    _receiver.registerContainer(_id.value(), _price.value(), _critValue.value(),
                                  _sidCaps, _serviceLvl.value(), _serviceQlt.value());
  }
}
