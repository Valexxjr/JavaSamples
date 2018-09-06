import java.util.*;
import java.io.*;

public class Test {

    static Locale createLocale( String[] args )	{
        if ( args.length == 2 ) {
            return new Locale( args[0], args[1] );
        }
        else if( args.length == 4 ) {
            return new Locale( args[2], args[3] );
        }
        return null;
    }

    static void setupConsole(String[] args) {
        if ( args.length >= 2 ) {
            if ( args[0].compareTo("-encoding")== 0 ) {
                try {
                    System.setOut( new PrintStream( System.out, true, args[1] ));
                } catch ( UnsupportedEncodingException ex ) {
                    System.err.println( "Unsupported encoding: " + args[1] );
                    System.exit(1);
                }
            }
        }
    }
    public static void main(String[] args) {
        try {
            setupConsole( args );
            Locale loc = createLocale( args );
            if ( loc == null ) {
                System.err.println(
                        "Invalid argument(s)\n" +
                                "Syntax: [-encoding ENCODING_ID] language country\n" +
                                "Example: -encoding Cp855 be BY" );
                System.exit(1);
            }
            AppLocale.set( loc );
            Connector con = new Connector("info.dat");
            con.writecustomer(new Customer(AppLocale.getString(AppLocale.customername), 10000));
            Customer cust1 = con.readcustomer();
            System.out.println(cust1);
            Constructor const1 = new Constructor(AppLocale.getString(AppLocale.constructorname), 100, 200);
            cust1.DoTask(const1.ProjectTask(cust1.CreateTask(10)));
            con.writecustomer(cust1);
            con.writecustomer(cust1);
	        con.writeconstructor(const1);
	        System.out.println(con.readconstructor());
            con.writecustomer(cust1);
	        System.out.println(con.readcustomer());
    }
    catch (Exception e){
        System.out.println(e.getMessage());
    }
}
}