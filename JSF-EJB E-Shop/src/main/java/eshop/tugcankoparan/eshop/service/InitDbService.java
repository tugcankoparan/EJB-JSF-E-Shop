package eshop.tugcankoparan.eshop.service;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import eshop.tugcankoparan.eshop.domain.Basket;
import eshop.tugcankoparan.eshop.entity.Item;
import eshop.tugcankoparan.eshop.entity.OrderedItem;
import eshop.tugcankoparan.eshop.entity.UserOrder;
@Startup
@Singleton
public class InitDbService {

	@EJB
	private UserOrderService userOrderService;

	@EJB
	private ItemService itemService;

	@PostConstruct
	public void init() {
		System.out.println("*** START INIT DATABASE ***");
		Item itemJavaBook = constructItem("Java book", "Learn how to use Java in 21 days", 300);
		itemService.save(itemJavaBook);

		Item itemIntellijIdea = constructItem("IntelliJ Idea licence", "single developer licence", 9000);
		itemService.save(itemIntellijIdea);

		Basket basket = new Basket();
		basket.setItems(new HashMap<Integer, OrderedItem>());
		basket.add(itemJavaBook, 5);
		basket.add(itemIntellijIdea, 1);

		UserOrder userOrder = new UserOrder();
		userOrder.setName("Tugcan Koparan");
		userOrder.setCity("Konya");
		userOrder.setStreet("Meram");
		userOrder.setZip("42100");
		userOrderService.save(userOrder, basket);
		System.out.println("*** FINISH INIT DATABASE ***");
	}

	private Item constructItem(String name, String description, double price) {
		Item item = new Item();
		item.setName(name);
		item.setDescription(description);
		item.setPrice(price);
		return item;
	}
}
