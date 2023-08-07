package woo.app.suppliers;

import java.util.ArrayList;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import woo.core.StoreManager;
import woo.core.Supplier;

/**
 * Show all suppliers.
 */
public class DoShowSuppliers extends Command<StoreManager> {

	private ArrayList<Supplier> _supplierList = new ArrayList<Supplier>();

  public DoShowSuppliers(StoreManager storefront) {
    super(Label.SHOW_ALL_SUPPLIERS, storefront);
  }

  @Override
  public void execute() throws DialogException {

  	_supplierList = _receiver.getSupplierList();
  	int len = _supplierList.size();
  	
    for(int i = 0; i < len; i++){
  		_display.addLine(_supplierList.get(i).toString());
  	}
    
    _display.display();
  }
}
