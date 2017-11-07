package live.cnaidai.com.ccyb.home.pojo;

import live.cnaidai.com.ccyb.base.BasePOJO;

/**
 * description: 是否可邀约
 * autour: ChristLu
 * date: 17/4/12
 * package: live.cnaidai.com.ccyb.home.pojo
 */
public class CanInvitePOJO extends BasePOJO{

    /**
     * status : 2
     */

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * 可邀约
     * @return
     */
    public boolean canInvite(){
        return status == 1 ;
    }
}
