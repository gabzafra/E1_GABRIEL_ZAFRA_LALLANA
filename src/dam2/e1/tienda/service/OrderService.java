/*
 * Alumno: Gabriel Zafra Lallana DNI: 51451671G
 */
package dam2.e1.tienda.service;

import java.util.HashMap;
import dam2.e1.tienda.dao.OrderDAO;
import dam2.e1.tienda.dao.ProductDAO;
import dam2.e1.tienda.model.Client;
import dam2.e1.tienda.model.Order;
import dam2.e1.tienda.model.Product;

public class OrderService {
  private OrderDAO ordersDb = OrderDAO.getInstance();
  private ProductDAO productsDb = new ProductDAO();


  public void generatePdfInvoice() {
    System.out.println("CREANDO EL PDF");
  }

  public Order addProductToOrder(int productId) {
    Order order = getOrder();
    HashMap<Integer, Product> productList = order.getProductList();

    if (productList.containsKey(productId)) {
      int wantedProducts = productList.get(productId).getStock();
      wantedProducts++;
      if (wantedProducts < productsDb.getProductById(productId).getStock()) {
        productList.get(productId).setStock(wantedProducts);
        return ordersDb.updateOrder(order);
      } else {
        return new Order();
      }
    } else {
      Product newProduct = productsDb.getProductById(productId);
      if (newProduct.getId() <= 0 || newProduct.getStock() <= 0) {
        return new Order();
      } else {
        newProduct.setStock(1);
        productList.put(newProduct.getId(), newProduct);
        return ordersDb.updateOrder(order);
      }
    }
  }

  public void completeOrder() {
    Order order = getOrder();
    order.getProductList().values().forEach(product -> updateProductStock(product));
  }

  private void updateProductStock(Product product) {
    Product storedProduct = productsDb.getProductById(product.getId());
    int newStock = storedProduct.getStock() - product.getStock();
    storedProduct.setStock(newStock);
    productsDb.updateProduct(storedProduct);
  }

  public int getNumOfItems() {
    Order order = getOrder();
    HashMap<Integer, Product> productList = order.getProductList();
    return productList.values().stream().mapToInt(product -> product.getStock()).sum();
  }

  public Order getOrder() {
    return ordersDb.getOrder();
  }

  public boolean resetOrder() {
    return ordersDb.resetOrder();
  }

  public void asignClient(Client newClient) {
    Order order = getOrder();
    if (order.getOwner().getId() > 0) {
      resetOrder();
      order = getOrder();
      order.setOwner(newClient);
      ordersDb.updateOrder(order);
    } else {
      order.setOwner(newClient);
      ordersDb.updateOrder(order);
    }

  }
}
