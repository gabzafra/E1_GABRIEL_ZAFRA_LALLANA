package dam2.e1.tienda.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dam2.e1.tienda.config.WebConfig;
import dam2.e1.tienda.model.Client;
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
    } else if (request.getParameter("clear") != null) {
      Client client = oService.getOrder().getOwner();
      if (client.getId() > 0) {
        oService.resetOrder(client);
      } else {
        oService.resetOrder();
      }
      request.getRequestDispatcher("./").forward(request, response);
    } else if (request.getParameter("complete") != null) {
      Order currentOrder = oService.getOrder();
      // El usuario existe
      if (currentOrder.getOwner().getId() > 0) {
        oService.completeOrder();
        oService.generatePdfInvoice();
        oService.resetOrder(currentOrder.getOwner());
        // TODO enviar este request al pdf
        request.getRequestDispatcher("./").forward(request, response);
      } else {
        // El usuario no existe
        request.setAttribute("productsNumber", oService.getNumOfItems());
        request.getRequestDispatcher("auth-form.jsp").forward(request, response);
      }
    } else {

      Order order = oService.getOrder();

      Client client = oService.getOrder().getOwner();
      if (client.getId() > 0) {
        request.setAttribute("clientName", client.getName());
      }
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
