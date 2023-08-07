package woo.app.clients;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import java.util.ArrayList;

import woo.core.StoreManager;
import woo.core.Notification;

import woo.app.exception.UnknownClientKeyException;

/**
 * Show client.
 */
public class DoShowClient extends Command<StoreManager> {

  private ArrayList<Notification> _notificationList = new ArrayList<Notification>();
  private Input<String> _clientKey;

  public DoShowClient(StoreManager storefront) {
    super(Label.SHOW_CLIENT, storefront);
    _clientKey = _form.addStringInput(Message.requestClientKey());
  }

  @Override
  public void execute() throws DialogException, UnknownClientKeyException {
    _form.parse();

    String _idCaps = _clientKey.value().toUpperCase();

    if(_receiver.isClientIDAvailable(_clientKey.value(), _idCaps)){
      throw new UnknownClientKeyException(_clientKey.value());
    }

    String str = _receiver.getClient(_idCaps).toString();
      _display.addLine(str);
    _notificationList = _receiver.getCNtfList(_idCaps);

    for(int i = 0; i < _notificationList.size(); i++){
      _display.addLine(_notificationList.get(i).toString());
    }

    _display.display();
  }
}