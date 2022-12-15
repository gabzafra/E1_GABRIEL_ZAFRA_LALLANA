/*
 * Alumno: Gabriel Zafra Lallana DNI: 51451671G
 */
package dam2.e1.tienda.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import dam2.e1.tienda.config.WebConfig;
import dam2.e1.tienda.model.Product;

public class ProductDAO {
  private File localFile;
  private File tempFile;

  public ProductDAO() {
    localFile = new File(WebConfig.getConfig().getGlobalPath() + File.separator + "files"
        + File.separator + "productos.txt");
    initDAO();
    tempFile = new File(WebConfig.getConfig().getGlobalPath() + File.separator + "files"
        + File.separator + "temp.txt");
    initDAO();
  }

  private void initDAO() {
    boolean isEmpty = false;
    if (!localFile.exists()) {
      try {
        localFile.createNewFile();
        isEmpty = true;
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      try (BufferedReader output = new BufferedReader(new FileReader(localFile))) {
        isEmpty = output.readLine() == null;
      } catch (Exception e) {

      }
    }
    if (isEmpty) {
      createNewProduct(
          new Product(getNewId(), "judias", "Probalemente las mejores judias del mundo", 0.76, 11));
      createNewProduct(
          new Product(getNewId(), "cerveza", "Tu compañera en estos dias de examenes", 3.78, 3));
      createNewProduct(
          new Product(getNewId(), "lechuga", "La salud no está reñida con la redondez", 0.35, 5));
      createNewProduct(new Product(getNewId(), "leche",
          "Oferta en packs de seis, llevatelos por el precio de media docena", 9.33, 22));
      createNewProduct(
          new Product(getNewId(), "yogourt", "Nadie en su sano juicio compra esto", 2.8, 1));
    }
  }

  public Product getProductById(int id) {
    return getAllProducts().values().stream().filter(p -> p.getId() == id).findFirst()
        .orElse(new Product());
  }

  public HashMap<Integer, Product> getAllProducts() {
    HashMap<Integer, Product> productsMap = new HashMap<Integer, Product>();

    try (BufferedReader output = new BufferedReader(new FileReader(localFile))) {
      String cursor;
      while ((cursor = output.readLine()) != null) {
        String[] colsArr = cursor.split(":");
        int id = Integer.parseInt(colsArr[0]);
        productsMap.put(id, new Product(id, colsArr[1], colsArr[2], Double.parseDouble(colsArr[3]),
            Integer.parseInt(colsArr[4])));
      }
    } catch (Exception e) {
      return productsMap;
    }
    return productsMap;
  }

  public Product updateProduct(Product product) {
    HashMap<Integer, Product> productMap = getAllProducts();
    if (productMap.containsKey(product.getId())) {
      productMap.put(product.getId(), product);
      try {
        tempFile.createNewFile();
      } catch (IOException e) {
        return new Product();
      }
      try (BufferedWriter input = new BufferedWriter(new FileWriter(tempFile))) {
        for (Product currentProduct : productMap.values()) {
          String formatedRow = currentProduct.getId() + ":" + currentProduct.getName() + ":"
              + currentProduct.getDescription() + ":" + currentProduct.getPrice() + ":"
              + currentProduct.getStock();
          input.write(formatedRow);
          input.newLine();
        }
      } catch (Exception e) {
        return new Product();
      }

      localFile.delete();
      tempFile.renameTo(localFile);
      return getProductById(product.getId());

    } else {
      return new Product();
    }
  }

  public Product createNewProduct(Product product) {
    try (BufferedWriter input = new BufferedWriter(new FileWriter(localFile, true))) {
      String formatedRow = getNewId() + ":" + product.getName() + ":" + product.getDescription()
          + ":" + product.getPrice() + ":" + product.getStock();
      input.write(formatedRow);
      input.newLine();
    } catch (Exception e) {
      return new Product();
    }
    return getProductById(product.getId());
  }

  private int getNewId() {
    int maxId = 0;
    try (BufferedReader output = new BufferedReader(new FileReader(localFile))) {
      String cursor;
      while ((cursor = output.readLine()) != null) {
        String[] colsArr = cursor.split(":");
        int currentId = Integer.parseInt(colsArr[0]);
        if (currentId > maxId) {
          maxId = currentId;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    maxId++;
    return maxId;
  }
}
