package net.atos.Customer.service;

import java.util.List;

import javax.transaction.Transactional;








import net.atos.Customer.dao.CustomerDao;
import net.atos.Customer.pojo.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * @author a634994
 *
 */
@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDao dao;
	
	@Override
	public Customer findById(long id) {

		return dao.findById(id);
	}
	
	@Override
	public List<Customer> findAll(){
		return dao.findAll();
	}
	
	@Override
	public Customer addCustomer(Customer customer){
		
		return dao.addCustomer(customer);
		
	}
	
	@Override
	public boolean deleteCustomer(long customerId){
		if(dao.findById(customerId)!=null){
		return dao.deleteCustomer(customerId);
		}
		return false;
	}
	
	
	@Override
	public boolean updateCustomer(Customer customer){
		if(dao.findById(customer.getCustomerId())!=null){
		return dao.updateCustomer(customer);
		}
		return false;
	}
	
	
}
