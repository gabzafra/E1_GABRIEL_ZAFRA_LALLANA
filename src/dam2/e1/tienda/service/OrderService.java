/*
 * Alumno: Gabriel Zafra Lallana DNI: 51451671G
 */
package dam2.e1.tienda.service;

import java.util.HashMap;
import dam2.e1.tienda.dao.OrderDAO;
import dam2.e1.tienda.dao.ProductDAO;
import dam2.e1.tienda.model.Order;
import dam2.e1.tienda.model.Product;

public class OrderService {
  private OrderDAO ordersDb = new OrderDAO();
  private ProductDAO productsDb = new ProductDAO();


  public Order startNewOrder() {
    return ordersDb.createNewOrder();
  }

  public Order addProductToOrder(int productId, Order order) {
    HashMap<Integer, Product> productList = order.getProductList();
    if (productList.containsKey(productId)) {
      int wantedProducts = productList.get(productId).getStock();
      wantedProducts++;
      if (wantedProducts > productsDb.getProductById(productId).getStock()) {
        productList.get(productId).setStock(wantedProducts);
        return ordersDb.updateOrder(order);
      } else {
        return new Order();
      }
    } else {
      return new Order();
    }
  }

  public Order getOrderById(int id) {
    return ordersDb.getOrderById(id);
  }

  public boolean deleteOrder(Order order) {
    return ordersDb.deleteOrder(order);
  }
}
