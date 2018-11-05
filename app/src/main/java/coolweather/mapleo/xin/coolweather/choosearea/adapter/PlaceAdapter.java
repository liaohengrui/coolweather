package coolweather.mapleo.xin.coolweather.choosearea.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import coolweather.mapleo.xin.coolweather.R;


public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> implements View.OnClickListener {
    private List<String> mPlaceList;
    private OnItemClickListener mItemClickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_china_place, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String name = mPlaceList.get(i);
        viewHolder.placeName.setText(name);
        viewHolder.itemView.setTag(i);
    }

    @Override
    public int getItemCount() {
        return mPlaceList == null ? 0 : mPlaceList.size();
    }

    @Override
    public void onClick(View v) {
        if (mItemClickListener != null) {
            mItemClickListener.onItemClick((Integer) v.getTag());
        }
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView placeName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            placeName = itemView.findViewById(R.id.item_place);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public PlaceAdapter(List<String> mPlaceList) {
        this.mPlaceList = mPlaceList;
    }

}
