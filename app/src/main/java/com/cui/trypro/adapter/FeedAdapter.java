package com.cui.trypro.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import com.cui.trypro.R;
import com.cui.trypro.View.circlerefreshlayout.Utils;
import com.cui.trypro.widget.SendingProgressView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by froger_mcs on 05.11.14.
 */
public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> implements View.OnClickListener {

    private Context mContext;
    private int itemsCount = 0;//item总数

    //随机给加like的
    private SparseIntArray likeCount = new SparseIntArray();
    //回调
    OnFeedItemClickListener onFeedItemClickListener;
    /**
     * 补间器
     */
    private static final DecelerateInterpolator DECCELERATE_INTERPOLATOR = new DecelerateInterpolator();
    private static final AccelerateInterpolator ACCELERATE_INTERPOLATOR = new AccelerateInterpolator();
    private static final OvershootInterpolator OVERSHOOT_INTERPOLATOR = new OvershootInterpolator(4);

    /**
     * 保存animatorSet来判断防止收藏被点两次
     */
    private final Map<RecyclerView.ViewHolder, AnimatorSet> likeAnimations = new HashMap<>();
    /**
     * 记录点击过收藏的position 防止再次收藏 同时onbind..方法会不停执行bindDefaultFeedItem里的updateHeartButton方法
     * 会一直判断是否是点击过的position如果点击过的就显示收藏否则反之。以此避免复用问题
     */
    private final ArrayList<Integer> likedPositions = new ArrayList<>();

    boolean isOne = true;//第一次进入才进行动画的判断

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
        final ViewHolder holder1 = holder;
        runEnterAnimation(holder1.itemView, position);
        bindDefaultFeedItem(holder, position);
        holder.ivFeedCenter.setOnClickListener(this);
        holder.btnLike.setOnClickListener(this);
        holder.btnComments.setOnClickListener(this);
        holder.btnMore.setOnClickListener(this);
    }


    @Override
    public int getItemCount() {
        return itemsCount;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivFeedCenter:
                ViewHolder holder1 = (ViewHolder) v.getTag();
                if (!likedPositions.contains(holder1.getPosition())) {//如果没被点击过
                    likedPositions.add(holder1.getPosition());//加入被点击的position
                    updateHeartButton((ViewHolder) v.getTag(), false);
                    animatePhotoLike((ViewHolder) v.getTag());
                    updateLikesCounter(holder1, true);
                }
                break;
            case R.id.btnLike:
                ViewHolder holder2 = (ViewHolder) v.getTag();
                if (!likedPositions.contains(holder2.getPosition())) {
                    likedPositions.add(holder2.getPosition());
                    updateHeartButton(holder2, true);
                    updateLikesCounter(holder2, true);
                }
                break;
            case R.id.btnComments:
                if (onFeedItemClickListener != null) {
                    onFeedItemClickListener.onCommentsClick(v, (Integer) v.getTag());
                }
                break;
            case R.id.btnMore:
                if (onFeedItemClickListener != null) {
                    onFeedItemClickListener.onMoreClick(v, (Integer) v.getTag());
                }
                break;
            case R.id.ivUserProfile:
                if (onFeedItemClickListener != null) {
                    onFeedItemClickListener.onProfileClick(v);
                }
                break;
        }
    }


    /**
     * 点击中间的图片
     */
    private void animatePhotoLike(final ViewHolder holder) {
        if (!likeAnimations.containsKey(holder)) {//有put进去就是收藏过的不播放动画
            holder.vBgLike.setVisibility(View.VISIBLE);
            holder.ivLike.setVisibility(View.VISIBLE);

            holder.vBgLike.setScaleY(0.1f);
            holder.vBgLike.setScaleX(0.1f);
            holder.vBgLike.setAlpha(1f);
            holder.ivLike.setScaleY(0.1f);
            holder.ivLike.setScaleX(0.1f);

            AnimatorSet animatorSet = new AnimatorSet();
            likeAnimations.put(holder, animatorSet);//把animatorSet 放进去用来做对比

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

            animatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
//                    resetLikeAnimationState(holder);
                }
            });
            animatorSet.start();
        }
    }

    private void resetLikeAnimationState(ViewHolder holder) {
        likeAnimations.remove(holder);//删除animatort再点就能再出来
        holder.vBgLike.setVisibility(View.GONE);
        holder.ivLike.setVisibility(View.GONE);
    }

    /**
     * 初始化数据
     */
    public void updateItem() {
        itemsCount = 20;
        //随机给不同的like数量
        for (int i = 0; i < itemsCount; i++) {
            likeCount.put(i, new Random().nextInt(100));
        }
        notifyDataSetChanged();
    }

    /**
     * 改变like总数 textSwitcher
     */
    private void updateLikesCounter(ViewHolder holder, boolean animated) {
        int likesCounts = likeCount.get(holder.getPosition()) + 1;//根据position拿初始化放进去的数字再加1
        String likeCountText = likesCounts + "   likes";
        if (animated) {
            holder.tsLikesCounter.setText(likeCountText);
        } else {
            holder.tsLikesCounter.setCurrentText(likeCountText);
        }
        likeCount.put(holder.getPosition(), likesCounts);//保存下来用以统计，
    }

    /**
     * 初始让第一个（可见）item有动画
     */
    private void runEnterAnimation(View view, int position) {
        if (isOne && position == 0) {
            view.setTranslationY(Utils.getScreenHeight(mContext));
            view.animate()
                    .translationY(0)
                    .setInterpolator(new DecelerateInterpolator(3.f))
                    .setDuration(700)
                    .start();
            isOne = false;
        }
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
        updateLikesCounter(holder, false);
        updateHeartButton(holder, false);

        //把Viewholder放入方便后面使用
        holder.btnComments.setTag(position);
        holder.btnMore.setTag(position);
        holder.ivFeedCenter.setTag(holder);
        holder.btnLike.setTag(holder);

        if (likeAnimations.containsKey(holder)) {
            likeAnimations.get(holder).cancel();
        }
        resetLikeAnimationState(holder);
    }

    /**
     * 更新收藏爱心
     *
     * @param animatored 是否做动画
     */
    private void updateHeartButton(final ViewHolder holder, boolean animatored) {
        if (animatored) {
            if (!likeAnimations.containsKey(holder)) {
                AnimatorSet animatorSet = new AnimatorSet();
                likeAnimations.put(holder, animatorSet);

                ObjectAnimator rotationAnim = ObjectAnimator.ofFloat(holder.btnLike, "rotation", 0f, 360f);
                rotationAnim.setDuration(300);
                rotationAnim.setInterpolator(ACCELERATE_INTERPOLATOR);

                ObjectAnimator bounceAnimX = ObjectAnimator.ofFloat(holder.btnLike, "scaleX", 0.2f, 1f);
                bounceAnimX.setDuration(300);
                bounceAnimX.setInterpolator(OVERSHOOT_INTERPOLATOR);

                ObjectAnimator bounceAnimY = ObjectAnimator.ofFloat(holder.btnLike, "scaleY", 0.2f, 1f);
                bounceAnimY.setDuration(300);
                bounceAnimY.setInterpolator(OVERSHOOT_INTERPOLATOR);
                bounceAnimY.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        holder.btnLike.setImageResource(R.drawable.ic_heart_red);
                    }
                });
                animatorSet.play(bounceAnimX).with(bounceAnimY).after(rotationAnim);
                animatorSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        resetLikeAnimationState(holder);
                    }
                });
                animatorSet.start();
            }
        } else {
            if (likedPositions.contains(holder.getPosition())) {//未点击过收藏点击过取消
                holder.btnLike.setImageResource(R.drawable.ic_heart_red);
            } else {
                holder.btnLike.setImageResource(R.drawable.ic_heart_outline_grey);
            }
        }
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
        TextSwitcher tsLikesCounter;
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

    /**
     * 对外提供方法
     */
    public void setOnFeedItemClickListener(OnFeedItemClickListener onFeedItemClickListener) {
        this.onFeedItemClickListener = onFeedItemClickListener;
    }

    /**
     * 让外部需要实现的方法这样能就把值传回去
     */
    public interface OnFeedItemClickListener {
        public void onCommentsClick(View v, int position);

        public void onMoreClick(View v, int position);

        public void onProfileClick(View v);

    }
}