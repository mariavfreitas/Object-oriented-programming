package woo.core;

import java.io.Serializable;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.Reader;

import java.util.Collection;
import java.util.ArrayList;

import woo.core.exception.BadEntryException;


	public class MyParser implements Serializable{

		private Store _store;

		MyParser(Store store){
			_store = store;
		}

		public void parseFile(String fileName) throws IOException, BadEntryException {

			try (
				BufferedReader reader = new BufferedReader(new FileReader(fileName))){
				String line;

				while((line = reader.readLine()) != null)
					processLine(line);
			} catch(BadEntryException wrongFileName){
				throw new BadEntryException(fileName);
			}
		}

		private void processLine(String line) throws BadEntryException{
			String[] components = line.split("\\|");

			switch(components[0]){
				case "SUPPLIER":
					parseSupplier(line, components);
					break;

				case "CLIENT":
					parseClient(line, components);
					break;

				case "BOX":
					parseBox(line, components);
					break;

				case "CONTAINER":
					parseContainer(line, components); 
					break;

				case "BOOK":
					parseBook(line, components); 
					break;

				default:
					throw new BadEntryException("Descrição inválida: " + components[0]);
			}
		}


		private void parseSupplier(String line, String[] components) throws BadEntryException {
			if(components.length != 4)
				throw new BadEntryException("Numero errado de componentes na descriçao do supplier (4): " + line);

			String id = components[1];
			String name = components[2];
			String address = components[3];

			_store.registerSupplier(id, name, address);
		}

		private void parseClient(String line, String[] components) throws BadEntryException {
			if(components.length != 4)
				throw new BadEntryException("Numero errado de componentes na descriçao do client (4): " + line);

			String id = components[1];
			String name = components[2];
			String address = components[3];

			_store.registerClient(id, name, address);
		}

		private void parseBox(String line, String[] components) throws BadEntryException {
			if(components.length != 7)
				throw new BadEntryException("Numero errado de componentes na descriçao da box (7): " + line);

			String id = components[1];
			String supplierKey = components[3];
			int price = Integer.parseInt(components[4]);
			int critValue = Integer.parseInt(components[5]);
			int stock = Integer.parseInt(components[6]);
			String serviceQlt = components[2];

			_store.registerBox(id, price, critValue, supplierKey, serviceQlt);
			_store.getProduct(id).setStock(stock);
		}

		private void parseContainer(String line, String[] components) throws BadEntryException {
			if(components.length != 8)
				throw new BadEntryException("Numero errado de componentes na descriçao do supplier (8): " + line);

			String id = components[1];
			String supplierKey = components[4];
			int price = Integer.parseInt(components[5]);
			int critValue = Integer.parseInt(components[6]);
			int stock = Integer.parseInt(components[7]);
			String serviceQlt = components[2];
			String serviceLvL = components[3];

			_store.registerContainer(id, price, critValue, supplierKey, serviceQlt, serviceLvL);
			_store.getProduct(id).setStock(stock);
		}

		private void parseBook(String line, String[] components) throws BadEntryException {
			if(components.length != 9)
				throw new BadEntryException("Numero errado de componentes na descriçao do supplier (8): " + line);

			String id = components[1];
			String supplierKey = components[5];
			int price = Integer.parseInt(components[6]);
			int critValue = Integer.parseInt(components[7]);
			int stock = Integer.parseInt(components[8]);
			String title = components[2];
			String author = components[3];
			String isbn = components[4];

			_store.registerBook(id, price, critValue, supplierKey, title, author, isbn);
			_store.getProduct(id).setStock(stock);
		}
		
	}