package org.d3ifcool.service.interfaces.works;

public interface MahasiswaWorkView {

    void showProgress();

    void hideProgress();

    void onSucces();

    void onFailed(String message);

}
