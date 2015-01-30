package com.pages.login;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.data.model.DataConstants;
import com.data.model.UserConfigs;
import com.app.ydd.R;
import com.pages.viewpager.MainActivity;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.UsersAPI;
import com.sina.weibo.sdk.openapi.models.ErrorInfo;
import com.sina.weibo.sdk.openapi.models.User;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity{

	Button weiboLogin;
	Button qqLogin;
	EditText phone;
	EditText password;
	TextView register;
	Button phoneLogin;
	//weibo
		 private AuthInfo mAuthInfo;
		 /** 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能  */
		 private Oauth2AccessToken mAccessToken;
		 /** 注意：SsoHandler 仅当 SDK 支持 SSO 时有效 */
		 private SsoHandler mSsoHandler;
		 /** 用户信息接口 */
	     private UsersAPI mUsersAPI;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if(UserConfigs.getAccount()!=null)//debug
		{
			setContentView(R.layout.activity_login);
			phone=(EditText)findViewById(R.id.username_edit);
			password=(EditText)findViewById(R.id.password_edit);
			phoneLogin=(Button)findViewById(R.id.signin_button);
			weiboLogin=(Button)findViewById(R.id.weibologin);
			qqLogin=(Button)findViewById(R.id.qqlogin);
			weiboLogin.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					weiboLogin();
				}
			});
			register=(TextView)findViewById(R.id.register_link);
			register.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
			register.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent intent=new Intent();
					intent.setClass(LoginActivity.this, RegisterActivity.class);
					startActivity(intent);
				}
			});
		}
		else
		{
			String loginWay=UserConfigs.getLoginWay();
			if(loginWay.equals(getResources().getString(R.string.user_weibo)))
				weiboLogin();
			
		}
		
		
	}
	 /**
     * 当 SSO 授权 Activity 退出时，该函数被调用。
     * 
     * @see {@link Activity#onActivityResult}
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       
        // SSO 授权回调
        // 重要：发起 SSO 登陆的 Activity 必须重写 onActivityResult
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

	 /**
     * 微博认证授权回调类。
     * 1. SSO 授权时，需要在 {@link #onActivityResult} 中调用 {@link SsoHandler#authorizeCallBack} 后，
     *    该回调才会被执行。
     * 2. 非 SSO 授权时，当授权结束后，该回调就会被执行。
     * 当授权成功后，请保存该 access_token、expires_in、uid 等信息到 SharedPreferences 中。
     */
    class AuthListener implements WeiboAuthListener {
        
        @Override
        public void onComplete(Bundle values) {
        	  Log.e(DataConstants.TAG,"oncomplete");
            // 从 Bundle 中解析 Token
            mAccessToken = Oauth2AccessToken.parseAccessToken(values);
            if (mAccessToken.isSessionValid()) {
                // 显示 Token
                updateTokenView(false);
              
                // 获取用户信息接口               
                mUsersAPI = new UsersAPI(LoginActivity.this, DataConstants.WEIBO_APP_KEY, mAccessToken);
                long uid = Long.parseLong(mAccessToken.getUid());
                mUsersAPI.show(uid, mListener);
               Log.e(DataConstants.TAG,"weibouid "+uid);
              // CCPConfig.storeLoginWay(getApplicationContext(), "weibo");
               String url=getLoginURL("weibo", uid+"", "");
               Log.e(DataConstants.TAG,"url:"+url);
               RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
               JsonObjectRequest jsonObjectRequest = new JsonObjectRequest( url, null,
                   new Response.Listener<JSONObject>() {
                       @Override
                       public void onResponse(JSONObject response) {
                           Log.i(DataConstants.TAG,"response=" + response);
                           		parseLoginInfo(response, getResources().getString(R.string.user_weibo));
//                           if (progressDialog.isShowing()
//                               && progressDialog != null) {
//                               progressDialog.dismiss();
//                           }
                       }}, 
               	new Response.ErrorListener() {
                       @Override
                       public void onErrorResponse(VolleyError arg0) {
                          // tv_1.setText(arg0.toString());
                           Log.i(DataConstants.TAG,"sorry,Error"+arg0.toString());
//                           if (progressDialog.isShowing()
//                               && progressDialog != null) {
//                               progressDialog.dismiss();
//                           }
                       }});
               requestQueue.add(jsonObjectRequest);
               //LoginTask wl=new LoginTask("weibo",mAccessToken.getUid(),LoginActivity.this);
               //wl.execute(DataConstants.SERVER_URL);
               //createSubAccount();
                // 保存 Token 到 SharedPreferences
                AccessTokenKeeper.writeAccessToken(LoginActivity.this, mAccessToken);
                //Toast.makeText(LoginActivity.this, R.string.weibosdk_demo_toast_auth_success, Toast.LENGTH_SHORT).show();
                //registerSuccess();
                Intent intent=new Intent();
    			intent.setClass(getApplicationContext(), MainActivity.class);
    			startActivity(intent);
            } else {
                // 以下几种情况，您会收到 Code：
                // 1. 当您未在平台上注册的应用程序的包名与签名时；
                // 2. 当您注册的应用程序包名与签名不正确时；
                // 3. 当您在平台上注册的包名和签名与您当前测试的应用的包名和签名不匹配时。
            	
                String code = values.getString("code");
                String message = getString(R.string.weibosdk_demo_toast_auth_failed);
                Log.e(DataConstants.TAG,"code"+code+" "+message);
                if (!TextUtils.isEmpty(code)) {
                    message = message + "\nObtained the code: " + code;
                }
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCancel() {
        	 Log.e(DataConstants.TAG,"oncancel ");
            Toast.makeText(LoginActivity.this, 
                    R.string.weibosdk_demo_toast_auth_canceled, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onWeiboException(WeiboException e) {
        	 Log.e(DataConstants.TAG,"onWeiboException ");
            Toast.makeText(LoginActivity.this, 
                    "Auth exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    
    /**
     * 显示当前 Token 信息。
     * 
     * @param hasExisted 配置文件中是否已存在 token 信息并且合法
     */
    private void updateTokenView(boolean hasExisted) {
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(
                new java.util.Date(mAccessToken.getExpiresTime()));
        String format = getString(R.string.weibosdk_demo_token_to_string_format_1);
        //mTokenText.setText(String.format(format, mAccessToken.getToken(), date));
        
        String message = String.format(format, mAccessToken.getToken(), date);
        if (hasExisted) {
            message = getString(R.string.weibosdk_demo_token_has_existed) + "\n" + message;
        }
        //mTokenText.setText(message);
    }
	
	 /**
     * 微博 OpenAPI 回调接口。 获取微博用户昵称
     */
    private RequestListener mListener = new RequestListener() {
        @Override
        public void onComplete(String response) {
            if (!TextUtils.isEmpty(response)) {
               // LogUtil.i(TAG, response);
                // 调用 User#parse 将JSON串解析成User对象
                User user = User.parse(response);
                if (user != null) {
                	
                   // Toast.makeText(LoginRegisterActivity.this, "获取User信息成功，用户昵称：" + user.screen_name, Toast.LENGTH_LONG).show();
                   // CCPConfig.storeScreenName(getApplicationContext(), user.screen_name);
                   // CCPConfig.storeWeiboId(getApplicationContext(), user.id);
                    Log.i(DataConstants.TAG,"listen weibo uid:"+user.id);
                    
                } else {
                    Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();
                }
            }
        }

        @Override
        public void onWeiboException(WeiboException e) {
           // LogUtil.e(TAG, e.getMessage());
            ErrorInfo info = ErrorInfo.parse(e.getMessage());
            Toast.makeText(LoginActivity.this, info.toString(), Toast.LENGTH_LONG).show();
        }
    };
    private void weiboLogin()
    {
    	// 快速授权时，请不要传入 SCOPE，否则可能会授权不成功
		Log.e(DataConstants.TAG,"onclick");
		mAuthInfo = new AuthInfo(LoginActivity.this, DataConstants.WEIBO_APP_KEY, DataConstants.REDIRECT_URL, "");
		mSsoHandler = new SsoHandler(LoginActivity.this, mAuthInfo);
     // SSO 授权, ALL IN ONE
		Log.e(DataConstants.TAG,"mSsoHandler");
        mSsoHandler.authorize(new AuthListener());
    }
    private String getLoginURL(String loginWay,String account,String passward)
    {
    	List<NameValuePair> params=new ArrayList<NameValuePair>();
		BasicNameValuePair pair = new BasicNameValuePair("methodno","MLogin");
		params.add(pair);
		pair = new BasicNameValuePair("device","android");
		params.add(pair);
		pair = new BasicNameValuePair("deviceid","1");
		params.add(pair);
		pair = new BasicNameValuePair("appid","nju");
		params.add(pair);
		if(loginWay.equals("weibo"))
			pair = new BasicNameValuePair("wbAccount",account);
		else if(loginWay.equals("qq"))
			pair = new BasicNameValuePair("qqAccount",account);
		else if(loginWay.equals("phone"))
		{
			pair = new BasicNameValuePair("account",account);
			params.add(pair);
			pair = new BasicNameValuePair("password",passward);
		}
		params.add(pair);
		String resultURL=DataConstants.SERVER_URL+"?";
		for(NameValuePair nvp:params)
		{
			resultURL+=nvp.getName()+"="+nvp.getValue()+"&";
			
		}
		return  "http://"+resultURL;
	}
    private void parseLoginInfo(JSONObject job,String loginWay)
    {
    	try 
    	{
			JSONObject data=job.getJSONObject("data");
			UserConfigs.storeLoginWay(loginWay);
			UserConfigs.storeAccount(data.getString("account_"));
			UserConfigs.storeVerify(data.getString("verify_"));
			UserConfigs.storeId(data.getString("id_"));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
	
}
