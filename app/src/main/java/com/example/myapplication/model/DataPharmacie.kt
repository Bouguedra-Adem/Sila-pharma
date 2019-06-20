package com.example.myapplication.model

class DataPharmacie {




    fun genratePharmacieWithVille():List<Pharmacie>{

        var  phar1 =Pharmacie("PHARMACIE HALISSE","08:00","21:00","Cours de la Révolution, Annaba","Annaba","23","vide","https://www.facebook.com",36.899452,7.759302)
        var  phar2=Pharmacie("Pharmacie Belhouchet Azzedine","08:00","23:00","Annaba, Annaba","Annaba","23","vide","https://www.facebook.com",36.897771,7.739324)
        var  phar3=Pharmacie("pharmacie medjabri takieddine mossadek","08:00","23:00","12 60, Rue du 26 Novembre 1974, Annaba","Annaba","23","vide","https://www.facebook.com",36.894618,7.746961)

        var phars : List<Pharmacie>
        phars= listOf(phar1,phar2,phar3)




        return phars
    }
}