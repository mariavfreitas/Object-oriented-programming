package woo.app.clients;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import woo.core.StoreManager;

import woo.app.exception.DuplicateClientKeyException;

/**
 * Register new client.
 */
public class DoRegisterClient extends Command<StoreManager> {

  private Input<String> _id;
  private Input<String> _name;
  private Input<String> _address;

  public DoRegisterClient(StoreManager storefront) {
    super(Label.REGISTER_CLIENT, storefront);
    _id = _form.addStringInput(Message.requestClientKey());
    _name = _form.addStringInput(Message.requestClientName());
    _address = _form.addStringInput(Message.requestClientAddress());
  }

  @Override
  public void execute() throws DialogException, DuplicateClientKeyException {
    _form.parse();

    String _idCaps = _id.value().toUpperCase();

    if(_receiver.isClientIDAvailable(_id.value(), _idCaps) == false){
      throw new DuplicateClientKeyException(_id.value());
    }
          
    _receiver.registerClient(_id.value(), _name.value(), _address.value());
  }
}

