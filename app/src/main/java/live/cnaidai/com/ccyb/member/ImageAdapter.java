package live.cnaidai.com.ccyb.member;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.okhttp.Request;

import java.util.List;

import live.cnaidai.com.ccyb.App;
import live.cnaidai.com.ccyb.BuildConfig;
import live.cnaidai.com.ccyb.R;
import live.cnaidai.com.ccyb.api.UserAPI;
import live.cnaidai.com.ccyb.base.BasePOJO;
import live.cnaidai.com.ccyb.net.callback.ResultCallback;
import live.cnaidai.com.ccyb.user.pojo.UserInfoPOJO;
import live.cnaidai.com.ccyb.util.CCToast;
import live.cnaidai.com.ccyb.util.ImageUtils;
import live.cnaidai.com.ccyb.weight.rvadapter.CommonAdapter;
import live.cnaidai.com.ccyb.weight.rvadapter.base.ViewHolder;

/**
 * description:
 * autour: ChristLu
 * date: 17/6/16
 * package: live.cnaidai.com.ccyb.home.V3
 */
public class ImageAdapter extends CommonAdapter<UserInfoPOJO.DataEntity.PicEntity> {
    private Context ctx ;

    private boolean isEdit ;

    private List<UserInfoPOJO.DataEntity.PicEntity> datas ;

    private DelListener mDelListener ;

    public ImageAdapter(Context context, int layoutId, List<UserInfoPOJO.DataEntity.PicEntity> datas,DelListener listener) {
        super(context, layoutId, datas);
        this.ctx = context ;
        this.datas = datas ;
        this.mDelListener = listener ;
    }

    public void setEdit(boolean isEdit){
        this.isEdit = isEdit ;
        notifyDataSetChanged();
    }

    public void delPic(int position){
        datas.remove(position);
        notifyDataSetChanged();
    }

    @Override
    protected void convert(ViewHolder holder, final UserInfoPOJO.DataEntity.PicEntity url, final int position) {
        SimpleDraweeView simpleDraweeView = holder.getView(R.id.sdv_hot);
        ImageUtils.showThumb(App.getContext(), BuildConfig.SERVER_PATH + url.getPicLocation(), simpleDraweeView);
        ImageView ivDel = holder.getView(R.id.iv_del);

        if(isEdit){
            ivDel.setVisibility(View.VISIBLE);
        }else{
            ivDel.setVisibility(View.GONE);
        }


        ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除
                UserAPI.getInstance().delImage(new ResultCallback<BasePOJO>() {
                    @Override
                    public void onResponse(BasePOJO response) {
                        super.onResponse(response);
                        if(response.isResultSuccess()){
                            delPic(position);
                            mDelListener.del();
                        }
                        CCToast.showShort(ctx,response.getMessage());
                    }

                    @Override
                    public void onError(Request request, Exception e) {
                        super.onError(request, e);
                    }
                },url.getId()+"");
            }
        });


    }


    public interface DelListener{
        void del();
    }
}
