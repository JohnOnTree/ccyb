package live.cnaidai.com.ccyb.user.pojo;

import java.io.Serializable;
import java.util.List;

import live.cnaidai.com.ccyb.base.BasePOJO;

/**
 * Created by ChristLu on 16/11/19.
 */

public class UserInfoPOJO extends BasePOJO {


    /**
     * creditScore : 0
     * birthday :
     * lon :
     * phone :
     * sex : 0
     * score :
     * headPic : image/member/default_head.png
     * type : 1
     * nowCity : 杭州
     * shareCode : 9X555OVY
     * parentShareCode :
     * memberId : 7
     * memberName : m17767166007
     * lat :
     * faceScore : 0
     */

    private DataEntity data;

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity implements Serializable{
        private String creditScore = "0";
        private String birthday = "";
        private String lon = "";
        private String phone = "";
        private int sex;
        private int score;
        private String headPic = "";
        private String nowCity = "";
        private String shareCode = "";
        private String parentShareCode = "";
        private int memberId;
        private String memberName = "";
        private String lat = "";
        private String faceScore = "";
        private String faceScoreStr ="";
        private int type ;// 1：普通用户  2：vip用户
        private String lvName;//等级名称 ：短工
        private String level;//等级值：lv1
        private String signStr;//签名
        private Integer hasPic;//0：无照片 1：有照片
        private List<PicEntity> picList;//个人相册

        public String getSignStr() {
            return signStr;
        }

        public void setSignStr(String signStr) {
            this.signStr = signStr;
        }

        public Integer getHasPic() {
            return hasPic;
        }

        public void setHasPic(Integer hasPic) {
            this.hasPic = hasPic;
        }

        public List<PicEntity> getPicList() {
            return picList;
        }

        public void setPicList(List<PicEntity> picList) {
            this.picList = picList;
        }

        public String getLvName() {
            return lvName;
        }

        public void setLvName(String lvName) {
            this.lvName = lvName;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getFaceScoreStr() {
            return faceScoreStr;
        }

        public void setFaceScoreStr(String faceScoreStr) {
            this.faceScoreStr = faceScoreStr;
        }

        public String getCreditScore() {
            return creditScore;
        }

        public void setCreditScore(String creditScore) {
            this.creditScore = creditScore;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getNowCity() {
            return nowCity;
        }

        public void setNowCity(String nowCity) {
            this.nowCity = nowCity;
        }

        public String getShareCode() {
            return shareCode;
        }

        public void setShareCode(String shareCode) {
            this.shareCode = shareCode;
        }

        public String getParentShareCode() {
            return parentShareCode;
        }

        public void setParentShareCode(String parentShareCode) {
            this.parentShareCode = parentShareCode;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getFaceScore() {
            return faceScore;
        }

        public void setFaceScore(String faceScore) {
            this.faceScore = faceScore;
        }

        public static class PicEntity implements Serializable{

            /**
             * picGroupId :
             * updator :
             * picDatetype : 6
             * deleted : false
             * picInfo :
             * creator :
             * id : 1452
             * picDateid : 80
             * updated :
             * created : 2017-07-28T21:55:05
             * picOrder :
             * picLocation : image/memberPhoto/photo201707282155053130.jpg
             * picResvar3 :
             * picName :
             * picResint :
             * picResvar1 :
             * picResvar2 :
             */

            private String picGroupId;
            private String updator;
            private int picDatetype;
            private boolean deleted;
            private String picInfo;
            private String creator;
            private int id;
            private int picDateid;
            private String updated;
            private String created;
            private String picOrder;
            private String picLocation;
            private String picResvar3;
            private String picName;
            private String picResint;
            private String picResvar1;
            private String picResvar2;

            public String getPicGroupId() {
                return picGroupId;
            }

            public void setPicGroupId(String picGroupId) {
                this.picGroupId = picGroupId;
            }

            public String getUpdator() {
                return updator;
            }

            public void setUpdator(String updator) {
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

            public String getPicInfo() {
                return picInfo;
            }

            public void setPicInfo(String picInfo) {
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

            public String getUpdated() {
                return updated;
            }

            public void setUpdated(String updated) {
                this.updated = updated;
            }

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }

            public String getPicOrder() {
                return picOrder;
            }

            public void setPicOrder(String picOrder) {
                this.picOrder = picOrder;
            }

            public String getPicLocation() {
                return picLocation;
            }

            public void setPicLocation(String picLocation) {
                this.picLocation = picLocation;
            }

            public String getPicResvar3() {
                return picResvar3;
            }

            public void setPicResvar3(String picResvar3) {
                this.picResvar3 = picResvar3;
            }

            public String getPicName() {
                return picName;
            }

            public void setPicName(String picName) {
                this.picName = picName;
            }

            public String getPicResint() {
                return picResint;
            }

            public void setPicResint(String picResint) {
                this.picResint = picResint;
            }

            public String getPicResvar1() {
                return picResvar1;
            }

            public void setPicResvar1(String picResvar1) {
                this.picResvar1 = picResvar1;
            }

            public String getPicResvar2() {
                return picResvar2;
            }

            public void setPicResvar2(String picResvar2) {
                this.picResvar2 = picResvar2;
            }
        }
    }
}
