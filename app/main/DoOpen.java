package woo.app.main;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;

import woo.core.StoreManager;

import woo.app.exception.FileOpenFailedException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Open existing saved state.
 */
public class DoOpen extends Command<StoreManager> {

  private Input<String> fileName;

  /** @param receiver */
  public DoOpen(StoreManager receiver) {
    super(Label.OPEN, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException, FileOpenFailedException {
    try {
        fileName = _form.addStringInput(Message.openFile());
        _form.parse();
        _receiver.load(fileName.value());
        _form.clear();
    }
    catch (FileNotFoundException e) {
        _display.add("Abrir: Operação inválida: Problema ao abrir '");
        _display.add(fileName.value());
        _display.add("'.");
        _display.display();
    }
    catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }
  }
}
