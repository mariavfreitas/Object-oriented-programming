package woo.app.transactions;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import woo.core.StoreManager;

import woo.app.exception.UnavailableProductException;
import woo.app.exception.UnknownClientKeyException;
import woo.app.exception.UnknownProductKeyException;

/**
 * Register sale.
 */

public class DoRegisterSaleTransaction extends Command<StoreManager> {

	private Input<String> _clientKey;
  private Input<Integer> _deadline;
  private Input<String> _productKey;
  private Input<Integer> _amount;
  private int _stock;

  public DoRegisterSaleTransaction(StoreManager receiver) {
    super(Label.REGISTER_SALE_TRANSACTION, receiver);
    _clientKey = _form.addStringInput(Message.requestClientKey());
    _deadline = _form.addIntegerInput(Message.requestPaymentDeadline());
    _productKey = _form.addStringInput(Message.requestProductKey());
    _amount = _form.addIntegerInput(Message.requestAmount());
  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();

    String _pidCaps = _productKey.value().toUpperCase();  
    String _cidCaps = _clientKey.value().toUpperCase();

  	if(_receiver.isProductIDAvailable(_productKey.value(), _pidCaps)){
  		throw new UnknownProductKeyException(_productKey.value());
  	}

  	if(_receiver.isClientIDAvailable(_clientKey.value(), _cidCaps)){
  		throw new UnknownClientKeyException(_clientKey.value());
  	}

    _stock =_receiver.getStock(_pidCaps);

    if(_stock < _amount.value()){
      throw new UnavailableProductException(_pidCaps, _amount.value(), _stock);
    }

  	_receiver.registerSale(_clientKey.value(), _pidCaps, _amount.value(), _deadline.value());
  }
}
