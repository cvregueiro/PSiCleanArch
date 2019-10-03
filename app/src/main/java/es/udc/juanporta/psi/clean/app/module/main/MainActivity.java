package es.udc.juanporta.psi.clean.app.module.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import es.udc.juanporta.psi.clean.R;
import es.udc.juanporta.psi.clean.app.domain.Artist;
import es.udc.juanporta.psi.clean.app.module.BaseActivity;
import es.udc.juanporta.psi.clean.app.module.main.adapter.ArtistAdapter;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_list)
    RecyclerView mRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpView();
    }

    private void setUpView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(linearLayoutManager);

        ArtistAdapter adapter = new ArtistAdapter(getArtists());
        mRecycler.setAdapter(adapter);
    }

    private List<Artist> getArtists() {

        List<Artist> artists = new ArrayList<>();

        artists.add(new Artist(1, "Ben Harper"));
        artists.add(new Artist(2, "Bon Iver"));
        artists.add(new Artist(3, "The National"));
        artists.add(new Artist(4, "Rancid"));
        artists.add(new Artist(5, "The Black Crows"));
        artists.add(new Artist(6, "The Band"));

        return artists;
    }
}
