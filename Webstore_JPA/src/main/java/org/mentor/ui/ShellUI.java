package org.mentor.ui;

import org.h2.jdbc.JdbcSQLNonTransientConnectionException;
import org.mentor.domain.Pack;
import org.mentor.exceptions.TriedToPurchaseMoreThanStoredException;
import org.mentor.model.Order;
import org.mentor.model.SubOrder;
import org.mentor.model.Product;
import org.mentor.service.OrderService;
import org.mentor.service.SubOrderService;
import org.mentor.service.ProductService;
import org.mentor.service.UserService;
import org.mentor.utils.InputUtils;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.sql.SQLException;
import java.util.List;

@ShellComponent
public class ShellUI {
    private static boolean webUILaunched = false;
    private final OrderService orderService;
    private final ProductService productService;
    private final UserService userService;
    private final SubOrderService subOrderService;

    public ShellUI(OrderService orderService, ProductService productService, UserService userService, SubOrderService subOrderService) {
        this.orderService = orderService;
        this.productService = productService;
        this.userService = userService;
        this.subOrderService = subOrderService;
    }

    @ShellMethod(value = "Create product", key = "n prod")
    public void createProduct(
            @ShellOption() String description,
            @ShellOption() Double price,
            @ShellOption() Integer stored
    ) {
        productService.create(description,price,stored);
    }

    @ShellMethod(value = "Create order", key = "n order")
    public void createOrder() {
        List<Pack> packs = InputUtils.selectProductsForOrder();
        try {
            orderService.create(packs);
        } catch (TriedToPurchaseMoreThanStoredException e) {
            System.out.println(e);
        }
    }

    @ShellMethod(value = "Show products", key = "l products")
    public void listProducts() {
        List<Product> products = productService.getAll();
        products.forEach(s -> System.out.println(s));
    }

    @ShellMethod(value = "Show orders", key = "l orders")
    public void listOrders() {
        List<Order> orders = orderService.getAll();
        orders.forEach(order -> {
            List<SubOrder> orderedProducts = subOrderService.getByOrder(order);
            System.out.println("\n********************************************************");
            System.out.println("Order " + order.getOrderTime());
            orderedProducts.forEach(subOrder -> System.out.println(subOrder));
            System.out.println("TOTAL: " + order.getTotalPrice());
        });
    }

    @ShellMethod(value = "Delete product", key = "d product")
    public void deleteProduct(
            @ShellOption() Integer id
    ) {
        productService.delete(id);
    }

    @ShellMethod(value = "Delete order", key = "d order")
    public void deleteOrder(
            @ShellOption() Integer id
    ) {
        orderService.delete(id);
    }

    @ShellMethod(value = "Open H2 WebUI", key = "web")
    public void openH2WebUI(){
        if (!webUILaunched) {
            try {
                org.h2.tools.Console.main();
                webUILaunched = true;
                System.out.println("\nWebUI will be opened in your browser\n" +
                        "Copy that to clipboard:\n\n" +
                        "SELECT * FROM T_USER;\n" +
                        "SELECT * FROM T_SUBORDERS;\n" +
                        "SELECT * FROM T_ORDER;\n" +
                        "SELECT * FROM T_PRODUCT;\n");
            }
            catch (JdbcSQLNonTransientConnectionException e) {
                System.out.println("Ошибка при открытии порта '8082' (порт может уже использоваться)");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
