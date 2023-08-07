package woo.app.main;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import woo.core.StoreManager;

import woo.app.exception.InvalidDateException;

/**
 * Advance current date.
 */
public class DoAdvanceDate extends Command<StoreManager> {
  
  private Input<Integer> _nDaysToAdvance;

  public DoAdvanceDate(StoreManager receiver) {
    super(Label.ADVANCE_DATE, receiver);
    _nDaysToAdvance = _form.addIntegerInput(Message.requestDaysToAdvance());
  }

  @Override
  public final void execute() throws DialogException, InvalidDateException {
    _form.parse();

    if(_nDaysToAdvance.value() < 0){
      throw new InvalidDateException(_nDaysToAdvance.value());
    }
    
    _receiver.advanceDate(_nDaysToAdvance.value());
  }
}