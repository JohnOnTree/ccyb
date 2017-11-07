package live.cnaidai.com.ccyb.st;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

import java.io.Serializable;
import java.util.List;

import live.cnaidai.com.ccyb.BuildConfig;
import live.cnaidai.com.ccyb.R;
import live.cnaidai.com.ccyb.st.pojo.StPOJO;
import live.cnaidai.com.ccyb.util.ImageUtils;
import live.cnaidai.com.ccyb.weight.rvadapter.CommonAdapter;
import live.cnaidai.com.ccyb.weight.rvadapter.base.ViewHolder;

/**
 * description:
 * autour: ChristLu
 * date: 17/6/14
 * package: live.cnaidai.com.ccyb.st
 */
public class AlbumAdapter extends CommonAdapter<StPOJO.DataEntity.PicListEntity> {
    private Context ctx ;
    private List<StPOJO.DataEntity.PicListEntity> datas ;
    public AlbumAdapter(Context context, int layoutId, List<StPOJO.DataEntity.PicListEntity> datas) {
        super(context, layoutId, datas);
        ctx = context ;
        this.datas = datas ;
    }

    @Override
    protected void convert(ViewHolder holder, StPOJO.DataEntity.PicListEntity picListEntity, final int position) {
        SimpleDraweeView simpleDraweeView = holder.getView(R.id.sdv_album);
        ImageUtils.showThumb(ctx, BuildConfig.SERVER_PATH + picListEntity.getPicLocation(),simpleDraweeView);

        simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击查看大图
                Intent intent = new Intent(ctx,ScannerBigPicActivity.class);
                intent.putExtra("index",position);
                intent.putExtra("datas",(Serializable) datas);
                ctx.startActivity(intent);
            }
        });

    }
}
