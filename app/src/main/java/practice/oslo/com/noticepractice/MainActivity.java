package practice.oslo.com.noticepractice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void noticeActivity(View view) {
        Intent intent = new Intent(this, NoticedActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("This is a notice").setContentText("Subject")
                .setSmallIcon(R.drawable.notification_template_icon_bg).setContentIntent(pendingIntent)
                .setAutoCancel(true).addAction(R.drawable.ic_action_phone, "Call", pendingIntent)
                .addAction(R.drawable.ic_action_add_to_queue, "More", pendingIntent)
                .addAction(R.drawable.ic_action_add_to_queue, "And more...", pendingIntent).build();


        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        manager.notify(0, notification);

    }



}
