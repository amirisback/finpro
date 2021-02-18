package org.d3ifcool.finpro.core.interfaces.works

/*
 * Created by Faisal Amir on 18/02/2021
 * Finpro-PA-D3IF-Telkom Source Code
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.      
 * All rights reserved
 *
 */
interface ICreate {

    fun showProgress()
    fun hideProgress()
    fun onSucces()
    fun onFailed(message: String?)

}