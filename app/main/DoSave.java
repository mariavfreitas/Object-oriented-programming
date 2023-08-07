package woo.app.main;

import java.io.IOException;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import woo.core.StoreManager;

/**
 * Save current state to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<StoreManager> {


  private Input<String> fileName;

  /** @param receiver */
  public DoSave(StoreManager receiver) {
    super(Label.SAVE, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException{
    try{
      if(_receiver.isFileAssociated()){
        _receiver.save();
      } else {
      fileName = _form.addStringInput(Message.newSaveAs());
      _form.parse();
      _receiver.saveAs(fileName.value());
      }
    } catch(IOException e){
      e.printStackTrace();
    } finally{
      _form.clear();
    }
  }
}
