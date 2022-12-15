package dam2.e1.tienda.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dam2.e1.tienda.config.WebConfig;
import dam2.e1.tienda.model.Order;
import dam2.e1.tienda.service.OrderService;

@WebServlet("/order")
public class OrdersController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private OrderService oService;
  private final WebConfig WEB_CONFIG = WebConfig.getConfig();

  public OrdersController() {
    super();
  }

  private void initBackServices(HttpServletRequest request) {
    if (WEB_CONFIG.getGlobalPath() == null) {
      WEB_CONFIG.setGlobalPath(request.getServletContext().getRealPath(""));
    }
    if (oService == null) {
      oService = new OrderService();
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    initBackServices(request);
    String productId = request.getParameter("id");
    if (productId != null) {
      oService.addProductToOrder(Integer.parseInt(productId));
      request.getRequestDispatcher("./").forward(request, response);
    } else {
      Order order = oService.getOrder();

      request.setAttribute("productsNumber", oService.getNumOfItems());
      request.setAttribute("orders", order.getProductList());
      request.getRequestDispatcher("order.jsp").forward(request, response);
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    // doGet(request, response);
    System.out.println("post -> order");
  }

}
