package woo.app.clients;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import woo.core.StoreManager;
import woo.core.Transaction;

import java.util.ArrayList;
import woo.app.exception.UnknownClientKeyException;

/**
 * Show all transactions for a specific client.
 */
public class DoShowClientTransactions extends Command<StoreManager> {

  private Input<String> _clientKey;

  private ArrayList<Transaction> clTransactions = new ArrayList<Transaction>();

  public DoShowClientTransactions(StoreManager storefront) {
    super(Label.SHOW_CLIENT_TRANSACTIONS, storefront);
    _clientKey = _form.addStringInput(Message.requestClientKey());
  }

  @Override
  public void execute() throws DialogException {
  	_form.parse();

    String _idCaps = _clientKey.value().toUpperCase();
  	
    if(_receiver.isClientIDAvailable(_clientKey.value(), _idCaps)){
      throw new UnknownClientKeyException(_clientKey.value());
    }

  	clTransactions = _receiver.getClientTransactions(_idCaps);

  	int len = clTransactions.size();
    for(int i = 0; i < len; i++){
  		_display.addLine(clTransactions.get(i).toString());
  	}

  	_display.display();
  }

}
