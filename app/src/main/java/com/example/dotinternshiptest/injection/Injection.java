package com.example.dotinternshiptest.injection;

import com.example.dotinternshiptest.data.Repository;
import com.example.dotinternshiptest.data.remote.RemoteDataSource;
import com.example.dotinternshiptest.util.JsonHelper;

public class Injection {
    public static Repository provideRepository(){
        RemoteDataSource remoteDataSource = RemoteDataSource.getINSTANCE(new JsonHelper());
        return Repository.getINSTANCE(remoteDataSource);
    }
}
