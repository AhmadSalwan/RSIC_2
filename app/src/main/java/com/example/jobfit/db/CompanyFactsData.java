package com.example.jobfit.db;

import com.example.jobfit.R;
import com.example.jobfit.model.CompanyFacts;
import com.example.jobfit.model.Item;

import java.util.ArrayList;

public class CompanyFactsData {
    public static ArrayList<CompanyFacts> companyFactsArrayList = generateDummyFacts();

    private static ArrayList<CompanyFacts> generateDummyFacts(){
        ArrayList<CompanyFacts> companyFactsArrayList1 = new ArrayList<>();
        companyFactsArrayList1.add(new CompanyFacts("Kota Masa Depan: Bagaimana Pemanfaatan Teknologi Canggih dapat Mewujudkan Smart Forest City?",
                "Smart Forest City adalah konsep kota masa depan yang menggabungkan teknologi canggih dengan prinsip keberlanjutan lingkungan. Pemanfaatan teknologi seperti Internet of Things (IoT), kecerdasan buatan (AI), dan jaringan 5G dapat menciptakan sistem yang lebih efisien dalam mengelola sumber daya alam.",
                R.drawable.artikelempat));
        companyFactsArrayList1.add(new CompanyFacts("Smart Glasses dan 5G Telkomsel: Solusi Streaming Canggih untuk IKN!",
                "Dengan hadirnya smart glasses yang semakin canggih, kombinasi teknologi 5G Telkomsel membuka peluang besar untuk memaksimalkan potensi streaming real-time di Ibu Kota Nusantara (IKN). Kecepatan internet 5G yang ultra-cepat memungkinkan pengguna smart glasses untuk mengakses konten berkualitas tinggi, dari video hingga augmented reality, dengan latensi yang sangat rendah.",
                R.drawable.artikeltiga));
        companyFactsArrayList1.add(new CompanyFacts("Tangguh Tak Goyah di UMKM, BRI Jauh dari Epicentrum Krisis Ekonomi Global",
                "BRI telah menunjukkan ketangguhannya dalam menghadapi tantangan ekonomi global, terutama melalui fokusnya pada sektor UMKM. Dengan dukungan finansial dan program-program inovatif, BRI berhasil mempertahankan pertumbuhan dan stabilitas, meskipun banyak negara terdampak krisis ekonomi.",
                R.drawable.artikellima));
        companyFactsArrayList1.add(new CompanyFacts("L’Oréal Indonesia telah menjadi perusahaan fast-moving consumer goods terdepan di Indonesia",
                "L’Oréal Indonesia telah berhasil mengukuhkan dirinya sebagai pemimpin di industri fast-moving consumer goods (FMCG) di Indonesia. Dengan portofolio produk kecantikan yang beragam, mulai dari perawatan kulit hingga kosmetik, L’Oréal terus berinovasi untuk memenuhi kebutuhan konsumen Indonesia",
                R.drawable.kantor_lorela));
        return companyFactsArrayList1;
    }
}
