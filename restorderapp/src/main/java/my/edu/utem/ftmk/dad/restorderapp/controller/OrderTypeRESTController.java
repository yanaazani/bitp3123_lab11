package my.edu.utem.ftmk.dad.restorderapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.edu.utem.ftmk.dad.restorderapp.model.OrderType;
import my.edu.utem.ftmk.dad.restorderapp.repository.OrderTypeRepository;

@RestController
@RequestMapping("/api/ordertypes")
public class OrderTypeRESTController {

	@Autowired
	private OrderTypeRepository orderTypeRepository;
	
	
	@DeleteMapping("{orderTypeId}")
	public ResponseEntity<HttpStatus> deleteOrderType(@PathVariable long orderTypeId)
	{
		orderTypeRepository.deleteById(orderTypeId);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	//For class OrderType
	
	//retrieve all order types detail
	@GetMapping
	public List<OrderType> getOrderTypes()
	{
		return orderTypeRepository.findAll();
		
	}
	
	//retrieve product detail based on product ID
	@GetMapping("{orderTypeId}")
	public OrderType getOrderType(@PathVariable long orderTypeId)
	{
		OrderType orderType = orderTypeRepository.findById(orderTypeId).get();
		
		return orderType;
	}
	
	//insert records for order type
	@PostMapping
	public OrderType insertOrderType(@RequestBody OrderType orderType)
	{
		return orderTypeRepository.save(orderType);
	}
	
	//update records for order type
	@PutMapping
	public OrderType updateOrderType(@RequestBody OrderType orderType)
	{
		return orderTypeRepository.save(orderType);
	}
	
	
	/**
	 * This method find order type data according to the order type's name. 
	 * 
	 * @param orderType
	 * @return A list of order typs record.
	 * 
	 */
	@RequestMapping("/find/name")
	public List<OrderType> findOrderType(OrderType orderType)
	{
		List<OrderType> orderTypes = orderTypeRepository.findByNameContaining(orderType.getName());
		return orderTypes;
		
	}
	
	
	/**
	 * Lab 11 Part 3 
	 * Find OrderType by Code
	 * 
	 * 
	 * @param partialCode
	 * @return a list of order type data
	 * 
	 */
	@RequestMapping ("/find/code/{partialCode}")
	public List<OrderType> findOrderType(@PathVariable String partialCode)
	{
		List<OrderType> orderTypes = orderTypeRepository.findByCodeStartingWith(partialCode);
		return orderTypes;
	}
	
	/**
	 * Lab 11 Part 4 
	 * Count Order Type Data
	 * 
	 * 
	 * @return The number of the order type data from the repository
	 * 
	 */
	@RequestMapping("/find/count")
	public long countOrderType()
	{
		long number = orderTypeRepository.count();
		return number;
	}
	
	
	/**
	 * Lab 11 Part 5.2
	 * Retrieve A sorted Order Type Data
	 * 
	 */
//	@GetMapping("/sortedAcs")
//	public List<OrderType> sortedAcsOrderType()
//	{
//		List<OrderType> orderTypes =  orderTypeRepository.findOrderByNameAsc() ;
//		return orderTypes;
//	}
//	
	
	/** 
	 * This method demonstrate the invocation of custom query and return the result in Object form.
	 * 
	 * 
	 * @return A list of objects where value of each filed in separated arrays
	 */
	@GetMapping("/find/pickup/raw")
	public List<Object[]> getRawPickUpOrderCode(){
		//Execute query method
		List<Object[]> objOrderTypes = orderTypeRepository.selectCustomByCode();
	
		//For debugging purpose
		for (Object[] objOrderType:objOrderTypes) {
			for (Object currentObject: objOrderType) {
				System.out.println(currentObject.toString());
				
			}
		}
		return objOrderTypes;
	}
	
	
	/**
	 * This methos demontrate the invocation of custom query 
	 * 
	 * 
	 * @return A list of objects where result of query execution wrap in OrderType
	 */
	@GetMapping("/find/pickup/wrap")
	public List<OrderType> getWrapPickUpOrderCode(){
		
		//Execute query method
		List<Object[]> objOrderTypes = orderTypeRepository.selectCustomByCode();
		
		//Wrap result in a list of order type
		List<OrderType> orderTypes = new ArrayList<OrderType>();
		for (Object[] objOrderType:objOrderTypes) {
			
			//Wrap in order type
			OrderType orderType = new OrderType();
			orderType.setCode(objOrderType[0].toString());
			orderType.setName(objOrderType[1].toString());
			
			//Add into list 
			orderTypes.add(orderType);
		}
		
		return orderTypes;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
