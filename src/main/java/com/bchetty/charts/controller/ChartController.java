package com.bchetty.charts.controller;

import com.bchetty.charts.model.Series;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Chart Controller
 *
 * @author Babji Prashanth, Chetty
 */
public class ChartController {
    private String chartData;
    private String categories;
    private List<String> categoryList = new ArrayList<String>();
    private List<Long> heapSizeList = new ArrayList<Long>();
    private List<Long> usedHeapSizeList = new ArrayList<Long>();
    SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");//dd/MM/yyyy
    private static final long MB = 1024*1024;
    int index = 0;
    private Long[] longs;
    
    /**
     * Load Chart Data
     */
    public void loadChartData() {
        if(heapSizeList.size() > 10) {
            heapSizeList.remove(0);
            usedHeapSizeList.remove(0);
            categoryList.remove(0);
        }
        List<Series> series = new ArrayList<Series>();
        
        malloc();
        long heapSize = Runtime.getRuntime().maxMemory();
        heapSizeList.add(heapSize/MB);
        usedHeapSizeList.add((heapSize - Runtime.getRuntime().freeMemory())/MB);
        
        series.add(new Series("Heap Size", heapSizeList));
        series.add(new Series("Used Heap", usedHeapSizeList));

        setChartData(new Gson().toJson(series));
        
        categoryList.add(sdfDate.format(new Date()));
        System.out.println(categoryList);
        
        setCategories(new Gson().toJson(categoryList));
    }

    /**
     * @return the chartData
     */
    public String getChartData() {
        return chartData;
    }

    /**
     * @param chartData the chartData to set
     */
    public void setChartData(String chartData) {
        this.chartData = chartData;
    }

    /**
     * @return the categories
     */
    public String getCategories() {
        return categories;
    }
    
    /**
     * @param categories the categories to set
     */
    public void setCategories(String categories) {
        this.categories = categories;
    }
    
    private void malloc() {
        if(index%2 == 0) {
            longs = new Long[100000];
            for(int i=0;i<1000;i++) {
                longs[i] = Long.valueOf(i);
            }
        } else {
            longs = null;
        }
        index++;
    }
}
