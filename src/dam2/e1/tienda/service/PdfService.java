/*
 * Alumno: Gabriel Zafra Lallana DNI: 51451671G
 */
package dam2.e1.tienda.service;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import dam2.e1.tienda.config.WebConfig;
import dam2.e1.tienda.dao.OrderDAO;
import dam2.e1.tienda.model.Product;

public class PdfService {
  private File localFile;
  private OrderDAO ordersDb = OrderDAO.getInstance();

  public PdfService() {
    localFile = new File(WebConfig.getConfig().getGlobalPath() + File.separator + "files"
        + File.separator + "factura.pdf");
  }

  public boolean generatePdfInvoice() {
    Document docu = new Document(PageSize.A4, 20, 20, 70, 50);
    PdfWriter output = null;

    HashMap<Integer, Product> productList = ordersDb.getOrder().getProductList();
    try {
      output = PdfWriter.getInstance(docu, new FileOutputStream(localFile));
      docu.open();

      Paragraph title =
          new Paragraph("Este es su pedido " + ordersDb.getOrder().getOwner().getName() + ":",
              FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD, BaseColor.BLACK));
      title.setAlignment(Element.ALIGN_CENTER);

      title.setSpacingBefore(0);
      title.setSpacingAfter(18);

      docu.add(title);

      productList.values().forEach(product -> {
        try {
          docu.add(formatRow(product));
        } catch (DocumentException e) {

        }
      });



      Paragraph finalScore =
          new Paragraph("El precio total es: " + getTotalPrice() + " €", FontFactory
              .getFont(FontFactory.HELVETICA, 16, Font.NORMAL, new BaseColor(150, 150, 150)));
      finalScore.setAlignment(Element.ALIGN_RIGHT);
      finalScore.setSpacingBefore(0);

      docu.add(finalScore);

      docu.close();
      output.close();

      try {
        Desktop.getDesktop().open(localFile);
      } catch (IOException ex) {
        return false;
      }

    } catch (Exception e) {
      return false;
    }
    return true;
  }

  private static Paragraph formatRow(Product producto) {

    Paragraph text = new Paragraph();

    Font blackFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);

    String subtotal = roundDouble(producto.getPrice() * producto.getStock());

    Chunk row = new Chunk(producto.getName() + " " + producto.getStock() + " unidades "
        + producto.getPrice() + "€ por unidad -> " + subtotal + " €.", blackFont);

    text.add(row);

    text.add(Chunk.NEWLINE);

    text.setSpacingBefore(0);
    text.setSpacingAfter(18);
    return text;
  }

  private String getTotalPrice() {
    double total = ordersDb.getOrder().getProductList().values().stream()
        .mapToDouble(p -> p.getPrice() * p.getStock()).sum();
    return roundDouble(total);
  }

  private static String roundDouble(double n) {
    return String.format("%.2f", n);
  }
}
