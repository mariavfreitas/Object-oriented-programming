package woo.app.products;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import woo.core.StoreManager;

import woo.app.exception.UnknownProductKeyException;

/**
 * Change product price.
 */
public class DoChangePrice extends Command<StoreManager> {

  private Input<String> _pid;
  private Input<Integer> _newPrice;
  
  public DoChangePrice(StoreManager receiver) {
    super(Label.CHANGE_PRICE, receiver);
    _pid = _form.addStringInput(Message.requestProductKey());
    _newPrice = _form.addIntegerInput(Message.requestPrice());
  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();

    String _pidCaps = _pid.value().toUpperCase();

    if(_receiver.isProductIDAvailable(_pid.value(), _pidCaps)){
      throw new UnknownProductKeyException(_pidCaps);
    }

    _receiver.changePrice(_pidCaps, _newPrice.value());
  }
}
