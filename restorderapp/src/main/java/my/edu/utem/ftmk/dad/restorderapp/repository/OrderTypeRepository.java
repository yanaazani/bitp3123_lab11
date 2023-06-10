package my.edu.utem.ftmk.dad.restorderapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import my.edu.utem.ftmk.dad.restorderapp.model.OrderType;
@Repository
public interface OrderTypeRepository extends JpaRepository<OrderType, Long> {

	public abstract List<OrderType> findByNameContaining(String partName);
	
	
	//This method will find order type data according to a few 
	//characters at the beginning code
	public abstract List<OrderType> findByCodeStartingWith(String partialCode);
	
	//public abstract List<OrderType> findOrderByNameAsc();
	
	
	//Lab 12 Part 8
	//Custom Query
	@Query(value="select code, name from OrderType where code like '%PU%'", nativeQuery=true)
	public List<Object[]> selectCustomByCode();


	
}
