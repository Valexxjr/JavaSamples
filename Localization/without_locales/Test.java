
public class Test {

    public static void main(String[] args) {
        Connector con = new Connector("info.dat");
        try {
            con.writecustomer(new Customer("John", 10000));
            Customer cust1 = con.readcustomer();
            System.out.println(cust1);
            Constructor const1 = new Constructor("Lee", 100, 200);
            cust1.DoTask(const1.ProjectTask(cust1.CreateTask(10)));
            con.writecustomer(new Customer("John", 10000));
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