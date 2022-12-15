/*
 * Alumno: Gabriel Zafra Lallana DNI: 51451671G
 */
package dam2.e1.tienda.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import dam2.e1.tienda.model.Client;
import dam2.e1.tienda.model.Order;
import dam2.e1.tienda.model.Product;

public class OrderDAO {
  private static OrderDAO instance;
  // En este ejercicio no se contempla mantener un registro de los
  // pedidos realizados por cada cliente. Así que solo consideramos
  // la existencia de un solo pedido con id = 1
  private final Order ORDER_DATA;

  private OrderDAO() {
    ORDER_DATA = new Order();
    ORDER_DATA.setId(1);
  }

  public static OrderDAO getInstance() {
    if (instance == null) {
      instance = new OrderDAO();
    }
    return instance;
  }

  public Order getOrder() {
    return deepCloneOrder(ORDER_DATA);
  }

  public Order updateOrder(Order order) {
    if (ORDER_DATA.getId() == order.getId()) {
      order = deepCloneOrder(order);
      ORDER_DATA.setProductList(order.getProductList());
      ORDER_DATA.setOwner(order.getOwner());
      return deepCloneOrder(ORDER_DATA);
    } else {
      return new Order();
    }
  }

  public boolean resetOrder(Client client) {
    ORDER_DATA.setProductList(new HashMap<Integer, Product>());
    ORDER_DATA.setOwner(client);
    return true;
  }

  private Order deepCloneOrder(Order order) {
    Order newOrder = new Order();
    newOrder.setId(order.getId());
    newOrder.setProductList((HashMap<Integer, Product>) order.getProductList().entrySet().stream()
        .collect(Collectors.toMap(Map.Entry::getKey, entry -> deepCloneProduct(entry.getValue()))));
    newOrder.setOwner(deepCloneClient(order.getOwner()));
    return newOrder;
  }

  private Product deepCloneProduct(Product product) {
    return new Product(product.getId(), product.getName(), product.getDescription(),
        product.getPrice(), product.getStock());
  }

  private Client deepCloneClient(Client client) {
    return new Client(client.getId(), client.getEmail(), client.getPassword(), client.getName(),
        client.getSurnames(), client.getPhone());
  }
}
