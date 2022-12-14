package dam2.e1.tienda.controller;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dam2.e1.tienda.config.WebConfig;
import dam2.e1.tienda.model.Product;
import dam2.e1.tienda.service.ProductService;

/**
 * Servlet implementation class ProductsController
 */
@WebServlet({"/product", ""})
public class ProductsController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private ProductService pService;
  private final WebConfig WEB_CONFIG = WebConfig.getConfig();


  public ProductsController() {
    super();
  }

  private void initBackServices(HttpServletRequest request) {
    if (WEB_CONFIG.getGlobalPath() == null) {
      WEB_CONFIG.setGlobalPath(request.getServletContext().getRealPath(""));
    }
    if (pService == null) {
      pService = new ProductService();
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    initBackServices(request);

    HashMap<Integer, Product> productList = pService.getProductsInStock();

    request.setAttribute("list", productList);
    request.getRequestDispatcher("index.jsp").forward(request, response);
  }

}
