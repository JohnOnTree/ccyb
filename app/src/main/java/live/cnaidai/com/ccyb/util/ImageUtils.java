package live.cnaidai.com.ccyb.util;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import live.cnaidai.com.ccyb.R;

import static live.cnaidai.com.ccyb.util.CommonUtils.dip2px;

/**
 * Created by ChristLu on 16/11/16.
 */

public class ImageUtils {
    /**
     * 加载图片
     *
     * @param simpleDraweeView 图片载体
     * @param url 图片url
     */
    public static void loadImage(SimpleDraweeView simpleDraweeView, String url) {
        GenericDraweeHierarchy hierarchy = simpleDraweeView.getHierarchy();
        hierarchy.setPlaceholderImage(R.drawable.zhanwei);
        if (url.endsWith(".gif")) {
            DraweeController draweeController;
            draweeController = Fresco.newDraweeControllerBuilder()
                    .setAutoPlayAnimations(true)
                    .setUri(Uri.parse(url))//设置uri
                    .build();
            //设置Controller
            simpleDraweeView.setController(draweeController);
        } else {
            Uri uri = Uri.parse(url);
            simpleDraweeView.setImageURI(uri);
        }
    }

    public static float getAspectRadio(String url){
        try {
            URL m_url=new URL(url);
            HttpURLConnection con=(HttpURLConnection)m_url.openConnection();
            InputStream in=con.getInputStream();
            BitmapFactory.Options options=new BitmapFactory.Options();
            options.inJustDecodeBounds=true;
            BitmapFactory.decodeStream(in,null,options);
            float height=options.outHeight;
            float width=options.outWidth;
            float s=width/height ;
            return s;
        } catch (Exception e)
        {
            e.printStackTrace();
            return 1;
        }
    }


    /**
     * 解决图片加载OOM
     * @param draweeView
     */
    public static void showThumb(Context context, String  url, SimpleDraweeView draweeView){
        Uri uri = Uri.parse(url);
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(dip2px(context,144), dip2px(context,144)))
                .build();

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(draweeView.getController())
                .setControllerListener(new BaseControllerListener<ImageInfo>())
                .build();
        draweeView.setController(controller);
    }
}
