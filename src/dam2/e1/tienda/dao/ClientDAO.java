/*
 * Alumno: Gabriel Zafra Lallana DNI: 51451671G
 */
package dam2.e1.tienda.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import dam2.e1.tienda.config.WebConfig;
import dam2.e1.tienda.model.Client;

public class ClientDAO {
  private File localFile;

  public ClientDAO() {
    localFile = new File(WebConfig.getConfig().getGlobalPath() + File.separator + "files"
        + File.separator + "usuarios.txt");
    System.out.println(localFile);
    System.out.println(WebConfig.getConfig().getGlobalPath());
    initDAO();
  }

  private void initDAO() {
    boolean isEmpty = false;
    if (!localFile.exists()) {
      try {
        localFile.createNewFile();
        isEmpty = true;
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      try (BufferedReader output = new BufferedReader(new FileReader(localFile))) {
        isEmpty = output.readLine() == null;
      } catch (Exception e) {

      }
    }
    if (isEmpty) {
      createNewClient(
          new Client(getNewId(), "adam@email.com", "aaaa", "Adam", "Alda Amador", "111111111"));
      createNewClient(
          new Client(getNewId(), "betty@email.com", "bbbb", "Betty", "Bueno Bonito", "222222222"));
      createNewClient(new Client(getNewId(), "charlie@email.com", "cccc", "Charlie", "Cielo Cela",
          "333333333"));
    }
  }

  public Client getClientById(int id) {
    Client foundClient = new Client();

    try (BufferedReader output = new BufferedReader(new FileReader(localFile))) {
      String cursor;
      while ((cursor = output.readLine()) != null) {
        String[] colsArr = cursor.split(":");
        int recordId = Integer.parseInt(colsArr[0]);
        if (recordId == id) {
          foundClient.setId(recordId);
          foundClient.setEmail(colsArr[1]);
          foundClient.setPassword(colsArr[2]);
          foundClient.setName(colsArr[3]);
          foundClient.setSurnames(colsArr[4]);
          foundClient.setPhone(colsArr[5]);

          return foundClient;
        }
      }
    } catch (Exception e) {
      return foundClient;
    }
    return foundClient;
  }

  public Client getClientByEmail(String email) {
    Client foundClient = new Client();

    try (BufferedReader output = new BufferedReader(new FileReader(localFile))) {
      String cursor;
      while ((cursor = output.readLine()) != null) {
        String[] colsArr = cursor.split(":");
        if (email.equals(colsArr[1])) {
          foundClient.setId(Integer.parseInt(colsArr[0]));
          foundClient.setEmail(colsArr[1]);
          foundClient.setPassword(colsArr[2]);
          foundClient.setName(colsArr[3]);
          foundClient.setSurnames(colsArr[4]);
          foundClient.setPhone(colsArr[5]);

          return foundClient;
        }
      }
    } catch (Exception e) {
      return foundClient;
    }
    return foundClient;
  }

  public Client createNewClient(Client client) {
    try (BufferedWriter input = new BufferedWriter(new FileWriter(localFile, true))) {
      String formatedRow = getNewId() + ":" + client.getEmail() + ":" + client.getPassword() + ":"
          + client.getName() + ":" + client.getSurnames() + ":" + client.getPhone();
      input.write(formatedRow);
      input.newLine();
    } catch (Exception e) {
      return new Client();
    }
    return getClientById(client.getId());
  }

  private int getNewId() {
    int maxId = 0;
    try (BufferedReader output = new BufferedReader(new FileReader(localFile))) {
      String cursor;
      while ((cursor = output.readLine()) != null) {
        String[] colsArr = cursor.split(":");
        int currentId = Integer.parseInt(colsArr[0]);
        if (currentId > maxId) {
          maxId = currentId;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    maxId++;
    return maxId;
  }
}
