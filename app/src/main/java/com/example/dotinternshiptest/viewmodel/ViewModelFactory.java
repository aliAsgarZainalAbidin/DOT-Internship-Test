package com.example.dotinternshiptest.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.dotinternshiptest.data.Repository;
import com.example.dotinternshiptest.injection.Injection;
import com.example.dotinternshiptest.ui.main.gallery.GalleryViewModel;
import com.example.dotinternshiptest.ui.main.list.ListViewModel;
import com.example.dotinternshiptest.ui.main.profile.ProfileViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;
    private Repository repository;

    public ViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    public static ViewModelFactory getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(Injection.provideRepository());
                }
            }
        }
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ListViewModel.class)) {
            return (T) new ListViewModel(repository);
        } else if (modelClass.isAssignableFrom(GalleryViewModel.class)){
            return (T) new GalleryViewModel(repository);
        } else if (modelClass.isAssignableFrom(ProfileViewModel.class)){
            return (T) new ProfileViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown View Model Class : " + modelClass.getSimpleName());
    }
}
