package org.d3ifcool.finpro.core.interfaces.works;

public interface MahasiswaWorkView {

    void showProgress();

    void hideProgress();

    void onSucces();

    void onFailed(String message);

}
