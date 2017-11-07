package live.cnaidai.com.ccyb.wxapi;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import live.cnaidai.com.ccyb.App;
import live.cnaidai.com.ccyb.util.CCToast;

/**
 * Created by ChristLu on 17/1/5.
 */

public class BaseUiListener implements IUiListener {
    @Override
    public void onComplete(Object o) {
        CCToast.showShort(App.getContext(),"onComplete");
    }

    @Override
    public void onError(UiError uiError) {
        CCToast.showShort(App.getContext(),"onError");
    }

    @Override
    public void onCancel() {
        CCToast.showShort(App.getContext(),"onCancel");
    }
}
