package model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Account.class)

public class Account_ {
    public static volatile SingularAttribute<Account, Integer> id;
    public static volatile SingularAttribute<Account, Double> balance;
    public static volatile SingularAttribute<Account, Boolean> locked;
    public static volatile SingularAttribute<Account, Client> holder;
}