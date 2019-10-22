package es.udc.juanporta.psi.clean.app.module.artist.presenter;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import es.udc.juanporta.psi.clean.BuildConfig;
import es.udc.juanporta.psi.clean.app.data.SetListFmAPI;
import es.udc.juanporta.psi.clean.app.data.interceptor.SetListFmApiInterceptor;
import es.udc.juanporta.psi.clean.app.domain.artist.Artist;
import es.udc.juanporta.psi.clean.app.domain.artist.service.ArtistService;
import es.udc.juanporta.psi.clean.app.domain.artist.service.ArtistServiceImp;
import es.udc.juanporta.psi.clean.app.domain.gig.Gigs;
import es.udc.juanporta.psi.clean.app.module.artist.viewmodel.ArtistViewModel;
import es.udc.juanporta.psi.clean.app.module.artist.viewmodel.ArtistViewModelMapper;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArtistsPresenterImp implements ArtistsPresenter {

    private final static String TAG = ArtistsPresenterImp.class.getSimpleName();

    private ArtistsView mView;

    private List<ArtistViewModel> mArtistsViewModels;

    private ArtistService mService = new ArtistServiceImp();

    public ArtistsPresenterImp(ArtistsView view) {

        mView = view;
    }

    @Override
    public void initFlow() {

        new GetArtistsTask().execute();
    }

    @Override
    public void onClickArtist() {

    }

    private List<ArtistViewModel> getArtistsViewModel(List<Artist> artists) {

        mArtistsViewModels = new ArtistViewModelMapper(artists).map();
        return mArtistsViewModels;
    }

    private void getLastGigs(List<ArtistViewModel> artistsViewModels) {

        int index = 0;
        for (ArtistViewModel artist : artistsViewModels) {

            getLastGig(artist, index++);
        }
    }

    private void getLastGig(ArtistViewModel artist,
                            int position) {

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        List<Interceptor> interceptors = okHttpClient.interceptors();
        interceptors.add(loggingInterceptor);
        interceptors.add(new SetListFmApiInterceptor());

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.setlist.fm/rest/1.0/")
                .client(okHttpClient.build()).addConverterFactory(GsonConverterFactory.create()).build();

        SetListFmAPI api = retrofit.create(SetListFmAPI.class);

        Call<Gigs> call = api.searchSetLists(artist.getId());

        call.enqueue(new Callback<Gigs>() {

            @Override
            public void onResponse(Call<Gigs> call,
                                   Response<Gigs> response) {

                if (response.isSuccessful()) {

                    Log.i(TAG, "Response OK: " + response.code());
                    ArtistViewModel artistViewModel = mArtistsViewModels.get(position);
                    artistViewModel.setEventDate(response.body().getGigs().get(0).getEventDate());
                    mView.updateArtist(artistViewModel, position);

                } else {

                    Log.e(TAG, "Response fails: " + response.code());
                }

            }

            @Override
            public void onFailure(Call<Gigs> call,
                                  Throwable t) {

                Log.e(TAG, "Response fails: " + t.getMessage());
                mView.showEmptyView();
                mView.showError();
            }
        });
    }

    private class GetArtistsTask extends AsyncTask<String, Void, List<Artist>> {

        protected List<Artist> doInBackground(String... textToSearch) {

            return mService.searchArtists("Green Day");
        }

        protected void onPostExecute(List<Artist> result) {

            mArtistsViewModels = getArtistsViewModel(result);
            mView.showArtists(mArtistsViewModels);
            getLastGigs(mArtistsViewModels);
        }
    }
}
