package live.cnaidai.com.ccyb.st;

import android.os.Bundle;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import live.cnaidai.com.ccyb.BuildConfig;
import live.cnaidai.com.ccyb.R;
import live.cnaidai.com.ccyb.base.BaseActivity;
import live.cnaidai.com.ccyb.base.BaseModel;
import live.cnaidai.com.ccyb.base.BasePresenter;
import live.cnaidai.com.ccyb.st.pojo.StPOJO;
import live.cnaidai.com.ccyb.util.ImageUtils;
import live.cnaidai.com.ccyb.view.ImageCycleView;

/**
 * description:
 * autour: ChristLu
 * date: 17/6/14
 * package: live.cnaidai.com.ccyb.st
 */
public class ScannerBigPicActivity extends BaseActivity<BasePresenter, BaseModel> {

    @Bind(R.id.icv)
    ImageCycleView mIcv;

    private int index ;

    private List<StPOJO.DataEntity.PicListEntity> datas ;
    private ArrayList<String> urls = new ArrayList<>();

    @Override
    public void initPresenter() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bigpic);
        ButterKnife.bind(this);

        initDatas();
    }

    private void initDatas() {
        index =  getIntent().getIntExtra("index",0);
        datas = (List<StPOJO.DataEntity.PicListEntity>)getIntent().getSerializableExtra("datas");
        for (StPOJO.DataEntity.PicListEntity picEntity: datas){
            urls.add( BuildConfig.SERVER_PATH+picEntity.getPicLocation());
        }



        mIcv.setImageResources(urls, new ImageCycleView.ImageCycleViewListener() {
            @Override
            public void displayImage(String imageURL, SimpleDraweeView imageView) {
                ImageUtils.showThumb(mContext, imageURL,imageView);
            }

            @Override
            public void onImageClick(int position, View imageView) {
                finish();
            }
        },index);
    }
}
