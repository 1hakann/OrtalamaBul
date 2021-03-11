package com.badlogic.androidgames.ortalamabul

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.view.LayoutInflater
import android.view.View
import android.widget.AbsSpinner
import android.widget.ScrollView
import android.widget.Spinner
import kotlinx.android.synthetic.main.yeni_icerik_layout.*
import kotlinx.android.synthetic.main.yeni_icerik_layout.view.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       btnEkle.setOnClickListener {
           var inflater = LayoutInflater.from(this)
           // layoutu kotlin objesine dönüştürelim
           var yeniİcerikLayout = inflater.inflate(R.layout.yeni_icerik_layout, null)

           //kullanıcı değerlerini alalım
           var dersAdi = tvDersler.text.toString()
           var harfSpinner = spHarf.selectedItem.toString()
           var krediSpinner = spKredi.selectedItem.toString()

           yeniİcerikLayout.tvYeniDersler.setText(dersAdi)
           yeniİcerikLayout.spYeniHarf.setSelection(spinnerIndexBul(spHarf, harfSpinner))
           yeniİcerikLayout.spYeniKredi.setSelection(spinnerIndexBul(spKredi, krediSpinner))

           rootLayout.addView(yeniİcerikLayout)
       }


    }

    fun ortalamaHesapla(view: View) {}

    fun spinnerIndexBul(spinner: Spinner, arananDeger:String) : Int {
        var index = 0
        for(i in 0..spinner.count) {
            if(spinner.getItemAtPosition(i).toString().equals(arananDeger))
            {
                index = i
                break
            }
        }
        return index
    }
}