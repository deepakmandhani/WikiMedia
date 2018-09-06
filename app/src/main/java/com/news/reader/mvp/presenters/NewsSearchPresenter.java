package com.news.reader.mvp.presenters;

import com.news.reader.models.NewsSearchAPIResponse;
import com.news.reader.models.Value;
import com.news.reader.mvp.views.INewsSearchView;
import com.news.reader.network.NewsSearchApiInterface;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewsSearchPresenter {

    NewsSearchApiInterface newsSearchApiInterface;
    INewsSearchView iNewsSearchView;

    @Inject
    NewsSearchPresenter(NewsSearchApiInterface newsSearchApiInterface) {
        this.newsSearchApiInterface = newsSearchApiInterface;
    }

    public void setiNewsSearchView(INewsSearchView iNewsSearchView) {
        this.iNewsSearchView = iNewsSearchView;
    }

    public void getQueriedNewsSearchResult(String query) {

        iNewsSearchView.showLoading();
        newsSearchApiInterface.getNewsSearchResult(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsSearchAPIResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(NewsSearchAPIResponse newsSearchAPIResponse) {
                        if (newsSearchAPIResponse != null && newsSearchAPIResponse.getValue() != null
                                && !newsSearchAPIResponse.getValue().isEmpty()) {
                            iNewsSearchView.showNewsListing(newsSearchAPIResponse.getValue());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iNewsSearchView.showErrorView();
                    }

                    @Override
                    public void onComplete() {
                        iNewsSearchView.hideLoading();
                    }
                });
    }
}


/*

program

public class Main {
    public static int processData(ArrayList<String> array) {
        HashMap<String, ArrayList<String>> softVerMap = new HashMap<String,ArrayList<String>>();
        int out = 0;
        for(String info: array) {
            String []infoArr = info.split(", ");
            if(softVerMap.containsKey(infoArr[2])) {
                ArrayList<String> strings = softVerMap.get(infoArr[2]);
                strings.add(infoArr[3]);
            } else {
                ArrayList<String> strings = new ArrayList<String>();
                strings.add(infoArr[3]);
                softVerMap.put(infoArr[2], strings);
            }
        }
        Set< Map.Entry< String,ArrayList<String>> > st = softVerMap.entrySet();
        for (Map.Entry< String,ArrayList<String>> me:st)
        {
            ArrayList<String> strings = me.getValue();
                Collections.sort(strings);
                String prev = strings.get(0);
                if(strings.size() > 2 && !prev.equals(strings.get(strings.size()-1))) {
                    int count = 0;
                    for (String s : strings) {
                        if (s.equals(prev)) {
                            count++;
                        } else if(!s.equals(strings.get(strings.size()-1))){
                            prev = s;
                        } else {
                            break;
                        }
                    }
                    if(count>1)
                        out++;
                }
        }
        return out;
    }

    public static void main (String[] args) {
        ArrayList<String> inputData = new ArrayList<String>();
        try {
            Scanner in = new Scanner(new BufferedReader(new FileReader("input.txt")));
            while(in.hasNextLine()) {
                String line = in.nextLine().trim();
                if (!line.isEmpty()) // Ignore blank lines
                    inputData.add(line);
            }
            int retVal = processData(inputData);
            PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")));
            output.println("" + retVal);
            output.close();
        } catch (IOException e) {
            System.out.println("IO error in input.txt or output.txt");
        }
    }
}
*/
