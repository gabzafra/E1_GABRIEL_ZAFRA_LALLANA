/*
 * Alumno: Gabriel Zafra Lallana DNI: 51451671G
 */
package dam2.e1.tienda.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dam2.e1.tienda.config.WebConfig;
import dam2.e1.tienda.model.Client;
import dam2.e1.tienda.service.ClientService;
import dam2.e1.tienda.service.OrderService;

@WebServlet("/auth")
public class AuthController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private ClientService cService;
  private OrderService oService;
  private final WebConfig WEB_CONFIG = WebConfig.getConfig();

  public AuthController() {
    super();
  }

  private void initBackServices(HttpServletRequest request) {
    if (WEB_CONFIG.getGlobalPath() == null) {
      WEB_CONFIG.setGlobalPath(request.getServletContext().getRealPath(""));
    }
    if (cService == null) {
      cService = new ClientService();
    }
    if (oService == null) {
      oService = new OrderService();
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    initBackServices(request);
    if (request.getParameter("exit") != null) {
      oService.resetOrder();
      response.sendRedirect(request.getContextPath());
    } else if (request.getParameter("register") != null) {
      request.setAttribute("productsNumber", oService.getNumOfItems());
      request.getRequestDispatcher("auth-form.jsp?register=true").forward(request, response);
    } else {
      request.setAttribute("productsNumber", oService.getNumOfItems());
      request.getRequestDispatcher("auth-form.jsp").forward(request, response);
    }

  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    initBackServices(request);
    Client client = new Client(request.getParameter("email"), request.getParameter("pass"),
        request.getParameter("name"), request.getParameter("surnames"),
        request.getParameter("phone"));
    if (request.getParameter("register") != null) {
      String errorMsg = cService.validateRegister(client, request.getParameter("pass2"));
      if (errorMsg.isEmpty()) {
        client = cService.registerNewClient(client);
        oService.asignClient(client);
        response.sendRedirect(request.getContextPath());
      } else {
        request.setAttribute("error", errorMsg);
        request.setAttribute("client", client);
        request.setAttribute("register", true);
        request.setAttribute("productsNumber", oService.getNumOfItems());
        request.getRequestDispatcher("auth-form.jsp").forward(request, response);
      }
    } else {
      String errorMsg = cService.validateLogin(client.getEmail(), client.getPassword());
      if (errorMsg.isEmpty()) {
        client = cService.getClientByEmail(client.getEmail());
        oService.asignClient(client);
        if (oService.getNumOfItems() > 0) {
          response.sendRedirect(request.getContextPath() + "/order");
        } else {
          response.sendRedirect(request.getContextPath());
        }
      } else {
        request.setAttribute("error", errorMsg);
        request.setAttribute("client", client);
        request.setAttribute("productsNumber", oService.getNumOfItems());
        request.getRequestDispatcher("auth-form.jsp").forward(request, response);
      }

    }
  }

}
