package practice.fragmenttest;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends FragmentActivity implements HeadlinesFragment.OnHeadlineSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_articles);
        if(findViewById(R.id.fragment_container) != null){
            if(savedInstanceState != null){
                return ;
            }
            HeadlinesFragment firstFragment = new HeadlinesFragment();
            firstFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, firstFragment).commit();
        }
    }


    @Override
    public void onArticleSelected(int position) {
        ContentFragment content = (ContentFragment)getSupportFragmentManager().findFragmentById(R.id.content_fragment);
        if( content != null){
            content.updateContentView(position);
        } else {
            ContentFragment newFragment = new ContentFragment();
            Bundle arguments = new Bundle();
            arguments.putInt(ContentFragment.POSITION, position);
            newFragment.setArguments(arguments);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

            transaction.commit();
        }
    }



}
