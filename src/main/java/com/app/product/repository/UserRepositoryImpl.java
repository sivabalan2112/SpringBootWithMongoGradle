package com.app.product.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.app.product.repository.bean.User;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

/**
 * 
 * @author Sivabalan
 *
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	@Qualifier("mogodbMongoTemplate")
	private MongoOperations mongoOps;

	@Override
	public List<User> getAllUsers() {
		return mongoOps.findAll(User.class);
	}

	@Override
	public User getUserByEmailIdAndPassword(String email, String password) {
		Query query = new Query();
		query.addCriteria(Criteria.where("emailAddress").is(email).and("password").is(password));
		Update update = new Update();
		update.set("lastLoginDate", new Date());
		return mongoOps.findAndModify(query, update, User.class);// findOne(query, User.class);
	}

	@Override
	public User addNewUser(User user) {
		User usr = mongoOps.insert(user);
		return usr;
	}

	@Override
	public Long deleteUserByEmail(String email) {
		DeleteResult dr = mongoOps.remove(Query.query(Criteria.where("emailAddress").is(email)), User.class);
		return dr.getDeletedCount();
	}

	@Override
	public Long updateUser(User user) {
		Update update = new Update();
		if (!user.getName().isEmpty()) {
			update.set("name", user.getName());
			update.set("lastLoginDate", user.getLastLoginDate());
		}
		if (!user.getPassword().isEmpty()) {
			update.set("password", user.getPassword());
		}

		UpdateResult usr = mongoOps.updateFirst(Query.query(Criteria.where("emailAddress").is(user.getEmailAddress())),
				update, User.class);
		return usr.getModifiedCount();
	}
}