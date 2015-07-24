package practice.fragmenttest;

import android.app.Activity;
import android.support.v4.app.ListFragment;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Oslo on 7/18/15.
 */

public class HeadlinesFragment extends ListFragment{

    OnHeadlineSelectedListener callBack;

    public interface OnHeadlineSelectedListener{
        public void onArticleSelected(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ? android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;
        setListAdapter(new ArrayAdapter<String>(getActivity(), layout, News.Headlines));
    }

    @Override
    public void onStart(){
        super.onStart();
        if(getFragmentManager().findFragmentById(R.id.content_fragment)!= null){
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try {
            callBack = (OnHeadlineSelectedListener)activity;
        } catch (ClassCastException e){
            throw new ClassCastException(activity.toString());
        }
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id){
        callBack.onArticleSelected(position);
        getListView().setItemChecked(position, true);
    }


}
