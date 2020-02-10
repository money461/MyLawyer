package com.tianzhi.shop520.callback;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Window;
import android.widget.Toast;

import com.google.gson.stream.JsonReader;
import com.lzy.okgo.request.BaseRequest;
import com.tianzhi.shop520.APPLICATION;
import com.tianzhi.shop520.entity.BaseResponse;
import com.tianzhi.shop520.entity.SimpleResponse;
import com.tianzhi.shop520.ui.diyview.dialog.LMToast;
import com.tianzhi.shop520.util.Convert;
import com.tianzhi.shop520.util.LogUtils;
import com.tianzhi.shop520.util.NetworkUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Response;

public abstract class DialogCallback<T> extends JsonCallback<T> {

    private ProgressDialog dialog;
    private Activity activity;
    private String flag;
    private String msg;
    private Dialog loadingDialog = null;
    private void initDialog(Activity activity) {
        dialog = new ProgressDialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
//        dialog.setMessage("请求网络中...");
    }
    public DialogCallback(Activity activity, boolean isture) {
        super();
        this.activity =activity;
        if (isture) {
            initDialog(activity);
        }
    }

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
        if (!NetworkUtil.isConnected(activity)) {
            LMToast.showToast("网络连接已断开");
            return;
        }
        //网络请求前显示对话框
        if (dialog != null && !dialog.isShowing()) {
            if(null !=activity){
                dialog.show();
            }
        }
    }


    @Override
    public void onAfter(@Nullable T t, @Nullable Exception e) {
        super.onAfter(t, e);
      //  网络请求结束后关闭对话框
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void onError(Call call, Response response, Exception e) {
        super.onError(call, response, e);
        if(!TextUtils.isEmpty(flag)){
            switch (flag){
                case"521"://手机号码或者密码错误
                    Toast.makeText(activity,"手机号码已经被使用！",Toast.LENGTH_LONG).show();
                    break;
                case "522":
                    Toast.makeText(activity,"发送验证码错误，请稍后重试！",Toast.LENGTH_LONG).show();
                    break;
                default:
                    LMToast.showToast(msg);
                    break;
            }
        }
    }

    @Override
    public void onSuccess(T t, Call call, Response response) {
        if(!TextUtils.isEmpty(flag)){
            switch (flag){
                case"200":
                    break;
                case"521"://手机号码或者密码错误
                    Toast.makeText(activity,"手机号码或者密码错误",Toast.LENGTH_LONG).show();
                    break;
                default:
                    LMToast.showToast(msg);
                    break;
            }
        }
    }

    @Override
    public T convertSuccess(Response response) throws Exception {
        // 如果你对这里的代码原理不清楚，可以看这里的详细原理说明：https://github.com/jeasonlzy/okhttp-OkGo/blob/master/README_JSONCALLBACK.md

        //以下代码是通过泛型解析实际参数,泛型必须传
        //这里为了方便理解，假如请求的代码按照上述注释文档中的请求来写，那么下面分别得到是

        Type genType = getClass().getGenericSuperclass();
        //从上述的类中取出真实的泛型参数，有些类可能有多个泛型，所以是数值
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        //我们的示例代码中，只有一个泛型，所以取出第一个，得到如下结果
        //com.lzy.demo.model.LzyResponse<com.lzy.demo.model.ServerModel>
        Type type = params[0];

        // 这里这么写的原因是，我们需要保证上面我解析到的type泛型，仍然还具有一层参数化的泛型，也就是两层泛型
        // 如果你不喜欢这么写，不喜欢传递两层泛型，那么以下两行代码不用写，并且javabean按照
        // https://github.com/jeasonlzy/okhttp-OkGo/blob/master/README_JSONCALLBACK.md 这里的第一种方式定义就可以实现
        if (!(type instanceof ParameterizedType)) throw new IllegalStateException("没有填写泛型参数");
        //如果确实还有泛型，那么我们需要取出真实的泛型，得到如下结果
        //class com.lzy.demo.model.LzyResponse
        //此时，rawType的类型实际上是 class，但 Class 实现了 Type 接口，所以我们用 Type 接收没有问题
        Type rawType = ((ParameterizedType) type).getRawType();
        //这里获取最终内部泛型的类型 com.lzy.demo.model.ServerModel
        Type typeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];

        //这里我们既然都已经拿到了泛型的真实类型，即对应的 class ，那么当然可以开始解析数据了，我们采用 Gson 解析
        //以下代码是根据泛型解析数据，返回对象，返回的对象自动以参数的形式传递到 onSuccess 中，可以直接使用
        JsonReader jsonReader = new JsonReader(response.body().charStream());


        if (typeArgument == Void.class) {
            //无数据类型,表示没有data数据的情况（以  new DialogCallback<LzyResponse<Void>>(this)  以这种形式传递的泛型)
            SimpleResponse simpleResponse = Convert.fromJson(jsonReader, SimpleResponse.class);
            response.close();
            //noinspection unchecked
            return (T) simpleResponse.toLzyResponse();
        } else if (rawType == BaseResponse.class) {
            //有数据类型，表示有data
            BaseResponse baseResponse = Convert.fromJson(jsonReader, type);
            response.close();
            flag = baseResponse.flag;
            msg = baseResponse.msg;
            LogUtils.e("┌────开始：","──────────────────────────────────────────────────────────────────────────────");
            LogUtils.e("├────flag：",baseResponse.flag.toString());
            LogUtils.e("├────msg：",baseResponse.msg.toString());
            if(!TextUtils.isEmpty(baseResponse.data.toString())){
                LogUtils.e("├────请求成功返回数据：",baseResponse.data.toString());
            }
            LogUtils.e("└────结束：","──────────────────────────────────────────────────────────────────────────────");
            //这里的0是以下意思
            //一般来说服务器会和客户端约定一个数表示成功，其余的表示失败，这里根据实际情况修改
            if ("200".equals(flag)) {
                //noinspection unchecked
                return (T) baseResponse;
            } else if ("521".equals(flag)) {
//                //比如：用户授权信息无效，在此实现相应的逻辑，弹出对话或者跳转到其他页面等,该抛出错误，会在onError中回调。
                throw new IllegalStateException("手机号码已经被使用");
            }else if ("522".equals(flag)) {
                throw new IllegalStateException("发送验证码错误，请稍后重试！");
            }else if ("523".equals(flag)) {
                throw new IllegalStateException("手机号码未注册！");
            }else if ("524".equals(flag)) {
                throw new IllegalStateException("手机号码格式错误！");
            }else if ("525".equals(flag)) {
                throw new IllegalStateException("手机号码不能为空！");
            }else if ("526".equals(flag)) {
                throw new IllegalStateException("手机号码已经被使用！");
            }else if ("527".equals(flag)) {
                throw new IllegalStateException("推荐码错误或推荐码已经失效！");
            }else if ("528".equals(flag)) {
                throw new IllegalStateException("服务器繁忙，请稍后重试！");
            }else if ("529".equals(flag)) {
                throw new IllegalStateException("密码必须为6-19位的数字、字母组成");
            }else if ("530".equals(flag)) {
                throw new IllegalStateException("验证码错误！");
            }else if ("531".equals(flag)) {
                throw new IllegalStateException("验证码不能为空！");
            } else if ("532".equals(flag)) {
                throw new IllegalStateException("密码不能为空！");
            }else if ("533".equals(flag)) {
                throw new IllegalStateException("手机号码不能为空！");
            }else if ("534".equals(flag)) {
                throw new IllegalStateException("手机号码或者密码错误！");
            }else if ("535".equals(flag)) {
                throw new IllegalStateException("用户账号已被冻结！");
            }else if ("536".equals(flag)) {
                throw new IllegalStateException("修改失败，原密码错误！");
            }else if ("537".equals(flag)) {
                throw new IllegalStateException("用户账号不存在！");
            }else if ("538".equals(flag)) {
                APPLICATION.isLogin = false;
                throw new IllegalStateException("用户未登录！");
            }else if ("539".equals(flag)) {
                throw new IllegalStateException("新密码不能为空！");
            }else if ("540".equals(flag)) {
                throw new IllegalStateException("原密码不能为空！");
            }else if ("541".equals(flag)) {
                throw new IllegalStateException("用户token不能为空！");
            }else if ("542".equals(flag)) {
                throw new IllegalStateException("用户Id不能为空！");
            }else if ("543".equals(flag)) {
                throw new IllegalStateException("请传入参数！");
            }else if ("544".equals(flag)) {
                throw new IllegalStateException("添加支付宝登录授权信息失败，请重新授权！");
            }else if ("545".equals(flag)) {
                throw new IllegalStateException("支付宝授权id不能为空！");
            }else if ("546".equals(flag)) {
                throw new IllegalStateException("支付宝授权code不能为空！");
            }else if ("547".equals(flag)) {
                throw new IllegalStateException("订单信息不存在，请稍后重试！");
            }else if ("548".equals(flag)) {
                throw new IllegalStateException("订单id不能为空！");
            }else if ("549".equals(flag)) {
                throw new IllegalStateException("请先选择支付的类型！");
            }else if ("551".equals(flag)) {
                throw new IllegalStateException("订单信息不存在，请稍后重试！");
            }else if ("552".equals(flag)) {
                throw new IllegalStateException("请先进行支付宝账户授权！");
            }else if ("553".equals(flag)) {
                throw new IllegalStateException("亲，没有这么多的爱心值可以提取！");
            }else if ("554".equals(flag)) {
                throw new IllegalStateException("爱心值出现异常，请稍后重试！");
            }else if ("556".equals(flag)) {
                throw new IllegalStateException("提现爱心值至少在50以上！");
            }else if ("557".equals(flag)) {
                throw new IllegalStateException("支付宝账号不能为空！");
            }else if ("558".equals(flag)) {
                throw new IllegalStateException("支付宝真实收款人姓名不能为空！");
            }else if ("559".equals(flag)) {
                throw new IllegalStateException("昵称长度不能大于10！");
            }else if ("560".equals(flag)) {
                throw new IllegalStateException("创建订单失败，您已是会员！");
            }else if ("561".equals(flag)) {
                throw new IllegalStateException("快递物流号不能为空！");
            }else if ("562".equals(flag)) {
                throw new IllegalStateException("快递简码不能为空！");
            }else if ("563".equals(flag)) {
                throw new IllegalStateException("app没有更新的版本信息");
            }else if ("564".equals(flag)) {
                throw new IllegalStateException("用户未注册");
            }else if ("565".equals(flag)) {
                throw new IllegalStateException("登录密码错误");
            }else if ("566".equals(flag)) {
                throw new IllegalStateException("物流查询错误信息");
            }else if ("567".equals(flag)) {
                throw new IllegalStateException("支付失败，请稍后重试，微信授权code错误！");
            }else if ("568".equals(flag)) {
                throw new IllegalStateException("支付失败，请稍后重试，微信授权openid错误！");
            }else {
                throw new IllegalStateException("错误代码：" + flag + "，错误信息：" + baseResponse.msg);
            }
        } else {
            response.close();
            throw new IllegalStateException("基类错误无法解析!");
        }
    }
}
