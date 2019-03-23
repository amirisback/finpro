package org.d3ifcool.service.interfaces;

public interface MahasiswaViewEditor {

    void showProgress();
    void hideProgress();
    void onSucces();
    void onFailed(String message);
}
