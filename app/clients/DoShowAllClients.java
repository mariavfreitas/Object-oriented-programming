package woo.app.clients;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException; 
import pt.tecnico.po.ui.Input;

import woo.core.StoreManager;
import woo.core.Client;

import java.util.ArrayList;

/**
 * Show all clients.
 */
public class DoShowAllClients extends Command<StoreManager> {

	private ArrayList<Client> clientList = new ArrayList<Client>();

  public DoShowAllClients(StoreManager storefront) {
    super(Label.SHOW_ALL_CLIENTS, storefront);
  }

  @Override
  public void execute() throws DialogException {

  	clientList = _receiver.getClientList();
  	int len = clientList.size();
  	
    for(int i = 0; i < len; i++){
  		_display.addLine(clientList.get(i).toString());
  	}

    _display.display();
  }
}
