package project;

import java.sql.*;

import java.util.*;



public class Project {
    private static final String url = "jdbc:mysql://localhost:8888/project";

    private static final String usr_name = "root";

    private static final String pass = "123456789";



    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);



        try {

            // Establish the database connection

            Connection conn = DriverManager.getConnection(url, usr_name, pass);

            System.out.println("you have been connected with the database:)");



            while (true) {

                System.out.println("Welcome to our Website");

                System.out.println("1. Login as Admin");

                System.out.println("2. Login as Agent");

                System.out.println("3. LogOut");

                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                switch (choice) {
                	case 1:

                        adminLogin(conn);

                        break;

                    case 2:

                        agentLogin(conn);

                        break;

                    case 3:

                        System.out.println("Thank you for using our Website :)");

                        return;

                    default:

                        System.out.println("Invalid choice. Please try again.");

                }

                System.out.println();

            }

        } catch (SQLException e) {

            System.out.println("Error connecting to the database: " + e.getMessage());

        }

    }



    private static void adminLogin(Connection conn) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Admin Login");

        System.out.print("Username: ");

        String userName = sc.nextLine();

        System.out.print("Password: ");

        String password = sc.nextLine();



        try {

            String query = "select * from login WHERE userName = ? AND password = ?";

            PreparedStatement statement = conn.prepareStatement(query);

            statement.setString(1, userName);

            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();



            if (resultSet.next()) {

                System.out.println("Admin login successful!");

                 while (true) {

                    System.out.println("Admin Menu");

                    System.out.println("1. Add Product to Agent");

                    System.out.println("2. View Product Table Records");

                    System.out.println("3. Logout");

                    System.out.print("Enter your choice: ");



                    int choice = sc.nextInt();

                    switch (choice) {

                        case 1:

                            addProductToTable(conn);

                            break;

                        case 2:

                            viewProductTableRecords(conn);

                            break;

                        case 3:

                            System.out.println("Admin logged out.");

                            return;

                        default:

                            System.out.println("Invalid choice. Please try again.");

                    }

                    System.out.println();

                }

            } else {

                System.out.println("Admin login failed. Invalid username or password.");

            }

        } catch (SQLException e) {

            System.out.println("Error during admin login: " + e.getMessage());

        }

    }



    private static void agentLogin(Connection conn) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Agent Login");

        System.out.print("Username: ");

        String userName = sc.nextLine();

        System.out.print("Password: ");

        String password = sc.nextLine();



        try {

            String query = "select * from Login WHERE userName = ? AND password = ?";

            PreparedStatement statement = conn.prepareStatement(query);

            statement.setString(1, userName);

            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();



            if (resultSet.next()) {

                System.out.println("Agent login successful!");

                while (true) {

                    System.out.println("Agent Menu");

                    System.out.println("1. Add Product to Table");

                    System.out.println("2. Buy the Product");

                    System.out.println("3. Logout");

                    System.out.print("Enter your choice: ");



                    int choice = sc.nextInt();                   

                    switch (choice) {

                       case 1:

                            addProductToTable(conn);

                            break;

                        case 2:

                            productSell(conn);

                            break;

                        case 3:

                           System.out.println("Agent logged out.");

                            return;

                        default:

                            System.out.println("Invalid choice. Please try again.");

                    }

                    System.out.println();

                }

            } else {

                System.out.println("Agent login failed. Invalid username or password.");

            }

        } catch (SQLException e) {

            System.out.println("Error during agent login: " + e.getMessage());

        }

    }



  private static void addProductToTable(Connection conn) {



        Scanner sc = new Scanner(System.in);

        System.out.println("Add Product");

        System.out.print("Enter Product ID: ");



        int productId = sc.nextInt();

        sc.nextLine(); // Consume the newline character

        System.out.print("Enter Product Name: ");

        String productName = sc.nextLine();

        System.out.print("Enter Product Min sale Quantity: ");

        int minsaleQuantity = sc.nextInt();

        System.out.print("Enter Product Price: ");

        int productPrice = sc.nextInt();

        System.out.print("Enter Product Quantity: ");

        int productQuantity = sc.nextInt();



        try {

            String query = "insert into product(productId, productName,minsaleQuantity, productPrice, productQuantity) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(query);

            statement.setInt(1, productId);

            statement.setString(2, productName);

            statement.setInt(3, minsaleQuantity);

            statement.setDouble(4, productPrice);

            statement.setInt(5, productQuantity);



            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {

                System.out.println("Product added to Table successfully.");

            } else {

                System.out.println("Failed to add the product to Agent.");

            }

        } catch (SQLException e) {

            System.out.println("Error adding product to Agent: " + e.getMessage());

        }

    }



    private static void viewProductTableRecords(Connection conn) {

        try {

            String query = "select * from product";

            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                int productId = resultSet.getInt("productId");

                String productName = resultSet.getString("productName");

                int minsaleQuantity = resultSet.getInt("minsaleQuantity");

                int productPrice = resultSet.getInt("productPrice");

                int productQuantity = resultSet.getInt("productQuantity");



                System.out.println("Product ID: " + productId);

                System.out.println("Product Name: " + productName);

                System.out.println("Product Min sale Quantity: " + minsaleQuantity);

                System.out.println("Product Price: " + productPrice);

                System.out.println("Product Quantity: " + productQuantity);

                System.out.println("*****************************");

            }

        } catch (SQLException e) {

            System.out.println("Error viewing Agent table records: " + e.getMessage());

        }

    }



private static void productSell(Connection conn) {

    Scanner sc = new Scanner(System.in);

    System.out.println("Enter the Product Id");

    int productId = sc.nextInt();

    System.out.println("Enter the Number of Quantity that You Want TO Buy");

    int buyQuantity = sc.nextInt();



    try {

        String query = "select productPrice from product WHERE productId = ?";

        PreparedStatement statement = conn.prepareStatement(query);

        statement.setInt(1, productId);

        ResultSet resultSet = statement.executeQuery();



        if (resultSet.next()) {

            int productPrice = resultSet.getInt("productPrice");

            System.out.println("Your Total Product Cost is RS." + productPrice * buyQuantity);

        } else {

            System.out.println("Invalid product Id");

        }

    } catch (SQLException e) {

        System.out.println("Error during product sell: " + e.getMessage());

    }

  }

}