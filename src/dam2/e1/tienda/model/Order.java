/*
 * Alumno: Gabriel Zafra Lallana DNI: 51451671G
 */
package dam2.e1.tienda.model;

import java.util.HashMap;

public class Order {
  private int id;
  private HashMap<Integer, Product> productList;
  private Client owner;

  public Order() {
    this(new HashMap<Integer, Product>(), new Client());
  }

  public Order(HashMap<Integer, Product> productList, Client owner) {
    this(0, productList, owner);
  }

  public Order(int id, HashMap<Integer, Product> productList, Client owner) {
    this.id = id;
    this.productList = productList;
    this.owner = owner;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public HashMap<Integer, Product> getProductList() {
    return productList;
  }

  public void setProductList(HashMap<Integer, Product> productList) {
    this.productList = productList;
  }

  public Client getOwner() {
    return owner;
  }

  public void setOwner(Client owner) {
    this.owner = owner;
  }
}
