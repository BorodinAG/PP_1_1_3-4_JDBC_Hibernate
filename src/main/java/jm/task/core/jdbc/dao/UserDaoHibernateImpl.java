//package jm.task.core.jdbc.dao;
//
//import jm.task.core.jdbc.model.User;
//import jm.task.core.jdbc.util.Util;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.query.Query;
//
//import java.util.List;
//
//import static jm.task.core.jdbc.util.Util.getSessionFactory;
//
//public class UserDaoHibernateImpl implements UserDao {
//    private final SessionFactory sessionFactory = Util.getSessionFactory();
//
//    public UserDaoHibernateImpl() {
//
//    }
//
//
//    @Override
//    public void createUsersTable() {
//        Session session = getSessionFactory().getCurrentSession(); //открыли текущую сессию
//        Transaction transaction = session.beginTransaction(); // начали транзакцию
//
//        String sql = "CREATE TABLE IF NOT EXISTS Staff";
////        String sql = "CREATE TABLE IF NOT EXISTS users " +
////                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
////                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
////                "age TINYINT NOT NULL)";
//
////                Query<User> query = session.createQuery(sql, User.class);
////        query.executeUpdate();
//
////        Query query = session.createSQLQuery(sql).addEntity(User.class);
//
//        session.getTransaction().commit();
//        session.close();
//    }
//
//    @Override
//    public void dropUsersTable() {
//
//    }
//
//    @Override
//    public void saveUser(String name, String lastName, byte age) {
//
//    }
//
//    @Override
//    public void removeUserById(long id) {
//
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//        return null;
//    }
//
//    @Override
//    public void cleanUsersTable() {
//
//    }
//
//
//}
