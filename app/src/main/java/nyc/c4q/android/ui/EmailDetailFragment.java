package nyc.c4q.android.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import nyc.c4q.android.R;
import nyc.c4q.android.model.Email;

public class EmailDetailFragment extends Fragment {
  private Email email;
  private static final DateFormat formatter = SimpleDateFormat.getDateInstance(DateFormat.SHORT);

  public static final String SERIALIZABLE_KEY = "email";

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    email = (Email) getArguments().getSerializable(SERIALIZABLE_KEY);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_email_detail, container, false);

    //  - get references to views
    ImageView picture = (ImageView) view.findViewById(R.id.email_from_img);
    TextView from = (TextView) view.findViewById(R.id.email_from);
    TextView subject = (TextView) view.findViewById(R.id.email_subject);
    TextView sent = (TextView) view.findViewById(R.id.email_sent);
    TextView body = (TextView) view.findViewById(R.id.email_body);

    //  - replace nulls
    Picasso.with(getActivity()).load(email.getFromUrl()).into(picture);

    //  - bind email data to views
    from.setText(email.getFrom());
    subject.setText(email.getSubject());
    sent.setText(formatter.format(email.getSent()));
    body.setText(email.getBody());

    return view;
  }

  public static EmailDetailFragment newInstance(Email email) {
    //  - implement this factory method
    // create fragment, set up its only argument (the email) and return it
    EmailDetailFragment emailDetailFragment = new EmailDetailFragment();
    Bundle bundle = new Bundle();
    bundle.putSerializable(SERIALIZABLE_KEY, email);
    emailDetailFragment.setArguments(bundle);

    // hint
    //args.putSerializable("email", email);
    return emailDetailFragment;
  }
}
