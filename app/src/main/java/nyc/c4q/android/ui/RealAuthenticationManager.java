package nyc.c4q.android.ui;

public class RealAuthenticationManager implements AuthenticationManager {
  public boolean validateLogin(String email, String password) {
    // valid credentials are email: "c4q", password: "c4q"
    if (!email.equals("c4q"))
      return false;
    if (!password.equals("c4q"))
      return false;

    return true;
  }
}
