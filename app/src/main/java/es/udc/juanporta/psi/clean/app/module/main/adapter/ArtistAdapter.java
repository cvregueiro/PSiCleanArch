package es.udc.juanporta.psi.clean.app.module.main.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import es.udc.juanporta.psi.clean.R;

public class ArtistAdapter extends Adapter<ArtistAdapter.ArtistHolder> {

    private List<String> mItems;

    public ArtistAdapter(List<String> items) {

        mItems = items;
    }

    @NonNull
    @Override
    public ArtistAdapter.ArtistHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                         int viewType) {

        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.row_artist, parent, false);

        ArtistHolder vh = new ArtistHolder(v);
        return vh;
    }

    @Override
    public int getItemCount() {

        return mItems.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistAdapter.ArtistHolder holder,
                                 int position) {

        holder.textView.setText(mItems.get(position));
    }

    public static class ArtistHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ArtistHolder(TextView v) {

            super(v);
            textView = v;
        }
    }

}
