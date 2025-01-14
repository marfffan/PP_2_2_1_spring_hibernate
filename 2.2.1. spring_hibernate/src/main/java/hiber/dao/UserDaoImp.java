package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
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
    public List<User> listUsers() {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM User user LEFT JOIN FETCH user.car car");
        return query.getResultList();
    }

    @Override
    public User getUserByCar(String model, int series) {
        String hql = "from User user " +
                "join fetch user.car c " +
                "where c.model = :model and c.series = :series";
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("model", model).setParameter("series", series);
        return query.setMaxResults(1).getSingleResult();
    }
}


