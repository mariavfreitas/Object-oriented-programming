package woo.app.products;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import woo.core.StoreManager;

import woo.app.exception.DuplicateProductKeyException;
import woo.app.exception.UnknownSupplierKeyException;

/**
 * Register book.
 */
public class DoRegisterProductBook extends Command<StoreManager> {

	private Input<String> _id;
	private Input<Integer> _price;
	private Input<Integer> _critValue;
	private Input<String> _supplierID;
	private Input<String> _title;
	private Input<String> _author;
	private Input<String> _isbn;

  public DoRegisterProductBook(StoreManager receiver) {
    super(Label.REGISTER_BOOK, receiver);
    _id = _form.addStringInput(Message.requestProductKey());
    _title = _form.addStringInput(Message.requestBookTitle());
    _author = _form.addStringInput(Message.requestBookAuthor());
    _isbn = _form.addStringInput(Message.requestISBN());
    _price = _form.addIntegerInput(Message.requestPrice());
    _critValue = _form.addIntegerInput(Message.requestStockCriticalValue());
    _supplierID = _form.addStringInput(Message.requestSupplierKey());
  }

  @Override
  public final void execute() throws DialogException, DuplicateProductKeyException {
    _form.parse();

    String _pidCaps = _id.value().toUpperCase();
    String _sidCaps = _supplierID.value().toUpperCase();

    if(!_receiver.isProductIDAvailable(_id.value(), _pidCaps)){
      throw new DuplicateProductKeyException(_pidCaps);
    }

    if(_receiver.isSupplierIDAvailable(_sidCaps)){
      throw new UnknownSupplierKeyException(_sidCaps);
    }
    
    _receiver.registerBook(_id.value(), _price.value(), _critValue.value(),
                             _sidCaps, _title.value(), _author.value(), 
                               _isbn.value());
  }
}
