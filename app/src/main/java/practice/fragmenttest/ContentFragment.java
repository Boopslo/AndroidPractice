package practice.fragmenttest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Oslo on 7/18/15.
 */
public class ContentFragment extends android.support.v4.app.Fragment{

    final static String POSITION = "position";
    int currentPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        if(savedInstanceState != null){
            currentPosition = savedInstanceState.getInt(POSITION);

        }

        return inflater.inflate(R.layout.content_view, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();
        // check if there are arguments passed to the fragment
        Bundle arguments = getArguments();
        if(arguments != null){
            updateContentView(arguments.getInt(POSITION));
        } else if( currentPosition != -1) {
            updateContentView(currentPosition);
        }
    }

    public void updateContentView(int position){
        TextView content = (TextView)getActivity().findViewById(R.id.content);
        content.setText(News.Contents[position]);
        currentPosition = position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION, currentPosition);
    }

}
