import org.testng.Assert;
import org.testng.annotations.Test;
import main.java.users.User;

public class UserTest {
  User user1 = new User("John", "Lehnon", 56, "johnlennon", "imagine");
  User user2 = new User("Paul", "McCartney", 43, "paulmccartney", "letitbe");
  User user3 = new User("Alexander", "Pushkin", 37, "sanyapushkin", "nyanya");
  User user4 = new User("Sergey", "Esenin", 28, "sergesenin", "hooligan");

  @Test
  public void getName(){
    Assert.assertEquals("John", user1.getName());
    Assert.assertEquals("Paul", user2.getName());
    Assert.assertEquals("Alexander", user3.getName());
    Assert.assertEquals("Sergey", user4.getName());
  }
  @Test
  public void getSurname(){
    Assert.assertEquals("Lehnon", user1.getSurname());
    Assert.assertEquals("McCartney", user2.getSurname());
    Assert.assertEquals("Pushkin", user3.getSurname());
    Assert.assertEquals("Esenin", user4.getSurname());
  }
  @Test
  public void getAge(){
    Assert.assertEquals(56,user1.getAge());
    Assert.assertEquals(43,user2.getAge());
    Assert.assertEquals(37,user3.getAge());
    Assert.assertEquals(28,user4.getAge());
  }
  @Test
  public void getPassword(){
    Assert.assertEquals("imagine", user1.getPassword());
    Assert.assertEquals("letitbe", user2.getPassword());
    Assert.assertEquals("nyanya", user3.getPassword());
    Assert.assertEquals("hooligan", user4.getPassword());
  }
  @Test
  public void getLogin(){
    Assert.assertEquals("johnlennon", user1.getLogin());
    Assert.assertEquals("paulmccartney", user2.getLogin());
    Assert.assertEquals("sanyapushkin", user3.getLogin());
    Assert.assertEquals("sergesenin", user4.getLogin());
  }
  @Test
  public void setPassword(){
    user1.setPassword("12345");
    Assert.assertEquals("12345", user1.getPassword());
    user2.setPassword("67890");
    Assert.assertEquals("67890", user2.getPassword());
    user3.setPassword("qwerty");
    Assert.assertEquals("qwerty", user3.getPassword());
    user4.setPassword("yuiop");
    Assert.assertEquals("yuiop", user4.getPassword());
  }
}
