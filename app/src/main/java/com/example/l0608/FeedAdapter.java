package com.example.l0608;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {
    private final List<FeedItem> feedItemList;

    public FeedAdapter(List<FeedItem> feedItemList) {
        this.feedItemList = new ArrayList<>(feedItemList);
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_item_layout, parent, false);
        return new FeedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        FeedItem currentItem = feedItemList.get(position);
        holder.usernameTextView.setText(currentItem.getUsername());
        holder.postContentTextView.setText(currentItem.getPostContent());
    }

    @Override
    public int getItemCount() {
        return feedItemList.size();
    }

    public void setFeedItems(List<FeedItem> newFeedItems) {
        this.feedItemList.clear();
        this.feedItemList.addAll(newFeedItems);
        notifyDataSetChanged();
    }

    class FeedViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTextView;
        TextView postContentTextView;

        FeedViewHolder(View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.usernameTextView);
            postContentTextView = itemView.findViewById(R.id.postContentTextView);
        }
    }
}
