package com.liebersonsantos.kanamobitest.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.liebersonsantos.kanamobitest.R;
import com.liebersonsantos.kanamobitest.contract.RepositoryContract;
import com.liebersonsantos.kanamobitest.model.Item;
import com.liebersonsantos.kanamobitest.presenter.RepositoryPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RepositoryContract.View {

    private RepositoryContract.Presenter presenter;
    private List<Item> items = new ArrayList<>();
    private RepositoryAdapter adapter;
    private int pag = 1;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new RepositoryPresenter();
        presenter.attachView(this);

        setRecyclerView();
        getAllRepositories(pag);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy>0){
                    //scrolling down
                    getAllRepositories(pag);
                }else {
                    //scolling up
                }
            }
        });

    }

    private void setRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RepositoryAdapter(items);
        recyclerView.setAdapter(adapter);
    }

    private void getAllRepositories(int pag) {
        presenter.getRepository(pag);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void showProgress(boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        recyclerView.setVisibility((show) ? View.GONE : View.VISIBLE);
        recyclerView.animate()
                .setDuration(shortAnimTime)
                .alpha((show) ? 0 : 1)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        recyclerView.setVisibility((show) ? View.GONE : View.VISIBLE);
                    }
                });

        progressBar.setVisibility((show) ? View.VISIBLE : View.GONE);
        progressBar.animate()
                .setDuration(shortAnimTime)
                .alpha((show) ? 1 : 0)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        progressBar.setVisibility((show) ? View.VISIBLE : View.GONE);
                    }
                });
    }

    @Override
    public void showRepositories(List<Item> response) {
        if (!response.isEmpty()){
            adapter.update(response);
        }

    }

    @Override
    public void showRepositoriesError(Throwable t) {
        Toast.makeText(this, "Erro ao carregar os dados", Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }


}