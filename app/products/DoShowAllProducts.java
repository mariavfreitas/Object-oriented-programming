package woo.app.products;

import java.util.ArrayList;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import woo.core.StoreManager;
import woo.core.Product;

/**
 * Show all products.
 */
public class DoShowAllProducts extends Command<StoreManager> {

	private ArrayList<Product> _productList = new ArrayList<Product>();

  public DoShowAllProducts(StoreManager receiver) {
    super(Label.SHOW_ALL_PRODUCTS, receiver);
  }

  @Override
  public final void execute() throws DialogException {

  	_productList = _receiver.getProductList();
  	int len = _productList.size();
  	
    for(int i = 0; i < len; i++){
  		_display.addLine(_productList.get(i).toString());
  	}

    _display.display();
  }
  
}
