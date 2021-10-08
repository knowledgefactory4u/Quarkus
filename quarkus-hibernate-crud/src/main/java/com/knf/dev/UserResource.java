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
	public void updateUser(Long id, User user) {
		User userToUpdate = entityManager.find(User.class, id);
		if (null != userToUpdate) {
			userToUpdate.setFirstName(user.getFirstName());
			userToUpdate.setLastName(user.getLastName());
			userToUpdate.setEmailId(user.getEmailId());
		} else {
			throw new RuntimeException("No such user available");
		}
	}

	@Transactional(Transactional.TxType.REQUIRED)
	public void deleteUser(Long id) {
		User user = getUser(id);
		entityManager.remove(user);
	}
}
