package live.cnaidai.com.ccyb.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import live.cnaidai.com.ccyb.BuildConfig;
import live.cnaidai.com.ccyb.R;
import live.cnaidai.com.ccyb.base.CommonWebActivity;
import live.cnaidai.com.ccyb.home.pojo.UsersPOJO;
import live.cnaidai.com.ccyb.user.LoginV2Activity;
import live.cnaidai.com.ccyb.util.CcConst;
import live.cnaidai.com.ccyb.util.CommonUtils;
import live.cnaidai.com.ccyb.util.ImageUtils;
import live.cnaidai.com.ccyb.util.UserInfoUtils;
import live.cnaidai.com.ccyb.weight.rvadapter.CommonAdapter;
import live.cnaidai.com.ccyb.weight.rvadapter.base.ViewHolder;

/**
 * @author MrSimpleZ
 * @version V1.0
 * @Package com.aidai.aidaiwine.home.adapter
 * @Description: 首页专卖适配器
 * @date 2016/9/21 16:17
 */

public class UserAdapter extends CommonAdapter<UsersPOJO.DataEntity.MemberListEntity> {

	private Context mContext ;

	public UserAdapter(Context context, int layoutId, List<UsersPOJO.DataEntity.MemberListEntity> datas) {
		super(context, layoutId, datas);
		this.mContext = context ;
	}

	@Override
	protected void convert(ViewHolder holder, final UsersPOJO.DataEntity.MemberListEntity user, final int position) {
		final int index = position;
		//设置头像
		final String url = BuildConfig.SERVER_PATH
				+ user.getHeadPic();
		final SimpleDraweeView simpleDraweeView = holder.getView(R.id.sfv_avatar);
		ImageUtils.loadImage(simpleDraweeView,url);
		//设置昵称,性别
		TextView tvName = holder.getView(R.id.tv_name);
		tvName.setText(user.getMemberName());
		Drawable drawable= mContext.getResources().getDrawable(R.mipmap.male);
		if(1 == user.getSex()){
			//男
			drawable= mContext.getResources().getDrawable(R.mipmap.male);
		}else{
			//女
			drawable= mContext.getResources().getDrawable(R.mipmap.female);
		}
		drawable.setBounds( 0 ,  0 , drawable.getMinimumWidth(), drawable.getMinimumHeight());
		tvName.setCompoundDrawables(null, null , drawable , null );

		//设置等级
		holder.setText(R.id.tv_level,user.getLevel() +  "  "+user.getLvName());

		//设置标签
		LinearLayout llTags = holder.getView(R.id.ll_tag);
		llTags.removeAllViews();
		List<UsersPOJO.DataEntity.MemberListEntity.MemberLabelListEntity> labs = user.getMemberLabelList();
		for (UsersPOJO.DataEntity.MemberListEntity.MemberLabelListEntity lab : labs){
			final String labName = lab.getLabelName();
			if (!TextUtils.isEmpty(labName)) {
				TextView textView = new TextView(mContext);
                textView.setTextColor(mContext.getResources().getColor(R.color.color_light_gray));
				textView.setBackgroundResource(R.mipmap.juxing);
				textView.setText(labName);
				textView.setTextSize(10f);
				textView.setGravity(Gravity.CENTER);
				textView.setPadding(5,1,5,1);
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
				lp.setMargins(0, 0, 10, 0);
				textView.setLayoutParams(lp);
				llTags.addView(textView);
			}
		}

		//位置
		holder.setText(R.id.tv_location,user.getDistance());


		final ViewHolder viewHolder = holder ;

		holder.setOnClickListener(R.id.cardview, new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				if(UserInfoUtils.isLogin()){
					checkCanInvite(user);
				}else{
					CommonUtils.launchActivity(mContext, LoginV2Activity.class);
				}




			}
		});


	}

	private void checkCanInvite(final UsersPOJO.DataEntity.MemberListEntity user) {

		Bundle bundle = new Bundle();
		String url = CcConst.URL_MEMBER_INFO+"?appType=3&passiveId=" + user.getMemberId()+"&memberId="+ UserInfoUtils.getMemberId();
		bundle.putString("url", url);
		bundle.putString("title", "约票");
		CommonUtils.launchActivity(mContext, CommonWebActivity.class, bundle);

//		//判断是否可约票
//		HomeAPI.getInstance().canInvite(new ResultCallback<CanInvitePOJO>() {
//			@Override
//			public void onResponse(CanInvitePOJO response) {
//				super.onResponse(response);
//				if(response.isResultSuccess()) {
//					if (response.canInvite()) {
//
//					}else{
//						//弹出对话框
//						showInviteResultDialog(response.getMessage());
//					}
//				}
//			}
//
//			@Override
//			public void onError(Request request, Exception e) {
//				super.onError(request, e);
//			}
//		}, UserInfoUtils.getMemberId(),user.getMemberId()+"");
	}


	private void showInviteResultDialog(String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext,
				AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
		builder.setMessage(message);
		builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Bundle bundle = new Bundle();
				bundle.putString("url", BuildConfig.SERVER_PATH + "mobileMember_toShowVIPService.do?appType=3&memberId=" + UserInfoUtils.getMemberId());
				bundle.putString("title", "VIP");
				CommonUtils.launchActivity(mContext, CommonWebActivity.class, bundle);
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});

		builder.show();
	}


}

