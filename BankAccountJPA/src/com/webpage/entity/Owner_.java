package com.webpage.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-11-10T13:47:06.388-0500")
@StaticMetamodel(Owner.class)
public class Owner_ {
	public static volatile SingularAttribute<Owner, Integer> id;
	public static volatile SingularAttribute<Owner, String> name;
	public static volatile SingularAttribute<Owner, String> address;
	public static volatile SingularAttribute<Owner, Date> dateOfBirth;
	public static volatile ListAttribute<Owner, PhoneNumber> phoneNumbers;
}
