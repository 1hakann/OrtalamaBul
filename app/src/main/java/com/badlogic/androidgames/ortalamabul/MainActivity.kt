package com.badlogic.androidgames.ortalamabul

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.yeni_icerik_layout.*
import kotlinx.android.synthetic.main.yeni_icerik_layout.view.*


class MainActivity : AppCompatActivity() {

    private val Dersler : Array<String> = arrayOf("Java", "PHP", "Kotlin", "Unity", "Laravel", "Linux", "Mobil Platform" )

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, Dersler)
        tvDersler.setAdapter(adapter)

        if (rootLayout.childCount == 0) {
            btnHesapla.visibility = View.INVISIBLE
        } else {
            btnHesapla.visibility = View.VISIBLE
        }

        btnEkle.setOnClickListener {

            if (!tvDersler.text.isNullOrEmpty()) {
                var inflater = LayoutInflater.from(this)

                // layoutu kotlin objesine dönüştürelim
                var yeniİcerikLayout = inflater.inflate(R.layout.yeni_icerik_layout, null)

                yeniİcerikLayout.tvYeniDersler.setAdapter(adapter)

                //kullanıcı değerlerini alalım
                var dersAdi = tvDersler.text.toString()
                var harfSpinner = spHarf.selectedItem.toString()
                var krediSpinner = spKredi.selectedItem.toString()

                yeniİcerikLayout.tvYeniDersler.setText(dersAdi)
                yeniİcerikLayout.spYeniHarf.setSelection(spinnerIndexBul(spHarf, harfSpinner))
                yeniİcerikLayout.spYeniKredi.setSelection(spinnerIndexBul(spKredi, krediSpinner))

                yeniİcerikLayout.btnSil.setOnClickListener {
                    rootLayout.removeView(yeniİcerikLayout)

                    if (rootLayout.childCount == 0) {
                        btnHesapla.visibility = View.INVISIBLE
                    } else {
                        btnHesapla.visibility = View.VISIBLE
                    }
                }

                rootLayout.addView(yeniİcerikLayout)

                btnHesapla.visibility = View.VISIBLE

                reset()

            } else {
                Toast.makeText(this, "Ders Adı Girin", Toast.LENGTH_LONG).show()
            }
        }


    }

    fun ortalamaHesapla(view: View) {}

    fun spinnerIndexBul(spinner: Spinner, arananDeger: String): Int {
        var index = 0
        for (i in 0..spinner.count) {
            if (spinner.getItemAtPosition(i).toString().equals(arananDeger)) {
                index = i
                break
            }
        }
        return index
    }

    fun reset() {
        tvDersler.setText("")
        spHarf.setSelection(0)
        spKredi.setSelection(0)
    }
}