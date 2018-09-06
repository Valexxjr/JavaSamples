
import java.io.Serializable;
import java.util.Random;

public class Customer implements Serializable{
    String name = "";
    int cash;
    public String getName() {
        return name;
    }
    public int CreateTask(int floors){
        return floors;
    }
    public int CreateTask(){
        Random rand = new Random();
        return rand.nextInt(20);
    }
    public void DoTask(int _cost) throws Exception{
        if(this.cash < _cost)
            throw new Exception("Customer " + name + "has not enough money to do this");
        this.cash -= _cost;
    }
    protected Customer(String _name, int _cash){
        name = _name;
        cash = _cash;
    }
    public Customer(){}
    public String toString(){
        return "Customer " + name + " that has " + cash + "$";
    }
}
