package eshop.tugcankoparan.eshop.service;
import java.util.List;
import javax.ejb.Local;
import eshop.tugcankoparan.eshop.entity.Item;
@Local
public interface ItemService {
	List<Item> findAll();
	Item findOne(int id);
	void save(Item item);
	void remove(int id);
}
