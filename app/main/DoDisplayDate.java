package woo.app.main;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import woo.core.StoreManager;

/**
 * Show current date.
 */
public class DoDisplayDate extends Command<StoreManager> {

  public DoDisplayDate(StoreManager storefront) {
    super(Label.SHOW_DATE, storefront);
  }

  @Override
  public final void execute() throws DialogException {
  	int i = _receiver.getDate();
  	_display.popup(Message.currentDate(i));
  }
}
