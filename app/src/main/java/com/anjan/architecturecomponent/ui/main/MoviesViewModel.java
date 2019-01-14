package com.anjan.architecturecomponent.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.anjan.architecturecomponent.ChatModelObject;
import com.anjan.architecturecomponent.DateObject;
import com.anjan.architecturecomponent.ListObject;
import com.anjan.architecturecomponent.MoviesDatabase;
import com.anjan.architecturecomponent.dao.DirectorDao;
import com.anjan.architecturecomponent.dao.MovieDao;
import com.anjan.architecturecomponent.entity.DirectorEntity;
import com.anjan.architecturecomponent.entity.MovieEntity;
import com.anjan.architecturecomponent.pager.DataSourceFactory;
import com.anjan.architecturecomponent.pager.MovieListDataSource;
import com.anjan.architecturecomponent.repository.DataRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Anjan Debnath on 8/17/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */

public class MoviesViewModel extends AndroidViewModel {

    private static final int INITIAL_LOAD_KEY = 0;
    private static final int PAGE_SIZE = 10;
    private static final int PREFETCH_DISTANCE = 5;

    private MovieDao movieDao;
    private DirectorDao directorDao;
    DataRepository dataRepository;

    private DataSourceFactory mDataSourceFactory;


    private LiveData<PagedList<MovieEntity>> moviesLiveData;
    private MutableLiveData<PagedList<ListObject>> mutableMovieList;



    private LiveData<PagedList<ListObject>> listObjLiveData;



    public MoviesViewModel(@NonNull Application application) {
        super(application);
        movieDao = MoviesDatabase.getDatabase(application).movieDao();
        directorDao = MoviesDatabase.getDatabase(application).directorDao();

        dataRepository = DataRepository.getInstance(application);


        /*PagedList.Config pagedListConfig  =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(true)
                        .setPrefetchDistance(PREFETCH_DISTANCE)
                        .setPageSize(PAGE_SIZE)
                        .build();

        moviesLiveData = new LivePagedListBuilder<>(dataRepository.getAllMovies(), pagedListConfig).build();

        mutableMovieList = new MutableLiveData<>();*/






        /*DataSource.Factory<Integer, MovieEntity> dataSourceMovieList = dataRepository.getAllMovies();

        DataSource.Factory<Integer, ListObject> integerObjectFactory = dataSourceMovieList.mapByPage(input -> {

            LinkedHashMap<String, Set<MovieEntity>> groupedHashMap = new LinkedHashMap<>();
            Set<MovieEntity> movieEntitySet = null;

            for (int i = 0; i < input.size(); i++) {

                MovieEntity movieEntity = input.get(i);

                if(movieEntity!= null){

                    String hashMapKey = movieEntity.getTime();

                    if (groupedHashMap.containsKey(hashMapKey)) {
                        // The key is already in the HashMap; add the pojo object
                        // against the existing key.
                        groupedHashMap.get(hashMapKey).add(movieEntity);
                    } else {
                        // The key is not there in the HashMap; create a new key-value pair
                        movieEntitySet = new LinkedHashSet<>();
                        movieEntitySet.add(movieEntity);
                        groupedHashMap.put(hashMapKey, movieEntitySet);
                    }
                }
            }
            return generateListFromMap(groupedHashMap);
        });

        listObjLiveData = new LivePagedListBuilder<>(integerObjectFactory, PAGE_SIZE).build();*/


    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }


    public LiveData<List<MovieEntity>> getMoviesEntityList(){
        return movieDao.getAllMovies();
    }


    public LiveData<PagedList<MovieEntity>> getMovieEntityList(){
        return moviesLiveData;
    }



    public LiveData<PagedList<ListObject>> getMoviesList(List<MovieEntity> movieEntityList) {

        List<ListObject> myList = groupDataIntoHashMap(movieEntityList);

        MovieListDataSource movieListDataSource = new MovieListDataSource(myList);

        mDataSourceFactory = new DataSourceFactory(movieListDataSource);


        PagedList.Config myConfig = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPrefetchDistance(PREFETCH_DISTANCE)
                .setPageSize(PAGE_SIZE)
                .build();

       /* PagedList<ListObject> pagedStrings = new PagedList.Builder<Integer, ListObject>(movieListDataSource, myConfig)
                .setInitialKey(INITIAL_LOAD_KEY)
                .setNotifyExecutor(new MainThreadExecutor()) //The executor defining where page loading updates are dispatched.
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build();

        mutableMovieList.setValue(pagedStrings);
        return mutableMovieList;*/


        listObjLiveData = new LivePagedListBuilder<>(mDataSourceFactory, myConfig).build();

        return listObjLiveData;


    }


    private List<ListObject> groupDataIntoHashMap(List<MovieEntity> movieEntities) {

        LinkedHashMap<String, Set<MovieEntity>> groupedHashMap = new LinkedHashMap<>();
        Set<MovieEntity> movieEntitySet = null;

        for (MovieEntity movieEntity : movieEntities) {

            if(movieEntity!= null){

                String hashMapKey = movieEntity.getTime();

                if (groupedHashMap.containsKey(hashMapKey)) {
                    // The key is already in the HashMap; add the pojo object
                    // against the existing key.
                    groupedHashMap.get(hashMapKey).add(movieEntity);
                } else {
                    // The key is not there in the HashMap; create a new key-value pair
                    movieEntitySet = new LinkedHashSet<>();
                    movieEntitySet.add(movieEntity);
                    groupedHashMap.put(hashMapKey, movieEntitySet);
                }
            }


        }

        //Generate list from map
        return generateListFromMap(groupedHashMap);

    }


    private List<ListObject> generateListFromMap(LinkedHashMap<String, Set<MovieEntity>> groupedHashMap) {
        // We linearly add every item into the consolidatedList.
        Date date1 = null; Date date2 = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //For declaring values in new date objects. use same date format when creating dates
        try {
            date1 = sdf.parse("1995-02-23"); //date1 is February 23, 1995
        } catch (ParseException e) {
            e.printStackTrace();
        }



        List<ListObject> consolidatedList = new ArrayList<>();

        for (String date : groupedHashMap.keySet()) {

            try {
                date2 = sdf.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(!isSameDay(date1, date2)){

                date1 = date2;

                DateObject dateItem = new DateObject();
                dateItem.setDate(date);
                consolidatedList.add(dateItem);

            }

            for (MovieEntity movieEntity : groupedHashMap.get(date)) {
                ChatModelObject generalItem = new ChatModelObject();
                generalItem.setMovieEntity(movieEntity);
                consolidatedList.add(generalItem);
            }

        }

        return consolidatedList;
    }


    public boolean isSameDay(Date date1, Date date2){

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        boolean sameDay = cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);

        return sameDay;
    }

    public DirectorEntity findDirector(int id){
        return dataRepository.findDirectorById(id);
    }


    public int findDirector(String name){
        DirectorEntity directorEntity =  null;
        directorEntity =  dataRepository.findDirectorByName(name);
        if(directorEntity!= null){
            return directorEntity.getId();
        }else{
            return -1;
        }
    }



    public void insertMovie(MovieEntity... movies) {
        dataRepository.insertMovie(movies);
    }

    public long insertDirector(DirectorEntity director){
        return dataRepository.insertDirector(director);
    }
}