package woo.app.suppliers;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import woo.core.StoreManager;
import woo.core.Transaction;

import java.util.ArrayList;
import woo.app.exception.UnknownSupplierKeyException;


/**
 * Show all transactions for specific supplier.
 */
public class DoShowSupplierTransactions extends Command<StoreManager> {

  private Input<String> _supplierKey;
  private ArrayList<Transaction> supTransactions = new ArrayList<Transaction>();

  public DoShowSupplierTransactions(StoreManager receiver) {
    super(Label.SHOW_SUPPLIER_TRANSACTIONS, receiver);
    _supplierKey = _form.addStringInput(Message.requestSupplierKey());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();

    if(_receiver.isSupplierIDAvailable(_supplierKey.value())){
      throw new UnknownSupplierKeyException(_supplierKey.value());
    }

    supTransactions = _receiver.getSupplierTransactions(_supplierKey.value());

    int len = supTransactions.size();
    for(int i = 0; i < len; i++){
        _display.addLine(supTransactions.get(i).toString());
    }

    _display.display();
  }
}