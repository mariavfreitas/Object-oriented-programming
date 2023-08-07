package woo.app.transactions;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import woo.core.StoreManager;

import woo.app.exception.UnknownTransactionKeyException;

/**
 * Pay transaction (sale).
 */
public class DoPay extends Command<StoreManager> {

  private Input<Integer> _transactionKey;
  
  public DoPay(StoreManager storefront) {
    super(Label.PAY, storefront);
    _transactionKey = _form.addIntegerInput(Message.requestTransactionKey());
  }

  @Override
  public final void execute() throws DialogException {
  	_form.parse();

  	if(!_receiver.transactionExists(_transactionKey.value())){
      throw new UnknownTransactionKeyException(_transactionKey.value());
    }

    _receiver.payTransaction(_transactionKey.value());

  }
}
