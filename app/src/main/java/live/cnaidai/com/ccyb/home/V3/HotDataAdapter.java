package live.cnaidai.com.ccyb.home.V3;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.orhanobut.logger.Logger;

import java.util.List;

import live.cnaidai.com.ccyb.App;
import live.cnaidai.com.ccyb.BuildConfig;
import live.cnaidai.com.ccyb.R;
import live.cnaidai.com.ccyb.base.CommonWebActivity;
import live.cnaidai.com.ccyb.util.CcConst;
import live.cnaidai.com.ccyb.util.CommonUtils;
import live.cnaidai.com.ccyb.util.ImageUtils;
import live.cnaidai.com.ccyb.util.UserInfoUtils;
import live.cnaidai.com.ccyb.weight.rvadapter.CommonAdapter;
import live.cnaidai.com.ccyb.weight.rvadapter.base.ViewHolder;

/**
 * description:
 * autour: ChristLu
 * date: 17/6/16
 * package: live.cnaidai.com.ccyb.home.V3
 */
public class HotDataAdapter extends CommonAdapter<HomeDataV3POJO.DataEntity.MemberListEntity> {
    private Context ctx ;
    public HotDataAdapter(Context context, int layoutId, List<HomeDataV3POJO.DataEntity.MemberListEntity> datas) {
        super(context, layoutId, datas);
        this.ctx = context ;
    }

    @Override
    protected void convert(ViewHolder holder, final HomeDataV3POJO.DataEntity.MemberListEntity hotMemberListEntity, int position) {
        SimpleDraweeView simpleDraweeView = holder.getView(R.id.sdv_hot);
        Logger.d("convert:" + hotMemberListEntity.getHeadPic());


        ImageUtils.showThumb(App.getContext(), BuildConfig.SERVER_PATH + hotMemberListEntity.getHeadPic(), simpleDraweeView);
        holder.setText(R.id.tv_name, hotMemberListEntity.getMemberName());

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("title", "个人主页");
                bundle.putString("url", CcConst.URL_MEMBER_INFO+"?appType=3&passiveId=" + hotMemberListEntity.getMemberId()+"&memberId="+ UserInfoUtils.getMemberId());
                CommonUtils.launchActivity(ctx, CommonWebActivity.class, bundle);
            }
        });
    }
}
