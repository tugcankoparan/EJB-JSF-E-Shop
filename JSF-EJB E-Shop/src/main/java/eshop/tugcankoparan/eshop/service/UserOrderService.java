package eshop.tugcankoparan.eshop.service;
import java.util.List;
import javax.ejb.Local;
import eshop.tugcankoparan.eshop.domain.Basket;
import eshop.tugcankoparan.eshop.entity.UserOrder;
@Local
public interface UserOrderService {

	List<UserOrder> findAll();

	UserOrder findOne(int id);

	void save(UserOrder userOrder, Basket basket);

	List<UserOrder> findAllWithItems();
	
	void remove(int id);

	int count();
}
