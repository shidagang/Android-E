package cn.encore.framecommon.base.configuration;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * app设置委托Base类
 * Created by：Encore
 * Created Time：16/4/23 14:36
 */
public abstract class ConfigBaseDeleagetImpl extends ConfigDelegate {

    private Object mFrom;
    //上下文
    public Context mContext;
    //settingInterface
    private ConfigSettingInterface mConfigSettingInterface;
    //配置管理器
    public EFrameConfiguration mEFrameConfiguration;


    public ConfigBaseDeleagetImpl(Object from, ConfigSettingInterface configSettingInterface) {
        mFrom = from;
        if (mFrom instanceof Activity) {
            mContext = (Context) mFrom;
        } else if (mFrom instanceof Fragment) {
            mContext = ((Fragment) mFrom).getActivity();
        }
        mConfigSettingInterface = configSettingInterface;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //生成默认Config配置,或者子类自定义配置
        if (mEFrameConfiguration == null) {
            mEFrameConfiguration = mConfigSettingInterface.getConfiguration(new EFrameConfiguration.Builder());
        }

    }

    /**
     * 创建内容View,子类容器可以根据此View做相应处理
     *
     * @return
     */
    public View createContentView() {
        return createContentView(null);
    }

    /**
     * 创建内容View,子类容器可以根据此View做相应处理
     *
     * @param container 容器View
     * @return
     */
    public View createContentView(ViewGroup container) {
        View contentView = mConfigSettingInterface.getContentView();
        if (contentView == null) {
            int resId = mConfigSettingInterface.getContentViewResId();
            //获取资源Id为-1不初始化
            if (resId != -1) {
                //初始化容器,获取容器实体View
                contentView = LayoutInflater.from(mContext).inflate(mConfigSettingInterface.getContentViewResId(), container);
            }
        }
        if (contentView != null) {
            //是否绑定依赖注入
            if (mEFrameConfiguration.isUseButterKnife()) {
                ButterKnife.bind(mFrom, contentView);
            }
            if(mEFrameConfiguration.isCallInitViews()) {
                //回调initViews
                mConfigSettingInterface.initViews(contentView);
            }
        }
        return contentView;
    }


    @Override
    public void onStop() {
        //待定
    }

    @Override
    public void onDestroy() {

    }

    //onPause
    @Override
    public void onPause() {

    }
    //onResume
    @Override
    public void onResume() {

    }

    public void setEFrameConfiguration(EFrameConfiguration EFrameConfiguration) {
        mEFrameConfiguration = EFrameConfiguration;
    }
}
