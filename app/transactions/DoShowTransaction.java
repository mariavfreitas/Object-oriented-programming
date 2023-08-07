package woo.app.transactions;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import woo.core.StoreManager;

import woo.app.exception.UnknownTransactionKeyException;

/**
 * Show specific transaction.
 */
public class DoShowTransaction extends Command<StoreManager> {

  private Input<Integer> _transactionKey;

  public DoShowTransaction(StoreManager receiver) {
    super(Label.SHOW_TRANSACTION, receiver);
    _transactionKey = _form.addIntegerInput(Message.requestTransactionKey());
  }

  @Override
  public final void execute() throws DialogException {
  	_form.parse();

  	if(_receiver.transactionExists(_transactionKey.value()) == false){
  		throw new UnknownTransactionKeyException(_transactionKey.value());
  	}
    
    String str = _receiver.getTransaction(_transactionKey.value()).toString();
  	_display.popup(str);
  }

}