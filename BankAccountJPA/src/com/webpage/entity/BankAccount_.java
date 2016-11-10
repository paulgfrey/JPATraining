package com.webpage.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-11-10T13:47:06.298-0500")
@StaticMetamodel(BankAccount.class)
public class BankAccount_ {
	public static volatile SingularAttribute<BankAccount, Integer> id;
	public static volatile SingularAttribute<BankAccount, Double> balance;
	public static volatile SingularAttribute<BankAccount, Owner> owner;
}
