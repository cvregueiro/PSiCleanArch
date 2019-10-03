package es.udc.juanporta.psi.clean.app.module.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import es.udc.juanporta.psi.clean.R;
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

    private List<String> getArtists() {

        List<String> artists = new ArrayList<>();

        artists.add("Ben Harper");
        artists.add("Bon Iver");
        artists.add("The National");
        artists.add("Rancid");
        artists.add("The Black Crows");
        artists.add("The Band");

        return artists;
    }
}
