package es.udc.juanporta.psi.clean.app.module.artist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import es.udc.juanporta.psi.clean.R;
import es.udc.juanporta.psi.clean.app.module.artist.viewmodel.ArtistViewModel;

public class ArtistAdapter extends Adapter<ArtistAdapter.ArtistHolder> {

    private List<ArtistViewModel> mItems;

    public ArtistAdapter() {

        mItems = new ArrayList<>();
    }

    public void setItems(List<ArtistViewModel> items) {

        mItems = items;
        notifyDataSetChanged();
    }

    public void updateItem(ArtistViewModel item,
                           int position) {

        mItems.add(position, item);
        notifyItemChanged(position);
    }

    @NonNull
    @Override
    public ArtistAdapter.ArtistHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                         int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_artist, parent, false);
        return new ArtistHolder(v);
    }

    @Override
    public int getItemCount() {

        return mItems.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistAdapter.ArtistHolder holder,
                                 int position) {

        holder.bind(mItems.get(position));
    }

    static class ArtistHolder extends RecyclerView.ViewHolder {

        private TextView mTvId;
        private TextView mTvName;

        private ArtistHolder(View v) {

            super(v);
            mTvId = v.findViewById(R.id.row_artist_id);
            mTvName = v.findViewById(R.id.row_artist_name);
        }

        private void bind(ArtistViewModel artist) {

            mTvId.setText("-");
            String name = artist.getName();
            if (artist.getEventDate() != null) {

                name = name + " (" + artist.getEventDate() + ")";
            }

            mTvName.setText(name);
        }
    }

}
