//package com.cui.trypro.adapter;
//
//import android.animation.AnimatorSet;
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.animation.AccelerateInterpolator;
//import android.view.animation.DecelerateInterpolator;
//import android.view.animation.OvershootInterpolator;
//import android.widget.FrameLayout;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.TextSwitcher;
//
//import com.cui.trypro.R;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Random;
//
//import butterknife.ButterKnife;
//import butterknife.InjectView;
//
///**
// * Created by froger_mcs on 05.11.14.
// */
//public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
//    private static final int VIEW_TYPE_DEFAULT = 1;
//    private static final int VIEW_TYPE_LOADER = 2;
//
//    private static final DecelerateInterpolator DECCELERATE_INTERPOLATOR = new DecelerateInterpolator();
//    private static final AccelerateInterpolator ACCELERATE_INTERPOLATOR = new AccelerateInterpolator();
//    private static final OvershootInterpolator OVERSHOOT_INTERPOLATOR = new OvershootInterpolator(4);
//
//    private static final int ANIMATED_ITEMS_COUNT = 2;
//
//    private Context context;
//    private int lastAnimatedPosition = -1;
//    private int itemsCount = 0;
//    private boolean animateItems = false;
//
//    private final Map<Integer, Integer> likesCount = new HashMap<>();
//    private final Map<RecyclerView.ViewHolder, AnimatorSet> likeAnimations = new HashMap<>();
//    private final ArrayList<Integer> likedPositions = new ArrayList<>();
//
//    private OnFeedItemClickListener onFeedItemClickListener;
//
//    private boolean showLoadingView = false;
//    private int loadingViewSize = Utils.dpToPx(200);
//
//    private Context mContext;
//    public FeedAdapter(Context mContext) {
//        this.mContext = mContext;
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        final View view = LayoutInflater.from(mContext).inflate(R.layout.item_feed, parent, false);
//        final CellFeedViewHolder cellFeedViewHolder = new CellFeedViewHolder(view);
//
//
//        return cellFeedViewHolder;
//    }
//
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
//
//    }
//
//    private void bindDefaultFeedItem(int position, CellFeedViewHolder holder) {
//
//
//    }
//
//    private void bindLoadingFeedItem(final CellFeedViewHolder holder) {
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//    }
//
//    @Override
//    public int getItemCount() {
//        return itemsCount;
//    }
//
//    private void updateLikesCounter(CellFeedViewHolder holder, boolean animated) {
//    }
//
//
//
//    @Override
//    public void onClick(View view) {
//
//    }
//
//    private void animatePhotoLike(final CellFeedViewHolder holder) {
//
//    }
//
//    private void resetLikeAnimationState(CellFeedViewHolder holder) {
//    }
//
//    public void updateItems(boolean animated) {
//        itemsCount = 10;
//        animateItems = animated;
//        fillLikesWithRandomValues();
//        notifyDataSetChanged();
//    }
//
//    private void fillLikesWithRandomValues() {
//        for (int i = 0; i < getItemCount(); i++) {
//            likesCount.put(i, new Random().nextInt(100));
//        }
//    }
//
//    public void setOnFeedItemClickListener(OnFeedItemClickListener onFeedItemClickListener) {
//        this.onFeedItemClickListener = onFeedItemClickListener;
//    }
//
//    public void showLoadingView() {
//        showLoadingView = true;
//        notifyItemChanged(0);
//    }
//
//    public static class CellFeedViewHolder extends RecyclerView.ViewHolder {
//        @InjectView(R.id.ivFeedCenter)
//        ImageView ivFeedCenter;
//        @InjectView(R.id.ivFeedBottom)
//        ImageView ivFeedBottom;
//        @InjectView(R.id.btnComments)
//        ImageButton btnComments;
//        @InjectView(R.id.btnLike)
//        ImageButton btnLike;
//        @InjectView(R.id.btnMore)
//        ImageButton btnMore;
//        @InjectView(R.id.vBgLike)
//        View vBgLike;
//        @InjectView(R.id.ivLike)
//        ImageView ivLike;
//        @InjectView(R.id.tsLikesCounter)
//        TextSwitcher tsLikesCounter;
//        @InjectView(R.id.ivUserProfile)
//        ImageView ivUserProfile;
//        @InjectView(R.id.vImageRoot)
//        FrameLayout vImageRoot;
//
//        SendingProgressView vSendingProgress;
//        View vProgressBg;
//
//        public CellFeedViewHolder(View view) {
//            super(view);
//            ButterKnife.inject(this, view);
//        }
//
//    }
//
//    public interface OnFeedItemClickListener {
//        public void onCommentsClick(View v, int position);
//
//        public void onMoreClick(View v, int position);
//
//        public void onProfileClick(View v);
//    }
//}
