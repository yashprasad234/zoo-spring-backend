package com.example.web.dto;

public class AnimalHistoryDTO {
	public static class AnimalHistoryInputs {
		private Integer fromZooId;
		
		private Integer toZooId;

		public Integer getFromZooId() {
			return fromZooId;
		}

		public void setFromZooId(Integer fromZooId) {
			this.fromZooId = fromZooId;
		}

		public Integer getToZooId() {
			return toZooId;
		}

		public void setToZooId(Integer toZooId) {
			this.toZooId = toZooId;
		}

		public AnimalHistoryInputs(Integer fromZooId, Integer toZooId) {
			super();
			this.fromZooId = fromZooId;
			this.toZooId = toZooId;
		}

		public AnimalHistoryInputs() {
		}
	}
}
