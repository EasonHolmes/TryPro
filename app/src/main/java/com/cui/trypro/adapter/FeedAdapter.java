package com.cui.trypro.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.cui.trypro.R;
import com.cui.trypro.View.circlerefreshlayout.Utils;
import com.cui.trypro.widget.SendingProgressView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by froger_mcs on 05.11.14.
 */
public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> implements View.OnClickListener {

    private Context mContext;
    private int itemsCount = 0;

    //随机给加like的
    private SparseIntArray map = new SparseIntArray();
    //纪录是否点过like的
    private SparseArray isClickLike = new SparseArray();


    /**
     * 补间器
     */
    private static final DecelerateInterpolator DECCELERATE_INTERPOLATOR = new DecelerateInterpolator();
    private static final AccelerateInterpolator ACCELERATE_INTERPOLATOR = new AccelerateInterpolator();
    private static final OvershootInterpolator OVERSHOOT_INTERPOLATOR = new OvershootInterpolator(4);

    private final Map<RecyclerView.ViewHolder, AnimatorSet> likeAnimations = new HashMap<>();


    public FeedAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        runEnterAnimation(holder.itemView, position);
        bindDefaultFeedItem(holder, position);
        holder.ivFeedCenter.setOnClickListener(this);
    }


    @Override
    public int getItemCount() {
        return itemsCount;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivFeedCenter:
                animatePhotoLike((ViewHolder) v.getTag());
                break;
        }
    }

    private void animatePhotoLike(final ViewHolder holder) {
        if (!likeAnimations.containsKey(holder)) {
            holder.vBgLike.setVisibility(View.VISIBLE);
            holder.ivLike.setVisibility(View.VISIBLE);

            holder.vBgLike.setScaleY(0.1f);
            holder.vBgLike.setScaleX(0.1f);
            holder.vBgLike.setAlpha(1f);
            holder.ivLike.setScaleY(0.1f);
            holder.ivLike.setScaleX(0.1f);

            AnimatorSet animatorSet = new AnimatorSet();
            likeAnimations.put(holder, animatorSet);

            ObjectAnimator bgScaleYAnim = ObjectAnimator.ofFloat(holder.vBgLike, "scaleY", 0.1f, 1f);
            bgScaleYAnim.setDuration(200);
            bgScaleYAnim.setInterpolator(DECCELERATE_INTERPOLATOR);
            ObjectAnimator bgScaleXAnim = ObjectAnimator.ofFloat(holder.vBgLike, "scaleX", 0.1f, 1f);
            bgScaleXAnim.setDuration(200);
            bgScaleXAnim.setInterpolator(DECCELERATE_INTERPOLATOR);
            ObjectAnimator bgAlphaAnim = ObjectAnimator.ofFloat(holder.vBgLike, "alpha", 1f, 0f);
            bgAlphaAnim.setDuration(200);
            bgAlphaAnim.setStartDelay(150);
            bgAlphaAnim.setInterpolator(DECCELERATE_INTERPOLATOR);

            ObjectAnimator imgScaleUpYAnim = ObjectAnimator.ofFloat(holder.ivLike, "scaleY", 0.1f, 1f);
            imgScaleUpYAnim.setDuration(300);
            imgScaleUpYAnim.setInterpolator(DECCELERATE_INTERPOLATOR);
            ObjectAnimator imgScaleUpXAnim = ObjectAnimator.ofFloat(holder.ivLike, "scaleX", 0.1f, 1f);
            imgScaleUpXAnim.setDuration(300);
            imgScaleUpXAnim.setInterpolator(DECCELERATE_INTERPOLATOR);

            ObjectAnimator imgScaleDownYAnim = ObjectAnimator.ofFloat(holder.ivLike, "scaleY", 1f, 0f);
            imgScaleDownYAnim.setDuration(300);
            imgScaleDownYAnim.setInterpolator(ACCELERATE_INTERPOLATOR);
            ObjectAnimator imgScaleDownXAnim = ObjectAnimator.ofFloat(holder.ivLike, "scaleX", 1f, 0f);
            imgScaleDownXAnim.setDuration(300);
            imgScaleDownXAnim.setInterpolator(ACCELERATE_INTERPOLATOR);

            animatorSet.playTogether(bgScaleYAnim, bgScaleXAnim, bgAlphaAnim, imgScaleUpYAnim, imgScaleUpXAnim);
            animatorSet.play(imgScaleDownYAnim).with(imgScaleDownXAnim).after(imgScaleUpYAnim);

//            animatorSet.addListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    resetLikeAnimationState(holder);
//                }
//            });
            animatorSet.start();
        }
    }

    public void updateItem() {
        itemsCount = 20;
        for (int i = 0; i < itemsCount; i++) {
            map.put(i, new Random().nextInt(100));
        }
        notifyDataSetChanged();
    }

    /**
     * 初始让第一个（可见）item有动画
     */
    private void runEnterAnimation(View view, int position) {
        if (position > 0) {
            return;
        }
        view.setTranslationY(Utils.getScreenHeight(mContext));
        view.animate()
                .translationY(0)
                .setInterpolator(new DecelerateInterpolator(3.f))
                .setDuration(700)
                .start();
    }

    /**
     * 初始化内容
     */
    private void bindDefaultFeedItem(FeedAdapter.ViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.ivFeedCenter.setImageResource(R.drawable.item_feed_center_1);
            holder.ivFeedBottom.setImageResource(R.drawable.img_feed_bottom_1);
        } else {
            holder.ivFeedCenter.setImageResource(R.drawable.item_feed_center_2);
            holder.ivFeedBottom.setImageResource(R.drawable.img_feed_bottom_2);
        }

//        updateLikesCounter(holder, false);
//        updateHeartButton(holder, false);

        //把Viewholder放入方便后面使用
        holder.btnComments.setTag(position);
        holder.btnMore.setTag(position);
        holder.ivFeedCenter.setTag(holder);
        holder.btnLike.setTag(holder);
        if (likeAnimations.containsKey(holder)) {
            likeAnimations.get(holder).cancel();
        }

//        resetLikeAnimationState(holder);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.ivFeedCenter)
        ImageView ivFeedCenter;
        @InjectView(R.id.ivFeedBottom)
        ImageView ivFeedBottom;
        @InjectView(R.id.btnComments)
        ImageButton btnComments;
        @InjectView(R.id.btnLike)
        ImageButton btnLike;
        @InjectView(R.id.btnMore)
        ImageButton btnMore;
        @InjectView(R.id.vBgLike)
        View vBgLike;
        @InjectView(R.id.ivLike)
        ImageView ivLike;
        @InjectView(R.id.tsLikesCounter)
        TextView tsLikesCounter;
        @InjectView(R.id.ivUserProfile)
        ImageView ivUserProfile;
        @InjectView(R.id.vImageRoot)
        FrameLayout vImageRoot;

        SendingProgressView vSendingProgress;
        View vProgressBg;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

    }
}