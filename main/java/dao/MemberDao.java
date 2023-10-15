package dao;

import vo.Member;

public interface MemberDao {
	public int loginCheck(String id, String pw);
	public int regMember(Member m);
	
}
