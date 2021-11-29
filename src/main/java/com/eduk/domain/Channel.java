package com.eduk.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Channel {

	@Id
	private Long channelId;
	
	private String channelType;
	
	private int state;
	
	@ManyToOne
	@JoinColumn(name="classRoomId")
	private ClassRoom classRoom;
	
	@OneToOne
	@JoinColumn(name="authId")
	private Authentication authentication;
	
}
