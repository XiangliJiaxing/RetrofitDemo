package com.xljx.net.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.xljx.net.retrofitdemo.bean.ExpressageBean;
import com.xljx.net.retrofitdemo.bean.GoodBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPlugin();
        findViews();
    }

    private void findViews() {
        findViewById(R.id.tv_a).setOnClickListener(this);
        findViewById(R.id.tv_b).setOnClickListener(this);
        findViewById(R.id.tv_c).setOnClickListener(this);
        findViewById(R.id.tv_d).setOnClickListener(this);
        findViewById(R.id.tv_e).setOnClickListener(this);
    }

    private void initPlugin() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://suggest.taobao.com/")
                .addConverterFactory(GsonConverterFactory.create()) // 添加json转换支持
                .build();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.tv_a:
                GoodSearch goodSearch = retrofit.create(GoodSearch.class);
                Call<List<GoodBean>> call = goodSearch.search("utf-8", "凉鞋");
                call.enqueue(new Callback<List<GoodBean>>() {
                    @Override
                    public void onResponse(Call<List<GoodBean>> call, Response<List<GoodBean>> response) {
                        List<GoodBean> body = response.body();
                        if(body == null){
                            return;
                        }
                        Log.i(TAG, "RESULT:" + body.toString());
                        if(null != body){
                            for(GoodBean good : body){
                                Log.i(TAG, good.toString());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<GoodBean>> call, Throwable t) {
                        Log.i(TAG, "failure msg");
                    }
                });


                break;
            case R.id.tv_b:
                retrofit = new Retrofit.Builder()
                        .baseUrl("http://www.kuaidi100.com")
                        .addConverterFactory(GsonConverterFactory.create()) // 添加json转换支持
                        .build();
                ExpressageService expressageService = retrofit.create(ExpressageService.class);
                expressageService.query("yuantong", "11111111111").enqueue(new Callback<ExpressageBean>() {
                    @Override
                    public void onResponse(Call<ExpressageBean> call, Response<ExpressageBean> response) {
                        Log.i(TAG, "快递查询结果：" + response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<ExpressageBean> call, Throwable t) {
                        Log.i(TAG, "failure msg");
                    }
                });
                break;
            case R.id.tv_c:

                break;
            case R.id.tv_d:

                    break;
            case R.id.tv_e:

                break;
        }
    }


    // https://suggest.taobao.com/sug?code=utf-8&q=%E5%8D%AB%E8%A1%A3&callback=cb
    public interface GoodSearch{
        @GET("sug")
        Call<List<GoodBean>> search(@Query("code") String charset, @Query("q") String name);
    }


    // http://www.kuaidi100.com/query?type=yuantong&postid=11111111111
    public interface ExpressageService{
        @GET("query")
        Call<ExpressageBean> query(@Query("type") String type, @Query("postid") String postID);
    }
}
