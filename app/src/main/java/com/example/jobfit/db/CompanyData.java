package com.example.jobfit.db;

import com.example.jobfit.R;
import com.example.jobfit.model.Company;

import java.util.ArrayList;
import java.util.Arrays;

public class CompanyData {
    public static ArrayList<Company> companies = generateDummyCompany();

    private static ArrayList<Company> generateDummyCompany() {
        ArrayList<Company> companies1 = new ArrayList<>();
        companies1.add(new Company(
                "Apple Corporation",
                "Cupertino, California",
                "Apple Inc. is a multinational technology company headquartered in Cupertino, California, that designs, develops, and sells consumer electronics, software, and online services.",
                R.drawable.apple_logo, // Replace with your actual drawable resource
                Arrays.asList("Machine Learning", "Data Analysis", "Python"),
                R.drawable.kantor_apple
        ));
        companies1.add(new Company(
                "Hp Invent",
                "Cupertino, CA 95342, New Zheland",
                "slogan yang digunakan oleh Hewlett-Packard (HP) pada awal tahun 2000-an sebagai bagian dari strategi rebranding mereka. Slogan ini mencerminkan fokus HP pada inovasi teknologi dan penemuan baru. Selama era ini, HP berupaya menonjolkan kemampuan mereka dalam mengembangkan produk-produk yang berorientasi pada masa depan, termasuk komputer, printer, dan perangkat lunak.",
                R.drawable.logohp, // Replace with your actual drawable resource
                Arrays.asList("Marketing and Communications", "Supply Chain and Operations", "Technical Support"),
                R.drawable.kantor_hp
        ));
        companies1.add(new Company(
                "Loreal",
                "Clichy, Perancis",
                "L'Oréal adalah perusahaan kecantikan global yang didirikan di Prancis pada tahun 1909. Perusahaan ini dikenal sebagai pemimpin di industri kosmetik dan perawatan kulit, dengan berbagai merek terkenal seperti Lancôme, Maybelline, dan Garnier. L'Oréal memiliki portofolio produk yang sangat beragam, mencakup perawatan kulit, kosmetik, perawatan rambut, dan parfum, yang dijual di lebih dari 150 negara di seluruh dunia.",
                R.drawable.logo_loreal, // Replace with your actual drawable resource
                Arrays.asList("Digital Technology", "Graduate Programs & Internships"),
                R.drawable.kantor_lorela
        ));
        companies1.add(new Company(
                "Google",
                "1600 Amphitheatre Parkway, Mountain View, California",
                "Google adalah sebuah perusahaan teknologi multinasional yang sangat terkenal dan berpengaruh di dunia. Didirikan oleh Larry Page dan Sergey Brin pada tahun 1998, Google awalnya dikenal sebagai mesin pencari web yang paling banyak digunakan di dunia. ",
                R.drawable.google_logo,
                Arrays.asList("Marketing Specialist", "Data Analyst"),
                R.drawable.google_office
        ));
        return companies1;
    }
}
