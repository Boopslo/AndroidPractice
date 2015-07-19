package practice.intentpractice;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    private Intent startSecond;
    private Button sendButton;
    private Button displayResult;
    private TextView info;
    private TextView receiving;
    //View.OnClickListener clickListener;
    private String getResult;
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String toSend = "Starting another activity...";
        setup();
        startSecond.putExtra("message", toSend);
        //startActivityForResult(startSecond, REQUEST_CODE);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(startSecond, REQUEST_CODE);
            }
        });
    }

    private void setup(){
        startSecond = new Intent(MainActivity.this, SecondActivity.class);
        sendButton = (Button)findViewById(R.id.send_button);
        info = (TextView)findViewById(R.id.message_to_send);
        receiving = (TextView)findViewById(R.id.get_response);
        displayResult = (Button)findViewById(R.id.display_button);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent getIntent){

        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE){
            getResult = getIntent.getStringExtra("get_response");
            displayResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    receiving.setText(getResult);
                }
            });

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
