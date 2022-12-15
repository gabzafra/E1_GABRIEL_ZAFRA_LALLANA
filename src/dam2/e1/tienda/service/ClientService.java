/*
 * Alumno: Gabriel Zafra Lallana DNI: 51451671G
 */
package dam2.e1.tienda.service;

import dam2.e1.tienda.dao.ClientDAO;
import dam2.e1.tienda.model.Client;

public class ClientService {
  private ClientDAO db = new ClientDAO();

  public Client getClientByEmail(String email) {
    return db.getClientByEmail(email);
  }

  public Client registerNewClient(Client client) {
    return db.createNewClient(client);
  }

  public String validateRegister(Client client, String pass2) {
    if (hasEmptyFields(client)) {
      return "No puede dejar los campos vacios.";
    } else if (!client.getPassword().equals(pass2)) {
      return "Las contraseñas no coinciden.";
    } else if (getClientByEmail(client.getEmail()).getId() > 0) {
      return "El email introducido ya esta en uso.";
    } else {
      return "";
    }
  }

  private boolean hasEmptyFields(Client client) {
    if (client.getEmail().isEmpty() || client.getEmail() == null) {
      return true;
    }
    if (client.getPassword().isEmpty() || client.getPassword() == null) {
      return true;
    }
    if (client.getName().isEmpty() || client.getName() == null) {
      return true;
    }
    if (client.getSurnames().isEmpty() || client.getSurnames() == null) {
      return true;
    }
    if (client.getPhone().isEmpty() || client.getPhone() == null) {
      return true;
    }
    return false;
  }

  public String validateLogin(String email, String pass) {
    if (email.isEmpty() || pass.isEmpty()) {
      return "Los campos no pueden estar vacios.";
    } else {
      Client client = getClientByEmail(email);
      if (client.getId() <= 0 || !client.getPassword().equals(pass)) {
        return "Los datos introducidos no son válidos";
      } else {
        return "";
      }
    }
  }

}
