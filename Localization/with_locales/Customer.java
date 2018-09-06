
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Customer implements Serializable{
    public final Date creationDate = new Date();
    public String getCreationDate() {
        DateFormat dateFormatter = DateFormat.getDateTimeInstance(
                DateFormat.DEFAULT, DateFormat.DEFAULT, AppLocale.get());
        String dateOut = dateFormatter.format(creationDate);
        return dateOut;
    }
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
            throw new Exception(name + ' ' + AppLocale.error);
        this.cash -= _cost;
    }
    protected Customer(String _name, int _cash){
        name = _name;
        cash = _cash;
    }
    public Customer(){}
    public String toString(){
        return AppLocale.getString(AppLocale.customer) + ' ' + name
                + ' ' + AppLocale.getString(AppLocale.cash) + ' ' + cash + AppLocale.getString(AppLocale.cashsign) +
                ' ' + AppLocale.getString(AppLocale.creation) + ' ' + getCreationDate();
    }
}
