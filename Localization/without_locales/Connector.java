import java.io.*;
public class Connector {
    private String filename;

    public Connector( String filename ) {
        this.filename = filename;
    }
    public void writecustomer(Customer cust) throws IOException{
        FileOutputStream fos = new FileOutputStream (filename);
        try ( ObjectOutputStream oos = new ObjectOutputStream( fos )) {
            oos.writeObject(cust);
            oos.flush();
        }
    }
    public Customer readcustomer() throws IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream(filename);
        try ( ObjectInputStream oin = new ObjectInputStream(fis)) {
            Customer cust = (Customer) oin.readObject();
            return cust;
        }
    }
    public void writeconstructor(Constructor constr) throws IOException{
        FileOutputStream fos = new FileOutputStream (filename);
        try ( ObjectOutputStream oos = new ObjectOutputStream( fos )) {
            oos.writeObject(constr);
            oos.writeInt(constr.brigsize);
            for(int i = 0; i < constr.brigsize; i++)
                oos.writeObject(constr.brigada[i]);
            oos.flush();
        }
    }
    public Constructor readconstructor()  throws IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream(filename);
        try ( ObjectInputStream oin = new ObjectInputStream(fis)) {
            Constructor constr = (Constructor) oin.readObject();
            int len = oin.readInt();
            constr.brigsize = len;
            for(int i = 0; i < len; i++)
                constr.brigada[i] = (Constructor) oin.readObject();
            return constr;
        }
    }
}
