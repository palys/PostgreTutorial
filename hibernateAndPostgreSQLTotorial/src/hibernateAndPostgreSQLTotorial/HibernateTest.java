package hibernateAndPostgreSQLTotorial;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateTest {
	
	SessionFactory sessionFactory = null;
	
	public void setUp() throws Exception {
	    // A SessionFactory is set up once for an application
	    sessionFactory = new Configuration()
	            .configure() // configures settings from hibernate.cfg.xml
	            .buildSessionFactory();
	}

	public static void main(String[] args) {
		HibernateTest test = new HibernateTest();
		try {
			test.setUp();
			
			System.out.println("ADD:");
			
			test.addUser("Ala", "Krakow", new Date(System.currentTimeMillis()));
			Integer userID = test.addUser("Ola", "Paklowice", new Date(System.currentTimeMillis()));
			
			test.listUsers();
			
			System.out.println("UPDATE:");
			
			test.updateUser(userID, "Warszawa");
			
			test.listUsers();
			
			System.out.println("DELETE:");
			
			test.deleteUser(userID);
			
			test.listUsers();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (test.sessionFactory != null) {
				test.sessionFactory.close();
			}
		}

	}
	
	public Integer addUser(String login, String city, Date birthDate) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Integer userID = null;
		try{
			tx = session.beginTransaction();
			User user = new User(login, city, birthDate);
			userID = (Integer) session.save(user); 
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		return userID;
	}
	
	public void listUsers() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			List users = session.createQuery("FROM hibernateAndPostgreSQLTotorial.User").list(); 
			for (Iterator iterator = users.iterator(); iterator.hasNext();) {
				User user = (User) iterator.next();
				System.out.println(user.getLogin() + " " + user.getCity() + " " + user.getBirthDate());
			}
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
	}
	
	public void updateUser(Integer UserID, String city ) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			User user = (User)session.get(User.class, UserID); 
			user.setCity(city);
			session.update(user); 
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
	}
	
	public void deleteUser(Integer userID){
	      Session session = sessionFactory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         User user = (User) session.get(User.class, userID); 
	         session.delete(user); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }

}
