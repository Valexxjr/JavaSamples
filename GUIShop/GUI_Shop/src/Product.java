

import java.io.Serializable;
import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;

public class Product implements Serializable {
    // class release version:
    private static final long serialVersionUID = 1L;

    // areas with prompts:
    int stocknumber;
    static final String P_stocknumber = "Stock #";
    String productcode;
    static final String P_productcode = "Product code";
    String productname;
    static final String P_productname = "Product name";
    String arrivaldate;
    String arrival;
    static final String P_arrivaldate = "Arrival date";
    int storageperiod;
    static final String P_storageperiod = "Storage period";
    int number;
    static final String P_number = "Number";
    double price;
    static final String P_price = "Price";

    public static Boolean validDate(String dat){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"){{setLenient(false);}};
        try{
            sdf.parse(dat);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    public static Boolean nextRead( Scanner fin, PrintStream out ) {
        return nextRead( P_stocknumber, fin, out );
    }

    static Boolean nextRead( final String prompt, Scanner fin, PrintStream out ) {
        out.print( prompt );
        out.print( ": " );
        return fin.hasNextLine();
    }
    void modifyDate(){
        String[] datas = arrival.split("\\.");
        int day = Integer.parseInt(datas[0]);
        int month = Integer.parseInt(datas[1]);
        int year = Integer.parseInt(datas[2]);
        arrivaldate = String.format("%04d.%02d.%02d", year, month, day);
    }
    public static final String productDel = ",";

    public static Product read( Scanner fin, PrintStream out )
            throws IOException, ParseException {
        Product prod = new Product();
        if(!fin.hasNextLine())
            return null;
        prod.stocknumber = Integer.parseInt(fin.nextLine());
        if(prod.stocknumber < 1)
            throw new IOException("Number of stocks should be >=1");
        if ( ! nextRead( P_productcode, fin, out ))
            return null;
        prod.productcode = fin.nextLine();
        if ( ! nextRead( P_productname, fin, out ))
            return null;
        prod.productname = fin.nextLine();
        if ( ! nextRead( P_arrivaldate, fin, out ))
            return null;
        prod.arrival = fin.nextLine();
        if ( ! Product.validDate(prod.arrival) ) {
            throw new IOException("Invalid Product.arrivaldate value");
        }
        prod.modifyDate();
        if ( ! nextRead( P_storageperiod, fin, out ))
            return null;
        prod.storageperiod = Integer.parseInt(fin.nextLine());
        if(prod.storageperiod < 0)
            throw new IOException("Invalid storage period value");
        if ( ! nextRead( P_number, fin, out ))       return null;
        prod.number = Integer.parseInt(fin.nextLine());
        if(prod.number < 0)
            throw new IOException("Invalid number of products value");
        if ( ! nextRead( P_price, fin, out ))            return null;
        prod.price = Double.parseDouble(fin.nextLine());
        if(prod.price < 0)
            throw new IOException("Invalid price value");
        return prod;
    }

    public Product() {
    }

    public String toString() {
        return "------------------------------------------------\n" +
                P_stocknumber + ": " + stocknumber + "\n" +
                P_productcode + ": " + productcode + "\n" +
                P_productname + ": " + productname + "\n" +
                P_arrivaldate + ": " + arrival + "\n" +
                P_storageperiod + ": " + storageperiod + "\n" +
                P_number + ": " + number + "\n" +
                P_price + ": " +  price + "\n------------------------------------------------\n";
    }
}
