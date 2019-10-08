package es.udc.juanporta.psi.clean.app.module.artist;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import es.udc.juanporta.psi.clean.R;
import es.udc.juanporta.psi.clean.app.domain.Artist;
import es.udc.juanporta.psi.clean.app.module.BaseActivity;
import es.udc.juanporta.psi.clean.app.module.artist.adapter.ArtistAdapter;
import es.udc.juanporta.psi.clean.app.module.artist.presenter.ArtistsPresenter;
import es.udc.juanporta.psi.clean.app.module.artist.presenter.ArtistsPresenterImp;
import es.udc.juanporta.psi.clean.app.module.artist.presenter.ArtistsView;
import es.udc.juanporta.psi.clean.app.module.artist.viewmodel.ArtistViewModel;

public class ArtistsActivity extends BaseActivity implements ArtistsView {

    private static final String TAG = ArtistsActivity.class.getSimpleName();

    @BindView(R.id.artists_list)
    RecyclerView mRecycler;

    @BindView(R.id.artists_empty_list)
    TextView mEmptyView;

    private ArtistAdapter mAdapter;

    private ArtistsPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpView();

        mPresenter = new ArtistsPresenterImp(this);
        mPresenter.initFlow();
    }

    @Override
    public void showArtists(List<ArtistViewModel> artists) {

        mAdapter.setItems(artists);
        mEmptyView.setVisibility(View.GONE);
        mRecycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyView() {

        mEmptyView.setVisibility(View.VISIBLE);
        mRecycler.setVisibility(View.GONE);
    }

    @Override
    public void showError() {

        Toast.makeText(this, R.string.error_general, Toast.LENGTH_SHORT).show();
    }

    private void setUpView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(linearLayoutManager);

        mAdapter = new ArtistAdapter();
        mRecycler.setAdapter(mAdapter);
    }
}
