package net.atos.Customer.dao;

import java.util.List;

import net.atos.Customer.pojo.Customer;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;




/**
 * @author a634994
 *
 */
@Transactional
@Repository
public class CustomerDaoImpl implements CustomerDao {
	
	private static final Logger logger = Logger.getLogger(CustomerDaoImpl.class);

	/**
	 * SessionFactory object
	 */
	@Autowired
	public SessionFactory sessionFactory;
	
	@Override
	public Customer findById(long id) {
		
		return (Customer)sessionFactory.getCurrentSession().createQuery("select c from Customer c where c.customerId = :id ").setParameter("id", id).uniqueResult();
	}
	
	@Override
	public List<Customer> findAll() {
		
		return (List<Customer>)sessionFactory.getCurrentSession().createQuery("select c from Customer c where c.isActive= true ").list();
	}
	
	@Override
	public Customer addCustomer(Customer customer){
		
		if(sessionFactory.getCurrentSession().save(customer)!=null){
			return customer;
		}
		return null;
			
	}
	
	
	@Override
	public boolean deleteCustomer(long customerId){
		
		String query="UPDATE Customer cst SET isActive = false where cst.customerId= :cstId ";
		sessionFactory.getCurrentSession().createQuery(query).setParameter("cstId", customerId).executeUpdate();
		return true;
	}
	
	
	@Override
	public boolean updateCustomer(Customer customer){
		
		try{
		sessionFactory.getCurrentSession().merge(customer);
		}
		catch(Exception e){
			logger.info("in catch block of update customer"+e);
		}
		return true;
	}
	
	
}
