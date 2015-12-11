package nyc.c4q.android.rest;

import android.support.annotation.NonNull;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import nyc.c4q.android.model.Email;

public class FakeEmailService implements EmailService {
  private static final DateFormat formatter = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
  private static final Random random = new Random();

  public static final String JUKAY_PIC =
      "http://static1.squarespace.com/static/528cfee3e4b0c3afb632d2fc/t/559efac2e4b0beaa65d04b0b/1436569758797/Jukay?format=500w";
  public static final String ALIYA_PIC =
      "http://static1.squarespace.com/static/528cfee3e4b0c3afb632d2fc/t/55a04855e4b0bd27125cbc36/1436567638052/Aliya?format=500w";
  public static final String KEVIN_PIC =
      "http://northamerica.startupbus.com/wp-content/uploads/sites/22/2014/03/Kevin-cropped.jpg";
  public static final String JROD_PIC =
      "https://avatars2.githubusercontent.com/u/1868149?v=3&s=460";
  private static final String LOREM_IPSUM =
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc aliquet lacus quis volutpat sollicitudin. Donec sit amet nulla in dolor suscipit consequat. Phasellus tempor velit et massa pretium, id tempor nulla consequat. Pellentesque hendrerit interdum aliquam. Aenean placerat viverra scelerisque. Nam id felis id ipsum placerat posuere at sit amet risus. Proin aliquet blandit lacinia. Vivamus vehicula, turpis sit amet finibus congue, arcu nulla suscipit augue, pharetra elementum nisi nunc eu eros. Vestibulum molestie augue at condimentum tempus. Sed efficitur commodo tortor. Proin tempus, quam eget suscipit eleifend, enim arcu porttitor lacus, in elementum eros ex ut lorem. Fusce rutrum in mauris vitae congue.\n"
          + "\n"
          + "Sed at pretium nulla. Nullam semper orci non magna venenatis mollis. Sed luctus eget arcu non semper. Sed elementum elementum orci quis ultricies. Aliquam erat volutpat. Etiam ac elit quis magna gravida maximus. In suscipit dui eget tortor consequat sollicitudin sed non tortor.\n"
          + "\n"
          + "Ut quis viverra magna, nec fermentum eros. Morbi sed arcu velit. Nunc interdum vulputate diam a finibus. Donec non turpis enim. Integer id massa ultricies, varius erat pretium, mollis odio. Ut vitae efficitur magna, in dignissim augue. Vivamus ultricies tortor vel sem scelerisque suscipit. Morbi iaculis ipsum vel nunc interdum, quis cursus lorem finibus. Vestibulum a elementum orci. Sed ut risus felis. Maecenas pellentesque, justo non dapibus imperdiet, dui dui iaculis neque, non egestas purus orci nec ante. Sed ornare sodales feugiat. Nulla ut auctor sapien. Morbi lobortis vel erat a viverra. Proin quis tortor lectus. Mauris varius sed dui in faucibus.\n"
          + "\n"
          + "Praesent ac lacus at odio venenatis lacinia vitae vitae risus. Vivamus a augue vitae ante porta dictum. Aliquam sed tellus lacus. Donec rhoncus mi tempor luctus facilisis. Proin odio libero, pharetra vitae ligula quis, pellentesque auctor neque. Aenean elementum vehicula diam, eget convallis ante laoreet vel. Nulla vehicula dapibus facilisis. Donec fringilla ullamcorper sem ut aliquet. Ut at placerat mauris. Vestibulum sollicitudin tortor vitae justo ultrices, quis consequat mi venenatis. Suspendisse justo eros, rhoncus nec efficitur id, pellentesque vitae ipsum. Nulla facilisi. Vestibulum in sagittis turpis. Curabitur ullamcorper tempus dui, ut ornare purus interdum in. Mauris molestie erat et tortor congue, et gravida sem euismod.\n"
          + "\n"
          + "In convallis augue non elit egestas, sed ullamcorper ante faucibus. Nullam sagittis erat dolor, eget aliquet neque luctus sit amet. Nullam a aliquam elit. Donec porta ligula ullamcorper, ultrices sem ac, gravida metus. Curabitur ornare, dolor ut finibus imperdiet, nisl neque fermentum nisi, in tincidunt nisl est eu ante. Pellentesque in tempus urna. Maecenas vel tincidunt purus. Morbi ut varius est, ut vehicula quam. Interdum et malesuada fames ac ante ipsum primis in faucibus. Mauris justo purus, sodales eget rutrum non, luctus id urna. Donec eu ipsum at augue interdum gravida. Integer id enim at velit rutrum efficitur nec ut ante. Nam finibus consectetur fermentum. Duis augue tellus, vulputate ut ante in, elementum convallis augue. Maecenas a posuere leo.";
  private static final String DOGE_PIC =
      "https://pbs.twimg.com/profile_images/378800000822867536/3f5a00acf72df93528b6bb7cd0a4fd0c.jpeg";
  public static final String RANDOM_PIC =
      "http://orig05.deviantart.net/be21/f/2014/248/9/5/facebook_silhouette_by_macgalope11-d7y0982.png";

  private static ArrayList<Email> emails = new ArrayList<>();

  static {
    emails.add(
        new Email("Jukay", JUKAY_PIC, "Welcome to C4Q!", "Come say hi!", makeDate("3/1/15")));
    emails.add(
        new Email("Aliya", ALIYA_PIC, "Submit homework", "Deadline upcoming", makeDate("5/23/15")));
    emails.add(new Email("Kevin", KEVIN_PIC, "No content providers", "Ever!", makeDate("6/12/15")));
    emails.add(new Email("John", JROD_PIC, "Final assessment", "Good luck everyone!",
        makeDate("12/10/15")));
    emails.add(new Email("Doge", DOGE_PIC, "Much Android", LOREM_IPSUM, makeDate("12/10/15")));
  }

  // this method is used by the application to 'load' mail
  @Override public List<Email> getEmails() {
    return emails;
  }

  public boolean hasNewMail() {
    //  - 30% of the time you should generate a new email
    // hint: this class has a member variable that generates random numbers
    int num = random.nextInt(1000) + 1;
    if (num <= 300) {
      emails.add(generateNewRandomEmail());
      return true;
    }
    return false;
  }

  @NonNull private Email generateNewRandomEmail() {
    //  - return a new email
    // hint: use RANDOM_PIC above
    return new Email("Luke", RANDOM_PIC, "This is hard", "OMG", makeDate("12/10/15"));
  }

  // utility method
  public static Date makeDate(String date) {
    try {
      return formatter.parse(date);
    } catch (ParseException e) {
      try {
        return formatter.parse("3/1/15");
      } catch (ParseException e2) {
        return null;
      }
    }
  }
}
