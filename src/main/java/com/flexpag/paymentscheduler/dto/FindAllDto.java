package com.flexpag.paymentscheduler.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flexpag.paymentscheduler.entities.Scheduler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FindAllDto implements Serializable {
	private static final long serialVersionUID = 1L;
		
		private Long id;
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm", timezone="GMT-3")
		private LocalDateTime date;
		
		public FindAllDto(Scheduler scheduler) {
			id = scheduler.getId();
			date = scheduler.getDate();
		}

}
