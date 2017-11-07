package live.cnaidai.com.ccyb.home.V3;

import java.io.Serializable;
import java.util.List;

import live.cnaidai.com.ccyb.base.BasePOJO;

/**
 * description:
 * autour: ChristLu
 * date: 17/6/16
 * package: live.cnaidai.com.ccyb.home.V3
 */
public class HomeDataV3POJO extends BasePOJO{

    private DataEntity data;

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity implements Serializable {
        /**
         * creditScore : null
         * birthday : null
         * sex : 1
         * phone : null
         * lon : null
         * score : null
         * headPic : image/member/head20170110023006.jpg
         * type : null
         * nowCity : null
         * shareCode : null
         * lvName : 短工
         * distance : 1.0 km
         * level : LV1
         * parentShareCode : null
         * faceScoreStr : null
         * memberId : 21
         * memberName : 小芳芳芳
         * lat : null
         * faceScore : null
         * memberLabelList : [{"id":161,"objName":"小芳芳芳","updated":null,"created":"2017-03-02T21:29:46","updator":null,"objId":21,"labelName":"韩剧粉丝","type":1,"deleted":false,"labelId":7,"creator":null},{"id":162,"objName":"小芳芳芳","updated":null,"created":"2017-03-02T21:29:46","updator":null,"objId":21,"labelName":"国产喜剧","type":1,"deleted":false,"labelId":8,"creator":null}]
         */

        private List<MemberListEntity> nearMemberList;
        /**
         * creditScore : null
         * birthday : null
         * sex : null
         * phone : null
         * lon : null
         * score : null
         * headPic : image/member/default_head.png
         * type : null
         * nowCity : null
         * shareCode : null
         * lvName : null
         * distance : null
         * level : null
         * parentShareCode : null
         * faceScoreStr : null
         * memberId : 140
         * memberName : 米米
         * lat : null
         * faceScore : null
         * memberLabelList : null
         */

        private List<MemberListEntity> hotMemberList;
        /**
         * creditScore : null
         * birthday : null
         * sex : 1
         * phone : null
         * lon : null
         * score : null
         * headPic : image/member/default_head.png
         * type : null
         * nowCity : null
         * shareCode : null
         * lvName : 短工
         * distance : 12.0 km
         * level : LV1
         * parentShareCode : null
         * faceScoreStr : null
         * memberId : 140
         * memberName : 米米
         * lat : null
         * faceScore : null
         * memberLabelList : []
         */

        private List<MemberListEntity> newMemberList;

        private List<AdvertInfo> indexAdvertList;//首页广告栏list




        public List<MemberListEntity> getNearMemberList() {
            return nearMemberList;
        }

        public void setNearMemberList(List<MemberListEntity> nearMemberList) {
            this.nearMemberList = nearMemberList;
        }

        public List<MemberListEntity> getHotMemberList() {
            return hotMemberList;
        }

        public void setHotMemberList(List<MemberListEntity> hotMemberList) {
            this.hotMemberList = hotMemberList;
        }

        public List<MemberListEntity> getNewMemberList() {
            return newMemberList;
        }

        public void setNewMemberList(List<MemberListEntity> newMemberList) {
            this.newMemberList = newMemberList;
        }


        public static class MemberListEntity implements Serializable {
            private Object creditScore;
            private Object birthday;
            private int sex;
            private Object phone;
            private Object lon;
            private Object score;
            private String headPic;
            private Object type;
            private String nowCity;
            private Object shareCode;
            private String lvName;
            private String distance;
            private String level;
            private Object parentShareCode;
            private Object faceScoreStr;
            private int memberId;
            private String memberName;
            private Object lat;
            private Object faceScore;
            private List<MemberLabelListEntity> memberLabelList;

            public Object getCreditScore() {
                return creditScore;
            }

            public void setCreditScore(Object creditScore) {
                this.creditScore = creditScore;
            }

            public Object getBirthday() {
                return birthday;
            }

            public void setBirthday(Object birthday) {
                this.birthday = birthday;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public Object getPhone() {
                return phone;
            }

            public void setPhone(Object phone) {
                this.phone = phone;
            }

            public Object getLon() {
                return lon;
            }

            public void setLon(Object lon) {
                this.lon = lon;
            }

            public Object getScore() {
                return score;
            }

            public void setScore(Object score) {
                this.score = score;
            }

            public String getHeadPic() {
                return headPic;
            }

            public void setHeadPic(String headPic) {
                this.headPic = headPic;
            }

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
                this.type = type;
            }

            public String getNowCity() {
                return nowCity;
            }

            public void setNowCity(String nowCity) {
                this.nowCity = nowCity;
            }

            public Object getShareCode() {
                return shareCode;
            }

            public void setShareCode(Object shareCode) {
                this.shareCode = shareCode;
            }

            public String getLvName() {
                return lvName;
            }

            public void setLvName(String lvName) {
                this.lvName = lvName;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public Object getParentShareCode() {
                return parentShareCode;
            }

            public void setParentShareCode(Object parentShareCode) {
                this.parentShareCode = parentShareCode;
            }

            public Object getFaceScoreStr() {
                return faceScoreStr;
            }

            public void setFaceScoreStr(Object faceScoreStr) {
                this.faceScoreStr = faceScoreStr;
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

            public Object getLat() {
                return lat;
            }

            public void setLat(Object lat) {
                this.lat = lat;
            }

            public Object getFaceScore() {
                return faceScore;
            }

            public void setFaceScore(Object faceScore) {
                this.faceScore = faceScore;
            }

            public List<MemberLabelListEntity> getMemberLabelList() {
                return memberLabelList;
            }

            public void setMemberLabelList(List<MemberLabelListEntity> memberLabelList) {
                this.memberLabelList = memberLabelList;
            }


            public static class MemberLabelListEntity implements Serializable{
                private int id;
                private String objName;
                private Object updated;
                private String created;
                private Object updator;
                private int objId;
                private String labelName;
                private int type;
                private boolean deleted;
                private int labelId;
                private Object creator;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getObjName() {
                    return objName;
                }

                public void setObjName(String objName) {
                    this.objName = objName;
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

                public Object getUpdator() {
                    return updator;
                }

                public void setUpdator(Object updator) {
                    this.updator = updator;
                }

                public int getObjId() {
                    return objId;
                }

                public void setObjId(int objId) {
                    this.objId = objId;
                }

                public String getLabelName() {
                    return labelName;
                }

                public void setLabelName(String labelName) {
                    this.labelName = labelName;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public boolean isDeleted() {
                    return deleted;
                }

                public void setDeleted(boolean deleted) {
                    this.deleted = deleted;
                }

                public int getLabelId() {
                    return labelId;
                }

                public void setLabelId(int labelId) {
                    this.labelId = labelId;
                }

                public Object getCreator() {
                    return creator;
                }

                public void setCreator(Object creator) {
                    this.creator = creator;
                }
            }
        }

        public List<AdvertInfo> getIndexAdvertList() {
            return indexAdvertList;
        }

        public void setIndexAdvertList(List<AdvertInfo> indexAdvertList) {
            this.indexAdvertList = indexAdvertList;
        }

        public static class AdvertInfo implements Serializable {
            private String adHtml5;
            private int status;
            private String updator;
            private String descNote;
            private String type;
            private boolean deleted;
            private String creator;
            private int id;
            private String created;
            private String updated;
            private String name;
            private int objId;
            private int orders;
            private String adPic;

            public String getAdHtml5() {
                return adHtml5;
            }

            public void setAdHtml5(String adHtml5) {
                this.adHtml5 = adHtml5;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getUpdator() {
                return updator;
            }

            public void setUpdator(String updator) {
                this.updator = updator;
            }

            public String getDescNote() {
                return descNote;
            }

            public void setDescNote(String descNote) {
                this.descNote = descNote;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public boolean isDeleted() {
                return deleted;
            }

            public void setDeleted(boolean deleted) {
                this.deleted = deleted;
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

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }

            public String getUpdated() {
                return updated;
            }

            public void setUpdated(String updated) {
                this.updated = updated;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getObjId() {
                return objId;
            }

            public void setObjId(int objId) {
                this.objId = objId;
            }

            public int getOrders() {
                return orders;
            }

            public void setOrders(int orders) {
                this.orders = orders;
            }

            public String getAdPic() {
                return adPic;
            }

            public void setAdPic(String adPic) {
                this.adPic = adPic;
            }
        }

    }
}
