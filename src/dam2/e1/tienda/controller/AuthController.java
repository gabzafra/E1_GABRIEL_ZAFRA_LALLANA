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
    // TODO Auto-generated method stub
    response.getWriter().append("Served at: ").append(request.getContextPath());
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    initBackServices(request);
    if (request.getParameter("register") != null) {
      Client newClient = new Client(request.getParameter("email"), request.getParameter("pass"),
          request.getParameter("name"), request.getParameter("surnames"),
          request.getParameter("phone"));
      String errorMsg = cService.validateRegister(newClient, request.getParameter("pass2"));
      if (errorMsg.isEmpty()) {
        newClient = cService.registerNewClient(newClient);
        oService.asignClient(newClient);
        response.sendRedirect(request.getContextPath());
      } else {
        request.setAttribute("error", errorMsg);
        request.setAttribute("client", newClient);
        request.setAttribute("register", true);
        request.getRequestDispatcher("auth-form.jsp").forward(request, response);
      }
    } else {
      String email = request.getParameter("email");
      String pass = request.getParameter("pass");
      String errorMsg = cService.validateLogin(email, pass);
    }
  }

}
