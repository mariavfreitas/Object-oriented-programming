package woo.core;

import java.io.Serializable;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

import java.util.ArrayList;
import java.util.Arrays;

import woo.core.exception.UnavailableFileException;
import woo.core.exception.MissingFileAssociationException;
import woo.core.exception.ImportFileException;
import woo.core.exception.BadEntryException;

    /*
    *   INSTITUTO SUPERIOR TÉCNICO
    *
    *    PROGRAMAÇÃO COM OBJECTOS
    *
    *         PROJECT WOO
    *        
    * FEITO POR:
    * 73942 - MIGUEL P. MESTRE
    * 96757 - MARIA N.A.V. FREITAS
    */


/**
 * StoreManager: façade for the core classes.
 */
public class StoreManager {

  /** Current filename. */
  private String _filename = "";

  /** The actual store. */
  private Store _store = new Store();

  private boolean _fileAssociated = false;

  public boolean isFileAssociated(){
    return _fileAssociated;
  }

  public void setFileName(String fn){
    _filename = fn;
  }

  public String getFileName(){
    return _filename;
  }

  /**
   * @throws IOException
   * @throws FileNotFoundException
   * @throws MissingFileAssociationException
   */
  public void save() throws IOException{
    ObjectOutputStream oos =  new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_filename)));
    oos.writeObject(_store);
    oos.close();
  }

  /**
   * @param filename
   * @throws MissingFileAssociationException
   * @throws IOException
   * @throws FileNotFoundException
   */
  public void saveAs(String filename) throws IOException {
    _filename = filename;
    save();
    _fileAssociated = true;

  }

  /**
   * @param filename
   * @throws UnavailableFileException
   */
  public void load(String filename) throws IOException, ClassNotFoundException {
      ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));
      _store = (Store) ois.readObject();
      _filename = filename;
      _fileAssociated = true;
      ois.close();
  }

  /**
   * @param textfile
   * @throws ImportFileException
   */
  public void importFile(String textfile) throws ImportFileException {
    try {
      _store.importFile(textfile, _store);
    } catch (IOException | BadEntryException /* FIXME maybe other exceptions */ e) {
      throw new ImportFileException(textfile);
    }
  }

  public int getDate(){
    return _store.getDate();
  }

  public void advanceDate(int nDays){
     _store.advanceDate(nDays);
  }

  public float getAvailableFunds(){
    return _store.getAvailableFunds();
  }

  public float getAccountedFunds(){
    return _store.getAccountedFunds();
  }


  /* Client functions */

  public ArrayList<Client> getClientList(){
    return _store.getClientList();
  }

  public Client getClient(String id){
    return _store.getClient(id);
  }

  public boolean isClientIDAvailable(String id, String idCaps){
    return _store.isClientIDAvailable(id, idCaps);
  }

  public void registerClient(String id, String name, String address){
    _store.registerClient(id, name, address);
  }

  public ArrayList<Transaction> getClientTransactions(String cid){
    return _store.getClientTransactions(cid);
  }

  public ArrayList<Transaction> getPaidTransactions(ArrayList<Transaction> clTrans){
    return _store.getPaidTransactions(clTrans);
  }

  public void toggleProductNotification(String cid, String pid){
    _store.toggleProductNotification(cid, pid);
  }

  public boolean isProductNotificationBlocked(String cid, String pid){
    return _store.isProductNotificationBlocked(cid, pid);
  }


  /* Supplier functions */

  public ArrayList<Supplier> getSupplierList(){
    return _store.getSupplierList();
  }

  public void registerSupplier(String id, String name, String address){
    _store.registerSupplier(id, name, address);
  }

  public boolean isSupplierIDAvailable(String id){
    return _store.isSupplierIDAvailable(id);
  }

  public void toggleTransaction(String id){
    _store.toggleTransaction(id);
  }

  public boolean isSupplierActive(String id){
    return _store.isSupplierActive(id);
  }

  public ArrayList<Transaction> getSupplierTransactions(String id){
    return _store.getSupplierTransactions(id);
  }

  public ArrayList<Transaction> getSupplierPaidTransactions(ArrayList<Transaction> supTrans){
    return _store.getSupplierPaidTransactions(supTrans);
  }


  /* Product functions */

  public void registerBox(String id, int price, int critValue,     //
                            String supplierID, String level){
    _store.registerBox(id, price, critValue, supplierID, level);
  }

  public void registerContainer(String id, int price, int critValue,
                                String supplierID, String level, String quality){
    _store.registerContainer(id, price, critValue, supplierID, level, quality);
  }

  public void registerBook(String id, int price, int critValue,
                              String supplierID, String title,
                                    String author, String isbn){
    _store.registerBook(id, price, critValue, supplierID, title, author, isbn);
  }

  public ArrayList<Product> getProductList(){
    return _store.getProductList();
  }

  public void changePrice(String id, int price){
    _store.changePrice(id, price);
  }

  public boolean isProductIDAvailable(String id, String idCaps){
    return _store.isProductIDAvailable(id, idCaps);
  }

  public boolean isServiceLvl(String sLVL){
    return _store.isServiceLvl(sLVL);
  }

  public boolean isServiceQlt(String sQLT){
    return _store.isServiceQlt(sQLT);
  }

  /* Transaction functions */

  public void registerSale(String clientID, String productKey, int amount, int deadline){
    _store.registerSale(clientID, productKey, amount, deadline);
  }

  public void registerOrder(String supplierID, ArrayList<String> productKeyList, 
                                                    ArrayList<Integer> amount){
    _store.registerOrder(supplierID, productKeyList, amount);
  }

  public boolean transactionExists(int id){
    return _store.transactionExists(id);
  }

  public Transaction getTransaction(int id){
    return _store.getTransaction(id);
  }

  public int getStock(String pid){
    return _store.getStock(pid);
  }

  public void payTransaction(int tid){
    _store.payTransaction(tid);
  }

  /* Notification functions */

  public ArrayList<Notification> getCNtfList(String cid){
    return _store.getCNtfList(cid);
  }

}

