/*
 * Alumno: Gabriel Zafra Lallana DNI: 51451671G
 */
package dam2.e1.tienda.model;

public class Product {
  private int id;
  private String name;
  private String description;
  private double price;
  private int stock;

  public Product() {
    this("", "", 0, 0);
  }

  public Product(String name, String description, double price, int stock) {
    this(0, name, description, price, stock);
  }

  public Product(int id, String name, String description, double price, int stock) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.stock = stock;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }
}
