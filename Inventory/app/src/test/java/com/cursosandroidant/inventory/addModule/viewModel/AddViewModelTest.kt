package com.cursosandroidant.inventory.addModule.viewModel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cursosandroidant.inventory.entities.Product
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*


@RunWith(AndroidJUnit4::class)
class AddViewModelTest{

        @get:Rule
        var instantExeccutorRule = InstantTaskExecutorRule()

        @Test
        fun addProductTest(){
                val addviewModel = AddViewModel()
                val product = Product(117,"Xbox",120,"https://upload.wikimedia.org/wikipedia/commons/thumb/8/87/Xbox_1.png/320px-Xbox_1.png",4.8, 56)
                val observer = Observer<Boolean>{}
                try {
                        addviewModel.getResult().observeForever(observer)
                        addviewModel.addProduct(product)
                        var result = addviewModel.getResult().value
                        assertThat(result, Matchers.`is`(true))
                        //assertThat(result, not(nullValue()))  //opt
                } finally {
                        addviewModel.getResult().removeObserver(observer)
                }
        }
}