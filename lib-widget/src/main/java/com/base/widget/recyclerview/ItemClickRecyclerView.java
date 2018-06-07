package com.base.widget.recyclerview;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;

/**
 * RecyclerView 二次封装
 * 作者 linbinghuang
 * 可点击性的view
 *
 */
public class ItemClickRecyclerView extends WrapRecyclerView {

	/**
	 * 手势控制
	 */
	private GestureDetectorCompat mCompat ;
	/**
	 * 单击
	 */
	private IOnItemCilick mItemClick;
	/**
	 * 长按
	 */
	private IOnItemLongCilick mItemLongCilick;

	public ItemClickRecyclerView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public ItemClickRecyclerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ItemClickRecyclerView(Context context) {
		super(context);
		init();

	}

	private void init() {
		SimpleOnGestureListener gestureListener = new SimpleOnGestureListener(){

			@Override
			public void onLongPress(MotionEvent e) {
				if(mItemLongCilick==null){
					return ;
				}
				View view = ItemClickRecyclerView.this.findChildViewUnder(e.getX(), e.getY());
				int i = ItemClickRecyclerView.this.getChildPosition(view);
				mItemLongCilick.onItemLongClick(view, i);
			}

			@Override
			public boolean onSingleTapConfirmed(MotionEvent e) {
				if(mItemClick==null){
					return false;
				}
				View view = ItemClickRecyclerView.this.findChildViewUnder(e.getX(), e.getY());
				int i = ItemClickRecyclerView.this.getChildPosition(view);
				mItemClick.onItemClick(view,i);
				return super.onSingleTapConfirmed(e);
			}


		};
		mCompat = 	new GestureDetectorCompat(getContext(),gestureListener);
//				new OnGestureListener() {
//
//			@Override
//			public boolean onSingleTapUp(MotionEvent e) {
//				if(mItemClick==null){
//					return false;
//				}
//				View view = BaseRecyclerView.this.findChildViewUnder(e.getX(), e.getY());
//				int i = BaseRecyclerView.this.getChildPosition(view);
//				mItemClick.onItemClick(view,i);
//				return false;
//			}
//
//			@Override
//			public void onShowPress(MotionEvent e) {
//			}
//
//			@Override
//			public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
//					float distanceY) {
//				return false;
//			}
//
//			@Override
//			public void onLongPress(MotionEvent e) {
//				if(mItemLongCilick==null){
//					return ;
//				}
//				View view = BaseRecyclerView.this.findChildViewUnder(e.getX(), e.getY());
//				int i = BaseRecyclerView.this.getChildPosition(view);
//				mItemLongCilick.onItemLongClick(view, i);
//			}
//
//			@Override
//			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
//					float velocityY) {
//				return false;
//			}
//
//			@Override
//			public boolean onDown(MotionEvent e) {
//				return false;
//			}
//		});

		OnItemTouchListener  itemTouchListener = new OnItemTouchListener() {


			@Override
			public void onTouchEvent(RecyclerView arg0, MotionEvent arg1) {

			}

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }

            @Override
			public boolean onInterceptTouchEvent(RecyclerView arg0, MotionEvent arg1) {

				if(mCompat!=null){
					mCompat.onTouchEvent(arg1);
				}
				return false;
			}


		};

		this.addOnItemTouchListener(itemTouchListener);
	}


	/**
	 * RecyclerView 点击
	 * 作者 linbinghuang
	 *
	 */
	public interface IOnItemCilick{
		 void onItemClick(View v, int position);
	}
	/**
	 * RecyclerView 长按
	 * 作者 linbinghuang
	 *
	 */
	public interface IOnItemLongCilick{
		 void onItemLongClick(View v, int position);
	}
	public void setmCompat(GestureDetectorCompat mCompat) {
		this.mCompat = mCompat;
	}

	public void setOnItemClick(IOnItemCilick mItemClick) {
		this.mItemClick = mItemClick;
	}

	public void setOnItemLongCilick(IOnItemLongCilick mItemLongCilick) {
		this.mItemLongCilick = mItemLongCilick;
	}


}
