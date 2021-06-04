package org.mentor.ui;

import org.mentor.domain.Pack;
import org.mentor.domain.UserSession;
import org.mentor.model.Order;
import org.mentor.model.Product;
import org.mentor.model.User;
import org.mentor.service.StoreService;
import org.mentor.util.InputUtils;
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
        List<String> answers = InputUtils.enterUserCreds();
        if (storeService.checkLoginExisting(answers.get(0))){
            System.out.println("User with such login already exists.");
            return;
        }
        answers.addAll(InputUtils.enterUserInfo());
        Boolean status = storeService.createUser(answers);
        if (status) System.out.println("User registered.");
    }

    @ShellMethod(value = "Login in system", key = {"logon"})
    public void login() {
        List<String> answers = InputUtils.enterUserCreds();
        userSession = storeService.logon(answers);
        if (userSession == null) System.out.println("Wrong login/password.");
        else System.out.println("You entered as " + userSession.getSurname() + " " +userSession.getName() + ".");
    }

    @ShellMethod(value = "Show products", key = {"prices"})
    public void showProducts() {
        List<Product> productList = storeService.showProducts();
        if (productList.isEmpty()){
            System.out.println("Nothing to display");
            return;
        }
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

    @ShellMethod(value = "Create order", key = {"order", "order -c"})
    public void createOrder() {
        if (userSession==null) {
            System.out.println("You should login to do this.");
            return;
        }
        List<Product> productList = storeService.showProducts();
        if(productList.isEmpty()){
            System.out.println("No products in system yet");
            return;
        }
        for (Product product:productList)
            System.out.println(product);
        List<Pack> packList = InputUtils.selectProductsForOrder();
        storeService.createOrder(userSession, packList);
    }

    @ShellMethod(value = "Show order history", key = {"order history", "order -h"})
    public void showPurchaseHistory() {
        if (userSession==null) {
            System.out.println("You should login to do this.");
            return;
        }
        List<Order> orderList = storeService.showUserOrders(userSession);
        if (orderList == null){
            System.out.println("Logged user cannot be found in database");
            logoff();
            return;
        }
        if(orderList.isEmpty()) {
            System.out.println("No orders for current user!");
            return;
        }
        for (Order order:orderList) {
            System.out.println(order.toString());
        }
    }

    @ShellMethod(value = "Change current user info", key = {"user -uc"})
    public void changeLoggedUserInfo() {
        if (userSession==null) {
            System.out.println("You should login to do this.");
            return;
        }
        System.out.println("Changing credentianls of " + userSession.getName() + " " + userSession.getSurname() +".");
        List<String> answers = InputUtils.updateUserInfo();
        UserSession userSessionChanged = storeService.changeLoggedUserCreds(userSession,answers);
        if (userSession == null){
            System.out.println("Logged user cannot be found in database");
            logoff();
            return;
        }
        userSession = userSessionChanged;
    }

    @ShellMethod(value = "Change current user login/password", key = {"user -up"})
    public void changeLoggedUserCredentials() {
        if (userSession==null) {
            System.out.println("You should login to do this.");
            return;
        }
        System.out.println("Changing credentials of " + userSession.getName() + " " + userSession.getSurname() +".");
        List<String> answers = InputUtils.updateUserCreds();
        UserSession userSessionChanged = storeService.changeLoggedUserInfo(userSession,answers);
        if (userSession == null){
            System.out.println("Logged user cannot be found in database");
            logoff();
            return;
        }
        userSession = userSessionChanged;
    }

    //ADMINISTRATOR COMMANDS

    @ShellMethod(value = "Create product", key = {"product -c"})
    public void createProduct() {
        if (userSession==null) {
            System.out.println("You should login to do this.");
            return;
        }
        List<String> answers = InputUtils.createProduct();
        Boolean status = storeService.createProduct(userSession, answers);
        if (status) {
            System.out.println("Product created");
            return;
        }
        if(!status) {
            System.out.println("Only for Admin");
            return;
        }
        System.out.println("Specified user no longer registered in the system");
        logoff();
    }

    @ShellMethod(value = "Show all users", key = {"user -l"})
    public void showUsers() {
        if (userSession==null) {
            System.out.println("You should login to do this.");
            return;
        }
        List<User> users = storeService.showUsers(userSession);
        if(users == null){
            System.out.println("Specified user no longer registered in the system");
            logoff();
            return;
        }
        if(users.isEmpty()){
            System.out.println("Only for Admin");
            return;
        }
        for (User user:users) {
            System.out.println(user.toString());
        }
    }

    @ShellMethod(value = "Show only administrators", key = {"user -la"})
    public void showAdmins() {
        if (userSession==null) {
            System.out.println("You should login to do this.");
            return;
        }
        List<User> users = storeService.showAdmins(userSession);
        if(users == null){
            System.out.println("Only for Admin/No such user");//TODO: separate cases
            logoff();
            return;
        }
        if(users.isEmpty()){
            System.out.println("No admins");
            return;
        }
        for (User user:users) {
            System.out.println(user.toString());
        }
    }

    @ShellMethod(value = "Show only clients", key = {"user -lc"})
    public void showClients() {
        if (userSession==null) {
            System.out.println("You should login to do this.");
            return;
        }
        List<User> users = storeService.showClients(userSession);
        if(users == null){
            System.out.println("Only for Admin/No such user"); //TODO: separate cases
            logoff();
            return;
        }
        if(users.isEmpty()){
            System.out.println("No clients yet");
            return;
        }
        for (User user:users) {
            System.out.println(user.toString());
        }
    }

    @ShellMethod(value = "Show all orders", key = {"order -l"})
    public void showOrders() {
        if (userSession==null) {
            System.out.println("You should login to do this.");
            return;
        }
        List<Order> orders = storeService.showOrders(userSession);
        if(orders == null){
            System.out.println("Specified user no longer registered in the system");
            logoff();
            return;
        }
        if(orders.isEmpty()){
            System.out.println("Only for Admin");
            return;
        }
        for (Order order:orders) {
            System.out.println(order.toString());
        }
    }

    @ShellMethod(value = "Delete user", key = {"user -d"})
    public void deleteUser() {
        if (userSession==null) {
            System.out.println("You should login to do this.");
            return;
        }
        showUsers();
        Integer user_id = InputUtils.readInteger("user_id");
        Boolean status = storeService.deleteUser(userSession, user_id);
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
        showProducts();
        Integer product_id = InputUtils.readInteger("product_id");
        Boolean status = storeService.deleteProduct(userSession,product_id);
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
        showOrders();
        Integer order_id = InputUtils.readInteger("order_id");
        Boolean status = storeService.deleteOrder(userSession,order_id);
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
        showClients();
        Integer user_id = InputUtils.readInteger("user_id");
        Boolean status = storeService.grantAdminAccess(userSession,user_id);
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
        showAdmins();
        Integer user_id = InputUtils.readInteger("user_id");
        Boolean status = storeService.revokeAdminAccess(userSession,user_id);
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
                System.out.println("\nWebUI will be opened in your browser\n" +
                        "Copy that to clipboard:\n\n" +
                        "SELECT * FROM T_CREDENTIALS;\n" +
                        "SELECT * FROM T_USER;\n" +
                        "SELECT * FROM T_ORDERED_PRODUCTS;\n" +
                        "SELECT * FROM T_ORDER;\n" +
                        "SELECT * FROM T_PRODUCT;\n\n");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @ShellMethod(value = "Help", key = {"h", "help"})
    public void showCommands() {
        System.out.println(
                "Command list:\n\n" +
                "*********************************ALL USERS:********************************\n" +
                "1) <user -c>                                -  register in the system\n" +
                "2) <logon, login>                           -  logon to the system\n" +
                "3) <prices, products>                       -  see list of products\n\n" +
                "*******************************ENTERED USERS********************************\n" +
                "1) <logoff, quit>                           -  exit from the system\n" +
                "2) <order>[-n]                              -  order products\n" +
                "3) <order -h>                               -  order history\n" +
                "4) <user -uc>                               -  change user info\n\n" +
                "5) <user -up>                               -  change user login/password\n\n" +
                "**********************************ADMINS************************************\n" +
                "1) <product -c>                             -  create product\n" +
                "2) <user -l>                                -  show all users\n" +
                "3) <user -la>                               -  show administrators only\n" +
                "4) <user -lc>                               -  show non-admin users only\n" +
                "5) <user -d>                                -  delete user\n" +
                "6) <product -d>                             -  delete product\n" +
                "7) <order -d>                               -  delete order\n" +
                "8) <add admin>, <admin -a>                  - add admin rights to some user\n" +
                "9) <remove admin>, <rem admin>, <admin -r>  -  revoke admin rights of user\n" +
                "******************************************************************************"
        );
    }
}
