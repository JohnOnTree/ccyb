package live.cnaidai.com.ccyb.st.pojo;

import java.io.Serializable;
import java.util.List;

import live.cnaidai.com.ccyb.base.BasePOJO;

/**
 * description:
 * autour: ChristLu
 * date: 17/6/13
 * package: live.cnaidai.com.ccyb.st.pojo
 */
public class StPOJO extends BasePOJO{


    /**
     * praiseNum : 0
     * sourceType : null
     * upType : null
     * upTime : 2017-06-13T18:14:22
     * demoId : 11
     * titleName : 相当满意的平台体验
     * inputTime : null
     * hasPraise : 0
     * memberId : null
     * memberName : 暖暖
     * headPic : image/member/default_head.png
     * picList : [{"picGroupId":null,"updator":null,"picDatetype":5,"deleted":false,"picInfo":null,"creator":"admin","id":1391,"picDateid":11,"updated":null,"created":"2017-06-13T18:14:14","picOrder":null,"picLocation":"image/demo/demo201706131814146541.jpg","picResvar3":null,"picName":null,"picResint":null,"picResvar1":null,"picResvar2":null},{"picGroupId":null,"updator":null,"picDatetype":5,"deleted":false,"picInfo":null,"creator":"admin","id":1392,"picDateid":11,"updated":null,"created":"2017-06-13T18:14:14","picOrder":null,"picLocation":"image/demo/demo201706131814146018.jpg","picResvar3":null,"picName":null,"picResint":null,"picResvar1":null,"picResvar2":null},{"picGroupId":null,"updator":null,"picDatetype":5,"deleted":false,"picInfo":null,"creator":"admin","id":1393,"picDateid":11,"updated":null,"created":"2017-06-13T18:14:14","picOrder":null,"picLocation":"image/demo/demo20170613181414337.jpg","picResvar3":null,"picName":null,"picResint":null,"picResvar1":null,"picResvar2":null},{"picGroupId":null,"updator":null,"picDatetype":5,"deleted":false,"picInfo":null,"creator":"admin","id":1394,"picDateid":11,"updated":null,"created":"2017-06-13T18:14:14","picOrder":null,"picLocation":"image/demo/demo201706131814144706.jpg","picResvar3":null,"picName":null,"picResint":null,"picResvar1":null,"picResvar2":null}]
     */

    private List<DataEntity> data;

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity implements Serializable{
        private int praiseNum;
        private Object sourceType;
        private Object upType;
        private String upTime;
        private int demoId;
        private String titleName;
        private Object inputTime;
        private int hasPraise;
        private Object memberId;
        private String memberName;
        private String headPic;
        /**
         * picGroupId : null
         * updator : null
         * picDatetype : 5
         * deleted : false
         * picInfo : null
         * creator : admin
         * id : 1391
         * picDateid : 11
         * updated : null
         * created : 2017-06-13T18:14:14
         * picOrder : null
         * picLocation : image/demo/demo201706131814146541.jpg
         * picResvar3 : null
         * picName : null
         * picResint : null
         * picResvar1 : null
         * picResvar2 : null
         */

        private List<PicListEntity> picList;

        public int getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(int praiseNum) {
            this.praiseNum = praiseNum;
        }

        public Object getSourceType() {
            return sourceType;
        }

        public void setSourceType(Object sourceType) {
            this.sourceType = sourceType;
        }

        public Object getUpType() {
            return upType;
        }

        public void setUpType(Object upType) {
            this.upType = upType;
        }

        public String getUpTime() {
            return upTime;
        }

        public void setUpTime(String upTime) {
            this.upTime = upTime;
        }

        public int getDemoId() {
            return demoId;
        }

        public void setDemoId(int demoId) {
            this.demoId = demoId;
        }

        public String getTitleName() {
            return titleName;
        }

        public void setTitleName(String titleName) {
            this.titleName = titleName;
        }

        public Object getInputTime() {
            return inputTime;
        }

        public void setInputTime(Object inputTime) {
            this.inputTime = inputTime;
        }

        public int getHasPraise() {
            return hasPraise;
        }

        public void setHasPraise(int hasPraise) {
            this.hasPraise = hasPraise;
        }

        public Object getMemberId() {
            return memberId;
        }

        public void setMemberId(Object memberId) {
            this.memberId = memberId;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public List<PicListEntity> getPicList() {
            return picList;
        }

        public void setPicList(List<PicListEntity> picList) {
            this.picList = picList;
        }


        public boolean isPraise(){
            return 1 == hasPraise ;//0 未点赞  1 已点赞
        }

        public static class PicListEntity implements Serializable{
            private Object picGroupId;
            private Object updator;
            private int picDatetype;
            private boolean deleted;
            private Object picInfo;
            private String creator;
            private int id;
            private int picDateid;
            private Object updated;
            private String created;
            private Object picOrder;
            private String picLocation;
            private Object picResvar3;
            private Object picName;
            private Object picResint;
            private Object picResvar1;
            private Object picResvar2;

            public Object getPicGroupId() {
                return picGroupId;
            }

            public void setPicGroupId(Object picGroupId) {
                this.picGroupId = picGroupId;
            }

            public Object getUpdator() {
                return updator;
            }

            public void setUpdator(Object updator) {
                this.updator = updator;
            }

            public int getPicDatetype() {
                return picDatetype;
            }

            public void setPicDatetype(int picDatetype) {
                this.picDatetype = picDatetype;
            }

            public boolean isDeleted() {
                return deleted;
            }

            public void setDeleted(boolean deleted) {
                this.deleted = deleted;
            }

            public Object getPicInfo() {
                return picInfo;
            }

            public void setPicInfo(Object picInfo) {
                this.picInfo = picInfo;
            }

            public String getCreator() {
                return creator;
            }

            public void setCreator(String creator) {
                this.creator = creator;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPicDateid() {
                return picDateid;
            }

            public void setPicDateid(int picDateid) {
                this.picDateid = picDateid;
            }

            public Object getUpdated() {
                return updated;
            }

            public void setUpdated(Object updated) {
                this.updated = updated;
            }

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }

            public Object getPicOrder() {
                return picOrder;
            }

            public void setPicOrder(Object picOrder) {
                this.picOrder = picOrder;
            }

            public String getPicLocation() {
                return picLocation;
            }

            public void setPicLocation(String picLocation) {
                this.picLocation = picLocation;
            }

            public Object getPicResvar3() {
                return picResvar3;
            }

            public void setPicResvar3(Object picResvar3) {
                this.picResvar3 = picResvar3;
            }

            public Object getPicName() {
                return picName;
            }

            public void setPicName(Object picName) {
                this.picName = picName;
            }

            public Object getPicResint() {
                return picResint;
            }

            public void setPicResint(Object picResint) {
                this.picResint = picResint;
            }

            public Object getPicResvar1() {
                return picResvar1;
            }

            public void setPicResvar1(Object picResvar1) {
                this.picResvar1 = picResvar1;
            }

            public Object getPicResvar2() {
                return picResvar2;
            }

            public void setPicResvar2(Object picResvar2) {
                this.picResvar2 = picResvar2;
            }
        }
    }
}
