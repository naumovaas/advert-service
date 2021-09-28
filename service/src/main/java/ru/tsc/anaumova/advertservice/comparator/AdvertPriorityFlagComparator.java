package ru.tsc.anaumova.advertservice.comparator;

import ru.tsc.anaumova.advertservice.model.Advert;

import java.util.Comparator;

public class AdvertPriorityFlagComparator implements Comparator<Advert> {

    @Override
    public int compare(final Advert o1, final Advert o2) {
        if (o1.getPriorityFlag() == null) return -1;
        if (o2.getPriorityFlag() == null) return 1;
        return o1.getPriorityFlag().compareTo(o2.getPriorityFlag());
    }

}