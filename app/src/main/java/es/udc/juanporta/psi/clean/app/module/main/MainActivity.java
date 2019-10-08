package es.udc.juanporta.psi.clean.app.module.main;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import es.udc.juanporta.psi.clean.R;
import es.udc.juanporta.psi.clean.app.data.MusicBrainzAPI;
import es.udc.juanporta.psi.clean.app.domain.Artist;
import es.udc.juanporta.psi.clean.app.module.BaseActivity;
import es.udc.juanporta.psi.clean.app.module.main.adapter.ArtistAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.main_list)
    RecyclerView mRecycler;

    private ArtistAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpView();
    }

    private void setUpView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(linearLayoutManager);

        mAdapter = new ArtistAdapter();
        mRecycler.setAdapter(mAdapter);

        getArtists();
    }

    private List<Artist> getArtists() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://musicbrainz.org/ws/2/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        MusicBrainzAPI api = retrofit.create(MusicBrainzAPI.class);

        String query = "artist:rancid";
        String format = "json";
        Call<List<Artist>> call = api.searchArtistByName(query, format);

        call.enqueue(new Callback<List<Artist>>() {

            @Override
            public void onResponse(Call<List<Artist>> call,
                                   Response<List<Artist>> response) {

                if (response.isSuccessful()) {

                    Log.i(TAG, "Response OK: " + response.code());
                    mAdapter.setItems(response.body());

                } else {

                    Log.e(TAG, "Response fails: " + response.code());
                }

            }

            @Override
            public void onFailure(Call<List<Artist>> call,
                                  Throwable t) {

                Log.e(TAG, "Response fails: " + t.getMessage());
                Toast.makeText(MainActivity.this, R.string.error_general, Toast.LENGTH_SHORT).show();
            }
        });

        return new ArrayList<>();
    }
}
