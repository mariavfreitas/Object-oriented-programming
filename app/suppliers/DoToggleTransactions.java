package woo.app.suppliers;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import woo.core.StoreManager;

import woo.app.exception.UnknownSupplierKeyException;

/**
 * Enable/disable supplier transactions.
 */
public class DoToggleTransactions extends Command<StoreManager> {

	private Input<String> _sid;

  public DoToggleTransactions(StoreManager receiver) {
    super(Label.TOGGLE_TRANSACTIONS, receiver);
    _sid = _form.addStringInput(Message.requestSupplierKey());
  }

  @Override
  public void execute() throws DialogException {
  	_form.parse();

    if(_receiver.isSupplierIDAvailable(_sid.value())){
      throw new UnknownSupplierKeyException(_sid.value());
    }

  	_receiver.toggleTransaction(_sid.value());

    if(_receiver.isSupplierActive(_sid.value())){
      _display.popup(Message.transactionsOn(_sid.value()));
    } else {
      _display.popup(Message.transactionsOff(_sid.value()));
    }
  }
  
}
