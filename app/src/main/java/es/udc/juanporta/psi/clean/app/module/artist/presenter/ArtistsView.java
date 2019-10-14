package es.udc.juanporta.psi.clean.app.module.artist.presenter;

import java.util.List;

import es.udc.juanporta.psi.clean.app.module.artist.viewmodel.ArtistViewModel;

public interface ArtistsView {

    void showArtists(List<ArtistViewModel> artists);

    void showEmptyView();

    void showError();

    void updateArtist(ArtistViewModel artist, int position);
}
