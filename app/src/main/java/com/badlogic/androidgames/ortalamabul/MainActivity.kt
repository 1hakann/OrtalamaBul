package com.badlogic.androidgames.ortalamabul

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.yeni_icerik_layout.view.*


class MainActivity : AppCompatActivity() {

    private val DERSLER : Array<String> = arrayOf("Java", "PHP", "Kotlin", "Unity", "Laravel", "Linux", "Mobil Platform", "Android")
    private var tumDersler:ArrayList<Dersler> = ArrayList(8)
    // for döngüsüne geri dönelim en hesaplada.

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, DERSLER)
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
                Toasty.warning(this, "Ders Adı Girin", Toast.LENGTH_LONG, true).show()
            }
        }


    }

    fun ortalamaHesapla(view: View) {
        var toplamHarf:Double = 0.0
        var toplamKredi:Double = 0.0
        //şimdi harfleri double nota çevirecek bir metoda iht. var. dönüştürme metodu oluşturalım.

        for(i in 0..rootLayout.childCount - 1) {
            var birSatir = rootLayout.getChildAt(i)
            //not ve kredileri tutacak bir data class oluşturalım
            var geciciDers = Dersler(birSatir.tvYeniDersler.text.toString(),
                ((birSatir.spYeniKredi.selectedItemPosition)+1).toString(),
                birSatir.spYeniHarf.selectedItem.toString())

            tumDersler.add(geciciDers)
        }

        for (mevcutDersler in tumDersler)
        {
            //metodun başına gelelim
            toplamHarf += harfCevirNot(mevcutDersler.DersHarf) * mevcutDersler.DersKredi.toDouble()
            toplamKredi += mevcutDersler.DersKredi.toDouble()
        }

        Toasty.warning(this, "Ortalamanız: "+toplamHarf/toplamKredi, Toast.LENGTH_LONG,true).show()
        tumDersler.clear()
    }

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

    fun harfCevirNot(gelenHarf:String) : Double {
        var deger = 0.0
        when(gelenHarf)
        {
            "AA" -> deger= 4.0
            "BA" -> deger= 3.5
            "BB" -> deger= 3.0
            "CB" -> deger= 2.5
            "CC" -> deger= 2.0
            "DC" -> deger= 1.5
            "DD" -> deger= 1.0
            "FF" -> deger= 0.0
        }
        return deger
        // 2. fora gidelim hesaplada.
    }
}