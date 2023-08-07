package woo.app.lookups;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import woo.core.StoreManager;
import woo.core.Product;

import java.util.ArrayList;

/**
 * Lookup products cheaper than a given price.
 */
public class DoLookupProductsUnderTopPrice extends Command<StoreManager> {
  private Input<Integer> _topPrice;
  private ArrayList<Product> _productList = new ArrayList<Product>();

  public DoLookupProductsUnderTopPrice(StoreManager storefront) {
    super(Label.PRODUCTS_UNDER_PRICE, storefront);
    _topPrice = _form.addIntegerInput(Message.requestPriceLimit());
  }

  @Override
  public void execute() throws DialogException {
      _form.parse();
      _productList = _receiver.getProductList();
      int len = _productList.size();

    for(int i = 0; i < len; i++){
        if(_productList.get(i).getPrice() < _topPrice.value()){
            _display.addLine(_productList.get(i).toString());
        }
      }
    _display.display();
  }
}