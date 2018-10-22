package com.liebersonsantos.kanamobitest.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.liebersonsantos.kanamobitest.R;
import com.liebersonsantos.kanamobitest.model.Item;
import com.liebersonsantos.kanamobitest.util.ImageUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder> {

    private List<Item> repositories;

    public RepositoryAdapter(List<Item> repositories) {
        this.repositories = repositories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main, parent,false);

        return new RepositoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(repositories.get(position));

    }

    @Override
    public int getItemCount() {
        return (repositories != null && repositories.size() > 0) ? repositories.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameRepository;
        private TextView descriptionRepository;
        private TextView numberForks;
        private TextView numberStars;
        private TextView userName;
        private TextView nameAndLastName;
        private ProgressBar progress;
        private CircleImageView imageUser;

        public ViewHolder(View itemView) {
            super(itemView);

            nameRepository = itemView.findViewById(R.id.repository_name);
            descriptionRepository = itemView.findViewById(R.id.description_repository);
            numberForks = itemView.findViewById(R.id.number_forks);
            numberStars = itemView.findViewById(R.id.number_star);
            userName = itemView.findViewById(R.id.user_name_main);
            nameAndLastName = itemView.findViewById(R.id.name_last_name);
            progress = itemView.findViewById(R.id.progress);
            imageUser = itemView.findViewById(R.id.circle_image_main);
        }

        public  void bind(Item item){

            nameRepository.setText(item.getName());
            descriptionRepository.setText(item.getDescription() + "...");
            numberForks.setText(String.valueOf(item.getForks()));
            numberStars.setText(String.valueOf(item.getStargazersCount()));
            userName.setText(item.getOwner().getLogin());
            nameAndLastName.setText(item.getFullName());
            ImageUtil.loadImage(item.getOwner().getAvatarUrl(), imageUser, progress);
        }
    }

    public void update(List<Item> repositories) {
        this.repositories.clear();
        this.repositories.addAll(repositories);
        notifyDataSetChanged();
    }
}
