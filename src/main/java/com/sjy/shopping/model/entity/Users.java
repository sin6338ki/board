package com.sjy.shopping.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="users")
public class Users extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="userseq")
	private Long id;
	
	@Column(name="userid")
	private String userid;
	
	@Column(name="userpw")
	private String userpw;
	
	@Column(name="nick")
	private String nick;
	
	@ManyToOne
	@JoinColumn(name="level_id")
	private UserAuth userAuth;
	
	@Builder
	public Users(String userid, String userpw) {
		this.userid = userid;
		this.userpw = userpw;
	}
	
	@Builder
	public Users(String userid, String userpw, String nick, UserAuth userAuth) {
		this.userid = userid;
		this.userpw = userpw;
		this.nick = nick;
		this.userAuth = userAuth;
	}
	
}
