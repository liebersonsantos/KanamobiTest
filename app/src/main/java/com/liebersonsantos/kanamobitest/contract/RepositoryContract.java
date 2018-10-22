package com.liebersonsantos.kanamobitest.contract;

import android.content.Context;

import com.liebersonsantos.kanamobitest.model.Item;
import com.liebersonsantos.kanamobitest.model.RepositoryResponse;

import java.util.List;

import io.reactivex.Single;

public class RepositoryContract {

    public interface Model{
        Single<RepositoryResponse> getRepository(String language, String stars, int page);

    }

    public interface View{
        void showProgress(boolean b);
        void showRepositories(List<Item> response);
        void showRepositoriesError(Throwable t);
        Context getContext();

    }

    public interface Presenter{
        void attachView(RepositoryContract.View view);
        void detachView();
        void getRepository(int page);

        void showRepositories(List<Item> response);

        void showRepositoryError(Throwable t);




    }
}
