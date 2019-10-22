package es.udc.juanporta.psi.clean.app.domain.gig.service;

import java.util.List;

import es.udc.juanporta.psi.clean.app.domain.gig.Gig;
import es.udc.juanporta.psi.clean.app.domain.gig.datasource.GigDatasource;

public class GigServiceImp implements GigService {

    private GigDatasource mDatasource;

    public GigServiceImp(GigDatasource gigDatasource) {

        mDatasource = gigDatasource;
    }

    @Override
    public List<Gig> searchGig(String artistId) {

        return mDatasource.searchGig(artistId);
    }
}
