package com.app.sugarcrush.repo;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.app.sugarcrush.model.Order;
import com.app.sugarcrush.model.User;

@Repository
public class UserRepo extends AbstractDao<Long, User> implements UserDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers(int offSet, int max) {
		Criteria criteria = getCriteria();
		criteria.setFirstResult(offSet);
		criteria.setMaxResults(max);
		return criteria.list();
	}

	@Override
	public User persistUser(User user) {
		return saveOrUpdate(user);
	}

	@Override
	public User getUserById(long userId) {
		return getById(userId);
	}

	@Override
	public void deleteUser(User user) {
		delete(user);
	}

	@Override
	public void deleteUserById(long userId) {
		delete(userId);
	}

	@Override
	public List<Order> getAllOrdersForAUser(long userId) {
		return getById(userId).getOrders();
	}

}
