package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;
/**

 1. Создайте соединение к своей базе данных и схему. Запустите приложение. Проверьте, что оно полностью работает.+
 2. Создайте сущность Car с полями String model и int series, на которую будет ссылаться User с помощью связи one-to-one.+
 3. Добавьте этот класс в настройки hibernate.+
 4. Создайте несколько пользователей с машинами, добавьте их в базу данных, вытащите обратно.+
 5. В сервис добавьте метод, который с помощью hql-запроса будет доставать юзера, владеющего машиной по ее модели и серии.+


 */


public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User u1 = new User("User1", "Lastname1", "user1@mail.ru");
      User u2 = new User("User2", "Lastname2", "user2@mail.ru");
      User u3 = new User("User3", "Lastname3", "user3@mail.ru");
      User u4 = new User("User4", "Lastname4", "user4@mail.ru");

      Car c1 = new Car("Car1",1);
      Car c2 = new Car("Car2",2);
      Car c3 = new Car("Car3",3);
      Car c4 = new Car("Car4",4);

      userService.add(u1.setCar(c1).setUser(u1));
      userService.add(u2.setCar(c2).setUser(u2));
      userService.add(u3.setCar(c3).setUser(u3));
      userService.add(u4.setCar(c4).setUser(u4));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }
      System.out.println(userService.getUserByCar("Car1",1));
      context.close();
   }
}
