import java.text.DateFormat;
import java.util.Date;
import java.io.Serializable;
import java.util.Random;

public class Constructor implements Serializable{
    public final Date creationDate = new Date();
    public String getCreationDate() {
        DateFormat dateFormatter = DateFormat.getDateTimeInstance(
                DateFormat.DEFAULT, DateFormat.DEFAULT, AppLocale.get());
        String dateOut = dateFormatter.format(creationDate);
        return dateOut;
    }
    int brigsize = 0;
    Constructor[] brigada = null;
    String name;
    String mainconst = "";
    int cost_for_floor = 0;
    int cost_for_work = 0;
    public Constructor(){}
    public Constructor(String _name, int cost, int work){
        mainconst = "";
        name = _name;
        cost_for_floor = cost;
        cost_for_work = work;
    }
    public Constructor(String _name){
        name = _name;
        Random rand = new Random();
        cost_for_floor = rand.nextInt(100);
        cost_for_work = rand.nextInt(500);
    }

    public Constructor(String nameofmain, int number) {
        mainconst = nameofmain;
        name = nameofmain + ' ' + AppLocale.getString(AppLocale.subordinate) + ' ' +
                AppLocale.getString(AppLocale.constructor) + ' ' + number;
    }

    @Override
    public String toString() {
       if(brigsize != 0){
           String res = AppLocale.getString(AppLocale.constructor) + ' ' + name + " ; "
                   + AppLocale.getString(AppLocale.projectcost) + ' ' +
                   cost_for_floor + AppLocale.getString(AppLocale.cashsign)  + "; " + AppLocale.getString(AppLocale.workcost)
                   + ' ' + cost_for_work + AppLocale.getString(AppLocale.cashsign) + ' ' + AppLocale.getString(AppLocale.creation)
                   + ' ' + getCreationDate() + "\n";
           for(int i = 0; i < brigsize; i++){
               res += brigada[i].toString();
           }
           return res;
       }
       else{
           return AppLocale.getString(AppLocale.constructor) + ' ' + name + ' ' +
                   AppLocale.getString(AppLocale.subordlot) + ' ' + mainconst + ' ' + AppLocale.getString(AppLocale.creation)
                   + ' ' + getCreationDate() + "\n";
       }
    }
    public int ProjectTask(int floors){
        brigsize = floors;
        brigada = new Constructor[brigsize];
        for(int i = 0; i < brigsize; i++){
            brigada[i] = new Constructor(this.name, i+1);
        }
        return floors * cost_for_floor + cost_for_work;
    }

}