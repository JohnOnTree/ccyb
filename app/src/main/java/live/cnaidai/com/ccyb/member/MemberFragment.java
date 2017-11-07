package live.cnaidai.com.ccyb.member;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import live.cnaidai.com.ccyb.BuildConfig;
import live.cnaidai.com.ccyb.R;
import live.cnaidai.com.ccyb.api.UserAPI;
import live.cnaidai.com.ccyb.base.BaseFragment;
import live.cnaidai.com.ccyb.base.CommonWebActivity;
import live.cnaidai.com.ccyb.user.LoginV2Activity;
import live.cnaidai.com.ccyb.user.pojo.UserInfoPOJO;
import live.cnaidai.com.ccyb.util.CcConst;
import live.cnaidai.com.ccyb.util.CommonUtils;
import live.cnaidai.com.ccyb.util.ImageUtils;
import live.cnaidai.com.ccyb.util.UploadUtil;
import live.cnaidai.com.ccyb.util.UserInfoUtils;
import live.cnaidai.com.ccyb.view.SelectAvatarPopupWindow;
import live.cnaidai.com.ccyb.view.SlipScollerView;

/**
 * Created by ChristLu on 16/11/14.
 */

public class MemberFragment extends BaseFragment<MemberPresenter, MemberModel> implements MemberContract.View {


    @Bind(R.id.tv_name)
    TextView mTvName;
    @Bind(R.id.iv_vip_level)
    TextView mIvVipLevel;
    @Bind(R.id.sfv_avatar)
    SimpleDraweeView mSfvAvatar;
    @Bind(R.id.tv_level)
    TextView mTvLevel;
    @Bind(R.id.tv_remark)
    TextView mTvRemark;
    @Bind(R.id.tv_score)
    TextView mTvScore;
    @Bind(R.id.rcv_image)
    RecyclerView mRcvImage;
    @Bind(R.id.tv_upload)
    TextView mTvUpload;
    @Bind(R.id.root)
    SlipScollerView mRoot;
    @Bind(R.id.iv_del)
    ImageView mIvDel;
    @Bind(R.id.iv_del_ok)
    TextView mIvDelOk;

    private int vipType;


    private boolean flagEdit ;


    //选择图片控件
    private SelectAvatarPopupWindow mPopupWindow;


    private Uri imageUri;

    public static final int REQUESTCODE_PICK = 0X002;
    public static final int REQUESTCODE_TAKE = 0X003;

    public static final int IMAGE = 0x001;

    private List<UserInfoPOJO.DataEntity.PicEntity> images = new ArrayList<>();

    private ImageAdapter mImageAdapter;

    public enum EditStatus {
        EDIT, OK;
    }

    private EditStatus mEditStatus = EditStatus.EDIT;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (CommonUtils.isNull(UserInfoUtils.getMemberId())) {
            CommonUtils.launchActivity(getActivity(), LoginV2Activity.class);
            return;
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRcvImage.setLayoutManager(linearLayoutManager);
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getMemberData();
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(MemberFragment.this, mModel);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    //设置用户数据
    @Override
    public void setUserData(UserInfoPOJO.DataEntity data) {
        if (!isAdded() || data == null) {
            return;
        }
        images = data.getPicList();

        mTvName.setText(data.getMemberName());
        String score = data.getCreditScore();
        if (TextUtils.isEmpty(score)) {
            score = "0";
        }
        vipType = data.getType();
        Drawable drawable = getActivity().getResources().getDrawable(R.mipmap.vip);


        if (vipType == 1) {
            //普通用户
            drawable = getActivity().getResources().getDrawable(R.mipmap.icon_member_level);
        } else {
            //VIP
            drawable = getActivity().getResources().getDrawable(R.mipmap.vip);
            mIvVipLevel.setText("VIP会员");
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mIvVipLevel.setCompoundDrawables(drawable, null, null, null);

        mTvLevel.setText(data.getLevel() + "   " + data.getLvName());

        mTvRemark.setText(data.getSignStr());

        ImageUtils.loadImage(mSfvAvatar, BuildConfig.SERVER_PATH + data.getHeadPic());
        mTvScore.setText("积分  " + data.getScore());

        loadImage();
    }

    @OnClick({R.id.rl_yp, R.id.rl_gift, R.id.rl_vip, R.id.rl_about_yb, R.id.rl_kf, R.id.sfv_avatar,
            R.id.rl_sharecode, R.id.iv_vip_level, R.id.tv_level, R.id.rl_about_mz, R.id.tv_upload,
            R.id.rl_account, R.id.rl_income, R.id.rl_jb_yp, R.id.iv_del, R.id.iv_del_ok})
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        String title = "";
        String url = "";
        boolean isWebPage = true;
        switch (view.getId()) {
            case R.id.rl_account:
                //我的账户
                title = "我的账户";
                url = CcConst.URL_ACCOUNT + "?memberId=" + UserInfoUtils.getMemberId();
                break;
            case R.id.rl_income:
                //我的提现
                title = "我的提成";
                url = CcConst.URL_INCOME + "?memberId=" + UserInfoUtils.getMemberId();
                break;
            case R.id.rl_yp:
                title = "我的约票";
                url = CcConst.URL_YUEPIAO + "?memberId=" + UserInfoUtils.getMemberId();
                bundle.putBoolean("isYue", true);
                break;
            case R.id.rl_gift:
                title = "我的礼包";
                url = CcConst.URL_GIFT + "?memberId=" + UserInfoUtils.getMemberId();
                break;
            case R.id.rl_vip:
                title = "会员特权";
                url = BuildConfig.SERVER_PATH + "mobileMember_toShowVIPService.do?memberId=" + UserInfoUtils.getMemberId();
                break;
            case R.id.rl_about_yb:
                title = "关于从从";
                url = BuildConfig.SERVER_PATH + "mobileMember_aboutUs.do";
                break;
            case R.id.rl_kf:
                isWebPage = false;
                showContactUsDialog();
                break;
            case R.id.sfv_avatar:
                title = "完善信息";
                url = BuildConfig.SERVER_PATH + "mobileMember_showImproveInfo.do?memberId=" + UserInfoUtils.getMemberId();
                bundle.putBoolean("setting", true);
                break;

            case R.id.rl_sharecode:
                title = "分享有礼";
                url = BuildConfig.SERVER_PATH + "mobileMember_showShareCode.do?memberId=" + UserInfoUtils.getMemberId();
                break;
            case R.id.tv_level:
                //等级说明
                title = "等级说明";
                url = BuildConfig.SERVER_PATH + "mobileMember_showLevelDetail.do?memberId=" + UserInfoUtils.getMemberId();
                break;
            case R.id.rl_about_mz:
                title = "免责声明";
                url = BuildConfig.SERVER_PATH + "mobileMember_showDisclaimer.do";
                break;
            case R.id.iv_vip_level:
                //附近的人
                title = "会员特权";
//                if (vipType == 1) {
//                    //普通用户
                    url = BuildConfig.SERVER_PATH + "mobileMember_toShowVIPService.do?memberId=" + UserInfoUtils.getMemberId();
//                } else {
//                    url = BuildConfig.SERVER_PATH + "mobileMember_toVip.do?memberId=" + UserInfoUtils.getMemberId();
//                }
                break;
            case R.id.tv_upload:
                isWebPage = false;
                //上传图片
                upload();
                break;
            case R.id.rl_jb_yp:
                title = "酒吧约会";
                url = BuildConfig.SERVER_PATH + "mobileMember_myBarOrderList.do?memberId=" + UserInfoUtils.getMemberId();
                bundle.putBoolean("JiuYue", true);

                break;
            case R.id.iv_del:
                //点击编辑
                isWebPage = false;
                doDelete();
                break;
            case R.id.iv_del_ok:
                isWebPage = false;
                doDelOK();

                break;
        }
        if (isWebPage) {
            bundle.putString("title", title);
            bundle.putString("url", url);
            CommonUtils.launchActivity(getActivity(), CommonWebActivity.class, bundle);
        }
    }

    private void doDelOK() {
        flagEdit = false ;
        mImageAdapter.setEdit(false);
        mEditStatus = EditStatus.EDIT;
        mIvDelOk.setVisibility(View.GONE);
        mIvDel.setVisibility(View.VISIBLE);
    }

    private void doDelete() {
        flagEdit = true ;
        mImageAdapter.setEdit(true);
        mEditStatus = EditStatus.OK;
        mIvDelOk.setVisibility(View.VISIBLE);
        mIvDel.setVisibility(View.GONE);
    }

    private void upload() {
        mPopupWindow = new SelectAvatarPopupWindow(getActivity(), onItemClick);
        mPopupWindow.showAtLocation(mRoot, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }


    View.OnClickListener onItemClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mPopupWindow.dismiss();
            switch (view.getId()) {
                // 拍照
                case R.id.takePhotoBtn:
                    startCamera();
                    break;
                // 相册选择图片
                case R.id.pickPhotoBtn:
                    Intent intent = new Intent(Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, IMAGE);
                    break;
                default:
                    break;
            }
        }
    };


    /**
     * 拍照
     */
    private void startCamera() {
        File imagePath = new File(Environment.getExternalStorageDirectory(), "images");
        if (!imagePath.exists()) imagePath.mkdirs();
        File newFile = new File(imagePath, "default_image.jpg");

        //第二参数是在manifest.xml定义 provider的authorities属性
        imageUri = FileProvider.getUriForFile(getActivity(), "live.ccyb.cc", newFile);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //兼容版本处理，因为 intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION) 只在5.0以上的版本有效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ClipData clip =
                    ClipData.newUri(getActivity().getContentResolver(), "A photo", imageUri);
            intent.setClipData(clip);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
            List<ResolveInfo> resInfoList =
                    getActivity().getPackageManager()
                            .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            for (ResolveInfo resolveInfo : resInfoList) {
                String packageName = resolveInfo.activityInfo.packageName;
                getActivity().grantUriPermission(packageName, imageUri,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, REQUESTCODE_TAKE);
    }


    private void showContactUsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),
                AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        builder.setTitle("呼叫");
        builder.setMessage("客服电话:400-254-2564");
        builder.setPositiveButton("拨打", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_CALL,
                        Uri.parse("tel:" + "40002542564"));
                startActivity(intent);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
            }
        });

        builder.show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case IMAGE:
                //相册
                applyAlum(data);
                break;
            case REQUESTCODE_TAKE:
                //拍照
                applyCamera();
                break;
        }


    }


    /**
     * 处理从相册中选择的图片
     *
     * @param data
     */
    private void applyAlum(Intent data) {
        Uri selectedImage = data.getData();
        String[] filePathColumns = {MediaStore.Images.Media.DATA};
        Cursor c = getActivity().getContentResolver().query(selectedImage, filePathColumns, null, null, null);
        if (c != null) {
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            uploadImg(imagePath);
            c.close();
        }
    }

    /**
     * 处理拍照的图片
     */
    private void applyCamera() {
        ContentResolver contentProvider = getActivity().getContentResolver();
        ParcelFileDescriptor mInputPFD;
        try {
            //获取contentProvider图片
            mInputPFD = contentProvider.openFileDescriptor(imageUri, "r");
            FileDescriptor fileDescriptor = mInputPFD.getFileDescriptor();
            Bitmap bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);
            String path = saveBitmap(bitmap);
            uploadImg(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void loadImage() {
        if (images.size() >= 4) {
            //加好消失
            mTvUpload.setVisibility(View.GONE);
        } else {
            mTvUpload.setVisibility(View.VISIBLE);
        }
        mRcvImage.setAdapter(mImageAdapter = new ImageAdapter(getActivity(), R.layout.item_image, images, new ImageAdapter.DelListener() {
            @Override
            public void del() {
                mPresenter.getMemberData();
            }
        }));
        mImageAdapter.setEdit(flagEdit);
    }


    Handler myhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //刷新页面
            mPresenter.getMemberData();

        }
    };


    private void uploadImg(final String filePath) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String restr = UploadUtil.uploadFile(UserAPI.URL_IMG_UPLOAD + "?memberId=" + UserInfoUtils.getMemberId(), filePath, new File(filePath).getName());//HttpPath.FABU_PATH为路径，将封装好的数据path绑到路径后传递给服务器，picpath为图片路径
                    Message message = myhandler.obtainMessage();
                    message.obj = restr;
                    myhandler.sendMessage(message);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }).start();


//
//        //上传发送图片
//        UserAPI.getInstance().uploadImg(new ResultCallback<BasePOJO>() {
//            @Override
//            public void onResponse(BasePOJO response) {
//                super.onResponse(response);
//                //TODO 上传成功同时显示
//                mPresenter.getMemberData();
//
//            }
//
//            @Override
//            public void onError(Request request, Exception e) {
//                super.onError(request, e);
//            }
//        }, new File(filePath));
    }


    /**
     * e
     * 保存方法
     */
    public String saveBitmap(Bitmap mBitmap) {
        File f = new File(getActivity().getCacheDir(), System.currentTimeMillis() + ".png");
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            return f.getAbsolutePath();


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }

    }

}
