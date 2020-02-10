package com.tianzhi.shop520.ui.activity.personal;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.client.result.ParsedResultType;
import com.lzy.okgo.OkGo;
import com.mylhyl.zxing.scanner.encode.QREncode;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.callback.DialogJsonCallback;
import com.tianzhi.shop520.entity.login.UserInfo;
import com.tianzhi.shop520.entity.personal.UploadHead;
import com.tianzhi.shop520.util.ACache;
import com.tianzhi.shop520.util.AppShared;
import com.tianzhi.shop520.util.BaseConstant;
import com.tianzhi.shop520.util.Constants;
import com.tianzhi.shop520.util.LogUtils;
import com.tianzhi.shop520.util.PathManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;
import okhttp3.Call;
import okhttp3.Response;

import static com.tianzhi.shop520.R.id.tv_mycode;


/**
 * Created by thinkpad on 2017/10/20.
 * 创建二维码
 */

public class CreateQrcodeAct extends BaseFragmentActivity {
    @BindView(R.id.iv_qrcode)
    ImageView ivQrcode;
    @BindView(R.id.save_qrcode)
    TextView saveQrcode;
    @BindView(tv_mycode)
    TextView tvMycode;
    @BindView(R.id.btn_copy)
    TextView btnCopy;
//    private static final String SD_PATH = "/sdcard/shop520/pic/";
//    private static final String IN_PATH = "/shop520/pic/";
    static File filePic;
    static Context context;
    Bitmap bitmap;//生成的二维码
    ACache aCache;
    UserInfo userInfo;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        aCache = ACache.get(context);
        setContentLayout(R.layout.act_creatqrcode);
        initView();
//        if(TextUtils.isEmpty(AppShared.getInstance(context).getLoginInfo().qrCode)){
            creatQRcode();
//        }
    }
//    /**
//     * 随机生产文件名
//     *
//     * @return
//     */
//    private static String generateFileName() {
//        return UUID.randomUUID().toString();
//    }
    //创建二维码
    private void creatQRcode() {
        Resources res = getResources();
        Bitmap logoBitmap = BitmapFactory.decodeResource(res, R.mipmap
                .ic_launcher);
//        Bitmap qrBg = BitmapFactory.decodeResource(res, R.mipmap
//                .wb_wlog_blow_bg_night);
        LogUtils.e("二维码",AppShared.getInstance(context).getLoginInfo().recommendedCode);
        String qrContent = "http://www.520zhiai.com/recommend/register.html?recommendedCode="+AppShared.getInstance(context).getLoginInfo().recommendedCode;
         bitmap = new QREncode.Builder(this)
//                        .setColor(getResources().getColor(R.color.colorPrimary))//二维码颜色
//                .setColors(0xFF0094FF, 0xFFFED545, 0xFF5ACF00, 0xFFFF4081)//二维码彩色
//                .setQrBackground(qrBg)//二维码背景
                .setMargin(0)//二维码边框
                //二维码类型
                .setParsedResultType(TextUtils.isEmpty(qrContent) ? ParsedResultType.URI
                        : ParsedResultType.TEXT)
                //二维码内容
                .setContents(TextUtils.isEmpty(qrContent) ? "http://www.tianzhirj.com/" :
                        qrContent)
                .setSize(500)//二维码等比大小
                .setLogoBitmap(logoBitmap, 90)
                .build().encodeAsBitmap();
        ivQrcode.setImageBitmap(bitmap);
        File  file = getFile(bitmap);
        if(!TextUtils.isEmpty(file.getAbsolutePath())){
            uploadQrpic(file);
        }
    }

    /*上传二维码图片*/
    private void uploadQrpic(File file){
        showLoading();
        OkGo.post(BaseConstant.TestUrl + BaseConstant.UPDATEQRCODE)
                .tag(this)
                .params("id", AppShared.getInstance(context).getLoginInfo().id)
                .params("userToken", AppShared.getInstance(context).getLoginInfo().userToken)
                .isMultipart(true)
                .params("file", file)
                .execute(new DialogJsonCallback<UploadHead>(this,false) {
                    @Override
                    public void onSuccess(UploadHead uploadHead, Call call, Response response) {
                        dismissLoading();
                       LogUtils.e("上传二维码",uploadHead.toString());
                        //{
                      /*  "flag":200,
                                "msg":"success",
                                "data":"group1/M00/00/03/rBBH51ny4w2AP6WlAAA3xUGX7Wo033.jpg?a30e16c6-6a1d-4e9a-97cd-81fe83794b02.jpg"
                    }*/
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dismissLoading();
                    }
                });
    }
    /*保存二维码图片到手机*/
    private static File  getFile(Bitmap bitmap){
        String savePath;

//        if (Environment.getExternalStorageState().equals(
//                Environment.MEDIA_MOUNTED)) {
//            savePath = SD_PATH;
//        } else {
            savePath =  PathManager.DOWNLOAD_FILES_DIR ;
//        }
        try {
            filePic = new File(savePath + "image" + ".jpg");
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(filePic);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return filePic;
    }
    /*保存二维码图片到手机*/
    private static String  savePic(Bitmap bitmap){
        String savePath;
        savePath =  PathManager.DOWNLOAD_FILES_DIR ;
//        if (Environment.getExternalStorageState().equals(
//                Environment.MEDIA_MOUNTED)) {
//            savePath = SD_PATH;
//        } else {
//            savePath = context.getApplicationContext().getFilesDir()
//                    .getAbsolutePath()
//                    + IN_PATH;
//        }
        try {
            filePic = new File(savePath + "image" + ".jpg");
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(filePic);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return filePic.getAbsolutePath();
    }

    protected void initView() {
        if(null!=(UserInfo) aCache.getAsObject(Constants.USERINFO)){
            userInfo=(UserInfo) aCache.getAsObject(Constants.USERINFO);
        }else
            userInfo = AppShared.getInstance(context).getLoginInfo();
        if(null!=userInfo){
            tvMycode.setText(userInfo.recommendedCode);
        }
        setActivityTitle("生成/分享二维码");
        setRightButtonShow(true);
        rightBtn.setText("分享");
        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });
    }

    /**
     * sharesdk分享
     */
    private void showShare() {
//        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("又有一位好友成为爱心520商城会员啦!分享爱拿红包！");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://www.520zhiai.com/recommend/register.html?recommendedCode="+AppShared.getInstance(context).getLoginInfo().recommendedCode);
        // text是分享文本，所有平台都需要这个字段
        oks.setText("进入520商城，成为爱心会员，享爱心特价，分享推荐入会得100元现金红包可即刻提现哦!");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath(SD_PATH+"image.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://www.520zhiai.com/recommend/register.html?recommendedCode="+AppShared.getInstance(context).getLoginInfo().recommendedCode);
       //设置图片
        oks.setImageUrl("http://www.shcmdn.cn/group1/M00/00/13/rBBH51pViAyANb9RAABvNaVGp18701.png?rBBH51oeSxKAAHV7AAAoxBEksho485.png");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("分享推荐入会得100元！");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://www.tianzhirj.com/");
// 启动分享GUI
        oks.show(this);
        // 启动分享GUI
        oks.show(context);
    }

    @OnClick({R.id.save_qrcode, R.id.btn_copy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.save_qrcode:
                String picpath = savePic(bitmap);
                if(!TextUtils.isEmpty(picpath)){
                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    Uri uri = Uri.fromFile(filePic);
                    intent.setData(uri);
                    context.sendBroadcast(intent);
                    //这个广播的目的就是更新图库，发了这个广播进入相册就可以找到你保存的图片了！，
                    // 记得要传你更新的file哦
                }
                LogUtils.e("图片路径",picpath.toString());
                toast("图片保存在"+picpath);
                break;
            case R.id.btn_copy:
                // 从API11开始android推荐使用android.content.ClipboardManager
                // 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(tvMycode.getText());
                toast("复制成功");
                break;
        }
    }
}
