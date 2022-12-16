repo https://github.com/gabzafra/E1_GABRIEL_ZAFRA_LALLANA

En este ejercicio he desarrollado todos los puntos pedidos, incluidos los de ampliación. En total he empleado aproximadamente unas doce horas.

La aplicación es una tienda web en que nos muestra inicialmente una lista de productos. Estos productos se presentan en tarjetas individuales con información del mismo, incluida su disponibilidad y precio. Cada tarjeta tiene un botón que permite añadir una unidad del producto a una cesta de la compra, siempre que no se sobrepase el inventario existente. Cada vez que añadimos un producto la disponibilidad de ese producto se actualiza en el escaparate.

En la parte superior hay una barra de navegación, que es común a todas las vistas de la aplicación. Inicialmente esta barra nos proporciona enlaces tanto a la vista inicial, al formulario de login y al de registro. Además tiene un enlace desactivado a la vista de la cesta de la compra. Cuando se añade algún producto a la cesta el enlace se activa, y muestra a su lado un indicador de cuantos productos hay en la cesta.

Si se pulsa en el enlace a login, se muestra un formulario para acceder.
Si se pulsa en el enlace de registro se muestra el formulario de creación de nuevos usuarios.

Estos formularios realizan validación en el lado del servidor de los datos introducidos, al tiempo que muestran mensajes de error si estos datos son erróneos.

Si el usuario se registra o hace login con éxito la barra de navegación pasa a mostrar un saludo con su nombre y un enlace para hacer logout en lugar de los enlaces de entrar y registrar.

Si se pulsa en ver cesta se accede a la vista de la cesta de la compra, con el detalle de las unidades de cada producto en la cesta, sus precios y coste total. Esta cesta ofrece la posibilidad de realizar la compra o de vaciar la cesta. Si se intenta completar una compra si esta auntenticado nos enviara al formulario de login, si nos autenticamos con éxito nos devolverá a la cesta para que confirmemos la compra.

Una vez confirmada la compra, los productos se restan del stock de la tienda, la cesta se vacía y se muestra al cliente una factura en pdf de su compra. Por ultimo se le devuelve a la página de entrada.

Comentario de las clases.

config/
    WebConfig -> Clase de configuración con la ruta del despliegue.
    
model/
    Client -> Almacena la información de un cliente.
    Order -> Almacena la información de un pedido.
    Product -> Almacena la información de un producto.   
dao/
    ClientDAO -> Clase de acceso a datos que maneja la persistencia de los clientes en el archivo usuarios.txt. Si no encuentra este archivo lo inicializará con unos usuarios por defecto.
        "adam@email.com", "aaaa", "Adam", "Alda Amador", "111111111"
        "betty@email.com", "bbbb", "Betty", "Bueno Bonito", "222222222"
        "charlie@email.com", "cccc", "Charlie", "Cielo Cela","333333333"
    OrderDAO -> Clase de acceso a datos que maneja el pedido del cliente, almacena los datos en memoria
    ProductoDAO -> Clase de acceso a satos que maneja los datos de los productos del archivo productos.txt. Si no encuentra este archivo lo inicializará con productos por defecto:
        "judias", "Probalemente las mejores judias del mundo", 0.76, 11
        "cerveza", "Tu compañera en estos dias de examenes", 3.78, 3
        "lechuga","La salud no está reñida con la redondez", 0.35, 5
        "leche","Oferta en packs de seis, llevatelos por el precio de media docena", 9.33, 22
        "yogourt", "Nadie en su sano juicio compra esto", 2.8, 1
service/
    ClientService -> Servicio para manejar clientes, incluye la validación de los formularios de entrada y registro.
    ProductService -> Servicio para obtener el listado de productos de la tienda
    PdfService -> Servicio/Utilidad para generar el PDF con la factura
    OrderService -> Servicio que maneja la cesta de la compra.
controller/
    AuthController -> Controlador de las peticiones de autenticación, se encarga de los formularios de login y registro, para lo que emplea el ClientService y añade al pedido su usuario propietario con OrderService.
    ProductsController -> Proporciona datos para la lista de productos de la tienda obteniendolos mediante ProductService, también utiliza OrderService para proporcionar información a la barra de navegación.
    OrdersController -> Tiene tanto rutas para pintar la cesta de la compra, como para las interacciones con esta. Para ello usa OrderService. Cuando genera la factura del cliente utiliza PdfService. 