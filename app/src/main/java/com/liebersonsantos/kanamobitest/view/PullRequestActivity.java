package com.liebersonsantos.kanamobitest.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.liebersonsantos.kanamobitest.R;
import com.liebersonsantos.kanamobitest.contract.PullRequestContract;
import com.liebersonsantos.kanamobitest.model.pullRequests.PullResquestResponse;
import com.liebersonsantos.kanamobitest.model.repositories.Item;
import com.liebersonsantos.kanamobitest.presenter.PullRequestPresenter;
import com.liebersonsantos.kanamobitest.view.adapter.PullResquestAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PullRequestActivity extends AppCompatActivity implements PullRequestContract.View{

    private PullRequestContract.Presenter presenter;
    private List<PullResquestResponse> responseList = new ArrayList<>();
    private PullResquestAdapter adapter;

    private String owner;
    private String repositoryName;
    private Item item;

    @BindView(R.id.text_opened)
    TextView openedText;
    @BindView(R.id.text_closed)
    TextView closedText;
    @BindView(R.id.recycler_view_pull)
    RecyclerView recyclerView;
    @BindView(R.id.arrow_back)
    ImageView imageBack;
    @BindView(R.id.toolbar_text)
    TextView textToolbar;
    @BindView(R.id.progress_content_pull)
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_request);
        ButterKnife.bind(this);

        presenter = new PullRequestPresenter();
        presenter.attachView(this);

        unBundle();

        presenter.getPullRequest(owner, repositoryName);
        settingToolbar();

        setRecyclerView();

    }

    private void setRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new PullResquestAdapter(responseList);
        recyclerView.setAdapter(adapter);
    }

    private void settingToolbar() {
        textToolbar.setText(repositoryName);
        imageBack.setVisibility(View.VISIBLE);
        goHome();
    }

    private void goHome() {
        imageBack.setOnClickListener(v -> {
            Intent intent = new Intent(PullRequestActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void unBundle() {
        owner = getIntent().getStringExtra(Navigation.OWNER);
        repositoryName = getIntent().getStringExtra(Navigation.REPOSITORY_NAME);
        Log.i("TAG", "unBundle: -------------------------------> " + owner + "------" + repositoryName);
    }

    @Override
    public Context getContext() {
        return this;
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
    public void showRepositories(List<PullResquestResponse> responseList) {
        if (!responseList.isEmpty()){
            adapter.update(responseList);
        }
    }

    @Override
    public void showRepositoriesError(Throwable t) {
        Toast.makeText(this, "Erro ao carregar os dados", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateOpenClosed(String open, String closed) {
        openedText.setText(getString(R.string.open, open));
        closedText.setText(getString(R.string.closed, closed));
    }
}
