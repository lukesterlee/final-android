package nyc.c4q.android;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v7.app.NotificationCompat;

import nyc.c4q.android.model.Email;
import nyc.c4q.android.rest.FakeEmailService;
import nyc.c4q.android.ui.EmailDetailActivity;

public class EmailApplication extends Application {
  public static final int EMAIL_POLL_IN_SEC = 5;

  public static final int MILLIS_PER_SEC = 1000;
  public static final int DELAY_MILLIS = EMAIL_POLL_IN_SEC * MILLIS_PER_SEC;

  public static final int NOTIFICATION_ID = 999;

  private static final FakeEmailService emailService = new FakeEmailService();

  private HandlerThread handlerThread;
  private NotificationManager notificationManager;
  private Runnable emailCheck;

  @Override public void onCreate() {
    super.onCreate();

    //  - finish this
    notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

    handlerThread = new HandlerThread("email-timer");
    handlerThread.start();
    Looper looper = handlerThread.getLooper();
    final Handler handler = new Handler(looper);

    emailCheck = new Runnable() {
      @Override public void run() {
        if (emailService.hasNewMail()) {

          //
          // 1) get the most recent email and..
          // a) send a notification to the user notifying of the new email
          // b) use R.string.you_got_email as title
          // c) use R.string.notification_email_from (accounting for who sent the email)
          // d) when user clicks on notification, go to EmailDetailActivity

          Email newEmail = emailService.getEmails().get(emailService.getEmails().size() - 1);

          NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
          builder.setAutoCancel(true);
          builder.setContentTitle(getResources().getString(R.string.you_got_email));
          builder.setTicker(getResources().getText(R.string.notification_email_from) + newEmail.getFrom());
          builder.setContentText(newEmail.getBody());
          builder.setSmallIcon(R.drawable.c4q);
          Intent goToEmailDetailActivity = new Intent(getApplicationContext(), EmailDetailActivity.class);
          PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, goToEmailDetailActivity, PendingIntent.FLAG_UPDATE_CURRENT);
          builder.setContentIntent(pendingIntent);

          Notification emailNotification = builder.build();
          notificationManager.notify(NOTIFICATION_ID, emailNotification);

        }


        handler.postDelayed(emailCheck, DELAY_MILLIS);
      }
    };

    handler.postDelayed(emailCheck, DELAY_MILLIS);
  }
}
