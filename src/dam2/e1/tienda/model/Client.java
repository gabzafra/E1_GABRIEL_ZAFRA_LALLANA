/*
 * Alumno: Gabriel Zafra Lallana DNI: 51451671G
 */
package dam2.e1.tienda.model;

public class Client {
  private int id;
  private String email;
  private String password;
  private String name;
  private String surnames;
  private String phone;

  public Client() {
    this("", "", "", "", "");
  }

  public Client(String email, String password, String name, String surnames, String phone) {
    this(0, email, password, name, surnames, phone);
  }

  public Client(int id, String email, String password, String name, String surnames, String phone) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.name = name;
    this.surnames = surnames;
    this.phone = phone;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurnames() {
    return surnames;
  }

  public void setSurnames(String surnames) {
    this.surnames = surnames;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
}
