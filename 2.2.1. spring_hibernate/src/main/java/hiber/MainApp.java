package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      User user1 = new User("UserVolga", "VolgasUser", "aa@bb.user");
      user1.setCar(new Car("Volga", 24));

      User user2 = new User("UserLada", "LadasUser", "lada@bb.user");
      Car car = new Car("Lada", 2101);
      user2.setCar(car);

      User user3 = new User("UserUAZ", "UAZsUser", "uaz@bb.user");
      user3.setCar(new Car("UAZ", 459));

      userService.add(user1);
      userService.add(user2);
      userService.add(user3); // грузим юзеров в БД

      System.out.println("Достаем из БД по id:");
      System.out.println(userService.getUserById(user3.getId()));
      System.out.println();
      System.out.println("Достаем из БД по id:");
      System.out.println(userService.getUserById(user1.getId()));
      System.out.println("\n");


      System.out.println("Достаем из БД по полям Car:");
      System.out.println(userService.getUserByCar("UAZ", 459));
      System.out.println("\n");

      System.out.println("Достаем из БД по полям Car:");
      System.out.println(userService.getUserByCar("Lada", 2101));
      System.out.println("\n");

      context.close();
   }
}
