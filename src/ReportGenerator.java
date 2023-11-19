import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ReportGenerator {

    public void generateReport1() {
        try (Connection conn = DBConnector.getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT BookID, ISBN, Title, AuthorID, GenreID, PublisherID, Price, PublicationDate, StockQuantity, ShelfLocation FROM book")) {

            System.out.println("Student name: Shane Schulte");
            System.out.println("Report 1: Book Table");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String today = formatter.format(new Date());
            System.out.println("Todays date is " + today);
            System.out.println();

            System.out.printf("%-7s %-15s %-25s %-10s %-10s %-15s %-7s %-15s %-15s %-15s \n", "BookID", "ISBN", "Title", "AuthorID", "GenreID", "PublisherID", "Price", "PublicationDate", "StockQuantity", "ShelfLocation");
            System.out.printf("%-7s %-15s %-25s %-10s %-10s %-15s %-7s %-15s %-15s %-15s \n", "-----", "----", "-----", "--------", "-------", "-----------", "-----", "---------------", "-------------", "-------------");

            while (resultSet.next()){
                int bookID = resultSet.getInt("BookID");
                String isbn = resultSet.getString("ISBN");
                String title = resultSet.getString("Title");
                int authorID = resultSet.getInt("AuthorID");
                int genreID = resultSet.getInt("GenreID");
                int publisherID = resultSet.getInt("PublisherID");
                double price = resultSet.getDouble("Price");
                Date publishedDate = resultSet.getDate("PublicationDate");
                String formattedPublishedDate = formatter.format(publishedDate);
                int stockQuantity = resultSet.getInt("StockQuantity");
                String shelfLocation = resultSet.getString("ShelfLocation");

                System.out.printf("%-7s %-15s %-25s %-10s %-10s %-15s %-7s %-15s %-15s %-15s \n", bookID, isbn, title, authorID, genreID, publisherID, price, formattedPublishedDate, stockQuantity, shelfLocation);
            }
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public void generateReport2(){
        String query =  "SELECT Genre.Name, SUM(Sales.TotalPrice) AS TotalSales " +
                        "FROM Sales " +
                        "JOIN Book ON Sales.BookID = Book.BookID " +
                        "JOIN Genre ON Book.GenreID = Genre.GenreID " +
                        "GROUP BY Genre.Name";

        try (Connection conn = DBConnector.getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            System.out.println("Student name: Shane Schulte");
            System.out.println("Report 2: Total Sales by Genre");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String today = formatter.format(new Date());
            System.out.println("Todays date is " + today);
            System.out.println();

            System.out.printf("%-20s %s \n", "Genre", "Total Sales");
            System.out.printf("%-20s %s \n", "-----", "-----------");

            while (resultSet.next()) {
                String genreName = resultSet.getString("Name");
                double totalSales = resultSet.getDouble("TotalSales");

                System.out.printf("%-20s %s \n", genreName, totalSales);
            }
        } catch (SQLException e){
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public void generateReport3(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the BookID (1-6) to retrieve information: ");
        int selectedBookID = scanner.nextInt();
        if (selectedBookID < 1 || selectedBookID > 6){
            System.out.println("You entered a Book ID that doesn exist.  Please enter a number 1-6");
            selectedBookID = scanner.nextInt();
        }

        String query =  "SELECT BookID, ISBN, Title, AuthorID, GenreID, PublisherID, Price, PublicationDate, StockQuantity, ShelfLocation " +
                        "FROM Book WHERE BookID = ?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setInt(1, selectedBookID); // Set the selectedBookID in the query
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Student name: Shane Schulte");
            System.out.println("Report 1: Book Table");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String today = formatter.format(new Date());
            System.out.println("Todays date is " + today);
            System.out.println();

            System.out.printf("%-7s %-15s %-25s %-10s %-10s %-15s %-7s %-15s %-15s %-15s \n", "BookID", "ISBN", "Title", "AuthorID", "GenreID", "PublisherID", "Price", "PublicationDate", "StockQuantity", "ShelfLocation");
            System.out.printf("%-7s %-15s %-25s %-10s %-10s %-15s %-7s %-15s %-15s %-15s \n", "-----", "----", "-----", "--------", "-------", "-----------", "-----", "---------------", "-------------", "-------------");

            while (resultSet.next()) {
                int bookID = resultSet.getInt("BookID");
                String isbn = resultSet.getString("ISBN");
                String title = resultSet.getString("Title");
                int authorID = resultSet.getInt("AuthorID");
                int genreID = resultSet.getInt("GenreID");
                int publisherID = resultSet.getInt("PublisherID");
                double price = resultSet.getDouble("Price");
                Date publishedDate = resultSet.getDate("PublicationDate");
                String formattedPublishedDate = formatter.format(publishedDate);
                int stockQuantity = resultSet.getInt("StockQuantity");
                String shelfLocation = resultSet.getString("ShelfLocation");

                System.out.printf("%-7s %-15s %-25s %-10s %-10s %-15s %-7s %-15s %-15s %-15s \n", bookID, isbn, title, authorID, genreID, publisherID, price, formattedPublishedDate, stockQuantity, shelfLocation);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
