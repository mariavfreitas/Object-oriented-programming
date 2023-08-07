package woo.app.lookups;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import woo.core.StoreManager;
import woo.core.Transaction;

import java.util.ArrayList;
import woo.app.exception.UnknownClientKeyException;

/**
 * Lookup payments by given client.
 */
public class DoLookupPaymentsByClient extends Command<StoreManager> {

  private Input<String> _clientKey;

  private ArrayList<Transaction> clPaidTransactions = new ArrayList<Transaction>();
  private ArrayList<Transaction> clTransactions = new ArrayList<Transaction>();

  public DoLookupPaymentsByClient(StoreManager storefront) {
    super(Label.PAID_BY_CLIENT, storefront);
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

    clPaidTransactions = _receiver.getPaidTransactions(clTransactions);

  	int len = clPaidTransactions.size();
    for(int i = 0; i < len; i++){
  		_display.addLine(clPaidTransactions.get(i).toString());
  	}

  	_display.display();
  	
  }

}
