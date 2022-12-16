/*
 * Alumno: Gabriel Zafra Lallana DNI: 51451671G
 */
package dam2.e1.tienda.service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import dam2.e1.tienda.dao.ProductDAO;
import dam2.e1.tienda.model.Order;
import dam2.e1.tienda.model.Product;

public class ProductService {
  private ProductDAO db = new ProductDAO();

  public HashMap<Integer, Product> getProductsInStock(Order order) {
    HashMap<Integer, Product> productList = db.getAllProducts();
    order.getProductList().forEach(
        (k, p) -> productList.replace(k, adjustProductAvailability(productList.get(k), p)));
    return (HashMap<Integer, Product>) productList.entrySet().stream()
        .filter(entry -> entry.getValue().getStock() > 0)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  private static Product adjustProductAvailability(Product pStock, Product pOrder) {
    pStock.setStock(pStock.getStock() - pOrder.getStock());
    return pStock;
  }

}
