package com.tianzhi.shop520.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.interfaces.OnViewChangeListener;
import com.tianzhi.shop520.ui.diyview.SlidingControl;

/**
 * 引导页
 */
public class StartGuideActivity extends BaseFragmentActivity implements
		OnViewChangeListener {

	private SlidingControl scrollLayout;// 自定义手势滑动控制
	private ImageButton btn_Start;// 开始按钮
	private LinearLayout ly_Point;// 小圆点
	private ImageView rl_guide_1;
	private ImageView rl_guide_2;
	private ImageView rl_guide_3;
	private ImageView rl_guide_4;
	private int count;
	private int currentItem;// 当前页
	private ImageView[] imgs;// 引导页图片

	private int[] imgUrls = {
			R.mipmap.guide_1,
			R.mipmap.guide_2,
			R.mipmap.guide_3,
	R.mipmap.guide_4};

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		initView();
		initBackground();
		initImg();
		clickLisenter();

	}

	/**
	 * 控件初始化
	 */
	protected void initView() {

		rl_guide_1 = (ImageView) findViewById(R.id.guide_1);
		rl_guide_2 = (ImageView) findViewById(R.id.guide_2);
		rl_guide_3 = (ImageView) findViewById(R.id.guide_3);
		rl_guide_4 = (ImageView) findViewById(R.id.guide_4);
		scrollLayout = (SlidingControl) findViewById(R.id.ScrollLayout);
		ly_Point = (LinearLayout) findViewById(R.id.llayout);
		btn_Start = (ImageButton) findViewById(R.id.startBtn);
	}

	/**
	 * 设置背景
	 */
	@SuppressLint("NewApi")
	private void initBackground() {
		setAsynImg(imgUrls[0], rl_guide_1, R.mipmap.guide_1);
		setAsynImg(imgUrls[1], rl_guide_2, R.mipmap.guide_2);
		setAsynImg(imgUrls[2], rl_guide_3, R.mipmap.guide_3);
		setAsynImg(imgUrls[3], rl_guide_4, R.mipmap.guide_4);
	}

	private void setAsynImg(int url, ImageView iv_pic, int drawID) {
//		DisplayImageOptions options = new DisplayImageOptions.Builder()
//				.showStubImage(drawID) // 设置图片在下载期间显示的图片
//				.showImageForEmptyUri(drawID) // 设置图片Uri为空或是错误的时候显示的图片
//				.showImageOnFail(drawID) // 设置图片加载/解码过程中错误时候显示的图片
//				.cacheInMemory() // 是否緩存都內存中
//				.cacheOnDisc() // 是否緩存到sd卡上
//				.build();
//
//		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
//				context).threadPriority(Thread.NORM_PRIORITY - 2)// 设置线程的优先级
//				.denyCacheImageMultipleSizesInMemory()// 当同一个Uri获取不同大小的图片，缓存到内存时，只缓存一个。默认会缓存多个不同的大小的相同图片
//				.discCacheFileNameGenerator(new Md5FileNameGenerator())// 设置缓存文件的名字
//				.discCacheFileCount(60)// 缓存文件的最大个数
//				.tasksProcessingOrder(QueueProcessingType.LIFO)// 设置图片下载和显示的工作队列排序
//				.build();
//		// 初始化ImageLoader配置
//		ImageLoader.getInstance().init(config);
//		ImageLoader.getInstance().displayImage(url, iv_pic, options);
		Glide.with(context).load(url).into(iv_pic);
	}

	/**
	 * 图片滑动
	 */
	private void initImg() {

		count = scrollLayout.getChildCount();
		imgs = new ImageView[count];
		for (int i = 0; i < count; i++) {
			imgs[i] = (ImageView) ly_Point.getChildAt(i);
			imgs[i].setEnabled(true);
			imgs[i].setTag(i);
		}
		currentItem = 0;// 默认当前在第0页
		imgs[currentItem].setEnabled(false);
	}

	/**
	 * 点击事件
	 */
	private void clickLisenter() {

		scrollLayout.SetOnViewChangeListener(this);
		btn_Start.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
//
				// 开启按钮进入登录页面
				goNext(MainActivity.class, paras, true);
			}
		});
	}

	@Override
	public void OnViewChange(int position) {
		setcurrentPoint(position);
	}

	/**
	 * 下面小圆点联动
	 */
	private void setcurrentPoint(int position) {
		if (position < 0 || position > count - 1 || currentItem == position) {
			return;
		}
		imgs[currentItem].setEnabled(true);
		imgs[position].setEnabled(false);
		currentItem = position;
	}

	/**
	 * 返回键重写.退出
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}

}