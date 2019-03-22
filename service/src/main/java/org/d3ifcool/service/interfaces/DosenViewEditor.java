package org.d3ifcool.service.interfaces;

public interface DosenViewEditor {
    void showProgress();
    void hideProgress();
    void onSucces();
    void onFailed(String message);
}
