package practice.intentpractice;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SecondActivity extends ActionBarActivity {
    private TextView textView2;
    private TextView showMessage;
    private Button returnBotton;
    private Intent returnIntent;
    //private Bundle getResult;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);
        //getResult = getIntent().getExtras();
        //result = getResult.getString("message");
        result = getIntent().getExtras().getString("message");
        setUp();
        showMessage.setText(result);

        returnBotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setUp(){
        textView2 = (TextView)findViewById(R.id.view1);
        showMessage = (TextView)findViewById(R.id.view_from_activity1);
        returnBotton = (Button)findViewById(R.id.return_button);
        //returnBotton.setOnClickListener();
        returnIntent = new Intent();
    }

    @Override
    public void finish(){
        String toReturnBack = "Back from Second Activity...";
        returnIntent.putExtra("get_response", toReturnBack);
        setResult(RESULT_OK, returnIntent);
        super.finish();
    }


}
