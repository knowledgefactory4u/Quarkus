package com.knf.dev;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Singleton
public class UserResource {

	@Inject
	EntityManager entityManager;

	public List<User> getUsers() {
		return entityManager.createQuery("SELECT c FROM User c").getResultList();
	}

	public User getUser(Long id) {
		return entityManager.find(User.class, id);
	}

	@Transactional(Transactional.TxType.REQUIRED)
	public User addUser(User user) {
		entityManager.persist(user);
		return user;
	}

	@Transactional(Transactional.TxType.REQUIRED)
	public void updateUser(User user) {
		entityManager.merge(user);
	}

	@Transactional(Transactional.TxType.REQUIRED)
	public void deleteUser(Long id) {
		User user = getUser(id);
		entityManager.remove(user);
	}
}
