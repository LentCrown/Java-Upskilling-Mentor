package org.mentor.ui;

import org.mentor.domain.UserSession;
import org.mentor.model.Order;
import org.mentor.model.Product;
import org.mentor.service.StoreService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.sql.SQLException;
import java.util.List;

@ShellComponent
public class ShellUI {
    private static boolean webUILaunched = false;
    private final StoreService storeService;
    private UserSession userSession;

    public ShellUI(StoreService storeService) {
        this.storeService = storeService;
    }

    //ALL USER COMMANDS

    @ShellMethod(value = "Register in the system", key = {"user -c", "register"})
    public void register() {
        Boolean status = storeService.register();
        if (status) System.out.println("User registered.");
    }

    @ShellMethod(value = "Login in system", key = {"logon"})
    public void login() {
        userSession = storeService.logon();
        if (userSession == null) System.out.println("Wrong login/password.");
        else System.out.println("You entered as " + userSession.getSurname() + " " +userSession.getName() + " .");
    }

    @ShellMethod(value = "Show products", key = {"prices"})
    public void showProducts() {
        List<Product> productList = storeService.showProducts();
        for (Product product:productList) {
            System.out.println(product.toString());
        }
    }

    //AUTHENTICATED USER COMMANDS

    @ShellMethod(value = "Logoff system", key = {"logoff"})
    public void logoff() {
        userSession = null;
        System.out.println("You logout from system.");
    }

    @ShellMethod(value = "Create order", key = {"order", "order -n"})
    public void createOrder() {
        if (userSession!=null) storeService.createOrder(userSession);
        System.out.println("You should login to do this.");
    }

    @ShellMethod(value = "Show order history", key = {"order history"})
    public void showPurchaseHistory() {
        if (userSession==null) {
            System.out.println("You should login to do this.");
            return;
        }
        List<Order> orderList = storeService.showUserOrders(userSession);
        for (Order order:orderList) {
            System.out.println(order.toString());
        }
    }

    @ShellMethod(value = "Change current user password", key = {"pwd -c"})
    public void changeLoggedUserPassword() {
        if (userSession==null) {
            System.out.println("You should login to do this.");
            return;
        }
        System.out.println("Enter user id");
        System.out.println("id>");
        Boolean status = storeService.changeLoggedUserPassword(userSession);
    }

    //ADMINISTRATOR COMMANDS

    @ShellMethod(value = "Create product", key = {"product"})
    public void createProduct() {
        if (userSession==null) {
            System.out.println("You should login to do this.");
            return;
        }
        Boolean status = storeService.createProduct(userSession);
    }

    @ShellMethod(value = "Delete user", key = {"user -d"})
    public void deleteUser() {
        if (userSession==null) {
            System.out.println("You should login to do this.");
            return;
        }
        Boolean status = storeService.deleteUser(userSession);
        if(status == null){
            System.out.println("Only for Admin");
            return;
        }
        if(!status){
            System.out.println("You cannot delete superadmin.");
            return;
        }
        System.out.println("You deleted user.");
    }

    @ShellMethod(value = "Delete product", key = {"product -d"})
    public void deleteProduct() {
        if (userSession==null) {
            System.out.println("You should login to do this.");
            return;
        }
        Boolean status = storeService.deleteProduct(userSession);
        if(!status){
            System.out.println("Only for Admin");
            return;
        }
        System.out.println("You deleted product.");
    }

    @ShellMethod(value = "Delete order", key = {"order -d"})
    public void deleteOrder() {
        if (userSession==null) {
            System.out.println("You should login to do this.");
            return;
        }
        Boolean status = storeService.deleteProduct(userSession);
        if(!status){
            System.out.println("Only for Admin");
            return;
        }
        System.out.println("You deleted order.");
    }

    @ShellMethod(value = "Add admin role", key = {"add admin", "admin -a"})
    public void grantAdminRole() {
        if (userSession==null) {
            System.out.println("You should login to do this.");
            return;
        }
        Boolean status = storeService.grantAdminAccess(userSession);
        if(!status){
            System.out.println("Only for Admin");
            return;
        }
        System.out.println("You added admin role successfully.");
    }

    @ShellMethod(value = "Revoke admin role", key = {"remove admin", "rem admin", "admin -r"})
    public void revokeAdminRole() {
        if (userSession==null) {
            System.out.println("You should login to do this.");
            return;
        }
        Boolean status = storeService.revokeAdminAccess(userSession);
        if(!status){
            System.out.println("Only for Admin");
            return;
        }
        System.out.println("You revoked admin role successfully.");
    }

    //ADDITIONAL COMMANDS

    @ShellMethod(value = "Open H2 WebUI ", key = {"web"})
    public void openH2WebUI(){
        if (!webUILaunched) {
            try {
                org.h2.tools.Console.main();
                webUILaunched = true;
                System.out.println("WebUI will be opened in your browser\n\n" +
                        "Copy that to clipboard:\n" +
                        "SELECT * FROM T_CREDENTIALS;\n" +
                        "SELECT * FROM T_USER;\n" +
                        "SELECT * FROM T_ORDERED_PRODUCTS;\n" +
                        "SELECT * FROM T_ORDER;\n" +
                        "SELECT * FROM T_PRODUCT;");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @ShellMethod(value = "Help", key = {"h", "help"})
    public void showCommands() {
        System.out.println(
                "Command list:\n\n" +
                "All users:\n" +
                "1) <user -c>       -  register in the system\n" +
                "2) <logon>         -  logon to the system\n" +
                "3) <prices>        -  see list of products\n\n" +
                "Entered users:\n" +
                "1) <logoff>        -  exit from the system\n" +
                "2) <order>[-n]     -  order products\n" +
                "3) <order -h>      -  order history\n" +
                "4) <pwd -c>        -  change password\n\n" +
                "Admins:\n" +
                "1) <product -c>    -  create product\n" +
                "2) <user -d>       -  delete user\n" +
                "3) <product -d>    -  delete product\n" +
                "4) <order -d>       -  delete order\n" +
                "3) <add admin>, <admin -a>                  - add admin rights to some user\n" +
                "4) <remove admin>, <rem admin>, <admin -r>  -  revoke admin rights of user"
        );
    }
}
