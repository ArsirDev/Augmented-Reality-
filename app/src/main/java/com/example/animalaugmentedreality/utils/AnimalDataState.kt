package com.example.animalaugmentedreality.utils

import com.example.animalaugmentedreality.domain.DetailModel
import com.example.animalaugmentedreality.utils.Content.AYAM
import com.example.animalaugmentedreality.utils.Content.BEBEK
import com.example.animalaugmentedreality.utils.Content.BUNGLON
import com.example.animalaugmentedreality.utils.Content.GAJAH
import com.example.animalaugmentedreality.utils.Content.HERBIVORA
import com.example.animalaugmentedreality.utils.Content.INSEKTIVORA
import com.example.animalaugmentedreality.utils.Content.KALELAWAR
import com.example.animalaugmentedreality.utils.Content.KAMBING
import com.example.animalaugmentedreality.utils.Content.KARNIVORA
import com.example.animalaugmentedreality.utils.Content.KATAK
import com.example.animalaugmentedreality.utils.Content.KELINCI
import com.example.animalaugmentedreality.utils.Content.KOMODO
import com.example.animalaugmentedreality.utils.Content.KUCING
import com.example.animalaugmentedreality.utils.Content.KUDA
import com.example.animalaugmentedreality.utils.Content.LABALABA
import com.example.animalaugmentedreality.utils.Content.OMNIVORA
import com.example.animalaugmentedreality.utils.Content.PAUS
import com.example.animalaugmentedreality.utils.Content.SERIGALA
import com.example.animalaugmentedreality.utils.Content.SINGA
import com.example.animalaugmentedreality.utils.Content.ULAR

class AnimalDataState {

    private val herbivoraTitle = listOf(
        GAJAH,
        KAMBING,
        KELINCI,
        KUDA
    )

    private val herbivoraJenis = listOf(
        "Herbivora",
        "Herbivora",
        "Herbivora",
        "Herbivora"
    )

    private val herbivoraDescription = listOf(
        "Gajah adalah hewan darat terbesar yang ada. Tiga spesies hidup saat ini diakui, gajah semak Afrika, gajah hutan Afrika, dan gajah Asia. Mereka adalah pengelompokan informal dalam subfamili Elephantinae dari ordo Proboscidea, anggota yang punah termasuk mastodon.",
        "Kambing atau kambing domestik adalah spesies kambing-kijang peliharaan yang biasanya dipelihara sebagai ternak. Itu dijinakkan dari kambing liar Asia Barat Daya dan Eropa Timur. Kambing adalah anggota dari hewan famili Bovidae dan suku Caprini, artinya berkerabat dekat dengan domba.",
        "Kelinci, juga dikenal sebagai bunnies atau kelinci kelinci, adalah mamalia kecil dalam famili Leporidae dari ordo Lagomorpha. Oryctolagus cuniculus termasuk spesies kelinci Eropa dan keturunannya, 305 ras kelinci domestik di dunia.",
        "Kuda adalah mamalia yang dijinakkan, berjari aneh, dan berkuku. Itu milik keluarga taksonomi Equidae dan merupakan salah satu dari dua subspesies Equus ferus yang masih ada. Kuda telah berevolusi selama 45 hingga 55 juta tahun terakhir dari makhluk kecil berjari banyak, Eohippus, menjadi hewan besar berjari satu saat ini."
    )

    private val herbivoraHabitat= listOf(
        "Padang Rumput atau Hutan",
        "Pegunungan",
        "Padang Rumput atau Hutan",
        "Padang Rumput"
    )

    private val herbivoraFeed = listOf(
        "Tumbuhan atau Tanaman",
        "Tumbuhan atau Tanaman",
        "Tumbuhan atau Tanaman",
        "Tumbuhan atau Tanaman"
    )

    private val insektivoraTitle = listOf(
        BUNGLON,
        KALELAWAR,
        KATAK,
        LABALABA
    )

    private val insektivoraJenis = listOf(
        "Insektivora",
        "Insektivora",
        "Insektivora",
        "Insektivora"
    )

    private val insektivoraDescription = listOf(
        "Bunglon (Calotes) adalah sebutan khusus untuk beraneka jenis kadal atau bengkarung yang memiliki kemampuan mengubah warna kulitnya. Secara umum, istilah bunglon digunakan untuk menyebut kadal-kadal dari suku Iguania termasuk Iguanidae, agamidae dan chamaeleonidae.",
        "Kelelawar adalah satu-satunya mamalia yang dapat terbang, dan berasal dari ordo Chiroptera dengan kedua kaki depan yang berkembang menjadi sayap.",
        "Katak atau di sebut tama pemakan serangga yang hidup di air tawar atau di daratan, berkulit licin, berwarna hijau atau merah kecokelat-cokelatan, kaki belakang lebih panjang, pandai melompat dan berenang sedangkan kodok, nama lain dari bangkong, memiliki kulit yang kasar dan berbintil-bintil atau berbingkul-bingkul",
        "Laba-laba adalah sejenis hewan berbuku-buku (arthropoda) dengan dua segmen tubuh, empat pasang kaki, tak bersayap, dan tak memiliki mulut pengunyah. Semua jenis laba-laba digolongkan ke dalam ordo (Araneae) dan bersama dengan kalajengking, ketonggeng, tungau —semuanya berkaki delapan— dimasukkan ke dalam kelas Arachnida."
    )

    private val insektivoraHabitat= listOf(
        "Hutan",
        "Gua atau Hutan",
        "Danau atau Bersemak",
        "Hutan"
    )

    private val insektivoraFeed = listOf(
        "Serangga",
        "Serangga",
        "Serangga",
        "Serangga"
    )

    private val karnivoraTitle = listOf(
        KOMODO,
        SERIGALA,
        SINGA,
        ULAR
    )

    private val karnivoraJenis = listOf(
        "Karnivora",
        "Karnivora",
        "Karnivora",
        "Karnivora"
    )

    private val karnivoraDescription = listOf(
        "Komodo atau lengkapnya biawak komodo (Varanus komodoensis), adalah spesies biawak besar yang terdapat di Pulau Komodo, Rinca, Flores, Gili Motang, dan Gili Dasami di Provinsi Nusa Tenggara Timur, Indonesia. Biawak ini oleh penduduk asli pulau Komodo juga disebut dengan nama setempat ora.",
        "Serigala abu-abu atau serigala adalah binatang mamalia karnivora. Serigala abu-abu mempunyai asal usul yang sama dengan anjing luar negeri dari keluarga Canis lupus melalui bukti pengurutan DNA dan penyelidikan genetika.",
        "Singa adalah spesies hewan dari keluarga felidae atau jenis kucing. Singa berada di benua Afrika dan sebagian di wilayah India. Singa merupakan hewan yang hidup berkelompok. Biasanya terdiri dari seekor jantan & banyak betina.",
        "Ular adalah kelompok reptilia tidak berkaki dan bertubuh panjang yang tersebar luas di dunia. Secara ilmiah, semua jenis ular dikelompokkan dalam satu sub-ordo, yaitu Serpentes dan juga merupakan anggota dari ordo Squamata (reptilia bersisik) bersama dengan kadal."
    )

    private val karnivoraHabitat= listOf(
        "Pulau atau Hutan",
        "Hutan",
        "PAdang Rumput Atau Hutan",
        "Hutan atau Rawa"
    )

    private val karnivoraFeed = listOf(
        "Daging atau Serangga",
        "Daging atau Serangga",
        "Daging atau Serangga",
        "Daging atau Serangga"
    )

    private val omnivoraTitle = listOf(
        AYAM,
        BEBEK,
        KUCING,
        PAUS
    )

    private val omnivoraJenis = listOf(
        "Omnivora",
        "Omnivora",
        "Omnivora",
        "Omnivora"
    )

    private val omnivoraDescription = listOf(
        "Ayam (Gallus gallus domesticus) adalah unggas yang biasa dipelihara untuk dimanfaatkan daging, telur, dan bulunya. Ayam peliharaan (selanjutnya disingkat ayam saja) merupakan keturunan langsung dari salah satu subspesies ayam hutan yang dikenal sebagai ayam hutan merah (Gallus gallus) atau ayam bangkiwa (bankiva fowl)",
        "Bebek atau itik adalah nama umum untuk beberapa spesies burung dalam famili Anatidae. Bebek umumnya adalah burung akuatik yang sebagian besar berukuran lebih kecil dibandingkan kerabatnya, angsa dan angsa berleher pendek, dan dapat ditemukan pada perairan air tawar maupun air laut.",
        "Kucing disebut juga kucing domestik atau kucing rumah adalah sejenis mamalia karnivora dari keluarga Felidae. Kata kucing biasanya merujuk kepada kucing yang telah dijinakkan, tetapi bisa juga merujuk kepada kucing besar seperti singa dan harimau.",
        "Paus adalah kelompok mamalia laut plasental akuatik yang tersebar luas dan beragam. Mereka adalah pengelompokan informal dalam Cetacea infraorder, yang biasanya tidak termasuk lumba-lumba dan lumba-lumba."
    )

    private val omnivoraHabitat= listOf(
        "Hutan",
        "Danau atau Rawa-Rawa",
        "Hutan",
        "Laut"
    )

    private val omnivoraFeed = listOf(
        "Daging atau Tanaman",
        "Daging atau Tanaman",
        "Daging atau Tanaman",
        "Daging atau Tanaman"
    )

    fun getListData(category: String): MutableList<DetailModel> {
        return mutableListOf<DetailModel>().apply {
            when (category) {
                HERBIVORA -> {
                    for (position in karnivoraTitle.indices) {
                        val dataModel = DetailModel().apply {
                            title = herbivoraTitle[position]
                            jenis = herbivoraJenis[position]
                            description = herbivoraDescription[position]
                            habitat = herbivoraHabitat[position]
                            feed = herbivoraFeed[position]
                        }
                        this.add(dataModel)
                    }
                }
                INSEKTIVORA -> {
                    for (position in karnivoraTitle.indices) {
                        val dataModel = DetailModel().apply {
                            title = insektivoraTitle[position]
                            jenis = insektivoraJenis[position]
                            description = insektivoraDescription[position]
                            habitat = insektivoraHabitat[position]
                            feed = insektivoraFeed[position]
                        }
                        this.add(dataModel)
                    }
                }
                KARNIVORA -> {
                    for (position in karnivoraTitle.indices) {
                        val dataModel =  DetailModel().apply {
                            title = karnivoraTitle[position]
                            jenis = karnivoraJenis[position]
                            description = karnivoraDescription[position]
                            habitat = karnivoraHabitat[position]
                            feed = karnivoraFeed[position]
                        }
                        this.add(dataModel)
                    }
                }
                OMNIVORA -> {
                    for (position in karnivoraTitle.indices) {
                        val dataModel = DetailModel().apply {
                            title = omnivoraTitle[position]
                            jenis = omnivoraJenis[position]
                            description = omnivoraDescription[position]
                            habitat = omnivoraHabitat[position]
                            feed = omnivoraFeed[position]
                        }
                        this.add(dataModel)
                    }
                }
                else -> {}
            }
        }
    }
}