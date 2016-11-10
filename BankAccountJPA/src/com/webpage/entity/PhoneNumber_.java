package com.webpage.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-11-10T13:47:06.389-0500")
@StaticMetamodel(PhoneNumber.class)
public class PhoneNumber_ {
	public static volatile SingularAttribute<PhoneNumber, Integer> id;
	public static volatile SingularAttribute<PhoneNumber, Integer> areaCode;
	public static volatile SingularAttribute<PhoneNumber, Integer> number;
	public static volatile SingularAttribute<PhoneNumber, Owner> owner;
}
