package practice.recyclerviewpractice;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Oslo on 7/9/15.
 */
public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder>{

    private List<Info> informationList;

    public InfoAdapter(List<Info> list){
        informationList = list;
    }

    @Override
    public InfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.info_layout, parent, false);

        return new InfoViewHolder(view);
    }

    // bind the data and the view
    @Override
    public void onBindViewHolder(InfoViewHolder holder, int position) {
        Info info = informationList.get(position);
        holder.infoTitle.setText(info.Info);
        holder.holderName.setText(info.name);
        holder.holderTitle.setText(info.title);
        holder.holderPhone.setText(info.phone);
        holder.holderEmail.setText(info.email);
    }

    @Override
    public int getItemCount() {
        return informationList.size();
    }

    // create holder class to get information from recycler view
    public static class InfoViewHolder extends RecyclerView.ViewHolder{

        protected TextView holderName;
        protected TextView holderTitle;
        protected TextView holderPhone;
        protected TextView holderEmail;
        protected TextView infoTitle;

        public InfoViewHolder(View itemView) {
            super(itemView);

            infoTitle = (TextView) itemView.findViewById(R.id.title);
            holderName = (TextView) itemView.findViewById(R.id.name);
            holderTitle = (TextView) itemView.findViewById(R.id.personTitle);
            holderPhone = (TextView) itemView.findViewById(R.id.phone);
            holderEmail = (TextView) itemView.findViewById(R.id.email);

        }
    }



}
