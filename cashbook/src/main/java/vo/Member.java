//vo: 데이터 전달
package vo;

public class Member {
	private String memberId;
	private String memberPw;
	private String memberName;
	private int memberAge;
	private String updateDate;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getMemberName() {
        return memberName;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    public int getMemberAge() {
        return memberAge;
    }
    public void setMemberAge(int memberAge) {
        this.memberAge = memberAge;
    }
    @Override
    public String toString() {
        return "Member [memberId=" + memberId + ", memberPw=" + memberPw + ", memberName=" + memberName + ", memberAge="
                + memberAge + ", updateDate=" + updateDate + "]";
    }
	
	
}
