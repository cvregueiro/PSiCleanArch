package es.udc.juanporta.psi.clean.app.module.artist.presenter;

import java.util.List;

import es.udc.juanporta.psi.clean.app.domain.Artist;

public interface ArtistsView {

    void showArtists(List<Artist> artists);

    void showEmptyView();

    void showError();
}
