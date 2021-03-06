package cn.encore.framecommon.base.configuration;

/**
 * Created by：Encore
 * Created Time：16/4/22 19:02
 *
 */
public class EFrameConfiguration {

    private Builder mBuilder;

    public EFrameConfiguration() {
        mBuilder = new Builder();
    }

    public EFrameConfiguration(Builder builder) {
        this.mBuilder = builder;
    }

    /**
     * 是否使用ButterKnife
     *
     * @return
     */
    public boolean isUseButterKnife() {
        if(mBuilder == null) return true;
        return mBuilder.isUseButterKnife;
    }

    /**
     * 是否滑动退出
     * @return
     */
    public boolean isSwipeBack(){
        if(mBuilder == null) return true;
        return mBuilder.isSwipeBack;
    }

    /**
     * 是否调用初始化 Views 方法
     * @return
     */
    public boolean isCallInitViews(){
        if(mBuilder == null) return true;
        return mBuilder.isCallInitViews;
    };



    /**
     * @ClassName: Builder
     * @Description: Builder模式, 用于构建复杂的对象,与Activity配置打交道
     */
    public static class Builder {
        //是否使用ButterKnife依赖注入
        private boolean isUseButterKnife;
        //是否滑动退出
        private boolean isSwipeBack;
        //是否调用初始化信息
        private boolean isCallInitViews;

        public Builder() {
            isUseButterKnife = true;
            isSwipeBack = true;
            isCallInitViews = true;
        }

        /**
         * 设置是否使用ButterKnife
         *
         * @param useButterKnife
         * @return
         */
        public Builder setUseButterKnife(boolean useButterKnife) {
            isUseButterKnife = useButterKnife;
            return this;
        }

        /**
         * 是否滑动退出
         * @param swipeBack
         * @return
         */
        public Builder setSwipeBack(boolean swipeBack) {
            isSwipeBack = swipeBack;
            return this;
        }

        public Builder setCallInitViews(boolean callInitViews) {
            isCallInitViews = callInitViews;
            return this;
        }

        public EFrameConfiguration build() {
            return new EFrameConfiguration(this);
        }
    }


}
