package live.cnaidai.com.ccyb.st;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import live.cnaidai.com.ccyb.BuildConfig;
import live.cnaidai.com.ccyb.R;
import live.cnaidai.com.ccyb.api.StAPI;
import live.cnaidai.com.ccyb.net.callback.ResultCallback;
import live.cnaidai.com.ccyb.st.pojo.PraisePOJO;
import live.cnaidai.com.ccyb.st.pojo.StPOJO;
import live.cnaidai.com.ccyb.user.LoginV2Activity;
import live.cnaidai.com.ccyb.util.CCToast;
import live.cnaidai.com.ccyb.util.CommonUtils;
import live.cnaidai.com.ccyb.util.ImageUtils;
import live.cnaidai.com.ccyb.util.UserInfoUtils;
import live.cnaidai.com.ccyb.weight.rvadapter.CommonAdapter;
import live.cnaidai.com.ccyb.weight.rvadapter.base.ViewHolder;

/**
 * description:
 * autour: ChristLu
 * date: 17/6/14
 * package: live.cnaidai.com.ccyb.st
 */
public class StAdapter extends CommonAdapter<StPOJO.DataEntity> {
    private Context ctx;
    public StAdapter(Context context, int layoutId, List<StPOJO.DataEntity> datas) {
        super(context, layoutId, datas);
        ctx = context;
    }

    @Override
    protected void convert(ViewHolder holder, final StPOJO.DataEntity dataEntity, int position) {
        SimpleDraweeView simpleDraweeView = holder.getView(R.id.sdv_item);
        //设置头像
        ImageUtils.showThumb(ctx, BuildConfig.SERVER_PATH + dataEntity.getHeadPic(), simpleDraweeView);
        //设置时间
        try {
            String upTime = dataEntity.getUpTime();
            SimpleDateFormat sf = new SimpleDateFormat("MM/dd hh:mm:ss");
            final Date parse = sf.parse(upTime);
            String format = sf.format(parse);
            holder.setText(R.id.tv_time, format);
        } catch (Exception e) {

        }

        //设置内部照片墙
        RecyclerView gridRv = holder.getView(R.id.rv_grid);
        AlbumAdapter albumAdapter = new AlbumAdapter(ctx, R.layout.item_album, dataEntity.getPicList());
        gridRv.setLayoutManager(new GridLayoutManager(ctx, 4));
        gridRv.setAdapter(albumAdapter);

        //设置内容
        holder.setText(R.id.tv_content, dataEntity.getTitleName());
        //设置点赞
        final TextView tvPraise = holder.getView(R.id.tv_dz);
        tvPraise.setText(dataEntity.getPraiseNum() + "");
        Drawable drawable = mContext.getResources().getDrawable(R.mipmap.icon_wdz);


        if (dataEntity.isPraise()) {
            //已点赞
            drawable = mContext.getResources().getDrawable(R.mipmap.icon_ydz);
        }

        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tvPraise.setCompoundDrawables(drawable, null, null, null);

        tvPraise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!UserInfoUtils.isLogin()){
                    CommonUtils.launchActivity(ctx, LoginV2Activity.class);
                }

                int type = dataEntity.isPraise()?2:1;

                //点赞操作
                StAPI.getInstance().praise(new ResultCallback<PraisePOJO>() {
                    @Override
                    public void onResponse(PraisePOJO response) {
                        super.onResponse(response);
                        if (response.isResultSuccess()) {
                            Drawable drawable = null ;
                            if (dataEntity.isPraise()){
                                drawable = mContext.getResources().getDrawable(R.mipmap.icon_wdz);
                                dataEntity.setHasPraise(0);
                            }else{
                                drawable = mContext.getResources().getDrawable(R.mipmap.icon_ydz);
                                dataEntity.setHasPraise(1);
                            }
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                            tvPraise.setCompoundDrawables(drawable, null, null, null);
                            tvPraise.setText(response.getPraiseNum() + "");
                            dataEntity.setPraiseNum(response.getPraiseNum());
                            notifyDataSetChanged();
                        }
                        CCToast.showShort(ctx, response.getMessage());

                    }
                }, dataEntity.getDemoId() + "", type + "");
            }
        });


    }
}
