package es.udc.juanporta.psi.clean.app.module.artist.presenter;

import android.util.Log;

import es.udc.juanporta.psi.clean.app.data.MusicBrainzAPI;
import es.udc.juanporta.psi.clean.app.domain.SearchArtists;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArtistsPresenterImp implements ArtistsPresenter {

    private final static String TAG = ArtistsPresenterImp.class.getSimpleName();

    private ArtistsView mView;

    public ArtistsPresenterImp(ArtistsView view) {

        mView = view;
    }

    @Override
    public void initFlow() {

        getArtists();
    }

    @Override
    public void onClickArtist() {

    }

    private void getArtists() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://musicbrainz.org/ws/2/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        MusicBrainzAPI api = retrofit.create(MusicBrainzAPI.class);

        String query = "artist:rancid";
        String format = "json";
        Call<SearchArtists> call = api.searchArtistByName(query, format);

        call.enqueue(new Callback<SearchArtists>() {

            @Override
            public void onResponse(Call<SearchArtists> call,
                                   Response<SearchArtists> response) {

                if (response.isSuccessful()) {

                    Log.i(TAG, "Response OK: " + response.code());
                    mView.showArtists(response.body().getArtists());

                } else {

                    Log.e(TAG, "Response fails: " + response.code());
                    mView.showEmptyView();
                    mView.showError();
                }

            }

            @Override
            public void onFailure(Call<SearchArtists> call,
                                  Throwable t) {

                Log.e(TAG, "Response fails: " + t.getMessage());
                mView.showEmptyView();
                mView.showError();
            }
        });
    }
}
