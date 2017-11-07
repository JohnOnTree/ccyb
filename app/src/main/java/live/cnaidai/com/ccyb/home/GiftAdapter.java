package live.cnaidai.com.ccyb.home;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import live.cnaidai.com.ccyb.App;
import live.cnaidai.com.ccyb.BuildConfig;
import live.cnaidai.com.ccyb.R;
import live.cnaidai.com.ccyb.base.CommonWebActivity;
import live.cnaidai.com.ccyb.home.pojo.HomeDataPOJO;
import live.cnaidai.com.ccyb.util.CcConst;
import live.cnaidai.com.ccyb.util.CommonUtils;
import live.cnaidai.com.ccyb.util.ImageUtils;
import live.cnaidai.com.ccyb.weight.rvadapter.CommonAdapter;
import live.cnaidai.com.ccyb.weight.rvadapter.base.ViewHolder;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Package com.aidai.aidaiwine.home.adapter
 * @Description: 首页专卖适配器
 * @date 2016/9/21 16:17
 */

public class GiftAdapter extends CommonAdapter<HomeDataPOJO.DataEntity.IndexGiftListEntity> {

    private Context mContext;

    public GiftAdapter(Context context, int layoutId, List<HomeDataPOJO.DataEntity.IndexGiftListEntity> datas) {
        super(context, layoutId, datas);
        this.mContext = context;
    }

    @Override
    protected void convert(ViewHolder holder, final HomeDataPOJO.DataEntity.IndexGiftListEntity giftEntity, final int position) {
        final int index = position;
        final String url = BuildConfig.SERVER_PATH
                + giftEntity.getIndexPic();

        final SimpleDraweeView simpleDraweeView = holder.getView(R.id.imageView4);

        final ViewHolder viewHolder = holder;


        ImageUtils.showThumb(App.getContext(), url, simpleDraweeView);

        //giftname
        holder.setText(R.id.tv_title, giftEntity.getGiftName());
        holder.setText(R.id.tv_desc, giftEntity.getDescNote());
        holder.setText(R.id.tv_price, "¥" + giftEntity.getTotalPrice());

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("url", CcConst.URL_GOOD + "?giftId=" + giftEntity.getGiftId());
                bundle.putString("title", "套餐详情");
                CommonUtils.launchActivity(mContext, CommonWebActivity.class, bundle);
            }
        });

    }


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            float aFloat = data.getFloat("aspectRadio");
            String url = data.getString("url");
            ViewHolder viewHolder = (ViewHolder) data.getSerializable("viewHolder");
            SimpleDraweeView simpleDraweeView = viewHolder.getView(R.id.imageView4);
            simpleDraweeView.setAspectRatio(aFloat);
            ImageUtils.showThumb(App.getContext(), url, simpleDraweeView);
        }
    };

}
