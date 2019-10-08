package es.udc.juanporta.psi.clean.app.module.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import es.udc.juanporta.psi.clean.R;
import es.udc.juanporta.psi.clean.app.data.MusicBrainzAPI;
import es.udc.juanporta.psi.clean.app.domain.Artist;
import es.udc.juanporta.psi.clean.app.module.BaseActivity;
import es.udc.juanporta.psi.clean.app.module.main.adapter.ArtistAdapter;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;

import java.io.IOException;
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

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://musicbrainz.org/ws/2/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        MusicBrainzAPI api = retrofit.create(MusicBrainzAPI.class);

        String query = "artist:rancid";
        String format = "json";
        Call<List<Artist>> call = api.searchArtistByName(query, format);

        try {

            return call.execute().body();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}
