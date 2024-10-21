package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   public User getUserById(long id) {
      String hql="FROM User WHERE id =:id_long";
      User user = sessionFactory.getCurrentSession().createQuery(hql, User.class)
              .setParameter("id_long", id)
              .uniqueResult();
      return user;
   }

   public User getUserByCar(String model, int series) {

      String hql="FROM User u LEFT OUTER JOIN FETCH u.car WHERE model=:model_str AND series=:series_int";
      User user = sessionFactory.getCurrentSession().createQuery(hql, User.class)
              .setParameter("model_str", model)
              .setParameter("series_int",series)
              .uniqueResult();
      return user;
   }



}
