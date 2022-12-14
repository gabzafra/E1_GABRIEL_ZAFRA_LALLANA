/*
 * Alumno: Gabriel Zafra Lallana DNI: 51451671G
 */
package dam2.e1.tienda.service;

import java.util.HashMap;
import dam2.e1.tienda.dao.ProductDAO;
import dam2.e1.tienda.model.Product;

public class ProductService {
  private ProductDAO db = new ProductDAO();

  public HashMap<Integer, Product> getAllProducts() {
    return db.getAllProducts();
  }
}
