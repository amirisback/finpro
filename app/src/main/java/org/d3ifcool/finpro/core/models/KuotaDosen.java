package org.d3ifcool.finpro.core.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 27/06/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * org.d3ifcool.finpro.core.models
 */
public class KuotaDosen {

    @Expose
    @SerializedName("kuota_id")
    private int kuota_id;

    @Expose
    @SerializedName("kuota_nama")
    private String kuota_nama;

    @Expose
    @SerializedName("kuota_value")
    private String kuota_value;


    public KuotaDosen(int kuota_id, String kuota_nama, String kuota_value) {
        this.kuota_id = kuota_id;
        this.kuota_nama = kuota_nama;
        this.kuota_value = kuota_value;
    }

    public int getKuota_id() {
        return kuota_id;
    }

    public void setKuota_id(int kuota_id) {
        this.kuota_id = kuota_id;
    }

    public String getKuota_nama() {
        return kuota_nama;
    }

    public void setKuota_nama(String kuota_nama) {
        this.kuota_nama = kuota_nama;
    }

    public String getKuota_value() {
        return kuota_value;
    }

    public void setKuota_value(String kuota_value) {
        this.kuota_value = kuota_value;
    }
}
