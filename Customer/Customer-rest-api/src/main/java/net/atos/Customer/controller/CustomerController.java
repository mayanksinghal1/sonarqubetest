package net.atos.Customer.controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import net.atos.Customer.dao.CustomerDaoImpl;
import net.atos.Customer.model.CustomerModel;
import net.atos.Customer.pojo.Customer;
import net.atos.Customer.service.CustomerService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author a634994
 *
 */
@RestController
@EnableSwagger2
@Api(value = "customer")
@RequestMapping(value = "/customer", produces = "application/json")
public class CustomerController {

	private static final Logger logger = Logger.getLogger(CustomerDaoImpl.class);
	
	
	@Autowired
	CustomerService custservice;
	Customer customerData=new Customer();
	
	
	/**
	 * @param customerId
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@CrossOrigin
	@RequestMapping(value = "/getCustomerbyId/{customer_id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Get Customer by Id")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "found data"),
			@ApiResponse(code = 404, message = "data not found") })
	@ResponseBody
	public ResponseEntity getCustomerById(@PathVariable("customer_id") int customerId)
	{
		if(customerId < 0)
		{
			return ResponseEntity.status(400).body("{\"error\":\" wrong input.\"}");
		}
		Customer customer = custservice.findById(customerId);
		if(customer==null ){
			
			return ResponseEntity.status(404).body("{\"error\":\" no data found.\"}");
		}
		return ResponseEntity.status(200).body(customer);
	}
	
	
	
	@SuppressWarnings({ "rawtypes" })
	@CrossOrigin
	@RequestMapping(value = "/getallCustomerList", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Get All Customer")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "found data"),
			@ApiResponse(code = 404, message = "data not found") })
	@ResponseBody
	public ResponseEntity getCustomer()
	{
		
		List<Customer> customerlist = custservice.findAll();
		if(customerlist==null){
			return ResponseEntity.status(404).body("{\"error\":\" no data found.\"}");
		}
		return ResponseEntity.status(200).body(customerlist);
	}
	
	
	
	
	/**
	 * @param customermodel
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@CrossOrigin
	@PostMapping(value = "/addCustomer",produces = "application/json")
	@ApiOperation(value = "Add Customer" ,response=Customer.class)
	
	@ApiResponses(value = { @ApiResponse(code = 201, message = "customer added successfully"),
	@ApiResponse(code = 404, message = "error in adding customer") })
	@ResponseBody
	public ResponseEntity addCustomer(@RequestBody CustomerModel customermodel)
	{
		logger.info(customermodel.getCustomerId());
		
		//if(custservice.findById(customermodel.getCustomerId()).getCustomerId()!= 0)
		if(custservice.findById(customermodel.getCustomerId())!=null)
		{
			return ResponseEntity.status(404).body("{\"error\":\"This CustomerId is already registered, try with different customerId.\"}");
		}
		
		Customer adduser=new Customer();
		adduser.setActive(true);
		adduser.setCreatedDate(new Date());
		adduser.setUpdatedDate(new Date());
		adduser.setCustomerId(customermodel.getCustomerId());
		adduser.setCustomerLocation(customermodel.getCustomerLocation());
		adduser.setCustomerName(customermodel.getCustomerName());
		adduser.setCustomerNumbere(customermodel.getCustomerNumbere());
		adduser.setEmailId(customermodel.getEmailId());
				
	    try{	
	    	custservice.addCustomer(adduser);
		    }
	    catch(Exception e)
	    {
	    
	    	return ResponseEntity.status(404).body("{\"error\":\"error in adding customer\"}");
	    }
	    return ResponseEntity.status(201).body("{\"success\":\"user added sucessfully\"}");
		
	}
	/**
	 * @param customerId
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@CrossOrigin
	@RequestMapping(value = "/deleteCustomer/{customerId}",method=RequestMethod.DELETE)
	@ApiOperation(value = "delete Customer" ,response=Customer.class)
	
	@ApiResponses(value = { @ApiResponse(code = 201, message = "customer deleted successfully"),
	@ApiResponse(code = 404, message = "error in deleting customer") })
	@ResponseBody
	public ResponseEntity deleteCustomer(@PathVariable long customerId)
	{
		
		if(custservice.findById(customerId)==null)
		{
			return ResponseEntity.status(404).body("{\"error\":\"This CustomerId is not exist/registered, try with different customerId.\"}");
		}
		
	    try{	
		custservice.deleteCustomer(customerId);
	
	    }
	    catch(Exception e)
	    {
	    
	    	return ResponseEntity.status(404).body("{\"error\":\"error in deleting customer\"}");
	    }
	       
		return ResponseEntity.status(201).body("{\"success\":\"deleted successfullly\"}");
		
	}
	
	

	/**
	 * @param customermodel
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings({ "rawtypes" })
	@CrossOrigin
	@RequestMapping(value = "/updateCustomer", method = RequestMethod.POST,produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Customer is updated"),
			@ApiResponse(code = 400, message = "Bad request! Required field missing"),
			@ApiResponse(code = 404, message = "assset-not successfully updated") })
	@ResponseBody
	public ResponseEntity updateCustomer(@ApiParam(value = "Updating Customer", required = true)@RequestBody CustomerModel customermodel)
			throws SQLException {
		try{
			if(customermodel.getCustomerId()!=0)
			{
				customerData=custservice.findById(customermodel.getCustomerId());
				if(customerData!=null){
				customerData.setActive(true);			
				customerData.setCustomerId(customermodel.getCustomerId());
				customerData.setCustomerLocation(customermodel.getCustomerLocation());
				customerData.setEmailId(customermodel.getEmailId());
				customerData.setCustomerName(customermodel.getCustomerName());
				customerData.setCustomerNumbere(customermodel.getCustomerNumbere());
				customerData.setUpdatedDate(new Date());
			    custservice.updateCustomer(customerData);
			    }
			}
			else{
				return ResponseEntity.status(404).body("{\"error\":\"Error in updating customer or customer id does not exist\"}");//"Error in updating customer//customer does not exist with this customerid");
			}
				
	}
		catch(Exception e){
			return ResponseEntity.status(404).body("{\"error\":\"Error in updating customer or customer id does not exist\"}");
		}
		
		return ResponseEntity.status(200).body("{\"success\":\"updated successfullly\"}");
		

	}
	

	
	
	
}
