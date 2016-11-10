package com.webpage.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;

import com.webpage.entity.BankAccount;
import com.webpage.entity.BankAccount_;
import com.webpage.entity.Owner;
import com.webpage.entity.Owner_;
import com.webpage.entity.PhoneNumber;
import com.webpage.entity.SavingsAccount;

/**
 * Session Bean implementation class Teller
 */
@Stateless
@Local(TellerLocal.class)
public class Teller implements TellerLocal {
	@PersistenceContext(unitName="BankAccountJPA")
	private EntityManager em;
	@Resource(name="MyDSAlias")
	DataSource myDS;

    /**
     * Default constructor. 
     */
    public Teller() {
        // TODO Auto-generated constructor stub
    }
    
    public int createAccount(BankAccount account) {
    	em.persist(account);
    	
    	return(account.getId());
    }
    
    public BankAccount findById(int id) {
    	return(em.find(BankAccount.class, id));
    }
    
    public void createAccount(int id, String ownername, double balance) {
    	try {
    		Connection conn = myDS.getConnection();
    		String sql = "INSERT INTO BANKACTS_TBL(ID, " +
    					"OWNERNAME, BALANCE) " +
    					"VALUES (?,?,?)";
    		System.out.println("SQL query is " + sql);
    		PreparedStatement st = conn.prepareStatement(sql);
    		st.setInt(1, id);
    		st.setString(2, ownername);
    		st.setDouble(3, balance);
    		st.execute();
    	}
    	catch(SQLException sqe) {
    		sqe.printStackTrace();
    	}
    }

	@Override
	public BankAccount deposit(int id, double amount) {
		BankAccount ba = em.find(BankAccount.class, id);
		ba.deposit(amount);
		return ba;
	}

	@Override
	public BankAccount withdraw(int id, double amount) {
		BankAccount ba = em.find(BankAccount.class, id);
		ba.withdraw(amount);
		return ba;
	}

	@Override
	public void closeAccount(int id) {
		BankAccount ba = em.find(BankAccount.class, id);
		em.remove(ba);
	}

	@Override
	public List<BankAccount> listAllAccounts() {
		CriteriaQuery<BankAccount> cq = em.getCriteriaBuilder().createQuery(BankAccount.class);
		Root<BankAccount> fromEntity = cq.from(BankAccount.class);
		cq.select(fromEntity);
		TypedQuery<BankAccount> query = em.createQuery(cq);
		System.out.println("Using simple criteria query");
		
		return query.getResultList();
	}

	@Override
	public List<BankAccount> findWithBalance(double amount, boolean sorted) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<BankAccount> cq = 
				cb.createQuery(BankAccount.class);
		ParameterExpression<Double> balance = cb.parameter(Double.class);
		Root<BankAccount> fromEntity = cq.from(BankAccount.class);
		cq.select(fromEntity);
		
		Predicate predicate = cb.ge(
				fromEntity.get(BankAccount_.balance), balance);
		cq.where(predicate);
		if(sorted) {
			Path<String> ownerNamePath = 
					fromEntity.get(BankAccount_.owner)
					.get(Owner_.name);
			cq.orderBy(cb.asc(ownerNamePath));					
		}
		TypedQuery<BankAccount> query = em.createQuery(cq);
		query.setParameter(balance, amount);
		
		System.out.println("Using complex criteria query");
		return(query.getResultList());
	}

	@Override
	public Owner findOwnerById(int ownerId) {
		return em.find(Owner.class, ownerId);
	}

	@Override
	public Owner addNumber(int ownerId, PhoneNumber phoneNumber) {
		Owner owner = em.find(Owner.class, ownerId);
		owner.addNumber(phoneNumber);
		return owner;
	}

	@Override
	public List<BankAccount> findAccountForAreaCode(int areaCode) {
		String statement = "SELECT ba " + 
				"FROM BankAccount ba " + 
				"JOIN ba.owner o " +		
				"JOIN o.phoneNumbers pn " +
				"WHERE pn.areaCode = :areaCode";
		TypedQuery<BankAccount> query = em.createQuery(statement, BankAccount.class).setParameter("areaCode", areaCode);
		
		return query.getResultList();
	}

	@Override
	public List<PhoneNumber> findNumbersForAmount(double amount) {
		String statement ="SELECT pn " + 
				"FROM BankAccount ba " + 
				"JOIN ba.owner o " +
				"JOIN o.phoneNumbers pn " + 
				"where ba.balance >= :amt";
		TypedQuery<PhoneNumber> query = em.createQuery(statement, PhoneNumber.class).setParameter("amt", amount);
		
		return query.getResultList();
	}

	@Override
	public List<SavingsAccount> listAllSavingsAccounts() {
		CriteriaQuery<SavingsAccount> cq = em.getCriteriaBuilder().
				createQuery(SavingsAccount.class);
		Root<SavingsAccount> fromEntity = cq.from(SavingsAccount.class);
		cq.select(fromEntity);
		TypedQuery<SavingsAccount> query = em.createQuery(cq);
		System.out.println("Using criteria query for savings account");

		return query.getResultList();
	}

}
