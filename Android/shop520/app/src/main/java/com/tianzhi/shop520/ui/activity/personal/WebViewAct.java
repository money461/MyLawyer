package com.tianzhi.shop520.ui.activity.personal;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.ui.MainActivity;


/**
 * from首页轮番 webView加载Url 参数：url
 *
 * @author wisdomnt
 *
 */
@SuppressLint({ "SetJavaScriptEnabled", "InlinedApi" })
public class WebViewAct extends BaseFragmentActivity {
    private WebView wv_Help;
    private String URL;
    private String title;
    private ValueCallback<Uri> mUploadMessage;
    public ValueCallback<Uri[]> uploadMessage;
    public static final int REQUEST_SELECT_FILE = 100;
    private final static int FILECHOOSER_RESULTCODE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.act_h5web);
        initData();
        initView();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            if (requestCode == REQUEST_SELECT_FILE)
            {
                if (uploadMessage == null)
                    return;
                uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));
                uploadMessage = null;
            }
        }
        else if (requestCode == FILECHOOSER_RESULTCODE)
        {
            if (null == mUploadMessage)
                return;
            // Use MainActivity.RESULT_OK if you're implementing WebView inside Fragment
            // Use RESULT_OK only if you're implementing WebView inside an Activity
            Uri result = intent == null || resultCode != MainActivity.RESULT_OK ? null : intent.getData();
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;
        }
        else
            Toast.makeText(getBaseContext(), "Failed to Upload Image", Toast.LENGTH_LONG).show();
    }
    /**
     * 初始化数据
     */
    private void initData() {
        String name = paras.getString("Classname");
        setActivityTitle(name);
        URL = paras.getString("linkAddress");
//        URL = "https://openauth.alipay.com/oauth2/appToAppAuth.htm?app_id=2017102109434079&redirect_uri=http://120.55.43.176:8082/user/app/api/zfbAPPnotify";
    }

    @Override
    protected void initView() {
        wv_Help = (WebView) findViewById(R.id.webview);
        //设置每次加载的都是最新的页面
        wv_Help.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 设置编码
        wv_Help.getSettings().setDefaultTextEncodingName("utf-8");
        // 设置WebView属性，能够执行Javascript脚本
        wv_Help.getSettings().setJavaScriptEnabled(true);
        // 设置支持http和https混合加载
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            wv_Help.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        // 自动打开窗口
        wv_Help.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        // 设置布局方式
        wv_Help.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        // 设置可以访问文件
        wv_Help.getSettings().setAllowFileAccess(true);
        // 设置支持缩放
        wv_Help.getSettings().setBuiltInZoomControls(true);
        // webSettings.setDatabaseEnabled(true);

        // 使用localStorage则必须打开
        wv_Help.getSettings().setDomStorageEnabled(true);

        wv_Help.getSettings().setGeolocationEnabled(true);
        // 设置Web视图
        wv_Help.setWebViewClient(new HelloWebViewClient());
        wv_Help.setWebChromeClient(new WebChromeClient() {
            // For 3.0+ Devices (Start)
            // onActivityResult attached before constructor
            protected void openFileChooser(ValueCallback uploadMsg, String acceptType)
            {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "File Browser"), FILECHOOSER_RESULTCODE);
            }


            // For Lollipop 5.0+ Devices
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams)
            {
                if (uploadMessage != null) {
                    uploadMessage.onReceiveValue(null);
                    uploadMessage = null;
                }

                uploadMessage = filePathCallback;

                Intent intent = fileChooserParams.createIntent();
                try
                {
                    startActivityForResult(intent, REQUEST_SELECT_FILE);
                } catch (ActivityNotFoundException e)
                {
                    uploadMessage = null;
                    Toast.makeText(getBaseContext(), "Cannot Open File Chooser", Toast.LENGTH_LONG).show();
                    return false;
                }
                return true;
            }

            //For Android 4.1 only
            protected void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture)
            {
                mUploadMessage = uploadMsg;
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "File Browser"), FILECHOOSER_RESULTCODE);
            }

            protected void openFileChooser(ValueCallback<Uri> uploadMsg)
            {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
            }
        });
        wv_Help.setDownloadListener(new DownloadListener() {

            @Override
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        // 加载需要显示的网页
        wv_Help.loadUrl(URL);
        // mHaojihuiWebView.loadUrl(testUrl);
    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        wv_Help.getSettings().setJavaScriptEnabled(true);
        // 加载需要显示的网页
    }

    // Web视图
    private class HelloWebViewClient extends WebViewClient {
//		@Override
//		public boolean shouldOverrideUrlLoading(WebView view, String url) {
//			 Log.e(TAG, "访问的url地址：" + url);
//			 if(parseScheme(url)){
//				 if( url.startsWith("http:") || url.startsWith("https:") ) {
//		                return false;
//		            }
//		   try{
//		            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//		            startActivity( intent );
//		   }catch(Exception e){}
//		            return true;
//			    }else{
//                 view.loadUrl(url);
//             }
//
//             return true;
//
//		}

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }


        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            goNext(MainActivity.class, paras, true);
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

}
