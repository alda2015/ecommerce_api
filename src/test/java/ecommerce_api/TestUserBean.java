///**
// * 
// */
//package ecommerce_api;
//
//import static org.junit.Assert.*;
//
//import java.util.Properties;
//
//import javax.naming.InitialContext;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import fr.ecommerce_api.entities.User;
//import fr.ecommerce_api.repository.UserRepository;
//
///**
// * @author ousman
// *
// */
//public class TestUserBean {
//
//	private UserRepository userBean;
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@Before
//	public void setUp() throws Exception {
//		System.out.println("Init the User Bean ...");
//		final Properties p = new Properties();
//		p.setProperty("java.naming.factory.initial", "org.apache.openejb.client.LocalInitialContextFactory");
//	    p.setProperty("openejb.jndiname.format", "UserRepository/Local");
//	    userBean = (UserRepository) new InitialContext(p).lookup("UserRepository/Local");
//	}
//
//	/**
//	 * Test method for {@link ecommerce_api.repository.UserRepository#addUser(ecommerce_api.entities.User)}.
//	 */
//	@Test
//	public void testAddUser() {
//		User u = new User();
//		u.setEmail("papsow@u-bordeaux.fr");
//		u.setMdp("123456789");
//		userBean.addUser(u);
//		assertNotNull(userBean.findUserByEmail("papsow@u-bordeaux.fr"));
//	}
//
//	/**
//	 * Test method for {@link ecommerce_api.repository.UserRepository#regen(ecommerce_api.entities.User)}.
//	 */
//	@Test
//	public void testRegen() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link ecommerce_api.repository.UserRepository#update(ecommerce_api.entities.User, javax.servlet.http.HttpServletRequest)}.
//	 */
//	@Test
//	public void testUpdate() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link ecommerce_api.repository.UserRepository#findUserByEmail(java.lang.String)}.
//	 */
//	@Test
//	public void testFindUserByEmail() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link ecommerce_api.repository.UserRepository#getAllTheUsers()}.
//	 */
//	@Test
//	public void testGetAllTheUsers() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link ecommerce_api.repository.UserRepository#deleteUser(java.lang.String)}.
//	 */
//	@Test
//	public void testDeleteUser() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link ecommerce_api.repository.UserRepository#login(java.lang.String, java.lang.String, javax.servlet.http.HttpServletRequest)}.
//	 */
//	@Test
//	public void testLogin() {
//		fail("Not yet implemented");
//	}
//
//}
