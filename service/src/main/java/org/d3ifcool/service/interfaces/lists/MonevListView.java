package org.d3ifcool.service.interfaces.lists;

import org.d3ifcool.service.models.DetailMonev;

import java.util.List;

public interface MonevListView {
    void showProgress();

    void hideProgress();

    void onGetListDetailMonev(List<DetailMonev> monevs);

    void onFailed(String message);
}
