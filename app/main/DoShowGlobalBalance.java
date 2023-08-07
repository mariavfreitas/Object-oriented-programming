package woo.app.main;

import java.lang.Math;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;

/**
 * Show global balance.
 */
public class DoShowGlobalBalance extends Command<StoreManager> {

  public DoShowGlobalBalance(StoreManager receiver) {
    super(Label.SHOW_BALANCE, receiver);
  }

  @Override
  public final void execute() {
  	int _availableF = Math.round(_receiver.getAvailableFunds());
  	int _accountedF = Math.round(_receiver.getAccountedFunds());
  	_display.popup(Message.currentBalance(_availableF, _accountedF));
  }
}
