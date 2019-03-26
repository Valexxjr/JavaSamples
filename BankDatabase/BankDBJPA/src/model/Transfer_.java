package model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.sql.Date;

@StaticMetamodel(Transfer.class)

public class Transfer_ {
    public static volatile SingularAttribute<Transfer, Integer> id;
    public static volatile SingularAttribute<Transfer, Account> sender;
    public static volatile SingularAttribute<Transfer, Account> receiver;
    public static volatile SingularAttribute<Transfer, Double> amount;
    public static volatile SingularAttribute<Transfer, Date> transfer_date;
}