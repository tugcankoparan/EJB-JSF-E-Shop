package eshop.tugcankoparan.eshop.service;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import eshop.tugcankoparan.eshop.domain.Basket;
import eshop.tugcankoparan.eshop.entity.OrderedItem;
import eshop.tugcankoparan.eshop.entity.UserOrder;
@Stateless
public class UserOrderServiceImpl implements UserOrderService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<UserOrder> findAll() {
		return entityManager.createQuery("from UserOrder", UserOrder.class).getResultList();
	}

	@Override
	public List<UserOrder> findAllWithItems() {
		List<UserOrder> list = entityManager.createQuery("select distinct uo from UserOrder uo left join fetch uo.orderedItems oi left join fetch oi.item", UserOrder.class).getResultList();
		return list;
	}

	@Override
	public UserOrder findOne(int id) {
		return entityManager.find(UserOrder.class, id);
	}
	@Override
	public void save(UserOrder userOrder, Basket basket) {
		if(userOrder.getId() == null) {
			userOrder.setOrderDate(new Date());
			entityManager.persist(userOrder);
			entityManager.flush();
			entityManager.refresh(userOrder);
			for (OrderedItem orderedItem : basket.getItems()) {
				orderedItem.setUserOrder(userOrder);
			}
			userOrder.setOrderedItems(basket.getItems());
		} else {
			entityManager.merge(userOrder);
		}
	}
	@Override
	public void remove(int id) {
		entityManager.remove(entityManager.find(UserOrder.class, id));
	}

	@Override
	public int count() {
		return ((Long) entityManager.createQuery("select count(u) from UserOrder u").getSingleResult()).intValue();
	}

}
