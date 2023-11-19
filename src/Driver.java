import java.sql.*;

public class Driver {

    public static void main(String[] args){
        ReportGenerator rg = new ReportGenerator();

        rg.generateReport1();
        System.out.println();

        rg.generateReport2();
        System.out.println();

        rg.generateReport3();
    }
}
