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
import javax.sql.DataSource;

import com.webpage.entity.BankAccount;
import com.webpage.entity.Owner;

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
		TypedQuery<BankAccount> query = em.createQuery(
				"SELECT a from BankAccount a", BankAccount.class);
		return query.getResultList();
	}

	@Override
	public List<BankAccount> findWithBalance(double amount) {
		String statement = 
				"SELECT ba from BankAccount ba where ba.balance " + 
			    " >= :amt ORDER BY ba.owner.name ASC";
		
		TypedQuery<BankAccount> query = em.createQuery(statement, BankAccount.class).setParameter("amt", amount);
		return(query.getResultList());
	}

	@Override
	public Owner findOwnerById(int ownerId) {
		return em.find(Owner.class, ownerId);
	}

}
