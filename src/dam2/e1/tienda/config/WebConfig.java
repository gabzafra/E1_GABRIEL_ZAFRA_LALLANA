/*
 * Alumno: Gabriel Zafra Lallana DNI: 51451671G
 */
package dam2.e1.tienda.config;

public final class WebConfig {
  private static WebConfig instance;
  private String globalPath = "";

  private WebConfig() {}

  public static WebConfig getConfig() {
    if (instance == null) {
      instance = new WebConfig();
    }
    return instance;
  }

  public void setGlobalPath(String path) {
    globalPath = path;
  }

  public String getGlobalPath() {
    return globalPath;
  }
}
