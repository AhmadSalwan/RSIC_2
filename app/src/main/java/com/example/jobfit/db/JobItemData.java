package com.example.jobfit.db;

import com.example.jobfit.model.Item;

import java.util.ArrayList;

public class JobItemData {
    public static ArrayList<Item> items = generateDummyItem();

    private static ArrayList<Item> generateDummyItem(){
        ArrayList<Item> items1 = new ArrayList<>();
        items1.add(new Item("Data Science", "Apple Corporation",
                "Apple Inc. is a multinational technology company headquartered in Cupertino, California, that designs, develops, and sells consumer electronics"));
        items1.add(new Item("Social Media Specialist", "PT Telkomsel",
                "Telkomsel is an Indonesian Digital Telecommunications Company which was founded on May 26 1995 and was the first to present 2G, 3G and 4G LTE technology"));
        items1.add(new Item("DevOps Engineer", "Tokopedia",
                "Tokopedia is an Indonesian technology company with a mission to achieve digital economic equality in Indonesia. Our vision is to build a super ecosystem "));
        return items1;
    }
}
