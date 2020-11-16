package emergon.dao;

import emergon.entity.MyUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    private SessionFactory sessionFactory;
    
    private Session getSession(){
        Session session = sessionFactory.getCurrentSession();
        return session;
    }
    @Override
    public MyUser findByUsername(String username) {
        Query<MyUser> query = getSession().createNamedQuery("MyUser.findByUsername", MyUser.class);
        query.setParameter("username", username);
        MyUser myuser = query.getSingleResult();
        return myuser;
    }
    
}
