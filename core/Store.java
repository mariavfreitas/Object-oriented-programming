package woo.core;

import java.io.Serializable;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import java.lang.Boolean;

import woo.core.exception.BadEntryException;

/**
 * Class Store implements a store.
 */
public class Store implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202009192006L;

  private MyParser _parser;

  private int _date;
  private int _transactionID = 0;
  private float _availableFunds;
  private float _accountedFunds;

  private ArrayList<Client> _clientList = new ArrayList<Client>();
  private ArrayList<Supplier> _supplierList = new ArrayList<Supplier>();
  private ArrayList<Product> _productList = new ArrayList<Product>();
  private ArrayList<Transaction> _transactionList = new ArrayList<Transaction>();
  private ArrayList<Notification> _notificationList = new ArrayList<Notification>();

  void Store(){
    _date = 0;
    _availableFunds = 0;
    _accountedFunds = 0;
  }

  /**
   * @param txtfile filename to be loaded.
   * @throws IOException
   * @throws BadEntryException
   */
  void importFile(String txtfile, Store s) throws IOException, BadEntryException{
    _parser = new MyParser(s);
    _parser.parseFile(txtfile);
    }


  /* Main functions */

  public int getDate(){
    return _date;
  }

  public void advanceDate(int nDays){
    _date = _date + nDays;
  }

  public float getAvailableFunds(){
    return _availableFunds;
  }

  public float getAccountedFunds(){
    return _accountedFunds;
  }

  public void addAvailableFunds(int value){
    _availableFunds += value;
  }
  public void removeAvailableFunds(int value){
    _availableFunds -= value;
  }

  public void addAccountedFunds(int value){
    _accountedFunds += value;
  }
  public void removeAccountedFunds(int value){
    _accountedFunds -= value;
  }



  /*Client functions */

  public ArrayList<Client> getClientList(){
    Collections.sort(_clientList, Comparator.comparing(Client::getID, String.CASE_INSENSITIVE_ORDER));
    return _clientList;
  }

  public Client getClient(String id){
    int len = _clientList.size();
    int i = 0;
    for(; i < len; i++){
      if(_clientList.get(i).getID().equals(id)){
        break; 
      }
    }
    return _clientList.get(i);
  }

  public boolean isClientIDAvailable(String id, String idCaps){
    int len = _clientList.size();
    for(int i = 0; i < len; i++){
      if((_clientList.get(i).getID().equals(id)) || (_clientList.get(i).getID().equals(idCaps))){
        return false;
      }
    }
    return true;
  }

  public void registerClient(String id, String name, String address){
    _clientList.add(new Client(id, name, address));
  }

  public ArrayList<Transaction> getClientTransactions(String cid){
    ArrayList<Transaction> clTransactions = new ArrayList<Transaction>();
    int len = _transactionList.size();
    for(int i = 0; i < len; i++){
      if(_transactionList.get(i).getClientID().equals(cid)){
        clTransactions.add(_transactionList.get(i));
      }
    }
    return clTransactions;
  }

  public ArrayList<Transaction> getPaidTransactions(ArrayList<Transaction> clTrans){
    ArrayList<Transaction> paidTrans = new ArrayList<Transaction>();
    int len = clTrans.size();
    for(int i = 0; i < len; i++){
      if(clTrans.get(i).isPaid()){
        paidTrans.add(clTrans.get(i));
      }
    }
    return paidTrans;
  }

  public void toggleProductNotification(String cID, String pID){
    Client c = getClient(cID);
    if(c.isIDBlocked(pID))
      c.removeBlockedID(pID);
    c.addBlockedID(pID);
  }

  public boolean isProductNotificationBlocked(String cid, String pid){
    Client c = getClient(cid);
    return c.isIDBlocked(pid);
  }

  /*Supplier functions */

  public ArrayList<Supplier> getSupplierList(){
    Collections.sort(_supplierList, Comparator.comparing(Supplier::getID, String.CASE_INSENSITIVE_ORDER));
    return _supplierList;
  }

  public boolean isSupplierIDAvailable(String id){
  int len = _supplierList.size();
  for(int i = 0; i < len; i++){
    if(_supplierList.get(i).getID().equals(id)){
      return false;
    }
  }
  return true;
  }

  public void registerSupplier(String id, String name, String address){
    _supplierList.add(new Supplier(id, name, address));
  }

  public void toggleTransaction(String id){
    int len = _supplierList.size();
    for(int i = 0; i < len; i++){
      if(_supplierList.get(i).getID().equals(id)){
        _supplierList.get(i).toggleTransaction();
        break;
      }
    }
  }

  public boolean isSupplierActive(String id){
    int len = _supplierList.size();
    for(int i = 0; i < len; i++){
      if(_supplierList.get(i).getID().equals(id)){
        if(_supplierList.get(i).isActive().equals("SIM"))
          return true;
        return false;
      }
    }
    return false;
  }

  public ArrayList<Transaction> getSupplierTransactions(String sid){
    ArrayList<Transaction> supTransactions = new ArrayList<Transaction>();
    int len = _transactionList.size();
    for(int i = 0; i < len; i++){
      if(_transactionList.get(i).getSupplierID().equals(sid)){
        supTransactions.add(_transactionList.get(i));
      }
    }
    return supTransactions;
  }

  public ArrayList<Transaction> getSupplierPaidTransactions(ArrayList<Transaction> supTrans){
    ArrayList<Transaction> paidTrans = new ArrayList<Transaction>();
    int len = supTrans.size();
    for(int i = 0; i < len; i++){
      if(supTrans.get(i).isPaid()){
        paidTrans.add(supTrans.get(i));
      }
    }
    return paidTrans;
  }



  /*Product functions */

  public void registerBox(String id, int price, int critValue,     //
                          String supplierID, String level){
    _productList.add(new Box(id, price, critValue, supplierID, level));
  }

  public void registerContainer(String id, int price, int critValue,     //
                          String supplierID, String level, String quality){
    _productList.add(new Container(id, price, critValue, supplierID, level, quality));
  }

  public void registerBook(String id, int price, int critValue,  //
                          String supplierID, String title,    //
                          String author, String isbn){
    _productList.add(new Book(id, price, critValue, supplierID, title, author, isbn));
  }

  public void changePrice(String id, int price){
    int len = _productList.size();
    for(int i = 0; i < len; i++){
      if (price < 0)
        break;
      if(_productList.get(i).getID().equals(id)){
        if(price < _productList.get(i).getPrice()){
          _productList.get(i).setPrice(price);
          _notificationList.add(new Notification(id, "BARGAIN", _productList.get(i).getPrice()));
        }
        else
          _productList.get(i).setPrice(price);
        break;
      }
    }
  }

  public Product getProduct(String id){
    int len = _productList.size();
    int i = 0;
    for(; i < len; i++){
      if(_productList.get(i).getID().equals(id)){
        break; 
      }
    }
    return _productList.get(i);
  }

  public ArrayList<Product> getProductList(){
    Collections.sort(_productList, Comparator.comparing(Product::getID, String.CASE_INSENSITIVE_ORDER));
    return _productList;
  }

  public boolean isProductIDAvailable(String id, String idCaps){
  int len = _productList.size();
  for(int i = 0; i < len; i++){
    if((_productList.get(i).getID().equals(id)) || (_productList.get(i).getID().equals(idCaps))){
      return false;
    }
  }
  return true;
  }

  public boolean isServiceLvl(String sLVL){
    for(ServiceLevel sl : ServiceLevel.values()){
      if(sl.name().equals(sLVL)){
        return true;
      }
    }
    return false;
  }

  public boolean isServiceQlt(String sQLT){
    for(ServiceQuality sq : ServiceQuality.values()){
      if(sq.name().equals(sQLT)){
        return true;
      }
    }
    return false;
  }

  /*Transaction functions */

  public int getOrderValue(ArrayList<String> productKeyList, ArrayList<Integer> amount){
    Product p;
    int tValue = 0;
    for(int i=0; i < productKeyList.size(); i++){
      p = getProduct(productKeyList.get(i));
      tValue += p.getPrice()*amount.get(i);
    }
    return tValue;
  }

  public int getSaleValue(String productID, int amount){
    Product p = getProduct(productID);
    int tValue = p.getPrice()*amount;
    return tValue;
  }

  public void registerSale(String clientID, String productKey, int amount, int deadline){
    int tValue = getSaleValue(productKey, amount);

    Client c = getClient(clientID);
    c.addPoints(tValue);
    c.addValuePurchased(tValue);

    Product p = getProduct(productKey);
    p.reduceStock(amount);

    addAccountedFunds(tValue);

    _transactionList.add(new Sale(_transactionID, deadline, clientID, productKey, amount, tValue));
    _transactionID += 1;
  }

  public void registerOrder(String supplierID, ArrayList<String> productKeyList, ArrayList<Integer> amount){
    int tValue = getOrderValue(productKeyList, amount);
    
    removeAccountedFunds(tValue);
    removeAvailableFunds(tValue);

    int len = productKeyList.size();
    for(int i = 0; i < len; i++){
      Product p = getProduct(productKeyList.get(i));
      if(p.getStock() == 0)
        _notificationList.add(new Notification(p.getID(), "NEW", p.getPrice()));
      p.addStock(amount.get(i));
    }

    _transactionList.add(new Order(_transactionID, _date, supplierID, productKeyList, amount, tValue));
    _transactionID += 1;

  }

  public boolean transactionExists(int id){
  int len = _transactionList.size();
  for(int i = 0; i < len; i++){
    if(_transactionList.get(i).getID() == id){
      return true;
    }
  }
  return false;
  }

  public Transaction getTransaction(int id){
    int len = _transactionList.size();
    int i = 0;
    for(; i < len; i++){
      if(_transactionList.get(i).getID() == id){
        break; 
      }
    }
    return _transactionList.get(i);
  }

  public int getStock(String pid){
    Product p = getProduct(pid);
    return p.getStock();
  }

  public void payTransaction(int tid){
    int value = _transactionList.get(tid).pay(_date);
    addAvailableFunds(value);
  }




  /*Notification functions */

public ArrayList<Notification> getCNtfList(String cid){
    ArrayList<Notification> _clNotifs = new ArrayList<Notification>();
    Client c = getClient(cid);

    int len = _notificationList.size();
    for(int i = 0; i < len; i++){

      if(c.isIDBlocked(_notificationList.get(i).getPid()))
        continue;

      if(_notificationList.get(i).hasClientSeen(cid))
        continue;

      _clNotifs.add(_notificationList.get(i));
      _notificationList.get(i).addClientShown(cid);
    }

    return _clNotifs;
  }

}
