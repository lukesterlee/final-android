package nyc.c4q.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import nyc.c4q.android.R;

public class LoginActivity extends Activity {

  private EditText emailField;
  private EditText passwordField;
  private Button loginButton;
  private ImageView c4qLogo;
  private final AuthenticationManager manager;

  public LoginActivity() {
    //  - fix this
    manager = new RealAuthenticationManager();
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //  - load view hierarchy in R.layout.activity_login
    setContentView(R.layout.activity_login);

    //  - get references to views, and other setup
    emailField = (EditText) findViewById(R.id.email);
    passwordField = (EditText) findViewById(R.id.password);
    loginButton = (Button) findViewById(R.id.login);
    c4qLogo = (ImageView) findViewById(R.id.logo);

    Picasso.with(getApplicationContext()).load(R.drawable.c4q).centerInside().centerCrop().into(c4qLogo);

    //  - call checkCredentials via OnClickListener
    loginButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        checkCredentials(emailField.getText().toString(), passwordField.getText().toString());
      }
    });
  }

  private void checkCredentials(String email, String password) {
    if(manager.validateLogin(email, password)) {
      //  - go to EmailListActivity
      Intent goToEmailListActivity = new Intent(getApplicationContext(), EmailListActivity.class);
      startActivity(goToEmailListActivity);
    }
    else {
      //  launch alert dialog on failed login
      new AlertDialog.Builder(getApplicationContext())
              .setMessage(R.string.alert_dialog_title)
              .create().show();
      // check strings.xml for dialog
    }
  }
}
